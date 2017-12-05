package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class YunDanDetailsgoodinfoEntity {

    private String fid;
    private int fpacknum;
    private String fpackunit;
    private int froughweight;
    private double fbulk;
    private String flenght;
    private String fwide;
    private String fheight;
    private String foneweight;
    private double fonebulk;
    private String ftransportno;
    private String ftskno;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public int getFpacknum() {
        return fpacknum;
    }

    public void setFpacknum(int fpacknum) {
        this.fpacknum = fpacknum;
    }

    public String getFpackunit() {
        return fpackunit;
    }

    public void setFpackunit(String fpackunit) {
        this.fpackunit = fpackunit;
    }

    public int getFroughweight() {
        return froughweight;
    }

    public void setFroughweight(int froughweight) {
        this.froughweight = froughweight;
    }

    public double getFbulk() {
        return fbulk;
    }

    public void setFbulk(double fbulk) {
        this.fbulk = fbulk;
    }

    public String getFlenght() {
        return flenght;
    }

    public void setFlenght(String flenght) {
        this.flenght = flenght;
    }

    public String getFwide() {
        return fwide;
    }

    public void setFwide(String fwide) {
        this.fwide = fwide;
    }

    public String getFheight() {
        return fheight;
    }

    public void setFheight(String fheight) {
        this.fheight = fheight;
    }

    public String getFoneweight() {
        return foneweight;
    }

    public void setFoneweight(String foneweight) {
        this.foneweight = foneweight;
    }

    public double getFonebulk() {
        return fonebulk;
    }

    public void setFonebulk(double fonebulk) {
        this.fonebulk = fonebulk;
    }

    public String getFtransportno() {
        return ftransportno;
    }

    public void setFtransportno(String ftransportno) {
        this.ftransportno = ftransportno;
    }

    public String getFtskno() {
        return ftskno;
    }

    public void setFtskno(String ftskno) {
        this.ftskno = ftskno;
    }


    /**
     * "fid": 69,
     "fpacknum": 23,
     "fpackunit": "PLTS",
     "froughweight": 22954,
     "fbulk": 63.756,
     "flenght": 1200,
     "fwide": 2100,
     "fheight": 1100,
     "foneweight": 998,
     "fonebulk": 2.772,
     "ftransportno": 45,
     "ftskno": null
     */
}
