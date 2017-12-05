package com.babuwyt.daili.entity;



import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/6/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class PersonInfoEntity {
    //身份证号码
    private String idcard;
    //身份证号码所在地区
    private String att;
    //出生日期
    private String born;
    //性别
    private String sex;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
