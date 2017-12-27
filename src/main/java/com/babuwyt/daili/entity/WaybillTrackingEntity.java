package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/29.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class WaybillTrackingEntity {


    private String fid;
    private String fownersendcarno;
    private String fsendcarno;
    private String fpickuptime;
    private String fmanageid;
    private String farrivetime;
    private int ftaskstate;
    private String fvicecard;
    private String fplateno;
    private String fdriverid;
    private String fshouldpay;
    private String fshouldpayoilcard;
    private String fshouldreturnmoney;
    private String fdrivername;
    private int fstate;
    private String ftransportno;
    private String foilcardrecharge1;
    private String foilcardrecharge2;
    private String frechargerecordId1;
    private String frechargerecordId2;
    private int fsettlestate;
    private long fsettime;
    private String fMoneyRecharge2;
    private String fMoneyRecharge1;
    private String fkouchu;
    private String facceptratio;
    private String fwonid;
    private String goods;
    private String ttype;
    private String picktime;
    private String ffromaddress;
    private String ftoaddress;
    private String pagenum;
    private String fweight;

    public String getFid() {
        return fid;
    }

    public String getFarrivetime() {
        return farrivetime;
    }

    public void setFarrivetime(String farrivetime) {
        this.farrivetime = farrivetime;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFownersendcarno() {
        return fownersendcarno;
    }

    public void setFownersendcarno(String fownersendcarno) {
        this.fownersendcarno = fownersendcarno;
    }

    public String getFsendcarno() {
        return fsendcarno;
    }

    public void setFsendcarno(String fsendcarno) {
        this.fsendcarno = fsendcarno;
    }

    public String getFpickuptime() {
        return fpickuptime;
    }

    public void setFpickuptime(String fpickuptime) {
        this.fpickuptime = fpickuptime;
    }

    public String getFmanageid() {
        return fmanageid;
    }

    public void setFmanageid(String fmanageid) {
        this.fmanageid = fmanageid;
    }

    public int getFtaskstate() {
        return ftaskstate;
    }

    public void setFtaskstate(int ftaskstate) {
        this.ftaskstate = ftaskstate;
    }

    public String getFvicecard() {
        return fvicecard;
    }

    public void setFvicecard(String fvicecard) {
        this.fvicecard = fvicecard;
    }

    public String getFplateno() {
        return fplateno;
    }

    public void setFplateno(String fplateno) {
        this.fplateno = fplateno;
    }

    public String getFdriverid() {
        return fdriverid;
    }

    public void setFdriverid(String fdriverid) {
        this.fdriverid = fdriverid;
    }

    public String getFshouldpay() {
        return fshouldpay;
    }

    public void setFshouldpay(String fshouldpay) {
        this.fshouldpay = fshouldpay;
    }

    public String getFshouldpayoilcard() {
        return fshouldpayoilcard;
    }

    public void setFshouldpayoilcard(String fshouldpayoilcard) {
        this.fshouldpayoilcard = fshouldpayoilcard;
    }

    public String getFshouldreturnmoney() {
        return fshouldreturnmoney;
    }

    public void setFshouldreturnmoney(String fshouldreturnmoney) {
        this.fshouldreturnmoney = fshouldreturnmoney;
    }

    public String getFdrivername() {
        return fdrivername;
    }

    public void setFdrivername(String fdrivername) {
        this.fdrivername = fdrivername;
    }

    public int getFstate() {
        return fstate;
    }

    public void setFstate(int fstate) {
        this.fstate = fstate;
    }

    public String getFtransportno() {
        return ftransportno;
    }

    public void setFtransportno(String ftransportno) {
        this.ftransportno = ftransportno;
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

    public String getFrechargerecordId1() {
        return frechargerecordId1;
    }

    public void setFrechargerecordId1(String frechargerecordId1) {
        this.frechargerecordId1 = frechargerecordId1;
    }

    public String getFrechargerecordId2() {
        return frechargerecordId2;
    }

    public void setFrechargerecordId2(String frechargerecordId2) {
        this.frechargerecordId2 = frechargerecordId2;
    }

    public int getFsettlestate() {
        return fsettlestate;
    }

    public void setFsettlestate(int fsettlestate) {
        this.fsettlestate = fsettlestate;
    }

    public long getFsettime() {
        return fsettime;
    }

    public void setFsettime(long fsettime) {
        this.fsettime = fsettime;
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

    public String getFkouchu() {
        return fkouchu;
    }

    public void setFkouchu(String fkouchu) {
        this.fkouchu = fkouchu;
    }

    public String getFacceptratio() {
        return facceptratio;
    }

    public void setFacceptratio(String facceptratio) {
        this.facceptratio = facceptratio;
    }

    public String getFwonid() {
        return fwonid;
    }

    public void setFwonid(String fwonid) {
        this.fwonid = fwonid;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getPicktime() {
        return picktime;
    }

    public void setPicktime(String picktime) {
        this.picktime = picktime;
    }

    public String getFfromaddress() {
        return ffromaddress;
    }

    public void setFfromaddress(String ffromaddress) {
        this.ffromaddress = ffromaddress;
    }

    public String getFtoaddress() {
        return ftoaddress;
    }

    public void setFtoaddress(String ftoaddress) {
        this.ftoaddress = ftoaddress;
    }

    public String getPagenum() {
        return pagenum;
    }

    public void setPagenum(String pagenum) {
        this.pagenum = pagenum;
    }

    public String getFweight() {
        return fweight;
    }

    public void setFweight(String fweight) {
        this.fweight = fweight;
    }
    /**
     * "fid": 273,
     "fownersendcarno": "DD201708090001",
     "fsendcarno": "D12220170825006",
     "fpickuptime": null,
     "fmanageid": 66,
     "ftaskstate": 5,
     "fvicecard": null,
     "fplateno": "苏G38115",
     "fdriverid": 122,
     "fshouldpay": 500,
     "fshouldpayoilcard": 500,
     "fshouldreturnmoney": 0,
     "ftransportno": "T2017080900001",
     "foilcardrecharge1": 500,
     "foilcardrecharge2": 0,
     "frechargerecordId1": null,
     "frechargerecordId2": null,
     "fstate": 2,
     "fsettlestate": 0,
     "fsettime": 1503983593000,
     "fMoneyRecharge2": 500,
     "fMoneyRecharge1": 0,
     "fkouchu": 0,
     "facceptratio": "50",
     "fwonid": 216,
     "goods": "方棒",
     "ttype": "9.6米",
     "picktime": "2019-08-07 16:00:00.0",
     "ffromaddress": "银川市灵武市",
     "ftoaddress": "西安市长安区",
     "fweight": 220,
     "pagenum": null,
     "fdrivername": "丁方圆"
     */
}
