package com.frontcamera.zhousong.frontcamera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhousong on 2016/9/28.
 * 单独的任务类。继承AsyncTask，来处理从相机实时获取的耗时操作
 */
public class FaceTask extends AsyncTask{
    private byte[] mData;
    Camera mCamera;
    private static final String TAG = "CameraTag";
    //构造函数
    FaceTask(byte[] data , Camera camera)
    {
        this.mData = data;
        this.mCamera = camera;

    }
    @Override
    protected Object doInBackground(Object[] params) {
        Camera.Parameters parameters = mCamera.getParameters();
        int imageFormat = parameters.getPreviewFormat();
        int w = parameters.getPreviewSize().width;
        int h = parameters.getPreviewSize().height;

        Rect rect = new Rect(0, 0, w, h);
        YuvImage yuvImg = new YuvImage(mData, imageFormat, w, h, null);
        try {
            ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
            yuvImg.compressToJpeg(rect, 100, outputstream);
            Bitmap rawbitmap = BitmapFactory.decodeByteArray(outputstream.toByteArray(), 0, outputstream.size());
            Okhttp.upLoadImage(rawbitmap,new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                }
            });
        }
        catch (Exception e)
        {
            Log.e(TAG, "onPreviewFrame: 获取相机实时数据失败" + e.getLocalizedMessage());
        }
        return null;
    }
}
