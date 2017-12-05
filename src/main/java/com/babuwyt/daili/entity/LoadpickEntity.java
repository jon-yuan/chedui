package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/25.
 */

@HttpResponse(parser = JsonResponseParser.class)
public class LoadpickEntity {
    private String fid;
    private String ftransportno;
    private String fromto;
    private String faddress;
    private String fcustomername;
    private String fdistrict;
    private String flinkman;
    private String fphone;
    private int fseq;
    private String fromtoloadid;
    private int pickcount;
    private int unloadcount;
    private String ssq;
    private String ownsendcarno;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFtransportno() {
        return ftransportno;
    }

    public void setFtransportno(String ftransportno) {
        this.ftransportno = ftransportno;
    }

    public String getFromto() {
        return fromto;
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
    }

    public String getFaddress() {
        return faddress;
    }

    public void setFaddress(String faddress) {
        this.faddress = faddress;
    }

    public String getFcustomername() {
        return fcustomername;
    }

    public void setFcustomername(String fcustomername) {
        this.fcustomername = fcustomername;
    }

    public String getFdistrict() {
        return fdistrict;
    }

    public void setFdistrict(String fdistrict) {
        this.fdistrict = fdistrict;
    }

    public String getFlinkman() {
        return flinkman;
    }

    public void setFlinkman(String flinkman) {
        this.flinkman = flinkman;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    public int getFseq() {
        return fseq;
    }

    public void setFseq(int fseq) {
        this.fseq = fseq;
    }

    public String getFromtoloadid() {
        return fromtoloadid;
    }

    public void setFromtoloadid(String fromtoloadid) {
        this.fromtoloadid = fromtoloadid;
    }

    public int getPickcount() {
        return pickcount;
    }

    public void setPickcount(int pickcount) {
        this.pickcount = pickcount;
    }

    public int getUnloadcount() {
        return unloadcount;
    }

    public void setUnloadcount(int unloadcount) {
        this.unloadcount = unloadcount;
    }

    public String getSsq() {
        return ssq;
    }

    public void setSsq(String ssq) {
        this.ssq = ssq;
    }

    public String getOwnsendcarno() {
        return ownsendcarno;
    }

    public void setOwnsendcarno(String ownsendcarno) {
        this.ownsendcarno = ownsendcarno;
    }

    /**
     * 轨迹用到的经纬度
     */

    private ArrayList<resultEntity> result;
    private String status;

    public ArrayList<resultEntity> getResult() {
        return result;
    }

    public void setResult(ArrayList<resultEntity> result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    /**
 * {
 "fid": 235,
 "ftransportno": 99,
 "fromto": "FROM",
 "faddress": "123123123123人间",
 "fcustomername": "Z总公司",
 "fdistrict": "370982",
 "flinkman": "Z总",
 "fphone": "18652609269",
 "fseq": 1,
 "fromtoloadid": "",
 "pickcount": 1,
 "unloadcount": 1,
 "ssq": "山东省泰安市新泰市",
 "ownsendcarno": "QD201707190001"
 },
 */


}
