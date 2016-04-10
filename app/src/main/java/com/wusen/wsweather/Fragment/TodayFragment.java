package com.wusen.wsweather.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.melnykov.fab.FloatingActionButton;
import com.wusen.wsweather.Activity.MainActivity;
import com.wusen.wsweather.Application.MyApplication;
import com.wusen.wsweather.Constant.Constant;
import com.wusen.wsweather.Model.Model.IndexInfo;
import com.wusen.wsweather.Model.Model.TodayInfo;
import com.wusen.wsweather.R;
import com.wusen.wsweather.Utils.FormatUtils;
import com.wusen.wsweather.Utils.HttpUtils;
import com.wusen.wsweather.Utils.ParseUtils;

import citypicker.CityPickerActivity;


/**
 * Created by 15059 on 2016/4/5.
 */
public class TodayFragment extends Fragment implements OnClickListener{

    private String city;
    private String date;
    private String week;
    private String curTemp;
    private String fengxiang;
    private String fengli;
    private String hightemp;
    private String lowtemp;
    private String type;
    private String updateTime;
    private String skinDetail;
    private MyApplication app;

    AlertDialog.Builder builder;
    private AlertDialog dialog;
    private View view;
    private ImageView locationIv;
    private TextView cityNameTv;
    private TextView updateTimeTv;
    private TextView dateTv;
    private TextView weekTv;
    private TextView currentTempTv;
    private TextView typeTv;
    private TextView tempRangeTv;
    private TextView fengliTv;
    private TextView fengxiangTv;
    private PullToRefreshScrollView scrollView;
    private com.melnykov.fab.FloatingActionButton addBtn;

    private MainActivity mainActivity;
    private boolean isFromSp = false;


    public static Fragment getInstance(String city) {
        TodayFragment fragment = new TodayFragment();
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
        if (app.sp.getBoolean(Constant.IS_FIRST, true)) {
            getFromServer(city);
            SharedPreferences.Editor editor = app.sp.edit();
            editor.putBoolean(Constant.IS_FIRST, false);
            editor.commit();
        } else {
            updateInfoFromSp();
            isFromSp = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_today, null);
        initView();
        locationIv.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                getFromServer(city);
                showWeather();
            }
        });
        if (isFromSp) {
            showWeather();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void updateInfoFromSp() {
        type = app.sp.getString(TodayInfo.TYPE, Constant.DEFAULT_VALUE);
        fengxiang = app.sp.getString(TodayInfo.FENG_XIANG, Constant.DEFAULT_VALUE);
        lowtemp = app.sp.getString(TodayInfo.LOW_TEMP, Constant.DEFAULT_VALUE);
        hightemp = app.sp.getString(TodayInfo.HIGH_TEMP, Constant.DEFAULT_VALUE);
        curTemp = app.sp.getString(TodayInfo.CUR_TEMP, Constant.DEFAULT_VALUE);
        fengli = app.sp.getString(TodayInfo.FENG_LI, Constant.DEFAULT_VALUE);
        updateTime = app.sp.getString(Constant.TIME, null);
        city = app.sp.getString(Constant.CITY_NAME, null);
        date = FormatUtils.formatDate();
        week = FormatUtils.formatWeek();

        skinDetail = app.sp.getString(IndexInfo.DETAILS,null);
        buildDialog(skinDetail);
    }

    public void initView() {
        cityNameTv = (TextView) view.findViewById(R.id.top_location_tv);
        locationIv = (ImageView) view.findViewById(R.id.iv_location);
        currentTempTv = (TextView) view.findViewById(R.id.today_temp_tv);
        typeTv = (TextView) view.findViewById(R.id.today_type_tv);
        dateTv = (TextView) view.findViewById(R.id.date_tv);
        weekTv = (TextView) view.findViewById(R.id.week_tv);
        tempRangeTv = (TextView) view.findViewById(R.id.today_temp_range_tv);
        updateTimeTv = (TextView) view.findViewById(R.id.update_tv);
        fengliTv = (TextView) view.findViewById(R.id.today_fengli_tv);
        fengxiangTv = (TextView) view.findViewById(R.id.today_fengxiang_tv);
        scrollView = (PullToRefreshScrollView) view.findViewById(R.id.today_scrollview);
        addBtn = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    public void showWeather() {
        cityNameTv.setText(city);
        currentTempTv.setText(curTemp);
        typeTv.setText(type);
        dateTv.setText(date);
        weekTv.setText(week);
        tempRangeTv.setText(FormatUtils.formatRange(lowtemp, hightemp));
        updateTimeTv.setText(updateTime);
        fengliTv.setText(fengli);
        fengxiangTv.setText(fengxiang);
    }

    public void getFromServer(final String city){
        HttpUtils.getJson(city, Constant.Url, new ApiCallBack() {
            @Override
            public void onError(int i, String s, Exception e) {
                super.onError(i, s, e);
                Toast.makeText(mainActivity, "对不起查询失败，请检查网络连接", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                updateTime = FormatUtils.formatTime();
                String response = ParseUtils.decodeUnicode(s);
                TodayInfo todayInfo = ParseUtils.parseToday(response);
                type = todayInfo.getType();
                curTemp = todayInfo.getCurTemp();
                lowtemp = todayInfo.getLowtemp();
                hightemp = todayInfo.getHightemp();
                fengxiang = todayInfo.getFengxiang();
                fengli = todayInfo.getFengli();
                date = FormatUtils.formatDate();
                week = FormatUtils.formatWeek();

                skinDetail = ParseUtils.parseIndex(response).get(1).getDetails();
                buildDialog(skinDetail);
                showWeather();
                HttpUtils.saveInfo(s, app, city);
                mainActivity.update();
            }

            @Override
            public void onComplete() {
                super.onComplete();
                Toast.makeText(mainActivity,Constant.UPDATE_SUCCESS,Toast.LENGTH_SHORT).show();
                scrollView.onRefreshComplete();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.iv_location:
                Intent intent = new Intent(getActivity(), CityPickerActivity.class);
                startActivity(intent);
                break;
            case R.id.fab:
                builder.show();
                break;
            default:
                break;
        }
    }

    public static interface updateListener{
        public void update();
    }

    public void buildDialog(String detail){

        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("护肤提示");
        builder.setMessage(detail);
        builder.setPositiveButton("知道啦！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}});
        dialog = builder.create();
    }
}
