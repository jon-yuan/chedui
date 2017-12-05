package com.babuwyt.daili.bean;

import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.entity.SijiEntity;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/30.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class SijiListBean extends BaseBean {
    ArrayList<SijiEntity> obj;

    public ArrayList<SijiEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<SijiEntity> obj) {
        this.obj = obj;
    }
}
