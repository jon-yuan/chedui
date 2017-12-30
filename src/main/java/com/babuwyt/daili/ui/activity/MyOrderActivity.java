package com.babuwyt.daili.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.MyOrderAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.bean.WaybillTrackingBean;
import com.babuwyt.daili.entity.WaybillTrackingEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.ui.views.RecycleViewDivider;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;

import com.bigkoo.pickerview.TimePickerView;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2017/8/1.
 */
@ContentView(R.layout.activity_myorder)
public class MyOrderActivity extends BaseActivity implements MyOrderAdapter.OnRecyclerViewItemClickListener{
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;
    @ViewInject(R.id.tv_time_select)
    TextView tv_time_select;
    @ViewInject(R.id.layout_show)
    LinearLayout layout_show;
    @ViewInject(R.id.tv_select)
    TextView tv_select;
    @ViewInject(R.id.layout_starY)
    LinearLayout layout_starY;
    @ViewInject(R.id.tv_startM)
    TextView tv_startM;
    @ViewInject(R.id.tv_startY)
    TextView tv_startY;
    @ViewInject(R.id.layout_endY)
    LinearLayout layout_endY;
    @ViewInject(R.id.tv_endM)
    TextView tv_endM;
    @ViewInject(R.id.tv_endY)
    TextView tv_endY;
    @ViewInject(R.id.springview)
    SpringView springview;

    private int isStartOrEnd=-1; //1为开始时间  2为结束时间

    private RecyclerView.LayoutManager mManager;
    private List<WaybillTrackingEntity> mDatas;
    private MyOrderAdapter mAdapter;

    private boolean isshow=false;

    private String StartY;
    private String StartMD;

    private String EndY;
    private String EndMD;
    private SimpleDateFormat Y;
    private SimpleDateFormat MD;
    private String uptime;
    private String entime;
    private int pageNum=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
        getOrderRefresh();
    }



    private void init() {
        toolbar.setTitle(getString(R.string.my_order));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        springview.setType(SpringView.Type.FOLLOW);
        springview.setHeader(new DefaultHeader(this));
        springview.setFooter(new DefaultFooter(this));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getOrderRefresh();
            }

            @Override
            public void onLoadmore() {
                getOrderLoadMore();
            }
        });
    }

    @SuppressLint("NewApi")
    private void initView() {
        mDatas=new ArrayList<>();

        recyclerview.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.main_gray_line)));

        mManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(mManager);
        mAdapter=new MyOrderAdapter(this,mDatas);
        recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.MONTH, -1);//当前时间的上个月
        Date dateFrom = cl.getTime();
        Y=new SimpleDateFormat("yyyy年");
        MD=new SimpleDateFormat("MM月dd日");

        StartY=Y.format(dateFrom);
        StartMD=MD.format(dateFrom);
        uptime=StartY.replace("年","-")+StartMD.replace("月","-").replace("日","");
        Date date=new Date();
        EndY=Y.format(date);
        EndMD=MD.format(date);
        entime=EndY.replace("年","-")+EndMD.replace("月","-").replace("日","");
        tv_startY.setText(StartY);
        tv_startM.setText(StartMD);
        tv_endY.setText(EndY);
        tv_endM.setText(EndMD);


    }
    //获取我的订单
    private void getOrderRefresh(){
        pageNum=0;
        ArrayList<String> list=new ArrayList<String>();
        list.add(pageNum+"");
        list.add(SessionManager.getInstance().getUser().getFid());
        list.add(SessionManager.getInstance().getUser().getFforwardercode()+"");
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.MYORDER, list, new MyCallBack<WaybillTrackingBean>() {
            @Override
            public void onSuccess(WaybillTrackingBean baseBean) {
                loadingDialog.dissDialog();
                if (baseBean.isSuccess()){
                    mDatas.clear();
                    mDatas.addAll(baseBean.getObj());
                    mAdapter.notifyDataSetChanged();
                }else {
                    UHelper.showToast(MyOrderActivity.this,baseBean.getMsg());
                }
                springview.onFinishFreshAndLoad();

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                springview.onFinishFreshAndLoad();
                loadingDialog.dissDialog();
            }
        });
    }
    //加载更多我的订单
    private void getOrderLoadMore(){
        pageNum++;
        ArrayList<String> list=new ArrayList<String>();
        list.add(pageNum+"");
        list.add(SessionManager.getInstance().getUser().getFid());
        list.add(SessionManager.getInstance().getUser().getFforwardercode()+"");
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.MYORDER, list, new MyCallBack<WaybillTrackingBean>() {
            @Override
            public void onSuccess(WaybillTrackingBean baseBean) {
                loadingDialog.dissDialog();
                if (baseBean.isSuccess()){
                    mDatas.clear();
                    mDatas.addAll(baseBean.getObj());
                    mAdapter.notifyDataSetChanged();
                }else {
                    UHelper.showToast(MyOrderActivity.this,baseBean.getMsg());
                }
                springview.onFinishFreshAndLoad();

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                springview.onFinishFreshAndLoad();
                loadingDialog.dissDialog();
            }
        });
    }

    private void searchOrder(String uptime,String endtime){
        ArrayList<String> list=new ArrayList<String>();
        list.add("0");
        list.add(SessionManager.getInstance().getUser().getFid());
        list.add(SessionManager.getInstance().getUser().getFforwardercode()+"");
        list.add(uptime);
        list.add(endtime);
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.SEARCH_ORDER, list, new MyCallBack<WaybillTrackingBean>() {
            @Override
            public void onSuccess(WaybillTrackingBean baseBean) {
                loadingDialog.dissDialog();
                showTimeSelectView();
                mDatas.clear();
                if (baseBean.isSuccess() && baseBean.getObj()!=null){
                    mDatas.clear();
                    mDatas.addAll(baseBean.getObj());
                    mAdapter.notifyDataSetChanged();
                }else {
                    UHelper.showToast(MyOrderActivity.this,baseBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
            }
        });
    }

    @Event(value = {R.id.tv_time_select,R.id.tv_select,R.id.layout_starY,R.id.layout_endY})
    private void gete(View view){
        switch (view.getId()){
            case R.id.tv_time_select:
                showTimeSelectView();
                break;
            case R.id.tv_select:
                searchOrder(uptime,entime);
                break;

            case R.id.layout_starY:
                isStartOrEnd=1;
                showTimeSelect();
                break;
            case R.id.layout_endY:
                isStartOrEnd=2;
                showTimeSelect();
                break;
        }
    }

    private void showTimeSelectView(){
        if (isshow){
            isshow=false;
            layout_show.setVisibility(View.GONE);
        }else {
            isshow=true;
            layout_show.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent();
        intent.setClass(this,OrderDetailsActivity.class);
        intent.putExtra("fsendcarno",mDatas.get(position).getFsendcarno());
        intent.putExtra("fwonid",mDatas.get(position).getFwonid());
        startActivity(intent);
    }
    private void showTimeSelect(){

        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (isStartOrEnd==1){
                    StartY=Y.format(date);
                    StartMD=MD.format(date);
                    tv_startY.setText(StartY);
                    tv_startM.setText(StartMD);
                    uptime=StartY.replace("年","-")+StartMD.replace("月","-").replace("日","");
                }else {
                    EndY=Y.format(date);
                    EndMD=MD.format(date);
                    tv_endY.setText(EndY);
                    tv_endM.setText(EndMD);
                    entime=EndY.replace("年","-")+EndMD.replace("月","-").replace("日","");
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
           .setLabel("","","","","","")//默认设置为年月日时分秒
            .build();
//        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
}
