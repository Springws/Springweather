package com.wusen.wsweather.Model.Model;

import java.util.List;

/**
 * Created by 15059 on 2016/3/29.
 */
public class TodayInfo {

    public final static String DATE = "date";
    public final static String WEEK = " week";
    public final static String CUR_TEMP = "curTemp";
    public final static String AQI = "aqi";
    public final static String FENG_XIANG = "fengxiang";
    public final static String FENG_LI = "fengli";
    public final static String HIGH_TEMP = "hightemp";
    public final static String LOW_TEMP = " lowtemp";
    public final static String TYPE = "type";

    private String date;
    private String week;
    private String curTemp;
    private String aqi;
    private String fengxiang;
    private String fengli;
    private String hightemp;
    private String lowtemp;
    private String type;
    private List<IndexInfo> index;



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<IndexInfo> getIndex() {
        return index;
    }

    public void setIndex(List<IndexInfo> index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLowtemp() {
        return lowtemp;
    }

    public void setLowtemp(String lowtemp) {
        this.lowtemp = lowtemp;
    }

    public String getHightemp() {
        return hightemp;
    }

    public void setHightemp(String hightemp) {
        this.hightemp = hightemp;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCurTemp() {
        return curTemp;
    }

    public void setCurTemp(String curTemp) {
        this.curTemp = curTemp;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }
}
