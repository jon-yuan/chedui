package com.babuwyt.daili.ui.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.OrderDetailsAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.bean.OrderDetailsBaseBean;
import com.babuwyt.daili.bean.OrderDetailsBean;
import com.babuwyt.daili.entity.LoadpickEntity;
import com.babuwyt.daili.entity.TOrderGoods;
import com.babuwyt.daili.entity.pictureEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.ui.views.CustomGridView;
import com.babuwyt.daili.ui.views.CustomListView;
import com.babuwyt.daili.ui.views.RecycleViewDivider;
import com.babuwyt.daili.utils.util.DateUtils;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/1.
 */
@ContentView(R.layout.activity_orderdetails)
public class OrderDetailsActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.tv_ordernum)
    TextView tv_ordernum;
    @ViewInject(R.id.tv_state)
    TextView tv_state;
    @ViewInject(R.id.tv_form)
    TextView tv_form;
    @ViewInject(R.id.tv_timeti)
    TextView tv_timeti;
    @ViewInject(R.id.tv_daodatime)
    TextView tv_daodatime;
    @ViewInject(R.id.tv_timexie)
    TextView tv_timexie;
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.tv_beizhu)
    TextView tv_beizhu;
    @ViewInject(R.id.image_zhuangzai)
    ImageView image_zhuangzai;
    @ViewInject(R.id.image_qianshou)
    ImageView image_qianshou;
    @ViewInject(R.id.image_state)
    ImageView image_state;


    @ViewInject(R.id.scrollView)
    ScrollView scrollView;
    @ViewInject(R.id.addresslistview)
    CustomListView addresslistview;

    @ViewInject(R.id.zhuangxie_gridview)
    CustomGridView zhuangxie_gridview;
    @ViewInject(R.id.qianshou_gridview)
    CustomGridView qianshou_gridview;

    @ViewInject(R.id.xiehuo_gridview)
    CustomGridView xiehuo_gridview;
    @ViewInject(R.id.image_xiehuo)
    ImageView image_xiehuo;


    private OrderDetailsAdapter zhAdapter;
    private ArrayList<pictureEntity> zhList;
    private OrderDetailsAdapter qsAdapter;
    private ArrayList<pictureEntity> qsList;

    private OrderDetailsAdapter xhAdapter;
    private ArrayList<pictureEntity> xhList;

    private addressAdapter addressAdapter;
    private ArrayList<LoadpickEntity> address;

    private String fsendcarno;
    private String fwonid;
    private boolean zhShow = true;
    private boolean qsShow = true;
    private boolean xhShow = true;
    private boolean stateShow = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fsendcarno = getIntent().getStringExtra("fsendcarno");
        fwonid = getIntent().getStringExtra("fwonid");

        init();
        initview();
        getHttp();
    }

    private void init() {
        toolbar.setTitle(getString(R.string.orderdetails));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

    }

    private void initview() {
        zhAdapter = new OrderDetailsAdapter(this);
        zhAdapter.setShow(zhShow);

        qsAdapter = new OrderDetailsAdapter(this);
        qsAdapter.setShow(qsShow);

        xhAdapter = new OrderDetailsAdapter(this);
        xhAdapter.setShow(xhShow);


        zhList = new ArrayList<pictureEntity>();
        qsList = new ArrayList<pictureEntity>();
        xhList = new ArrayList<pictureEntity>();


        zhAdapter.setmList(zhList);
        zhuangxie_gridview.setAdapter(zhAdapter);
        zhuangxie_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.setClass(OrderDetailsActivity.this,LookBigPictureActivity.class);
                intent.putExtra("index",i);
                intent.putExtra("list",zhList);
                startActivity(intent);
            }
        });

        qsAdapter.setmList(qsList);
        qianshou_gridview.setAdapter(qsAdapter);
        qianshou_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.setClass(OrderDetailsActivity.this,LookBigPictureActivity.class);
                intent.putExtra("index",i);
                intent.putExtra("list",qsList);
                startActivity(intent);
            }
        });

        xhAdapter.setmList(xhList);
        xiehuo_gridview.setAdapter(xhAdapter);
        xiehuo_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.setClass(OrderDetailsActivity.this,LookBigPictureActivity.class);
                intent.putExtra("index",i);
                intent.putExtra("list",xhList);
                startActivity(intent);
            }
        });

        addressAdapter = new addressAdapter();
        address = new ArrayList<LoadpickEntity>();
        addresslistview.setAdapter(addressAdapter);




    }

    private void setData(OrderDetailsBaseBean baseBean) {
        tv_ordernum.setText(fsendcarno);
        tv_timeti.setText(DateUtils.timedate(baseBean.getFactuptime()));
        tv_timexie.setText(DateUtils.timedate(baseBean.getFpickuptime()));
        tv_daodatime.setText(DateUtils.timedate(baseBean.getFarrivetime()));
        if (baseBean.getHttpTOrderGoodRes() != null && baseBean.getHttpTOrderGoodRes().size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (TOrderGoods goods : baseBean.getHttpTOrderGoodRes()) {
                buffer.append(goods.getFname() + ",");
            }
            tv_name.setText(buffer.toString());
        }
        switch (baseBean.getFtaskState()) {
            case 1:
                tv_state.setText("装货已签到");
                break;
            case 2:
                tv_state.setText("装货已拍照");
                break;
            case 3:
                tv_state.setText("已装货（装货照片已审核）");
                break;
            case 4:
                tv_state.setText("卸货已签到");
                break;
            case 5:
                tv_state.setText("已卸货（签收单照片已审核）");
                break;
            case 6:
                tv_state.setText("签收单已交回");
                break;
            case 7:
                tv_state.setText("签收单已确认");
                break;
            default:
                tv_state.setText("未作业");
                break;
        }
        address.clear();
        address.addAll(baseBean.getLoadpick());
        addressAdapter.notifyDataSetChanged();

        int ti = 0;
        int xie = 0;
        for (LoadpickEntity entity : address) {
            if (entity.getFromto().equals("FROM")) {
                ti += 1;
            } else {
                xie += 1;
            }
        }
        tv_beizhu.setText(baseBean.getFremark());
        for (pictureEntity entity : baseBean.getPicture()) {
            if (entity.getFstate() == 1) {
                zhList.add(entity);
            } else if (entity.getFstate() == 2){
                qsList.add(entity);
            }else if (entity.getFstate() == 3){
                xhList.add(entity);
            }
        }
        zhAdapter.notifyDataSetChanged();
        qsAdapter.notifyDataSetChanged();
        xhAdapter.notifyDataSetChanged();
    }

    private void getHttp() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(fsendcarno);
        list.add(fwonid);
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.PSELECT_DETAIL, list, new MyCallBack<OrderDetailsBean>() {
            @Override
            public void onSuccess(OrderDetailsBean baseBean) {
                loadingDialog.dissDialog();
                Log.d("===================",new Gson().toJson(baseBean));
                if (baseBean.isSuccess()) {
                    setData(baseBean.getObj());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
                Log.d("3231313213",ex+"");
            }
        });
    }

    @Event(value = {R.id.image_qianshou, R.id.image_zhuangzai, R.id.layout_state,R.id.image_xiehuo})
    private void gete(View v) {
        switch (v.getId()) {
            case R.id.image_zhuangzai:
                if (zhShow) {
                    zhShow = false;
                } else {
                    zhShow = true;
                }
                zhAdapter.setShow(zhShow);
                break;
            case R.id.image_qianshou:
                if (qsShow) {
                    qsShow = false;
                } else {
                    qsShow = true;
                }
                qsAdapter.setShow(qsShow);
                break;
                case R.id.image_xiehuo:
                if (xhShow) {
                    xhShow = false;
                } else {
                    xhShow = true;
                }
                xhAdapter.setShow(xhShow);
                break;
            case R.id.layout_state:
                Intent intent = new Intent();
                intent.setClass(this, StateGenzongActivity.class);
                intent.putExtra("fwonid", fwonid);
                startActivity(intent);
                break;
        }
    }

    class addressAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return address.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHoder hoder = null;
            if (view == null) {
                hoder = new ViewHoder();
                view = LayoutInflater.from(OrderDetailsActivity.this).inflate(R.layout.addressdetails_item, null);
                hoder.tv_form = view.findViewById(R.id.tv_form);
                hoder.tv_tixie = view.findViewById(R.id.tv_tixie);
                view.setTag(hoder);
            } else {
                hoder = (ViewHoder) view.getTag();
            }
            if (address.get(i).getFromto().equals("FROM")) {
                hoder.tv_tixie.setText("提货地");
            } else {
                hoder.tv_tixie.setText("卸货地");
            }
            hoder.tv_form.setText(address.get(i).getSsq() + address.get(i).getFaddress());
            return view;
        }

        class ViewHoder {
            TextView tv_form;
            TextView tv_tixie;
        }
    }
}
