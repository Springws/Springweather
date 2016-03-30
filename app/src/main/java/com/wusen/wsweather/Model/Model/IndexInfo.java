package com.wusen.wsweather.Model.Model;

/**
 * Created by 15059 on 2016/3/29.
 */
public class IndexInfo {
//    {
//        "code": "fs",
//            "details": "属中等强度紫外辐射天气，外出时应注意防护，建议涂擦SPF指数高于15，PA+的防晒护肤品。",
//            "index": "中等",
//            "name": "防晒指数",
//            "otherName": ""
//    },
//
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
