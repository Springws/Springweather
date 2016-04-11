package com.wusen.wsweather.Model.Model;

/**
 * Created by 15059 on 2016/3/29.
 */
public class IndexInfo {

    public final static String CODE = "code";
    public final static String DETAILS = "details"; //生活建议
    public final static String NAME = "name";
    public final static String OTHER_NAME = "otherName";

    private String code;
    private String details;
    private String name;
    private String otherName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }
}
