package com.frontcamera.zhousong.frontcamera;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    Context context = MainActivity.this;
    SurfaceView surfaceView;
    CameraSurfaceHolder mCameraSurfaceHolder = new CameraSurfaceHolder();
    private final Timer timer = new Timer();
    private int count=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        timer.schedule(task,0,1000);
    }

    public void initView()
    {
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        mCameraSurfaceHolder.setCameraSurfaceHolder(context,surfaceView);
    }

    TimerTask  task= new TimerTask() {
        @Override
        public void run() {
            if(count==10){
                finish();
            }
            count++;
        }
    };



}
