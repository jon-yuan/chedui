package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/31.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class WeizhiObjEntity {
    private String address;
    private WeizhiEntity gps;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public WeizhiEntity getGps() {
        return gps;
    }

    public void setGps(WeizhiEntity gps) {
        this.gps = gps;
    }
}
