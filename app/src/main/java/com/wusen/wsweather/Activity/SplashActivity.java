package com.wusen.wsweather.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.wusen.wsweather.Application.MyApplication;
import com.wusen.wsweather.Constant.Constant;
import com.wusen.wsweather.R;

public class SplashActivity extends AppCompatActivity {
    public final static int START_ACTIVITY = 1;
    private MyApplication app;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START_ACTIVITY:
                    boolean isFirst = app.sp.getBoolean(Constant.IS_FIRST,true);
                    if (isFirst) {
                        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra(Constant.IS_FROM_CITYPICKER,false);
                        startActivity(intent);
                        finish();
                    }

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        app = (MyApplication) getApplication();
        handler.sendEmptyMessageDelayed(START_ACTIVITY, 2500);

    }


}
