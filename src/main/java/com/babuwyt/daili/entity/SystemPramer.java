package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/31.
 */

@HttpResponse(parser = JsonResponseParser.class)
public class SystemPramer {
    private String facceptratio;//默认比例系数
    private String freturnratio;//油卡奖励系数
    private String discountratio;//现金扣除系数

    private String userid;
    private String fid;
    private String ftruckid;
    private String fmanageid;
    private String ffreight;
    private String ftion;
    private String foilcardValue;
    private String freight;
    private String fgiveoilcardValue;
    private String fkouchu;
    private String fMoneyRecharge2;
    private String fMoneyRecharge1;
    private String foilcardrecharge1;
    private String foilcardrecharge2;
    private String ftotalCost;
    private String shijian;
    private String xianlu;
    private String sijiname;

    public String getSijiname() {
        return sijiname;
    }

    public void setSijiname(String sijiname) {
        this.sijiname = sijiname;
    }

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }

    public String getXianlu() {
        return xianlu;
    }

    public void setXianlu(String xianlu) {
        this.xianlu = xianlu;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFtruckid() {
        return ftruckid;
    }

    public void setFtruckid(String ftruckid) {
        this.ftruckid = ftruckid;
    }

    public String getFmanageid() {
        return fmanageid;
    }

    public void setFmanageid(String fmanageid) {
        this.fmanageid = fmanageid;
    }

    public String getFfreight() {
        return ffreight;
    }

    public void setFfreight(String ffreight) {
        this.ffreight = ffreight;
    }

    public String getFtion() {
        return ftion;
    }

    public void setFtion(String ftion) {
        this.ftion = ftion;
    }

    public String getFoilcardValue() {
        return foilcardValue;
    }

    public void setFoilcardValue(String foilcardValue) {
        this.foilcardValue = foilcardValue;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getFgiveoilcardValue() {
        return fgiveoilcardValue;
    }

    public void setFgiveoilcardValue(String fgiveoilcardValue) {
        this.fgiveoilcardValue = fgiveoilcardValue;
    }

    public String getFkouchu() {
        return fkouchu;
    }

    public void setFkouchu(String fkouchu) {
        this.fkouchu = fkouchu;
    }

    public String getfMoneyRecharge2() {
        return fMoneyRecharge2;
    }

    public void setfMoneyRecharge2(String fMoneyRecharge2) {
        this.fMoneyRecharge2 = fMoneyRecharge2;
    }

    public String getfMoneyRecharge1() {
        return fMoneyRecharge1;
    }

    public void setfMoneyRecharge1(String fMoneyRecharge1) {
        this.fMoneyRecharge1 = fMoneyRecharge1;
    }

    public String getFoilcardrecharge1() {
        return foilcardrecharge1;
    }

    public void setFoilcardrecharge1(String foilcardrecharge1) {
        this.foilcardrecharge1 = foilcardrecharge1;
    }

    public String getFoilcardrecharge2() {
        return foilcardrecharge2;
    }

    public void setFoilcardrecharge2(String foilcardrecharge2) {
        this.foilcardrecharge2 = foilcardrecharge2;
    }

    public String getFtotalCost() {
        return ftotalCost;
    }

    public void setFtotalCost(String ftotalCost) {
        this.ftotalCost = ftotalCost;
    }

    public String getFacceptratio() {
        return facceptratio;
    }

    public void setFacceptratio(String facceptratio) {
        this.facceptratio = facceptratio;
    }

    public String getFreturnratio() {
        return freturnratio;
    }

    public void setFreturnratio(String freturnratio) {
        this.freturnratio = freturnratio;
    }

    public String getDiscountratio() {
        return discountratio;
    }

    public void setDiscountratio(String discountratio) {
        this.discountratio = discountratio;
    }

    /**
     * "facceptratio": "50",
     "freturnratio": "8",
     "discountratio": "20"
     */
}
