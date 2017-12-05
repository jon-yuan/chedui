package com.babuwyt.daili.bean;

import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.entity.SystemPramer;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/31.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class SystemPramerBean extends BaseBean {
    private SystemPramer obj;

    public SystemPramer getObj() {
        return obj;
    }

    public void setObj(SystemPramer obj) {
        this.obj = obj;
    }
}
