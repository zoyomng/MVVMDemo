package com.zoyo.data.camera2;

import android.Manifest;
import android.hardware.camera2.CaptureRequest;
import android.os.Build;
import android.os.Bundle;
import android.view.TextureView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.zoyo.data.R;

/**
 * @Description: Camera2的使用
 * @CreateDate: 2019/10/11 11:05
 */
public class Camera2Activity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private TextureView mTextureView;
    private CaptureRequest.Builder mPreviewRequestBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mTextureView = (TextureView) findViewById(R.id.texture);
//        Button btPicture = (Button) findViewById(R.id.picture);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission(){
        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
//new Confirm
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
    }

}
