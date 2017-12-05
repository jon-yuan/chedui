package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.WayBillAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.bean.WaybillTrackingBean;
import com.babuwyt.daili.entity.WaybillTrackingEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/22.
 */
@ContentView(R.layout.activity_waybilltracking)
public class WaybillTrackingActivity extends BaseActivity{
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;
    @ViewInject(R.id.springview)
    SpringView springview;
    //    @ViewInject(R.id.load_more_list_view_container)
//    LoadMoreListViewContainer mLoadMoreListViewContainer;
    private RecyclerView.LayoutManager mManager;
    private WayBillAdapter mAdapter;
    private ArrayList<WaybillTrackingEntity> mList;
    private int pageNum = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getHttpRefresh();
    }

    private void init() {
        toolbar.setTitle(getString(R.string.yundangenzong));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mList = new ArrayList<WaybillTrackingEntity>();
        mAdapter = new WayBillAdapter(this, mList);
        mManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(mManager);
        recyclerview.setAdapter(mAdapter);
        springview.setType(SpringView.Type.FOLLOW);
        springview.setHeader(new DefaultHeader(this));
        springview.setFooter(new DefaultFooter(this));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getHttpRefresh();
            }

            @Override
            public void onLoadmore() {
                getHttpLoadMore();
            }
        });
    }


    private void getHttpRefresh() {
        pageNum=0;
        ArrayList<String> list = new ArrayList<String>();
        list.add(pageNum + "");
        list.add(SessionManager.getInstance().getUser().getFid());
        list.add(SessionManager.getInstance().getUser().getFforwardercode() + "");
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.FINDCAR, list, new MyCallBack<WaybillTrackingBean>() {
            @Override
            public void onSuccess(WaybillTrackingBean baseBean) {
                loadingDialog.dissDialog();
                Log.d("22222222",new Gson().toJson(baseBean));
                if (baseBean.isSuccess()) {
                    if (mList != null) {
                        mList.clear();
                        mList.addAll(baseBean.getObj());
                        mAdapter.notifyDataSetChanged();
                    }
                }else {
                    UHelper.showToast(WaybillTrackingActivity.this,baseBean.getMsg());
                }
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }

    private void getHttpLoadMore() {
        pageNum++;
        ArrayList<String> list = new ArrayList<String>();
        list.add(pageNum + "");
        list.add(SessionManager.getInstance().getUser().getFid());
        list.add(SessionManager.getInstance().getUser().getFforwardercode() + "");
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.FINDCAR, list, new MyCallBack<WaybillTrackingBean>() {
            @Override
            public void onSuccess(WaybillTrackingBean baseBean) {
                loadingDialog.dissDialog();
                if (baseBean.isSuccess()) {
                    if (mList != null) {
                        mList.addAll(baseBean.getObj());
                        mAdapter.notifyDataSetChanged();
                    }
                }else {
                    UHelper.showToast(WaybillTrackingActivity.this,baseBean.getMsg());
                }
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }

}
