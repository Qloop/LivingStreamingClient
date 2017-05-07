package com.qloop.orange.view;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qloop.orange.R;
import com.qloop.orange.config.AppConfig;
import com.qloop.orange.netInterface.AvatarNetInterface;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.utils.UserCache;
import com.qloop.orange.view.Iview.IProfileView;
import com.qloop.orange.wight.BasePopupWindow;
import com.qloop.orange.wight.CircleImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Qloop on 2017/5/6.
 */

public class ProfileActivity extends BaseActivity implements IProfileView {


    @BindView(R.id.tv_avator)
    TextView tvAvator;
    @BindView(R.id.civ_avator)
    CircleImageView civAvator;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rl_change_avator)
    RelativeLayout rlChangeAvator;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_change_nickname)
    RelativeLayout rlChangeNickname;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_user_mail)
    TextView tvUserMail;
    @BindView(R.id.ll_profile_root)
    LinearLayout mRootView;

    private static int CAMERA_REQUEST_CODE = 1;
    private static int GALLERY_REQUEST_CODE = 2;
    private static int CROP_REQUEST_CODE = 3;
    private File img;
    private BasePopupWindow popupWindow;

    @Override
    protected void initViews() {
        tvName.setText(UserCache.getUserName(this));
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_profile;
    }


    @OnClick({R.id.rl_change_avator, R.id.rl_change_nickname})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_change_avator:
                showPopWindow();
                break;
            case R.id.rl_change_nickname:
                break;
        }
    }

    @Override
    public void showPopWindow() {
        popupWindow = new BasePopupWindow(this);
        popupWindow.setWidth(getScreenSize().x * 2 / 3);
        popupWindow.setHeight(getScreenSize().y / 3);
        View contentView = LayoutInflater.from(this)
                .inflate(R.layout.content_popwindow_change_avator, null);
        popupWindow.setContentView(contentView);
//        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(mRootView, Gravity.CENTER, 0, 0);

        //拍照
        contentView.findViewById(R.id.tv_take_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });

        //从相册选
        contentView.findViewById(R.id.tv_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });

        contentView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    private Uri saveBitmap(Bitmap bm) {
        File tmpDir;
        if (hasSD()) {
            tmpDir = new File(Environment.getExternalStorageDirectory() + "/com.upc.avatar");
        } else {
            tmpDir = new File(Environment.getDataDirectory() + "/com.upc.avatar");
        }

        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        img = new File(tmpDir.getAbsolutePath() + "avater.png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Uri convertUri(Uri uri) {
        InputStream is = null;
        try {
            is = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return saveBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void startImageZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (data == null) {
                return;
            } else {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap bm = extras.getParcelable("data");
                    Uri uri = saveBitmap(bm);
                    startImageZoom(uri);
                }
            }
        } else if (requestCode == GALLERY_REQUEST_CODE) {
            if (data == null) {
                return;
            }
            Uri uri;
            uri = data.getData();
            Uri fileUri = convertUri(uri);
            startImageZoom(fileUri);
        } else if (requestCode == CROP_REQUEST_CODE) {
            if (data == null) {
                return;
            }
            Bundle extras = data.getExtras();
            if (extras == null) {
                return;
            }
            Bitmap bm = extras.getParcelable("data");
            finish();
            sendImage(bm);
        }
    }

    private void sendImage(Bitmap img) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 60, stream);
        byte[] bytes = stream.toByteArray();
        String avatar = Base64.encodeToString(bytes, Base64.DEFAULT);
        System.out.println("base64 is " + avatar);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        AvatarNetInterface avatarNetInterface = retrofit.create(AvatarNetInterface.class);
        avatarNetInterface.sendNewAvatar(UserCache.getEmail(this), avatar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        refreshAvatar();
                    }
                });


    }

    private void refreshAvatar() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        AvatarNetInterface avatarNetInterface = retrofit.create(AvatarNetInterface.class);
        avatarNetInterface.getAvatar(UserCache.getEmail(this))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        showPromptDialog("网络错误");
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        popupWindow.dismiss();
                        if (responseBodyResponse.body().toString().equals("failed")) {
                            showPromptDialog("更新失败,请重试");
                        }
                        ToastUtils.showToastLong(ProfileActivity.this, responseBodyResponse.body().toString());
                        //修改成功后 更新本地缓存
                        UserCache.setAvator(ProfileActivity.this, responseBodyResponse.body().toString());
                        Glide.with(ProfileActivity.this)
                                .load(responseBodyResponse.body().toString())
                                .centerCrop()
                                .placeholder(R.mipmap.ic_avatar_default)
                                .into(civAvator);
                        showPromptDialog("修改成功^.^");
                    }
                });
    }

    /**
     * 是否有SD卡
     */
    private boolean hasSD() {
        //如果有SD卡 则下载到SD卡中
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private Point getScreenSize() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }

    @Override
    public void error() {

    }

    @Override
    public void setAvator() {

    }

    @Override
    public void takePhoto() {

    }

    @Override
    public void getPicFromPhone() {

    }

    @Override
    public void showPopAnimation() {

    }

    @Override
    public void hidePopAnimation() {

    }
}
