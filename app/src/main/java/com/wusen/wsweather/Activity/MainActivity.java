package com.wusen.wsweather.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.wusen.wsweather.Adapter.FragmentAdapter;
import com.wusen.wsweather.Application.MyApplication;
import com.wusen.wsweather.Constant.Constant;
import com.wusen.wsweather.Fragment.ForeCastFragment;
import com.wusen.wsweather.Fragment.TodayFragment;
import com.wusen.wsweather.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements TodayFragment.updateListener {



    public static final int UPDATE_FROM_SERVER = 100;
    public static final int UPDATE_FROM_SP = 200;

    private MyApplication app;
    private String city;

    private  List<Fragment> fragmentList;
    private TodayFragment todayFragment;
    private ForeCastFragment foreCastFragment;
    private boolean isFromCityPicker = false;
    private ViewPager viewPager;

    public static void actionStart(String city, Context context,boolean isFromCityPicker) {
        Intent intent = new Intent();
        intent.putExtra("city", city);
        intent.putExtra(Constant.IS_FROM_CITYPICKER,isFromCityPicker);
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        app = (MyApplication) getApplication();
        Intent intent = getIntent();
        boolean isFromCityPicker = intent.getBooleanExtra(Constant.IS_FROM_CITYPICKER,false);
        if(isFromCityPicker){
            city = intent.getStringExtra("city");
        }else {
            city = app.sp.getString(Constant.CITY_NAME,"杭州");
        }
        initFragment(city);

    }

    public void initFragment(String city){
        viewPager = (ViewPager) findViewById(R.id.main_pager);
        fragmentList = new ArrayList<>();
        todayFragment = (TodayFragment) TodayFragment.getInstance(city);
        foreCastFragment = (ForeCastFragment) ForeCastFragment.getInstance(city);
        fragmentList.add(todayFragment);
        fragmentList.add(foreCastFragment);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),this,fragmentList);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void update() {
            foreCastFragment.updateFromDB();
    }
}
