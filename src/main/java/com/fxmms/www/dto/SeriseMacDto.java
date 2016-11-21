package com.fxmms.www.dto;

/**
 * Created by mark on 16/11/8.
 * @usage 接收前台添加区间MAC地址页面的起始地址与结束地址参数
 */
public class SeriseMacDto {
    //startMacStr
    private String startMacStr;
    private String endMacStr;

    public String getStartMacStr() {
        return startMacStr;
    }

    public void setStartMacStr(String startMacStr) {
        this.startMacStr = startMacStr;
    }

    public String getEndMacStr() {
        return endMacStr;
    }

    public void setEndMacStr(String endMacStr) {
        this.endMacStr = endMacStr;
    }
}
