package com.wusen.wsweather.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wusen.wsweather.Model.Model.ForecastInfo;
import com.wusen.wsweather.R;
import com.wusen.wsweather.Utils.FormatUtils;

import java.util.List;

/**
 * Created by 15059 on 2016/4/5.
 */
public class ForecastAdapter extends BaseAdapter {
    private Context context;
    private List<ForecastInfo> forecastInfos;
    public ForecastAdapter(Context context,List<ForecastInfo> forecastInfos) {
        this.context = context;
        this.forecastInfos = forecastInfos;
    }

    @Override
    public int getCount() {
        return forecastInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return forecastInfos;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view;
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_listview_forecast, null);
            viewHolder.weekTv = (TextView) view.findViewById(R.id.forecast_week_tv);
            viewHolder.typeTv = (TextView) view.findViewById(R.id.forecast_type_tv);
            viewHolder.rangeTv = (TextView) view.findViewById(R.id.forecast_range_tv);
            viewHolder.fengxiangTv = (TextView) view.findViewById(R.id.forecast_fengxiang_tv);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }
            ForecastInfo forecastInfo = forecastInfos.get(i);
            viewHolder.weekTv.setText(forecastInfo.getWeek());
            viewHolder.typeTv.setText(forecastInfo.getType());
            viewHolder.rangeTv.setText(FormatUtils.formatRange(forecastInfo.getLowtemp(),forecastInfo.getHightemp()));
            viewHolder.fengxiangTv.setText(forecastInfo.getFengxiang());
            return view;
        }

    class ViewHolder{
        TextView weekTv;
        TextView typeTv;
        TextView rangeTv;
        TextView fengxiangTv;


    }
}
