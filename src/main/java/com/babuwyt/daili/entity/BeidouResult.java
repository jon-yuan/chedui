package com.babuwyt.daili.entity;

import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2018/1/15.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class BeidouResult {
    private String drc;
    private String utc;
    private String spd;
    private double lon;
    private String adr;
    private String drivename;
    private double lat;

    public String getDrc() {
        return drc;
    }

    public void setDrc(String drc) {
        this.drc = drc;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getDrivename() {
        return drivename;
    }

    public void setDrivename(String drivename) {
        this.drivename = drivename;
    }
    /**
     *  "drc": "0",
     "utc": "2018-01-15 09:09:12",
     "spd": "0.0",
     "lon": "64968933",
     "adr": "陕西省咸阳市乾县王家坡，向西北方向，681米",
     "lat": "20733783"
     */
}
