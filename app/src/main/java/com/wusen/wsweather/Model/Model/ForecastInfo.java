package com.wusen.wsweather.Model.Model;

/**
 * Created by 15059 on 2016/3/29.
 */
public class ForecastInfo {
    public final static String DATE = "date";
    public final static String WEEK = " week";
    public final static String FENG_XIANG = "fengxiang";
    public final static String FENG_LI = "fengli";
    public final static String HIGH_TEMP = "hightemp";
    public final static String LOW_TEMP = " lowtemp";
    public final static String TYPE = "type";

    private String date;
    private String week;
    private String fengxiang;
    private String fengli;
    private String hightemp;
    private String lowtemp;
    private String type;

    private long _id;

    public long get_id() {return _id;}

    public void set_id(long _id) {this._id = _id;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
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

    public String getHightemp() {
        return hightemp;
    }

    public void setHightemp(String hightemp) {
        this.hightemp = hightemp;
    }

    public String getLowtemp() {
        return lowtemp;
    }

    public void setLowtemp(String lowtemp) {
        this.lowtemp = lowtemp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
