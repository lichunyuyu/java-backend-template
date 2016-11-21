package com.fxmms.www.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mark on 16/11/7.
 *
 * @usage MAC地址实体类，与数据库中MAC地址数据表相对应
 * 外键关联管理员与任务 出现一个冗余字段date，需要在后期剔除
 */
@Entity
@Table(name = "mms_mac")
public class Mac {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column
    private int id;           //所属id
    @Column
    private String macAddr;   //MAC地址
    @Column
    private String downLoadId;//生成downLoadId
    @Column
    private String deviceId;
    @Column
    private int status;       //MAC地址状态 分为如下几个状态0--初始化、1--正在录入、2--录入成功、3--录入失败
    @JoinColumn(name = "aid")
    @OneToOne
    private Admin admin;      //操作人
    @JoinColumn(name = "tid")
    @ManyToOne
    private Task task;        //所属任务
    @Column
    private Date date;

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
}
