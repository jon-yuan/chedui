package com.babuwyt.daili.entity;

import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class TOrderGoodsEntity {


    private String fid;
    private String fgoodtype;
    private int fgoodid;
    private String ftransportno;
    private String fname;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFgoodtype() {
        return fgoodtype;
    }

    public void setFgoodtype(String fgoodtype) {
        this.fgoodtype = fgoodtype;
    }

    public int getFgoodid() {
        return fgoodid;
    }

    public void setFgoodid(int fgoodid) {
        this.fgoodid = fgoodid;
    }

    public String getFtransportno() {
        return ftransportno;
    }

    public void setFtransportno(String ftransportno) {
        this.ftransportno = ftransportno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     *   "fid": 45,
     "fgoodtype": "组件",
     "fgoodid": 1,
     "ftransportno": 45,
     "fname": "组件"
     */
}
