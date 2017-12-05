package com.babuwyt.daili.bean;

import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.entity.WaybillTrackingEntity;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/29.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class WaybillTrackingBean extends BaseBean {
    private ArrayList<WaybillTrackingEntity> obj;

    public ArrayList<WaybillTrackingEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<WaybillTrackingEntity> obj) {
        this.obj = obj;
    }
}
