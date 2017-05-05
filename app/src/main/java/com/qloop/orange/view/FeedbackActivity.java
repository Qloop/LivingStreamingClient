package com.qloop.orange.view;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.qloop.orange.R;
import com.qloop.orange.config.AppConfig;
import com.qloop.orange.netInterface.FeedbackNetInterface;
import com.qloop.orange.utils.UserCache;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Qloop on 2017/5/4.
 */

public class FeedbackActivity extends BaseActivity {
    private static final String NET_ERROR = "提交失败，请检查网络后重试";
    private static final String SUBMIT_SUCCESS = "提交成功 \n 感谢您的反馈";
    private static final String INPUT_EMPTY = "反馈不能为空哦";
    private static final String PERMISSON_DENIED = "请先登录";
    @BindView(R.id.et_feedback_content)
    EditText mFeedbackContent;
    @BindView(R.id.btn_feedback)
    Button btnFeedback;
    private SweetAlertDialog pDialog;

    @Override
    protected void initViews() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_feedback;
    }

    @OnClick(R.id.btn_feedback)
    public void onViewClicked() {
        if (checkInput() && checkPermission()) {
            submit();
        }
        if (!checkInput()) {
            showPromptDialog(INPUT_EMPTY);
        }
        if (!checkPermission()) {
            showPromptDialog(PERMISSON_DENIED);
        }
    }

    private void submit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        FeedbackNetInterface feedbackNetInterface = retrofit.create(FeedbackNetInterface.class);
        feedbackNetInterface.submitFeedback(getContent(), getMail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        showFailedDialog();
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        showSuccessDialog();
                    }
                });
    }

    private void showFailedDialog() {
        pDialog = new SweetAlertDialog(this)
                .setContentText(NET_ERROR)
                .setConfirmText("知道啦-、-");

        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pDialog.dismiss();
            }
        });
        pDialog.show();
    }

    private void showSuccessDialog() {
        pDialog = new SweetAlertDialog(this)
                .setContentText(SUBMIT_SUCCESS)
                .setConfirmText("确定");

        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pDialog.dismiss();
                finish();
            }
        });
        pDialog.show();
    }

    private void showPromptDialog(String content) {
        pDialog = new SweetAlertDialog(this)
                .setContentText(content)
                .setConfirmText("确定");

        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pDialog.dismiss();
            }
        });
        pDialog.show();
    }

    private boolean checkInput() {
        return !TextUtils.isEmpty(mFeedbackContent.getText().toString());
    }

    private boolean checkPermission() {
        String userName = UserCache.getUserName(this);
        return !TextUtils.isEmpty(userName);
    }

    private String getContent() {
        return mFeedbackContent.getText().toString();
    }

    private String getMail() {
        return UserCache.getEmail(this);
    }
}
