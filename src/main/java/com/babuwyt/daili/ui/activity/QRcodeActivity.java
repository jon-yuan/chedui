package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/7/11.
 * 二维码推广界面
 */
@ContentView(R.layout.activity_qrcode)
public class QRcodeActivity extends BaseActivity {
    @ViewInject(R.id.qr_code)
    ImageView qr_code;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getQRcode();
    }

    private void init(){
        toolbar.setTitle(getString(R.string.wodetuiguang));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void getQRcode(){
        String uri=BaseURL.BASE_URL+"driverBDhtml/gettuiguanghtml/"+SessionManager.getInstance().getUser().getFforwardercode();
        Map<String,String> map=new HashMap<String, String>();
        map.put("name", SessionManager.getInstance().getUser().getFforwardercode()+"");
        map.put("url",uri );
        //QRCODE
        XUtil.PostJson(BaseURL.QRCODE, map, new MyCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                if (result.isSuccess()){
                    x.image().bind(qr_code, "http://bbkj-1253538594.picgz.myqcloud.com/QRcode/"+SessionManager.getInstance().getUser().getFforwardercode()+".png",
                            XUtil.options(false));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }
}
