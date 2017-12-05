package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.Date;

/**
 * Created by lenovo on 2017/8/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class VersionEntity {

    private String furl;			//'下载地址',
    private String fadvphone ;		//'欢迎界面广告电话',
    private int fversion ;		//'app版本号 1.0 1.1 1.2..',
    private Integer ftype ;			//'app类型  1 : 司机app  2 : 现场app  3 : 合伙人app  4 : 货主app',
    private String fservicephone ;		//'客服咨询电话',
    private String fupdateinfo ;		//'更新信息',
    private  boolean fisforceupdate ;		//'是否强制更新',
    private Date fcreatetime ;		//'创建时间',
    private Date   fupdatetime ;		//'更新时间',

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public String getFadvphone() {
        return fadvphone;
    }

    public void setFadvphone(String fadvphone) {
        this.fadvphone = fadvphone;
    }

    public int getFversion() {
        return fversion;
    }

    public void setFversion(int fversion) {
        this.fversion = fversion;
    }

    public Integer getFtype() {
        return ftype;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    public String getFservicephone() {
        return fservicephone;
    }

    public void setFservicephone(String fservicephone) {
        this.fservicephone = fservicephone;
    }

    public String getFupdateinfo() {
        return fupdateinfo;
    }

    public void setFupdateinfo(String fupdateinfo) {
        this.fupdateinfo = fupdateinfo;
    }

    public boolean getFisforceupdate() {
        return fisforceupdate;
    }

    public void setFisforceupdate(boolean fisforceupdate) {
        this.fisforceupdate = fisforceupdate;
    }

    public Date getFcreatetime() {
        return fcreatetime;
    }

    public void setFcreatetime(Date fcreatetime) {
        this.fcreatetime = fcreatetime;
    }

    public Date getFupdatetime() {
        return fupdatetime;
    }

    public void setFupdatetime(Date fupdatetime) {
        this.fupdatetime = fupdatetime;
    }
}
