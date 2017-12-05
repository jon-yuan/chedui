package com.babuwyt.daili.entity;



import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/6/30.
 */

@HttpResponse(parser = JsonResponseParser.class)
public class UserInfoEntity {
    private String fid;
    private String fuserid;
    private String floginname;
    private String fpassword;
    private long flogintime;
    private int flogincount;
    private String fforwardercode;
    private long fcreatetime;
    private String fiphone;
    private String webtoken;
    private String companyname;
    private String fusername;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFuserid() {
        return fuserid;
    }

    public void setFuserid(String fuserid) {
        this.fuserid = fuserid;
    }

    public String getFloginname() {
        return floginname;
    }

    public void setFloginname(String floginname) {
        this.floginname = floginname;
    }

    public String getFpassword() {
        return fpassword;
    }

    public void setFpassword(String fpassword) {
        this.fpassword = fpassword;
    }

    public long getFlogintime() {
        return flogintime;
    }

    public void setFlogintime(long flogintime) {
        this.flogintime = flogintime;
    }

    public int getFlogincount() {
        return flogincount;
    }

    public void setFlogincount(int flogincount) {
        this.flogincount = flogincount;
    }

    public String getFforwardercode() {
        return fforwardercode;
    }

    public void setFforwardercode(String fforwardercode) {
        this.fforwardercode = fforwardercode;
    }

    public long getFcreatetime() {
        return fcreatetime;
    }

    public void setFcreatetime(long fcreatetime) {
        this.fcreatetime = fcreatetime;
    }

    public String getFiphone() {
        return fiphone;
    }

    public void setFiphone(String fiphone) {
        this.fiphone = fiphone;
    }

    public String getWebtoken() {
        return webtoken;
    }

    public void setWebtoken(String webtoken) {
        this.webtoken = webtoken;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getFusername() {
        return fusername;
    }

    public void setFusername(String fusername) {
        this.fusername = fusername;
    }

    /**
     * "obj": {
     "fid": 126,
     "fuserid": null,
     "floginname": "HHRTest",
     "fpassword": null,
     "floginip": "192.168.2.17",
     "flogintime": 1503632415438,
     "flogincount": 124,
     "fforwardercode": "10001",
     "fcreatetime": null,
     "fisdelete": null,
     "fiphone": null,
     "webtoken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0VXNlciI6eyJmaWQiOjEyNiwiZnVzZXJpZCI6bnVsbCwiZmxvZ2lubmFtZSI6IkhIUlRlc3QiLCJmcGFzc3dvcmQiOm51bGwsImZsb2dpbmlwIjoiMTkyLjE2OC4yLjE3IiwiZmxvZ2ludGltZSI6MTUwMzYzMjQxNTQzOCwiZmxvZ2luY291bnQiOjEyNCwiZmZvcndhcmRlcmNvZGUiOiIxMDAwMSIsImZjcmVhdGV0aW1lIjpudWxsLCJmaXNkZWxldGUiOm51bGwsImZpcGhvbmUiOm51bGwsIndlYnRva2VuIjpudWxsLCJjb21wYW55bmFtZSI6IuWfjuW4guWQiOS8meS6ui3pk7blt50iLCJmdXNlcm5hbWUiOm51bGx9LCJpc3MiOiJ0bXNhcGl1c2VyIiwiZXhwIjoxNTA0OTI4NDE1LCJuYmYiOjE1MDM2MzI0MTV9._hEzkUtBM4C1wbkb7eWBlLARVX6zoGBJs7HonkFePrs",
     "companyname": "城市合伙人-银川",
     "fusername": null
     }


     */
}
