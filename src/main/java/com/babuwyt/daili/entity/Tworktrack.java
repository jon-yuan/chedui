package com.babuwyt.daili.entity;



import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/23.
 */

@HttpResponse(parser = JsonResponseParser.class)
public class Tworktrack {

    private String fid;
    private String fsendcarid;
    private int fstate;
    private long fcreatetime;
    private int fuserid;
    private String fremark;
    private String fremark1;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFsendcarid() {
        return fsendcarid;
    }

    public void setFsendcarid(String fsendcarid) {
        this.fsendcarid = fsendcarid;
    }

    public int getFstate() {
        return fstate;
    }

    public void setFstate(int fstate) {
        this.fstate = fstate;
    }

    public long getFcreatetime() {
        return fcreatetime;
    }

    public void setFcreatetime(long fcreatetime) {
        this.fcreatetime = fcreatetime;
    }

    public int getFuserid() {
        return fuserid;
    }

    public void setFuserid(int fuserid) {
        this.fuserid = fuserid;
    }

    public String getFremark() {
        return fremark;
    }

    public void setFremark(String fremark) {
        this.fremark = fremark;
    }

    public String getFremark1() {
        return fremark1;
    }

    public void setFremark1(String fremark1) {
        this.fremark1 = fremark1;
    }

    /**
     * "fid": 269,
     "fsendcarid": 176,
     "fstate": 1,
     "fcreatetime": 1500629601000,
     "fuserid": 121,
     "fremark": null,
     "fremark1": null
     */
}
