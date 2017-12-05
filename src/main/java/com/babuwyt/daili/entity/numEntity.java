package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/9/4.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class numEntity {

    private int drivercount;
    private int ordercount;

    public int getDrivercount() {
        return drivercount;
    }

    public void setDrivercount(int drivercount) {
        this.drivercount = drivercount;
    }

    public int getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(int ordercount) {
        this.ordercount = ordercount;
    }
    /**
     * {"drivercount":2,"ordercount":1}
     */
}
