package com.babuwyt.daili.entity;



import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/9/26.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class PicEntity implements Serializable {
    private String picture;
    private String fpicture;
    private int type;
    private String fSignNo;

    public String getFpicture() {
        return fpicture;
    }

    public void setFpicture(String fpicture) {
        this.fpicture = fpicture;
    }

    public String getfSignNo() {
        return fSignNo;
    }

    public void setfSignNo(String fSignNo) {
        this.fSignNo = fSignNo;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    /**
     * "fid": 633,
     "fsendcarid": 227,
     "fpicturename": "DD201709010000灞桥区-装货002",
     "fpicture": "wyt1506492503.jpg",
     "flongitude": "109.068422",
     "flatitude": "34.372072",
     "faddressno": 610111,
     "fuploadtime": 1506492506000,
     "fuserid": null,
     "faudittime": null,
     "fstate": 1,
     "fcheckstate": 1,
     "fSignNo": null,
     "fSignState": null,
     "fshortflag": null,
     "sendcarno": "DD201709010000",
     "address": "陕西省西安市灞桥区",
     "auditor": null
     */
}
