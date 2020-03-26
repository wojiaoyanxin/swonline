package com.sunlands.intl.yingshi.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.ui.widget.MySurfaceView;
import com.sunlands.intl.yingshi.util.CameraUtils;
import com.sunlands.intl.yingshi.util.FileUtils;

/**
 * 人脸识别
 */

public class FaceRecognitionActivity extends CommonActivity<Object> {

    private FrameLayout camera_preview;
    private MySurfaceView cameraSurfaceView;
    private Camera camera;
    private Point mScreenResolution;//屏幕分辨率
    private Point previewSizeOnScreen;//相机预览尺寸
    private Point pictureSizeOnScreen;//图片尺寸
    private Bitmap bitmapCamera = null;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        camera_preview = (FrameLayout) findViewById(R.id.camera_preview);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (camera != null) {
                    // 控制摄像头自动对焦后才拍摄
                    //关闭声音
                    CameraUtils.setCameraSound(true, FaceRecognitionActivity.this);
                    camera.takePicture(null, null, jpeg);
                }
            }
        });
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);

        initCamera();
        init();
    }

    private void initCamera() {
        // 此处默认打开后置摄像头
        // 通过传入参数可以打开前置摄像头
        //判断系统版本大于23，即24（7.0）和以上版本提示打开权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            String[] permissions = {Manifest.permission.CAMERA};
            int check = ContextCompat.checkSelfPermission(FaceRecognitionActivity.this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check == PackageManager.PERMISSION_GRANTED) {
                //调用相机
                camera = CameraUtils.openFrontFacingCameraGingerbread();
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            }
        } else {
            camera = CameraUtils.openFrontFacingCameraGingerbread();
        }
        if (camera == null) {
            ToastUtils.showShort( "摄像头被占用,摄像头权限没打开！");
            return;
        }
        setCameraParameters(camera, camera.getParameters());
    }

    private void init() {
        cameraSurfaceView = new MySurfaceView(FaceRecognitionActivity.this, camera);
        //设置界面展示大小
        Point point = CameraUtils.calculateViewSize(previewSizeOnScreen, mScreenResolution);
        System.out.println(point.x + "," + point.y);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(point.x, point.y);
        layoutParams.gravity = Gravity.CENTER;
        cameraSurfaceView.setLayoutParams(layoutParams);
        camera_preview.addView(cameraSurfaceView);
    }

    private void setCameraParameters(Camera camera, Camera.Parameters parameters) {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point theScreenResolution = new Point();
        display.getSize(theScreenResolution);//得到屏幕的尺寸，单位是像素
        mScreenResolution = theScreenResolution;
        previewSizeOnScreen = CameraUtils.findBestPreviewSizeValue(parameters, theScreenResolution);//通过相机尺寸、屏幕尺寸来得到最好的展示尺寸，此尺寸为相机的
        parameters.setPreviewSize(previewSizeOnScreen.x, previewSizeOnScreen.y);
        pictureSizeOnScreen = CameraUtils.findBestPictureSizeValue(parameters, theScreenResolution);//通过相机尺寸、屏幕尺寸来得到最好的展示尺寸，此尺寸为相机的
        parameters.setPictureSize(pictureSizeOnScreen.x, pictureSizeOnScreen.y);
        boolean isScreenPortrait = mScreenResolution.x < mScreenResolution.y;
        boolean isPreviewSizePortrait = previewSizeOnScreen.x < previewSizeOnScreen.y;
        if (isScreenPortrait != isPreviewSizePortrait) {//相机与屏幕一个方向，则使用相机尺寸
            previewSizeOnScreen = new Point(previewSizeOnScreen.y, previewSizeOnScreen.x);//否则翻个
        }
        // 设置照片的格式
        parameters.setPictureFormat(ImageFormat.JPEG);
        CameraUtils.setFocus(parameters, true, false, true);//设置相机对焦模式
        CameraUtils.setBarcodeSceneMode(parameters, Camera.Parameters.SCENE_MODE_BARCODE);//设置相机场景模式
        CameraUtils.setBestPreviewFPS(parameters);//设置相机帧数
        camera.setParameters(parameters);
        // 系统相机默认是横屏的，我们要旋转90°
        camera.setDisplayOrientation(90);
    }

    //创建jpeg图片回调数据对象
    Camera.PictureCallback jpeg = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            bitmapCamera = BitmapFactory.decodeByteArray(data, 0, data.length);
            String s = FileUtils.bitmapToBase64(bitmapCamera);
            Log.e("TAG",s);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //回收数据
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        if (bitmapCamera != null) {
            bitmapCamera.recycle();//回收bitmap空间
            bitmapCamera = null;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_face_recognition;
    }

    @Override
    public String getTitleText() {
        return null;
    }
}
