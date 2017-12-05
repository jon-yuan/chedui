package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.StateGenzongAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.bean.StateGenzongBean;
import com.babuwyt.daili.entity.Driver;
import com.babuwyt.daili.entity.StateGenzongEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/24.
 */
@ContentView(R.layout.activity_stategenzong)
public class StateGenzongActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.listview)
    ListView listview;
    TextView tv_dingdan;
    TextView tv_state;
    TextView tv_siji;
    TextView tv_dianhua;
    @ViewInject(R.id.layout_driver)
    LinearLayout layout_driver;
    private TextView tv_wancheng;
    private ArrayList<StateGenzongEntity> mList;
    private StateGenzongAdapter mAdapter;
    private String orderId;
    private Driver mdriver;
    private boolean state;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderId=getIntent().getStringExtra("fwonid");
        init();
        getState();
    }

    private void init() {

        toolbar.setTitle(getString(R.string.zhuangtaigenzong));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAdapter=new StateGenzongAdapter(this);
        mList=new ArrayList<StateGenzongEntity>();
        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);
        listview.addHeaderView(header());
        listview.addFooterView(footer());
    }

    private View header(){
        View header= LayoutInflater.from(this).inflate(R.layout.view_state_header,null);
        tv_dingdan=header.findViewById(R.id.tv_dingdan);
        tv_state=header.findViewById(R.id.tv_state);
        tv_siji=header.findViewById(R.id.tv_siji);
        tv_dianhua=header.findViewById(R.id.tv_dianhua);
        return header;
    }
    private View footer(){
        View footer= LayoutInflater.from(this).inflate(R.layout.view_state_footer,null);
        tv_wancheng=footer.findViewById(R.id.tv_wancheng);
        return footer;
    }
    private void getState(){
        ArrayList<String> list=new ArrayList<String>();
        list.add(orderId);
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.GETWORKTRACK, list, new MyCallBack<StateGenzongBean>() {
            @Override
            public void onSuccess(StateGenzongBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()){
                    mList.clear();
                    mList.addAll(result.getObj().getWorktrack());
                    state=result.getObj().isState();
                    mAdapter.notifyDataSetChanged();
                    mdriver=result.getObj().getDriver();
                    setData();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                Log.d("==合伙人==",ex+"");
                UHelper.showToast(StateGenzongActivity.this,getString(R.string.NET_ERROR));
            }
        });
    }

    private void setData(){
        if (mdriver==null){
            return;
        }
        tv_dingdan.setText("订单"+mdriver.getFsendcarno());
        tv_dianhua.setText("联系电话:"+mdriver.getFtel());
        tv_siji.setText("司机："+mdriver.getDrivername()+"  车牌号："+mdriver.getFplateno());
        tv_state.setText("已派车");
        tv_wancheng.setEnabled(state);
    }


}
