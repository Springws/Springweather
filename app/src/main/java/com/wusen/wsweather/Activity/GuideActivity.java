package com.wusen.wsweather.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.wusen.wsweather.Adapter.GuideAdapter;
import com.wusen.wsweather.Application.MyApplication;
import com.wusen.wsweather.R;

import java.util.ArrayList;
import java.util.List;

import citypicker.CityPickerActivity;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,View.OnClickListener{
    private List<View> viewList;
    private GuideAdapter adapter;
    private ViewPager pager;
    private ImageView[] dots;
    private int[] ids;
    private Button startBtn;
    private MyApplication app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        app = (MyApplication) getApplication();
        initView();
        initPager();
        pager.setOnPageChangeListener(this);
        startBtn.setOnClickListener(this);
        adapter = new GuideAdapter(viewList,this);
        pager.setAdapter(adapter);
    }

    public void initPager(){
        viewList = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.guide_pager_item1,null);
        View view2 = inflater.inflate(R.layout.guide_pager_item2,null);
        View view3 = inflater.inflate(R.layout.guide_pager_item3,null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        startBtn = (Button) view3.findViewById(R.id.guide_start_activity);
    }

    public void initView(){
        pager = (ViewPager) findViewById(R.id.guide_pager);
        startBtn = (Button) findViewById(R.id.guide_start_activity);
        ids = new int[]{R.id.dot_one, R.id.dot_two, R.id.dot_three};
        dots = new ImageView[ids.length];
        for(int i=0;i<ids.length;i++)
        {
              dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
            for(int i=0;i<dots.length;i++)
            {
                if(i == position)
                {
                    dots[i].setImageResource(R.mipmap.dot_select);
                }else {
                    dots[i].setImageResource(R.mipmap.dot_not_select);
                }
            }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.guide_start_activity:
                Intent intent = new Intent(GuideActivity.this, CityPickerActivity.class);
                startActivity(intent);
                finish();
        }
    }
}
