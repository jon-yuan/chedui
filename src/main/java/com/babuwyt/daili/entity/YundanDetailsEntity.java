package com.babuwyt.daili.entity;


import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/25.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class YundanDetailsEntity extends BaseBean {


    private String fid;
    private String fownersendcarno;
    private String fsendcarno;
    private String fpickuptime;
    private String fmanageid;
    private int ftaskstate;
    private String fvicecard;
    private String fplateno;
    private String fdriverid;
    private String fshouldpay;
    private String fshouldpayoilcard;
    private String fshouldreturnmoney;
    private String ftransportno;
    private String foilcardrecharge1;
    private String foilcardrecharge2;
    private String frechargerecordId1;
    private String frechargerecordId2;
    private int fstate;
    private int fsettlestate;
    private long fsettime;
    private String fMoneyRecharge2;
    private String fMoneyRecharge1;
    private String fkouchu;
    private String facceptratio;
    private int fwonid;
    private String goods;
    private String ttype;
    private String picktime;
    private String ffromaddress;
    private String ftoaddress;
    private String fweight;
    private int pagenum;
    private String fdrivername;
    private String fsprovince;
    private String fscity;
    private String fsdistrict;
    private String fsdisplay;
    private String fuprovince;
    private String fucity;
    private String fudistrict;
    private String fudisplay;
    private String ftrucktypename;
    private String forwardername;
    private String fownersendcarid;
    private String fbidstate;
    private String foilcardrechargestate1;
    private String foilcardrechargestate2;
    private String fdelivergoodtime;
    private String farrivetime;
    private double fvalue;
    private ArrayList<YunDanDetailsgoodinfoEntity> tordergoodinfo;
    private ArrayList<TOrderGoodsEntity> tOrderGoods;

    public ArrayList<YunDanDetailsgoodinfoEntity> getTordergoodinfo() {
        return tordergoodinfo;
    }

    public void setTordergoodinfo(ArrayList<YunDanDetailsgoodinfoEntity> tordergoodinfo) {
        this.tordergoodinfo = tordergoodinfo;
    }

    public ArrayList<TOrderGoodsEntity> gettOrderGoods() {
        return tOrderGoods;
    }

    public void settOrderGoods(ArrayList<TOrderGoodsEntity> tOrderGoods) {
        this.tOrderGoods = tOrderGoods;
    }

    private ArrayList<LoadpickEntity> loadpick;

    public String getFid() {
        return fid;
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

    public int getFstate() {
        return fstate;
    }

    public void setFstate(int fstate) {
        this.fstate = fstate;
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

    public int getFwonid() {
        return fwonid;
    }

    public void setFwonid(int fwonid) {
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

    public String getFweight() {
        return fweight;
    }

    public void setFweight(String fweight) {
        this.fweight = fweight;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public String getFdrivername() {
        return fdrivername;
    }

    public void setFdrivername(String fdrivername) {
        this.fdrivername = fdrivername;
    }

    public String getFsprovince() {
        return fsprovince;
    }

    public void setFsprovince(String fsprovince) {
        this.fsprovince = fsprovince;
    }

    public String getFscity() {
        return fscity;
    }

    public void setFscity(String fscity) {
        this.fscity = fscity;
    }

    public String getFsdistrict() {
        return fsdistrict;
    }

    public void setFsdistrict(String fsdistrict) {
        this.fsdistrict = fsdistrict;
    }

    public String getFsdisplay() {
        return fsdisplay;
    }

    public void setFsdisplay(String fsdisplay) {
        this.fsdisplay = fsdisplay;
    }

    public String getFuprovince() {
        return fuprovince;
    }

    public void setFuprovince(String fuprovince) {
        this.fuprovince = fuprovince;
    }

    public String getFucity() {
        return fucity;
    }

    public void setFucity(String fucity) {
        this.fucity = fucity;
    }

    public String getFudistrict() {
        return fudistrict;
    }

    public void setFudistrict(String fudistrict) {
        this.fudistrict = fudistrict;
    }

    public String getFudisplay() {
        return fudisplay;
    }

    public void setFudisplay(String fudisplay) {
        this.fudisplay = fudisplay;
    }

    public String getFtrucktypename() {
        return ftrucktypename;
    }

    public void setFtrucktypename(String ftrucktypename) {
        this.ftrucktypename = ftrucktypename;
    }

    public String getForwardername() {
        return forwardername;
    }

    public void setForwardername(String forwardername) {
        this.forwardername = forwardername;
    }

    public String getFownersendcarid() {
        return fownersendcarid;
    }

    public void setFownersendcarid(String fownersendcarid) {
        this.fownersendcarid = fownersendcarid;
    }

    public String getFbidstate() {
        return fbidstate;
    }

    public void setFbidstate(String fbidstate) {
        this.fbidstate = fbidstate;
    }

    public String getFoilcardrechargestate1() {
        return foilcardrechargestate1;
    }

    public void setFoilcardrechargestate1(String foilcardrechargestate1) {
        this.foilcardrechargestate1 = foilcardrechargestate1;
    }

    public String getFoilcardrechargestate2() {
        return foilcardrechargestate2;
    }

    public void setFoilcardrechargestate2(String foilcardrechargestate2) {
        this.foilcardrechargestate2 = foilcardrechargestate2;
    }

    public String getFdelivergoodtime() {
        return fdelivergoodtime;
    }

    public void setFdelivergoodtime(String fdelivergoodtime) {
        this.fdelivergoodtime = fdelivergoodtime;
    }

    public String getFarrivetime() {
        return farrivetime;
    }

    public void setFarrivetime(String farrivetime) {
        this.farrivetime = farrivetime;
    }

    public double getFvalue() {
        return fvalue;
    }

    public void setFvalue(double fvalue) {
        this.fvalue = fvalue;
    }

    public ArrayList<LoadpickEntity> getLoadpick() {
        return loadpick;
    }

    public void setLoadpick(ArrayList<LoadpickEntity> loadpick) {
        this.loadpick = loadpick;
    }

    /**
     *
     *     "fid": 99,
     "fownersendcarno": "DD201707120013",
     "fsendcarno": null,
     "fpickuptime": null,
     "fmanageid": null,
     "ftaskstate": 0,
     "fvicecard": null,
     "fplateno": null,
     "fdriverid": null,
     "fshouldpay": null,
     "fshouldpayoilcard": null,
     "fshouldreturnmoney": null,
     "ftransportno": "T2017071200005",
     "foilcardrecharge1": null,
     "foilcardrecharge2": null,
     "frechargerecordId1": null,
     "frechargerecordId2": null,
     "fstate": 1,
     "fsettlestate": 0,
     "fsettime": null,
     "fMoneyRecharge2": null,
     "fMoneyRecharge1": null,
     "fkouchu": null,
     "facceptratio": null,

     "fsprovince": "宁夏回族自治区",
     "fscity": "银川市",
     "fsdistrict": "西夏区",
     "fsdisplay": "文萃南街战略新兴材料加工区14#",
     "fuprovince": "江苏省",
     "fucity": "泰州市",
     "fudistrict": "海陵区",
     "fudisplay": "梅兰东路海陵工业园共建区18号楼",
     "ftrucktypename": "17.5米",
     "fdrivername": null,
     "forwardername": null,
     "fownersendcarid": "45",
     "fbidstate": null,
     "foilcardrechargestate1": null,
     "foilcardrechargestate2": null,
     "fdelivergoodtime": "2017-07-12 10:43:00.0",
     "farrivetime": null,
     "fvalue": "906756.60",
     *
     */



}
