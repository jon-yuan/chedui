package com.babuwyt.daili.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.YundanDetailsAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.SessionManager;
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

import org.w3c.dom.ProcessingInstruction;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/24.
 * 运单详情
 */
@ContentView(R.layout.activity_yundandetails)
public class YundanDetailsActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.tv_tupaiche)
    TextView tv_tupaiche;
    @ViewInject(R.id.tv_state)
    TextView tv_state;
    @ViewInject(R.id.tv_num)
    TextView tv_num;
    @ViewInject(R.id.tv_luxian)
    TextView tv_luxian;
    @ViewInject(R.id.tv_chexing)
    TextView tv_chexing;
    @ViewInject(R.id.tv_zhongliang)
    TextView tv_zhongliang;
    @ViewInject(R.id.tv_mingcheng)
    TextView tv_mingcheng;
    @ViewInject(R.id.tv_tuoshu)
    TextView tv_tuoshu;
    @ViewInject(R.id.tv_tiji)
    TextView tv_tiji;
    @ViewInject(R.id.tv_baozhi)
    TextView tv_baozhi;
    @ViewInject(R.id.tv_time)
    TextView tv_time;
    @ViewInject(R.id.beizhu)
    TextView beizhu;



    @ViewInject(R.id.listview)
    CustomListView listview;
    private YundanDetailsAdapter mAdapter;
    private String fid;
    private ArrayList<LoadpickEntity> list;
    private YundanDetailsEntity entity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fid = getIntent().getStringExtra("fid");

        init();
        getHttp();
    }

    private void init() {
        toolbar.setTitle(getString(R.string.yundanxiangqing));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list = new ArrayList<LoadpickEntity>();
        mAdapter = new YundanDetailsAdapter(this, list);
        listview.setAdapter(mAdapter);

        tv_tupaiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (entity==null){
                    return;
                }
                Intent intent=new Intent();
                intent.setClass(YundanDetailsActivity.this,MySijiActivity.class);
                intent.putExtra(MySijiActivity.MYSIJI_TYPE,MySijiActivity.SELECTSIJI);
                intent.putExtra(MySijiActivity.MYSIJI_ADDRESS,entity.getFsprovince()+entity.getFscity()+entity.getFsdistrict()+entity.getFsdisplay());
                intent.putExtra("fid",fid);
                intent.putExtra("shijian",entity.getFpickuptime());
                intent.putExtra("xianlu",entity.getFfromaddress()+entity.getFtoaddress());

                startActivity(intent);
            }
        });
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
                    setData(baseBean);
                } else {
                    UHelper.showToast(YundanDetailsActivity.this, baseBean.getMsg());
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
                UHelper.showToast(YundanDetailsActivity.this, getString(R.string.NET_ERROR));
            }
        });
    }

    private void setData(YundanDetailsBean bean) {
        entity = bean.getObj();

        tv_state.setText(setState(entity.getFtaskstate()));
        tv_num.setText(entity.getFownersendcarno());
        tv_luxian.setText(entity.getFscity() + " - " + entity.getFucity());
        tv_chexing.setText(entity.getFtrucktypename());
        int zl = 0;
        double tiji = 0;
        int tuoshu = 0;
        if (entity.getTordergoodinfo() != null && entity.getTordergoodinfo().size() != 0) {
            for (YunDanDetailsgoodinfoEntity entity : entity.getTordergoodinfo()) {
                zl += entity.getFroughweight();
                tiji += entity.getFbulk();
                tuoshu += entity.getFpacknum();
            }
        }
        tv_zhongliang.setText(zl + "");
        tv_tiji.setText(tiji + "");
        tv_tuoshu.setText(tuoshu + "");
        StringBuilder name = new StringBuilder();
        ArrayList<String> namelist = new ArrayList<String>();
        if (entity.gettOrderGoods() != null && entity.gettOrderGoods().size() != 0) {
            for (TOrderGoodsEntity entity : entity.gettOrderGoods()) {
                if (!namelist.contains(entity.getFname())) {
                    namelist.add(entity.getFname());
                    name.append(entity.getFname() + ",");
                }
            }
//                tiji+=entity.
        }
        tv_mingcheng.setText(name.toString());
        tv_baozhi.setText(entity.getFvalue() + "");
        tv_time.setText(entity.getFdelivergoodtime());

        if (entity.getLoadpick() != null) {
            list.addAll(entity.getLoadpick());
            mAdapter.notifyDataSetChanged();
        }


    }

    private String setState(int state) {
        String s="";
        switch (state) {
            case 0:
                s=getString(R.string.daipaiche);
                break;
            case 1:
                s=getString(R.string.zhuanghuoyiqiandao);
                break;
            case 2:
                s=getString(R.string.zhuanghuoyipaizhao);
                break;
            case 3:
                s=getString(R.string.yizhuanghuo);
                break;
            case 4:
                s=getString(R.string.xiehuo)+"已签到";
                break;
            case 5:
                s="已卸货(签收单照片已审核)";
                break;
            case 6:
                s="签收单已交回";
                break;
            case 7:
                s="签收单已确认";
                break;
            //1装货已签到、2装货已拍照、3已装货（装货照片已审核）、4卸货已签到、5已卸货（签收单照片已审核）、6签收单已交回、7签收单已确认
        }

        return s;
    }

}
