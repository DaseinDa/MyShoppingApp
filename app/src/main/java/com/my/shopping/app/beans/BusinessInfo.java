package com.my.shopping.app.beans;

import org.litepal.crud.LitePalSupport;

public class BusinessInfo   extends LitePalSupport {
    long id;//主键
    String headImg;//图像==头像
    String img;//背景图像
    String name;//店铺名字
    String con;//描述
    String fkId;//外键

    public String getFkId() {
        return fkId;
    }

    public void setFkId(String fkId) {
        this.fkId = fkId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }
}
