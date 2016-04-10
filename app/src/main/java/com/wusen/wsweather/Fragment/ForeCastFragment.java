package com.wusen.wsweather.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.lidroid.xutils.exception.DbException;
import com.wusen.wsweather.Activity.MainActivity;
import com.wusen.wsweather.Adapter.ForecastAdapter;
import com.wusen.wsweather.Application.MyApplication;
import com.wusen.wsweather.Constant.Constant;
import com.wusen.wsweather.Model.Model.ForecastInfo;
import com.wusen.wsweather.R;
import com.wusen.wsweather.Utils.HttpUtils;
import com.wusen.wsweather.Utils.ParseUtils;

import java.util.List;


/**
 * Created by 15059 on 2016/4/5.
 */
public class ForeCastFragment extends Fragment {
    private MyApplication app;
    private ListView forecastLv;
    private ForecastAdapter adapter;
    private String city;
    private MainActivity mainActivity;
    private List<ForecastInfo> forecastInfos;

    public static Fragment getInstance(String city) {
        ForeCastFragment fragment = new ForeCastFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.CITY_NAME, city);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MyApplication) getActivity().getApplication();
        final Bundle bundle = this.getArguments();
        city = bundle.getString(Constant.CITY_NAME);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, null);
        forecastLv = (ListView) view.findViewById(R.id.forecast_lv);
        try {
            forecastInfos = app.dbForecast.findAll(ForecastInfo.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (forecastInfos != null) {
            adapter = new ForecastAdapter(getActivity(), forecastInfos);
            forecastLv.setAdapter(adapter);
            return view;
        } else {
            getFromServer(city);
            return view;
        }

    }

    public void getFromServer(String city) {
        HttpUtils.getJson(city, Constant.Url, new ApiCallBack() {
            @Override
            public void onError(int i, String s, Exception e) {
                super.onError(i, s, e);
                Toast.makeText(getActivity(), "对不起查询失败,请检查网络连接", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                String response = ParseUtils.decodeUnicode(s);
                List<ForecastInfo> forecastInfos = ParseUtils.parseForecastInfo(response);
                adapter = new ForecastAdapter(getActivity(), forecastInfos);
                forecastLv.setAdapter(adapter);
            }
        });
    }

    public void updateFromDB() {
        try {
            forecastInfos = app.dbForecast.findAll(ForecastInfo.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        adapter = new ForecastAdapter(getActivity(), forecastInfos);
        forecastLv.setAdapter(adapter);
    }
}






