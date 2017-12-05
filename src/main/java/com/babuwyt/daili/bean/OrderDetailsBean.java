package com.babuwyt.daili.bean;

import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.entity.LoadpickEntity;
import com.babuwyt.daili.entity.Tworktrack;
import com.babuwyt.daili.entity.HttpSelectBySendCarIdRes;
import com.babuwyt.daili.entity.pictureEntity;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/29.
 */

@HttpResponse(parser = JsonResponseParser.class)
public class OrderDetailsBean extends BaseBean {
    private OrderDetailsBaseBean obj;

    public OrderDetailsBaseBean getObj() {
        return obj;
    }

    public void setObj(OrderDetailsBaseBean obj) {
        this.obj = obj;
    }
}
