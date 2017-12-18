package com.babuwyt.daili.bean;

import com.babuwyt.daili.entity.HttpSelectBySendCarIdRes;
import com.babuwyt.daili.entity.LoadpickEntity;
import com.babuwyt.daili.entity.TOrderGoods;
import com.babuwyt.daili.entity.Tworktrack;
import com.babuwyt.daili.entity.pictureEntity;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/29.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderDetailsBaseBean {
    private long fpickuptime;
    private long factuptime;
    private int ftaskState;
    private long farrivetime;
    private ArrayList<LoadpickEntity> loadpick;
    private ArrayList<pictureEntity> picturelist;
    private ArrayList<Tworktrack> worktrack;
    private ArrayList<TOrderGoods> httpTOrderGoodRes;

    public long getFarrivetime() {
        return farrivetime;
    }

    public void setFarrivetime(long farrivetime) {
        this.farrivetime = farrivetime;
    }

    public int getFtaskState() {
        return ftaskState;
    }

    public void setFtaskState(int ftaskState) {
        this.ftaskState = ftaskState;
    }

    public ArrayList<LoadpickEntity> getLoadpick() {
        return loadpick;
    }

    public void setLoadpick(ArrayList<LoadpickEntity> loadpick) {
        this.loadpick = loadpick;
    }

    public ArrayList<pictureEntity> getPicture() {
        return picturelist;
    }

    public void setPicture(ArrayList<pictureEntity> picture) {
        this.picturelist = picture;
    }

    public ArrayList<Tworktrack> getWorktrack() {
        return worktrack;
    }

    public void setWorktrack(ArrayList<Tworktrack> worktrack) {
        this.worktrack = worktrack;
    }

    public long getFpickuptime() {
        return fpickuptime;
    }

    public void setFpickuptime(long fpickuptime) {
        this.fpickuptime = fpickuptime;
    }

    public long getFactuptime() {
        return factuptime;
    }

    public void setFactuptime(long factuptime) {
        this.factuptime = factuptime;
    }

    public ArrayList<pictureEntity> getPicturelist() {
        return picturelist;
    }

    public void setPicturelist(ArrayList<pictureEntity> picturelist) {
        this.picturelist = picturelist;
    }

    public ArrayList<TOrderGoods> getHttpTOrderGoodRes() {
        return httpTOrderGoodRes;
    }

    public void setHttpTOrderGoodRes(ArrayList<TOrderGoods> httpTOrderGoodRes) {
        this.httpTOrderGoodRes = httpTOrderGoodRes;
    }
    //    public HttpSelectBySendCarIdRes getHttpSelectBySendCarIdRes() {
//        return httpSelectBySendCarIdRes;
//    }
//
//    public void setHttpSelectBySendCarIdRes(HttpSelectBySendCarIdRes httpSelectBySendCarIdRes) {
//        this.httpSelectBySendCarIdRes = httpSelectBySendCarIdRes;
//    }
    /**
     *  "sendcarno": "D12220170825006",
     "fpickuptime": null,
     "factuptime": 1503639103000,
     */
}
