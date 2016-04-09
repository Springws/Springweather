package com.wusen.wsweather.Model.Model;

/**
 * Created by 15059 on 2016/3/29.
 */
public class ResultInfo{


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
