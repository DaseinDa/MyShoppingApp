package com.my.shopping.app.beans;

import org.litepal.crud.LitePalSupport;

public class GoodsInfo  extends LitePalSupport {
    long id;
    String goodsName;
    String goodsCon;
    String fkId;
    String img;
    int moneySize;
    int purchase;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFkId() {
        return fkId;
    }

    public void setFkId(String fkId) {
        this.fkId = fkId;
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

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
    }
}
