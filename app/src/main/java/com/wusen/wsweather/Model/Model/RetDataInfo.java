package com.wusen.wsweather.Model.Model;

import java.util.List;

/**
 * Created by 15059 on 2016/3/28.
 */
public class RetDataInfo {
//    "city": "北京",
//            "cityid": "101010100",
//            "today":
    private String city;
    private String cityid;
    private TodayInfo today;
    private List<ForecastInfo> forecast;

    public List<ForecastInfo> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastInfo> forecast) {
        this.forecast = forecast;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public TodayInfo getToday() {
        return today;
    }

    public void setToday(TodayInfo today) {
        this.today = today;
    }
}
