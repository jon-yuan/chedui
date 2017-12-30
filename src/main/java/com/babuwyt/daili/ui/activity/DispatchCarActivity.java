package com.babuwyt.daili.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.DispatchCarAdapter;
import com.babuwyt.daili.adapter.DispatchSelectSijiAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.bean.YundanDetailsBean;
import com.babuwyt.daili.entity.LoadpickEntity;
import com.babuwyt.daili.entity.SijiEntity;
import com.babuwyt.daili.entity.SystemPramer;
import com.babuwyt.daili.entity.TOrderGoodsEntity;
import com.babuwyt.daili.entity.YunDanDetailsgoodinfoEntity;
import com.babuwyt.daili.entity.YundanDetailsEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.ui.views.CustomListView;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    @ViewInject(R.id.listview)
    CustomListView listview;
    private DispatchCarAdapter mAdapter;
    private ArrayList<LoadpickEntity> mList;

    private boolean isOpen = false;//地址默认是关闭
    private String fid;
    private String mTitle;

    private DispatchSelectSijiAdapter adapter;
    private ArrayList<SijiEntity> list;
    private YundanDetailsEntity entity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fid = getIntent().getStringExtra("fid");
        mTitle = getIntent().getStringExtra("fendcarno");
        init();
        getHttp();
    }

    private void init() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText(mTitle);

        mAdapter = new DispatchCarAdapter(this);
        mList = new ArrayList<LoadpickEntity>();
        mAdapter.setmList(mList);
        customlistview.setAdapter(mAdapter);

        adapter = new DispatchSelectSijiAdapter(this);
        list = new ArrayList<SijiEntity>();
        for (int i = 0; i < 2; i++) {
            SijiEntity entity = new SijiEntity();
            list.add(entity);
        }
        adapter.setmList(list);
        listview.setAdapter(adapter);

        adapter.setCallBack(new DispatchSelectSijiAdapter.addSijiCallBack() {
            @Override
            public void addSiji(int ponsition) {
                Intent intent = new Intent();
                intent.setClass(DispatchCarActivity.this, MySijiActivity.class);
                intent.putExtra(MySijiActivity.MYSIJI_TYPE, MySijiActivity.SELECTSIJI);
                intent.putExtra("fdriverid0", list.get(0).getFdriverid());
                intent.putExtra("fdriverid1", list.get(1).getFdriverid());
                startActivityForResult(intent, ponsition);
            }

            @Override
            public void updateSiji(int ponsition) {
                Intent intent = new Intent();
                intent.setClass(DispatchCarActivity.this, MySijiActivity.class);
                intent.putExtra(MySijiActivity.MYSIJI_TYPE, MySijiActivity.SELECTSIJI);
                intent.putExtra("fdriverid0", list.get(0).getFdriverid());
                intent.putExtra("fdriverid1", list.get(1).getFdriverid());
                startActivityForResult(intent, ponsition);
            }

            @Override
            public void delSiji(int ponsition) {
                list.set(1, new SijiEntity());
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            SijiEntity entity = (SijiEntity) data.getSerializableExtra("sijientity");
            if (entity != null) {
                list.set(0, entity);
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            SijiEntity entity = (SijiEntity) data.getSerializableExtra("sijientity");
            if (entity != null) {
                list.set(1, entity);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Event(value = {R.id.btn_sd, R.id.btn_img, R.id.commit})
    private void getE(View v) {
        switch (v.getId()) {
            case R.id.btn_sd:
            case R.id.btn_img:
                isOpen = !isOpen;
                mAdapter.setOpen(isOpen);
                btn_sd.setText(isOpen == true ? getString(R.string.shouqi) : getString(R.string.chakan));
                btn_img.setImageResource(isOpen == true ? R.drawable.icon_less_blue1 : R.drawable.icon_less_blue);
                break;
            case R.id.commit:
                if (TextUtils.isEmpty(list.get(0).getFdrivername())) {
                    UHelper.showToast(DispatchCarActivity.this, getString(R.string.please_select_siji));
                    return;
                }
                isPay();
                break;
        }
    }

    private void isPay() {
        if (list.get(0).getFispay() == 2) {
            //结算司机
            Intent intent=new Intent(this,BaojiaActivity.class);
            intent.putExtra("ftruckid",list.get(0).getCarid());
            intent.putExtra("fmanageid",list.get(0).getFdriverid());
            intent.putExtra("ffmanageid",list.get(1).getFdriverid());
            intent.putExtra("fid", fid);
            startActivity(intent);
        } else if (list.get(0).getFispay() == 3) {
            //非结算司机
            getCommit();

//            Intent intent=new Intent(this,BaojiaActivity.class);
//            intent.putExtra("ftruckid",list.get(0).getCarid());
//            intent.putExtra("fmanageid",list.get(0).getFdriverid());
//            intent.putExtra("ffmanagfeid",list.get(1).getFdriverid());
//            intent.putExtra("fid", fid);
//            startActivity(intent);
        }
    }


    private void getCommit() {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("ftruckid",list.get(0).getCarid());
        map.put("fmanageid",list.get(0).getFdriverid());//主司机
        map.put("ffmanageid",list.get(1).getFdriverid());//副司机
        map.put("userid", SessionManager.getInstance().getUser().getFid());
        map.put("fid", fid);

        map.put("ffreight", 0);//报价
        map.put("fkouchu", 0);//扣除
        map.put("freight", 0);//现金
        map.put("foilcardValue", 0);//油卡金额
        map.put("ftotalCost", 0);//总金额
        map.put("fMoneyRecharge1", 0);//第一次现金
        map.put("fMoneyRecharge2", 0);//第二次现金
        map.put("foilcardrecharge1", 0);//第一次油卡
        map.put("foilcardrecharge2", 0);//第二次油卡
        map.put("fgiveoilcardValue", 0);//奖励
        map.put("facceptratio", 0);//油卡比例
        Log.d("==参数=",new Gson().toJson(map));
        loadingDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.PUBLISHSENDCAR,map, new MyCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()) {
                    finish();
                }
                UHelper.showToast(DispatchCarActivity.this, result.getMsg());
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
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
                    entity = baseBean.getObj();
                    setData();
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

    private void setData() {
        if (entity == null) {
            return;
        }
        tv_times.setText(entity.getFdelivergoodtime());
        tv_timef.setText(entity.getFarrivetime());
        tv_address_f.setText(entity.getFscity());
        tv_address_t.setText(entity.getFucity());


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
        tv_weight.setText(zl + "");
        tv_tiji.setText(tiji + "");
        tv_num.setText(tuoshu + "");
        StringBuilder name = new StringBuilder();
        ArrayList<String> namelist = new ArrayList<String>();
        if (entity.gettOrderGoods() != null && entity.gettOrderGoods().size() != 0) {
            for (TOrderGoodsEntity entity : entity.gettOrderGoods()) {
                if (!namelist.contains(entity.getFname())) {
                    namelist.add(entity.getFname());
                    name.append(entity.getFname() + ",");
                }
            }
        }
        tv_name.setText(name);
        tv_baozhuangshuliang.setText(name);
        mList.addAll(entity.getLoadpick());
        mAdapter.notifyDataSetChanged();
        int ti = 0;
        int xie = 0;
        for (LoadpickEntity entity : entity.getLoadpick()) {
            if (entity.getFseq() == 1) {
                ti++;
            } else {
                xie++;
            }
        }
        tv_shuoming.setText(ti + getString(R.string.ti) + xie + getString(R.string.xie));
    }
}
