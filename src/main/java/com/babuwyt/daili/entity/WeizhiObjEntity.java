package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/31.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class WeizhiObjEntity {
    private String drivename;
    private String address;
    private String drivecard;
    private String utc;
    private WeizhiEntity gps;

    public String getDrivename() {
        return drivename;
    }

    public void setDrivename(String drivename) {
        this.drivename = drivename;
    }

    public String getDrivecard() {
        return drivecard;
    }

    public void setDrivecard(String drivecard) {
        this.drivecard = drivecard;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

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



    private String status;
    private String state;
    private BeidouResult result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BeidouResult getResult() {
        return result;
    }

    public void setResult(BeidouResult result) {
        this.result = result;
    }
}
