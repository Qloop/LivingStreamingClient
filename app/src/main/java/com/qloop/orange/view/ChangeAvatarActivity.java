package com.qloop.orange.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qloop.orange.R;
import com.qloop.orange.bean.UserInfo;
import com.qloop.orange.config.AppConfig;
import com.qloop.orange.netInterface.AvatarNetInterface;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.utils.UserCache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
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
 * Created by Qloop on 2017/5/7.
 */

public class ChangeAvatarActivity extends Activity {


    @BindView(R.id.tv_take_phone)
    TextView tvTakePhone;
    @BindView(R.id.tv_album)
    TextView tvAlbum;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    private static int CAMERA_REQUEST_CODE = 1;
    private static int GALLERY_REQUEST_CODE = 2;
    private static int CROP_REQUEST_CODE = 3;
    private SweetAlertDialog pDialog;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avator);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_take_phone, R.id.tv_album, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_take_phone:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
                break;
            case R.id.tv_album:
                Intent intentAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                intentAlbum.setType("image/*");
                startActivityForResult(intentAlbum, GALLERY_REQUEST_CODE);
                break;
            case R.id.tv_cancel:
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                break;
        }
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
        File img = new File(tmpDir.getAbsolutePath() + "avater.png");
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
                        showPromptDialog("网络错误");
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
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        showPromptDialog("网络错误");
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        Log.i("AVATAR", userInfo.getAvatar() + "");

                        if (userInfo.getResult().equals("failed")) {
                            ToastUtils.showToastLong(ChangeAvatarActivity.this, "更新失败 请重试");
                        } else {
                            //修改成功后 更新本地缓存
                            UserCache.setAvator(ChangeAvatarActivity.this, userInfo.getAvatar() + "");
                            ToastUtils.showToastLong(ChangeAvatarActivity.this, "更新成功^.^");
                            Log.i("AVATAR", "cache is : " + UserCache.getAvator(ChangeAvatarActivity.this));
                        }
                    }
                });
    }

    /**
     * 是否有SD卡
     */
    private boolean hasSD() {
        //如果有SD卡 则下载到SD卡中
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;

        } else {
            //如果没有SD卡
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    public void showPromptDialog(String content) {
        pDialog = new SweetAlertDialog(this)
                .setTitleText("-.-")
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
