package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.bean.SijiDetailsBean;
import com.babuwyt.daili.entity.SijiEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.utils.util.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/2.
 */
@ContentView(R.layout.activity_sijidetails)
public class SijiDetailsActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.tv_user)
    TextView tv_user;
    @ViewInject(R.id.tv_phone)
    TextView tv_phone;
    @ViewInject(R.id.tv_shenfenzheng)
    TextView tv_shenfenzheng;
    @ViewInject(R.id.img_shenfenzheng1)
    ImageView img_shenfenzheng1;
    @ViewInject(R.id.img_shenfenzheng2)
    ImageView img_shenfenzheng2;
    @ViewInject(R.id.img_shenfenzheng3)
    ImageView img_shenfenzheng3;
    @ViewInject(R.id.img_jiashizheng)
    ImageView img_jiashizheng;
    @ViewInject(R.id.img_xingshizheng)
    ImageView img_xingshizheng;
    @ViewInject(R.id.tv_chepai)
    TextView tv_chepai;
    @ViewInject(R.id.tv_chexing)
    TextView tv_chexing;
    @ViewInject(R.id.tv_jiashizheng)
    TextView tv_jiashizheng;
    @ViewInject(R.id.tv_suoyouren)
    TextView tv_suoyouren;
    @ViewInject(R.id.tv_xingshizheng)
    TextView tv_xingshizheng;
    private String id;
    private SijiEntity entity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getDetails();
    }

    private void init() {
        id = getIntent().getStringExtra("id");
        toolbar.setTitle(getString(R.string.siji_details));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getDetails() {
        ArrayList<String> list = new ArrayList<String>();

        list.add(id);
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.SIJI_DETAILS, list, new MyCallBack<SijiDetailsBean>() {
            @Override
            public void onSuccess(SijiDetailsBean sijiDetailsBean) {
                loadingDialog.dissDialog();
                if (sijiDetailsBean.isSuccess()) {
                    entity=sijiDetailsBean.getObj();
                    setData();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
            }
        });

    }

    private void setData() {
        tv_user.setText(entity.getFdrivername());
        tv_phone.setText(entity.getFphone());
        tv_shenfenzheng.setText(entity.getFdrivingcard());
        tv_chepai.setText(entity.getFplateno());
        tv_chexing.setText(entity.getFname());
        tv_jiashizheng.setText(entity.getFdrivingcard());
        tv_suoyouren.setText(entity.getFattribution());
        tv_xingshizheng.setText(entity.getFdrivenumber());
        x.image().bind(img_shenfenzheng1,BaseURL.BASE_IMAGE_URI+entity.getFcardpicture(),XUtil.options(ImageView.ScaleType.FIT_CENTER));
        x.image().bind(img_shenfenzheng2,BaseURL.BASE_IMAGE_URI+entity.getFcardpicturerear(),XUtil.options(ImageView.ScaleType.FIT_CENTER));
        x.image().bind(img_jiashizheng,BaseURL.BASE_IMAGE_URI+entity.getFhandcardpicture(),XUtil.options(ImageView.ScaleType.FIT_CENTER));
        x.image().bind(img_xingshizheng,BaseURL.BASE_IMAGE_URI+entity.getFdrivingpicture(),XUtil.options(ImageView.ScaleType.FIT_CENTER));
    }

}
