package com.babuwyt.daili.bean;

import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.entity.SijiEntity;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/30.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class SijiDetailsBean  extends BaseBean {
    private SijiEntity obj;

    public SijiEntity getObj() {
        return obj;
    }

    public void setObj(SijiEntity obj) {
        this.obj = obj;
    }
}
