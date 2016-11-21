package com.fxmms.www.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mark on 16/11/8.
 *
 * @usage 每批MAC录入
 */
@Entity
@Table(name = "mms_task")
public class Task {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column
    private int id;         //taskId
    @Column
    private Date date;      //task发生时间。
    @Column
    private int flag;       //MAC地址批次录入任务是否成功标志 0:未成功 1:成功

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
