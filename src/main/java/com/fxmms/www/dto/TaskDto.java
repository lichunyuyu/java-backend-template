package com.fxmms.www.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fxmms.common.ro.Dto;

import java.util.Date;

/**
 * Created by mark on 16/11/8.
 *
 * @usage 数据访问传输对象，可接收前端页面传递过来的参数，参数name需要跟此类中的属性名称相同。
 * 使用的是spring mvc参数绑定功能
 */
public class TaskDto implements Dto {
    private int id;             //taskId
    @JsonIgnore
    private Date loadDate;      //task发生时间。
    private int flag;           //任务录入是否成功标志
    private String dateStr;     //显示到前台页面的是date的字符串形式

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public Dto newInstance() {
        return new TaskDto();
    }
}
