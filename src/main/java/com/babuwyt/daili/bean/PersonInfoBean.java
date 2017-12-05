package com.babuwyt.daili.bean;

import com.babuwyt.daili.entity.PersonInfoEntity;
import com.babuwyt.daili.entity.UserInfoEntity;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/6/28.
 */

@HttpResponse(parser = JsonResponseParser.class)
public class PersonInfoBean{
    private boolean success;
    private UserInfoEntity obj;
    //失败时出现
    private String msg;
    //失败时出现
    private String msgid;
    public boolean isSuccess() {
        return success;
    }

    public UserInfoEntity getObj() {
        return obj;
    }

    public void setObj(UserInfoEntity obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsgid() {
        return msgid;
    }
    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}
