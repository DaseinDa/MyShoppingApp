package com.my.shopping.app.beans;

import org.litepal.crud.LitePalSupport;

public class TestOrderInfo extends LitePalSupport {
    long id;
    String sizeMoney;
    String orderNO;
    String userId;
    String fkId;

    public String getFkId() {
        return fkId;
    }

    public void setFkId(String fkId) {
        this.fkId = fkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSizeMoney() {
        return sizeMoney;
    }

    public void setSizeMoney(String sizeMoney) {
        this.sizeMoney = sizeMoney;
    }

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }
}
