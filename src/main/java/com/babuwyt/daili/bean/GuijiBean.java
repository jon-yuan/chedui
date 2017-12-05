package com.babuwyt.daili.bean;

import android.widget.ProgressBar;

import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.entity.LoadpickEntity;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/30.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class GuijiBean extends BaseBean {
   private LoadpickEntity obj;

    public LoadpickEntity getObj() {
        return obj;
    }

    public void setObj(LoadpickEntity obj) {
        this.obj = obj;
    }
}
