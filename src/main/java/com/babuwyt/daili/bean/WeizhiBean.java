package com.babuwyt.daili.bean;


import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.entity.WeizhiEntity;
import com.babuwyt.daili.entity.WeizhiObjEntity;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/7/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class WeizhiBean extends BaseBean {

    private WeizhiObjEntity obj;

    public WeizhiObjEntity getEntity() {
        return obj;
    }

    public void setEntity(WeizhiObjEntity entity) {
        this.obj = entity;
    }
}
