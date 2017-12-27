package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.DispatchCarAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.bean.YundanDetailsBean;
import com.babuwyt.daili.entity.LoadpickEntity;
import com.babuwyt.daili.entity.TOrderGoodsEntity;
import com.babuwyt.daili.entity.YunDanDetailsgoodinfoEntity;
import com.babuwyt.daili.entity.YundanDetailsEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.ui.views.CustomListView;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/12/27.
 */
@ContentView(R.layout.activity_dispatchcar)
public class DispatchCarActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.title)
    TextView title;
    @ViewInject(R.id.btn_sd)
    TextView btn_sd;
    @ViewInject(R.id.btn_img)
    ImageView btn_img;
    @ViewInject(R.id.tv_shuoming)
    TextView tv_shuoming;
    @ViewInject(R.id.tv_timef)
    TextView tv_timef;
    @ViewInject(R.id.tv_times)
    TextView tv_times;
    @ViewInject(R.id.tv_address_f)
    TextView tv_address_f;
    @ViewInject(R.id.tv_address_t)
    TextView tv_address_t;
    @ViewInject(R.id.tv_weight)
    TextView tv_weight;
    @ViewInject(R.id.tv_tiji)
    TextView tv_tiji;
    @ViewInject(R.id.tv_num)
    TextView tv_num;
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.tv_baozhuangshuliang)
    TextView tv_baozhuangshuliang;
    @ViewInject(R.id.customlistview)
    CustomListView customlistview;
    private DispatchCarAdapter mAdapter;
    private ArrayList<LoadpickEntity> mList;

    private boolean isOpen=false;//地址默认是关闭
    private String fid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fid = getIntent().getStringExtra("fid");
        init();
        getHttp();
    }

    private void init() {
        mAdapter=new DispatchCarAdapter(this);
        mList=new ArrayList<LoadpickEntity>();
        mAdapter.setmList(mList);
        customlistview.setAdapter(mAdapter);
    }

    @Event(value = {R.id.btn_sd,R.id.btn_img})
    private void getE(View v){
        switch (v.getId()){
            case R.id.btn_sd:
            case R.id.btn_img:
                isOpen=!isOpen;
                mAdapter.setOpen(isOpen);
                btn_sd.setText(isOpen==true?getString(R.string.shouqi):getString(R.string.chakan));
                btn_img.setImageResource(isOpen==true?R.drawable.icon_less_blue1:R.drawable.icon_less_blue);
                break;
        }
    }
    private void getHttp() {
        ArrayList<String> list = new ArrayList<>();
        list.add(fid);
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.PSELECTBYSENDCARID, list, new MyCallBack<YundanDetailsBean>() {
            @Override
            public void onSuccess(YundanDetailsBean baseBean) {
                loadingDialog.dissDialog();
                if (baseBean.isSuccess()) {
                    setData(baseBean.getObj());
                } else {
                    UHelper.showToast(DispatchCarActivity.this, baseBean.getMsg());
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
                UHelper.showToast(DispatchCarActivity.this, getString(R.string.NET_ERROR));
            }
        });
    }
    private void setData(YundanDetailsEntity yundanDetailsEntity){

        tv_times.setText(yundanDetailsEntity.getFdelivergoodtime());
        tv_timef.setText(yundanDetailsEntity.getFarrivetime());
        tv_address_f.setText(yundanDetailsEntity.getFscity());
        tv_address_t.setText(yundanDetailsEntity.getFucity());


        int zl = 0;
        double tiji = 0;
        int tuoshu = 0;
        if (yundanDetailsEntity.getTordergoodinfo() != null && yundanDetailsEntity.getTordergoodinfo().size() != 0) {
            for (YunDanDetailsgoodinfoEntity entity : yundanDetailsEntity.getTordergoodinfo()) {
                zl += entity.getFroughweight();
                tiji += entity.getFbulk();
                tuoshu += entity.getFpacknum();
            }
        }
        tv_weight.setText(zl + "");
        tv_tiji.setText(tiji + "");
        tv_num.setText(tuoshu + "");
        StringBuilder name = new StringBuilder();
        ArrayList<String> namelist = new ArrayList<String>();
        if (yundanDetailsEntity.gettOrderGoods() != null && yundanDetailsEntity.gettOrderGoods().size() != 0) {
            for (TOrderGoodsEntity entity : yundanDetailsEntity.gettOrderGoods()) {
                if (!namelist.contains(entity.getFname())) {
                    namelist.add(entity.getFname());
                    name.append(entity.getFname() + ",");
                }
            }
        }
        tv_name.setText(name);
        tv_baozhuangshuliang.setText(name);
        mList.addAll(yundanDetailsEntity.getLoadpick());
        mAdapter.notifyDataSetChanged();
        int ti=0;
        int xie=0;
        for (LoadpickEntity entity:yundanDetailsEntity.getLoadpick()){
            if (entity.getFseq()==1){
                ti++;
            }else {
                xie++;
            }
        }
        tv_shuoming.setText(ti+getString(R.string.ti)+xie+getString(R.string.xie));
    }
}
