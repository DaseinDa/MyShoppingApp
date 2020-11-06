package com.my.shopping.app.beans;

import org.litepal.crud.LitePalSupport;

public class CommentBeans extends LitePalSupport {
    long id;
    long goodsId;
    String userId;
    String headImg;
    String school;
    String com;

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
