package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/29.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class TOrderGoods {
private String fid;
private String fgoodtype;
private String fname;
private String ftransportno;
private int fgoodid;

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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFtransportno() {
        return ftransportno;
    }

    public void setFtransportno(String ftransportno) {
        this.ftransportno = ftransportno;
    }

    public int getFgoodid() {
        return fgoodid;
    }

    public void setFgoodid(int fgoodid) {
        this.fgoodid = fgoodid;
    }
/**
     *  "fid": 159,
     "fgoodtype": "组件",
     "fgoodid": 1,
     "ftransportno": 159,
     "fname": "组件"
     */

}
