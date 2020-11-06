package com.my.shopping.app.beans;

import org.litepal.crud.LitePalSupport;

public class UserBean extends LitePalSupport {
    long id;//主键
    String userName;//账号
    String password;//密码
    String type;//类型
    String head;//头像




    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
