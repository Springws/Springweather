package com.wusen.wsweather.Model.Model;

/**
 * Created by 15059 on 2016/3/29.
 */
public class ResultInfo {
//    "errNum": 0,
//            "errMsg": "success",
//            "retData": {
//        "city": "北京",
//                "cityid": "101010100",
//                "today": {
//            "date": "2016-03-29",
//                    "week": "星期二",
//                    "curTemp": "21℃",
//                    "aqi": "66",
//                    "fengxiang": "无持续风向",
//                    "fengli": "微风级",
//                    "hightemp": "22℃",
//                    "lowtemp": "7℃",
//                    "type": "晴",
//                    "index":
    private int errNum;
    private String errMsg;
    private RetDataInfo retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public RetDataInfo getRetData() {
        return retData;
    }

    public void setRetData(RetDataInfo retData) {
        this.retData = retData;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
