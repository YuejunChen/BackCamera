package com.frontcamera.zhousong.frontcamera;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Mr.Chen on 2017/10/10.
 */
public class Okhttp {
    public static void upLoadImage(Bitmap bos, Callback callback){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //参数1转换类型，参数2压缩质量，参数3字节流资源
        bos.compress(Bitmap.CompressFormat.JPEG, 100, out);
        OkHttpClient client = new OkHttpClient();
        MultipartBody body = new MultipartBody.Builder("AaB03x")
                .setType(MultipartBody.FORM)
                .addFormDataPart("files", null, new MultipartBody.Builder("BbC04y")
                .addPart(Headers.of("Content-Disposition", "form-data; filename=\"img.png\""),
                RequestBody.create(MediaType.parse("image/png"), out.toByteArray()))
                .build())
                .build();
        Request request = new Request.Builder()
                .url("")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
