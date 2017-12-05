package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/30.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class SijiEntity {
    private String fdriverid;
    private String fdrivername;
    private String carid;
    private String fplateno;
    private String fphonenum;
    private String fdrivenumber;

    public String getFdrivenumber() {
        return fdrivenumber;
    }

    public void setFdrivenumber(String fdrivenumber) {
        this.fdrivenumber = fdrivenumber;
    }

    public String getFdriverid() {
        return fdriverid;
    }

    public void setFdriverid(String fdriverid) {
        this.fdriverid = fdriverid;
    }

    public String getFdrivername() {
        return fdrivername;
    }

    public void setFdrivername(String fdrivername) {
        this.fdrivername = fdrivername;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getFplateno() {
        return fplateno;
    }

    public void setFplateno(String fplateno) {
        this.fplateno = fplateno;
    }

    public String getFphonenum() {
        return fphonenum;
    }

    public void setFphonenum(String fphonenum) {
        this.fphonenum = fphonenum;
    }
    private String fid;
    private String fcard;
    private String fphone;
    private String furgencylineman;
    private String flinemanphone;
    private String fdrivingcard;
    private String fdrivingtime;
    private String fparenttype;
    private String fparentid;
    private String fbankid;
    private String fbankcard;
    private String favatar;
    private String fcardpicture;
    private String fcardpicturerear;
    private String fhandcardpicture;
    private String fdrivingpicture;
    private String fcautionmoney;
    private String fmoneystate;
    private String fidrs;
    private String fremark;
    private String fisdelete;
    private String fcreateid;
    private String fcreatetime;
    private String fauditid;
    private String fname;
    private String fattribution;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFcard() {
        return fcard;
    }

    public void setFcard(String fcard) {
        this.fcard = fcard;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    public String getFurgencylineman() {
        return furgencylineman;
    }

    public void setFurgencylineman(String furgencylineman) {
        this.furgencylineman = furgencylineman;
    }

    public String getFlinemanphone() {
        return flinemanphone;
    }

    public void setFlinemanphone(String flinemanphone) {
        this.flinemanphone = flinemanphone;
    }

    public String getFdrivingcard() {
        return fdrivingcard;
    }

    public void setFdrivingcard(String fdrivingcard) {
        this.fdrivingcard = fdrivingcard;
    }

    public String getFdrivingtime() {
        return fdrivingtime;
    }

    public void setFdrivingtime(String fdrivingtime) {
        this.fdrivingtime = fdrivingtime;
    }

    public String getFparenttype() {
        return fparenttype;
    }

    public void setFparenttype(String fparenttype) {
        this.fparenttype = fparenttype;
    }

    public String getFparentid() {
        return fparentid;
    }

    public void setFparentid(String fparentid) {
        this.fparentid = fparentid;
    }

    public String getFbankid() {
        return fbankid;
    }

    public void setFbankid(String fbankid) {
        this.fbankid = fbankid;
    }

    public String getFbankcard() {
        return fbankcard;
    }

    public void setFbankcard(String fbankcard) {
        this.fbankcard = fbankcard;
    }

    public String getFavatar() {
        return favatar;
    }

    public void setFavatar(String favatar) {
        this.favatar = favatar;
    }

    public String getFcardpicture() {
        return fcardpicture;
    }

    public void setFcardpicture(String fcardpicture) {
        this.fcardpicture = fcardpicture;
    }

    public String getFcardpicturerear() {
        return fcardpicturerear;
    }

    public void setFcardpicturerear(String fcardpicturerear) {
        this.fcardpicturerear = fcardpicturerear;
    }

    public String getFhandcardpicture() {
        return fhandcardpicture;
    }

    public void setFhandcardpicture(String fhandcardpicture) {
        this.fhandcardpicture = fhandcardpicture;
    }

    public String getFdrivingpicture() {
        return fdrivingpicture;
    }

    public void setFdrivingpicture(String fdrivingpicture) {
        this.fdrivingpicture = fdrivingpicture;
    }

    public String getFcautionmoney() {
        return fcautionmoney;
    }

    public void setFcautionmoney(String fcautionmoney) {
        this.fcautionmoney = fcautionmoney;
    }

    public String getFmoneystate() {
        return fmoneystate;
    }

    public void setFmoneystate(String fmoneystate) {
        this.fmoneystate = fmoneystate;
    }

    public String getFidrs() {
        return fidrs;
    }

    public void setFidrs(String fidrs) {
        this.fidrs = fidrs;
    }

    public String getFremark() {
        return fremark;
    }

    public void setFremark(String fremark) {
        this.fremark = fremark;
    }

    public String getFisdelete() {
        return fisdelete;
    }

    public void setFisdelete(String fisdelete) {
        this.fisdelete = fisdelete;
    }

    public String getFcreateid() {
        return fcreateid;
    }

    public void setFcreateid(String fcreateid) {
        this.fcreateid = fcreateid;
    }

    public String getFcreatetime() {
        return fcreatetime;
    }

    public void setFcreatetime(String fcreatetime) {
        this.fcreatetime = fcreatetime;
    }

    public String getFauditid() {
        return fauditid;
    }

    public void setFauditid(String fauditid) {
        this.fauditid = fauditid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFattribution() {
        return fattribution;
    }

    public void setFattribution(String fattribution) {
        this.fattribution = fattribution;
    }

    /**
     * "fid": 109,
     "fdrivername": "张全蛋",
     "fcard": "61032319891006593X",
     "fphone": "18628602197",
     "furgencylineman": null,
     "flinemanphone": null,
     "fdrivingcard": "61032319891006593X",
     "fdrivingtime": null,
     "fparenttype": null,
     "fparentid": 66,
     "fbankid": 1,
     "fbankcard": "64816879764346946",
     "favatar": null,
     "fcardpicture": "/tms/driver/f24cc1f810f14ee9ae8244b9db9f0728.png",
     "fcardpicturerear": "/tms/driver/0e1b811cc87c4a4691ec5286fd333683.png",
     "fhandcardpicture": "2017/07/18109/7109983a7e1846c0a615b17838e2f23a.jpg",
     "fdrivingpicture": "2017/07/18109/0b77e12dc7e74f15bb0dadbcd79291f9.jpg",
     "fcautionmoney": 1000,
     "fmoneystate": 2,
     "fidrs": null,
     "fremark": null,
     "fisdelete": 1,
     "fcreateid": 1,
     "fcreatetime": 1500349236000,
     "fauditid": 1,
     "faudittime": 1500349242000,
     "fname": "13米",
     "fattribution": "赵铁柱",
     "fplateno": "陕A9527A"
     */

    /**
     *
     *    "fdriverid": 109,
     "fdrivername": "张全蛋",
     "carid": 92,
     "fplateno": "陕A9527A",
     "fphonenum": "18628602197"
     *
     */
}
