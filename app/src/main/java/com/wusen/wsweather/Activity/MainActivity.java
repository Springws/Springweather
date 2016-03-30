package com.wusen.wsweather.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.wusen.wsweather.Constant.Constant;
import com.wusen.wsweather.Model.Model.IndexInfo;
import com.wusen.wsweather.Model.Model.ResultInfo;
import com.wusen.wsweather.Model.Model.RetDataInfo;
import com.wusen.wsweather.Model.Model.TodayInfo;
import com.wusen.wsweather.R;
import com.wusen.wsweather.Utils.HttpUtils;
import com.wusen.wsweather.Utils.ParseUtils;

import java.util.List;


public class MainActivity extends AppCompatActivity{

    public static void actionStart(String city,Context context)
    {
        Intent intent = new Intent();
        intent.putExtra("city",city);
        intent.setClass(context,MainActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        Toast.makeText(this,city,Toast.LENGTH_SHORT).show();
        HttpUtils.getJson(city, Constant.Url, new ApiCallBack() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                String response = ParseUtils.decodeUnicode(s);
                //Log.i("main",response);
                ResultInfo info = ParseUtils.parseResult(response);
                Log.i("main",info.getErrMsg());
                RetDataInfo dateInfo = ParseUtils.parseRetdata(response);
                Log.i("main",dateInfo.getCity());
                TodayInfo todayInfo = ParseUtils.parseToday(response);
                Log.i("main",todayInfo.getFengli());
                List<IndexInfo> indexInfos = ParseUtils.parseIndex(response);
                Log.i("main",indexInfos.get(1).getName());
            }

            @Override
            public void onError(int i, String s, Exception e) {
                Log.i("main","失败");
            }
        });


    }


}
