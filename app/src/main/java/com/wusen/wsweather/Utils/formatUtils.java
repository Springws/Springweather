package com.wusen.wsweather.Utils;

import java.util.Calendar;

/**
 * Created by 15059 on 2016/4/6.
 */
public class FormatUtils {

    //返回日期
    public static String formatDate(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String date = month+"月"+dayOfMonth+"日";
        return date;
    }

    //返回时间
    public static String formatTime(){
        Calendar calendar = Calendar.getInstance();
        String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
        String minute = calendar.get(Calendar.MINUTE)+"";
        if(hour.length()==1)
        {
            hour = "0"+ hour;
        }
        if(minute.length()==1)
        {
            minute = "0"+minute;
        }

        String time = hour+":"+minute;
        return time;
    }

    //返回星期几
    public static String formatWeek(){
        Calendar calendar = Calendar.getInstance();
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return weekDays[week-1];

    }

    //返回最低温度至最高温度
    public static String formatRange(String low,String height){
        String s = low+"-"+height;
        return s;
    }
}
