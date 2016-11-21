package com.fxmms.www.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fxmms.common.ro.Dto;
import com.fxmms.common.util.DateUtil;
import com.fxmms.www.domain.Admin;
import com.fxmms.www.domain.Task;

import java.util.Date;

/**
 * Created by mark on 16/11/7.
 *
 * @usage MacDto.class生成数据传输对象
 */
public class MacDto implements Dto {

    private int id;           //所属id
    private String macAddr;   //MAC地址
    private String downLoadId;//生成downLoadId
    private String deviceId;
    @JsonIgnore
    private int status;       //MAC地址状态 分为如下几个状态0--初始化、1--正在录入、2--录入成功、3--录入失败
    private Admin admin;      //操作人
    private Task task;        //任务id
    @JsonIgnore               //冗余字段在接口返回的json 数据中不显示，折衷办法，更改数据库结构是一件很可怕的事情。
    private Date date;
    private String dateStr;   //前台特面显示的字符串日期格式
    private String statusStr; //前台显示状态对应字符串

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public String getDownLoadId() {
        return downLoadId;
    }

    public void setDownLoadId(String downLoadId) {
        this.downLoadId = downLoadId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateStr() {
        return DateUtil.doChangeDateToStr(this.date);
    }

    public void setDateStr(String dateStr) {
        this.dateStr = this.getDateStr();
    }

    public String getStatusStr() {
        if (this.status == 0) {
            return "<span class=\"label label-info\">初始化</span>";
        }
        if (this.status == 1) {
            return "<span class=\"label label-primary\">正在录入</span>";
        }
        if (this.status == 2) {
            return "<span class=\"label label-success\">录入成功</span>";
        }
        return "<span class=\"label label-danger\">录入失败</span>";
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = this.getStatusStr();
    }

    @Override
    public Dto newInstance() {
        return new MacDto();
    }
}
