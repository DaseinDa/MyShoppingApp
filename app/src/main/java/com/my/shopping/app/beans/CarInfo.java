package com.my.shopping.app.beans;

import org.litepal.crud.LitePalSupport;

public class CarInfo extends LitePalSupport {
    long id;
    String userId;
    String goodsName;
    String goodsCon;
    String img;
    String goodsId;
    int moneySize;
    int size;
    String isYes;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getIsYes() {
        return isYes;
    }

    public void setIsYes(String isYes) {
        this.isYes = isYes;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCon() {
        return goodsCon;
    }

    public void setGoodsCon(String goodsCon) {
        this.goodsCon = goodsCon;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMoneySize() {
        return moneySize;
    }

    public void setMoneySize(int moneySize) {
        this.moneySize = moneySize;
    }
}
