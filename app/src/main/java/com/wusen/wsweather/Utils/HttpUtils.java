package com.wusen.wsweather.Utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.lidroid.xutils.exception.DbException;
import com.wusen.wsweather.Application.MyApplication;
import com.wusen.wsweather.Constant.Constant;
import com.wusen.wsweather.Model.Model.ForecastInfo;
import com.wusen.wsweather.Model.Model.TodayInfo;

import java.util.List;

/**
 * Created by 15059 on 2016/3/28.
 */
public class HttpUtils {


    public static void getJson(String city, String address, ApiCallBack callBack) {
        String URL = address;
        Parameters param = new Parameters();
        param.put("cityname",city);
        ApiStoreSDK.execute(URL, ApiStoreSDK.GET, param, callBack);
    }

    public static void getDateFromServer(final Context context, final String city,final MyApplication app) {
        HttpUtils.getJson(city, Constant.Url,new ApiCallBack() {
            @Override
            public void onError(int i, String s, Exception e) {
                super.onError(i, s, e);
                Toast.makeText(context ,"对不起查询失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                saveInfo(s,app,city);
            }
        });
    }

    public static void saveTodayInfo(String s, MyApplication app){
        String response = ParseUtils.decodeUnicode(s);
        TodayInfo todayInfo = ParseUtils.parseToday(response);
        SharedPreferences.Editor editor = app.sp.edit();
        editor.putString(TodayInfo.DATE,todayInfo.getDate());
        editor.putString(TodayInfo.CUR_TEMP,todayInfo.getCurTemp());
        editor.putString(TodayInfo.FENG_LI,todayInfo.getFengli());
        editor.putString(TodayInfo.HIGH_TEMP,todayInfo.getHightemp());
        editor.putString(TodayInfo.LOW_TEMP,todayInfo.getLowtemp());
        editor.putString(TodayInfo.FENG_XIANG,todayInfo.getFengxiang());
        editor.putString(TodayInfo.TYPE,todayInfo.getType());
        editor.commit();
    }

    public static void saveForecastInfo(String s,MyApplication app){
        String response = ParseUtils.decodeUnicode(s);
        List<ForecastInfo> forecastInfos = ParseUtils.parseForecastInfo(s);

        try {
            app.dbForecast.deleteAll(ForecastInfo.class);
            app.dbForecast.saveAll(forecastInfos);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void saveInfo(String s,MyApplication app,String city){
        saveTodayInfo(s,app);
        saveForecastInfo(s,app);
        SharedPreferences.Editor editor = app.sp.edit();
        editor.putBoolean(Constant.IS_FIRST,false);
        editor.putString(Constant.TIME, FormatUtils.formatTime());
        editor.putString(Constant.CITY_NAME,city);
        editor.commit();
    }
}
