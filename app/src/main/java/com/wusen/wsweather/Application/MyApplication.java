package com.wusen.wsweather.Application;

import android.app.Application;
import android.content.SharedPreferences;

import com.baidu.apistore.sdk.ApiStoreSDK;
import com.lidroid.xutils.DbUtils;
import com.wusen.wsweather.Constant.Constant;

/**
 * Created by 15059 on 2016/3/28.
 */
public class MyApplication extends Application {
    public SharedPreferences sp;
    public DbUtils dbForecast;

    @Override
    public void onCreate() {
        ApiStoreSDK.init(this, "520997adaf041b7ef5aedf0e382012bb");
        sp = getSharedPreferences(Constant.SP_NAME,MODE_PRIVATE);
        dbForecast = DbUtils.create(getApplicationContext(),Constant.DB_FORECAST);
        super.onCreate();
    }
}
