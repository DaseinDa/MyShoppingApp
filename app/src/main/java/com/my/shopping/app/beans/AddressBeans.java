package com.my.shopping.app.beans;

import org.litepal.crud.LitePalSupport;

public class AddressBeans   extends LitePalSupport {
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
