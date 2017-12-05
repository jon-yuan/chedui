package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/29.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class HttpSelectBySendCarIdRes {

    private String fpickuptime;
    private String fdelivergoodtime;
    private int ftaskstate;
    private int fstate;
    private ArrayList<TOrderGoods> tOrderGoods;

    public ArrayList<TOrderGoods> gettOrderGoods() {
        return tOrderGoods;
    }

    public void settOrderGoods(ArrayList<TOrderGoods> tOrderGoods) {
        this.tOrderGoods = tOrderGoods;
    }

    public String getFpickuptime() {
        return fpickuptime;
    }

    public void setFpickuptime(String fpickuptime) {
        this.fpickuptime = fpickuptime;
    }

    public String getFdelivergoodtime() {
        return fdelivergoodtime;
    }

    public void setFdelivergoodtime(String fdelivergoodtime) {
        this.fdelivergoodtime = fdelivergoodtime;
    }

    public int getFtaskstate() {
        return ftaskstate;
    }

    public void setFtaskstate(int ftaskstate) {
        this.ftaskstate = ftaskstate;
    }

    public int getFstate() {
        return fstate;
    }

    public void setFstate(int fstate) {
        this.fstate = fstate;
    }
}
