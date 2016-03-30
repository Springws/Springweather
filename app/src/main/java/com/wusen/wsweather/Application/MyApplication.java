package com.wusen.wsweather.Application;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;

/**
 * Created by 15059 on 2016/3/28.
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        ApiStoreSDK.init(this, "520997adaf041b7ef5aedf0e382012bb");
        super.onCreate();
    }
}
