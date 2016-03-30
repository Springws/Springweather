package com.wusen.wsweather.Utils;


import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;

/**
 * Created by 15059 on 2016/3/28.
 */
public class HttpUtils {

//    public interface HttpCallBack{
//        public void onFinish(String response);
//    }

   // public final static String URL = "http://apis.baidu.com/apistore/weatherservice/cityinfo";
    public static void getJson(String city,String address,ApiCallBack callBack){
        String URL = address;
        Parameters param = new Parameters();
        ApiStoreSDK.execute(URL,ApiStoreSDK.GET,param,callBack);
//        ApiStoreSDK.execute(URL,ApiStoreSDK.GET,param,new ApiCallBack(){
//            @Override        param.put("cityname",city);

//            public void onSuccess(int i, String s) {
//                super.onSuccess(i, s);
//                if(httpCallBack!= null) {
//                    Log.i("sdkdemo", s);
//                    httpCallBack.onFinish(s);
//                }
//            }
//
//            @Override
//            public void onError(int i, String s, Exception e) {
//                super.onError(i, s, e);
//                Log.i("HttpUtils","查询失败");
//            }
//        });

    }
}
