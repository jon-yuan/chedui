package com.babuwyt.daili.entity;



import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/7/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class WeizhiEntity {
    private double wgLon;
    private double wgLat;

    public double getWgLon() {
        return wgLon;
    }

    public void setWgLon(double wgLon) {
        this.wgLon = wgLon;
    }

    public double getWgLat() {
        return wgLat;
    }

    public void setWgLat(double wgLat) {
        this.wgLat = wgLat;
    }

    /**
     *  /**
     * "obj": {
     "wgLon": 0,
     "wgLat": 0
     }
     */
}
