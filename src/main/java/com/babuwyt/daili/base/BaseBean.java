package com.babuwyt.daili.base;




import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/7/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class BaseBean {
    private boolean success;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
