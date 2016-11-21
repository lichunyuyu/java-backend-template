package com.fxmms.www.dto;

import com.fxmms.common.ro.Dto;

/**
 * Created by mark on 16/11/3.
 * @usage 数据传输对象经过序列化
 */
public class AdminDto implements Dto {
    private String userName;
    private String password;
    private String role;
    private int enable;
    private int isDelete;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public Dto newInstance() {
        return new AdminDto();
    }
}
