package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/8/29.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class pictureEntity implements Serializable{
    private String fpicture;
    private int fstate;

    public String getFpicture() {
        return fpicture;
    }

    public void setFpicture(String fpicture) {
        this.fpicture = fpicture;
    }

    public int getFstate() {
        return fstate;
    }

    public void setFstate(int fstate) {
        this.fstate = fstate;
    }
    /**
     *   "fid": 583,
     "fsendcarid": 216,
     "fpicturename": "DD201708090001灞桥区-装货005",
     "fpicture": "imgtmsUpload/Penguins.jpg",
     "flongitude": null,
     "flatitude": null,
     "faddressno": 610111,
     "fuploadtime": 1503639102000,
     "fuserid": null,
     "faudittime": null,
     "fstate": 1,
     "fcheckstate": 1,
     "fSignNo": null,
     "fSignState": null,
     "fshortflag": null,
     "sendcarno": "DD201708090001",
     "address": "陕西省西安市灞桥区",
     "auditor": null
     */
}
