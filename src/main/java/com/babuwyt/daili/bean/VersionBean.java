package com.babuwyt.daili.bean;
import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.entity.VersionEntity;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class VersionBean extends BaseBean {
    private VersionEntity obj;

    public VersionEntity getObj() {
        return obj;
    }

    public void setObj(VersionEntity obj) {
        this.obj = obj;
    }
}
