package com.babuwyt.daili.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.MainAdapter;
import com.babuwyt.daili.adapter.WayBillAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.bean.MainBean;
import com.babuwyt.daili.bean.WaybillTrackingBean;
import com.babuwyt.daili.entity.MainEntity;
import com.babuwyt.daili.entity.WaybillTrackingEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MainAdapterCallBack;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/30.
 */

@ContentView(R.layout.activity_searchwaybillorder)
public class SearchMainOrderActivity extends BaseActivity  implements MainAdapterCallBack {
    @ViewInject(R.id.et_search)
    EditText et_search;
    @ViewInject(R.id.tv_search)
    TextView tv_search;
    boolean iscallback=true;

    @ViewInject(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @ViewInject(R.id.springview)
    SpringView springview;
    //    @ViewInject(R.id.load_more_list_view_container)
//    LoadMoreListViewContainer mLoadMoreListViewContainer;
    /**
     * 布局管理器
     */
    private RecyclerView.LayoutManager mManager;
    /**
     * 数据
     */
    private List<MainEntity> mDatas;

    /**
     * 适配器
     */
    private MainAdapter mAdapter;
    private int pageNum = 0;
    private String searchStr="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        et_search.setHint(getString(R.string.please_yundahao));
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length()<=0){
                        searchStr="";
                        iscallback=true;
                        tv_search.setText(getString(R.string.canal));
                    }else {
                        tv_search.setText(getString(R.string.search));
                        iscallback=false;
                        searchStr=charSequence+"";
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



            mDatas = new ArrayList<MainEntity>();
            mManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mManager);
            mAdapter = new MainAdapter(mDatas);
            mAdapter.setCallBack(this);
            mRecyclerView.setAdapter(mAdapter);
            springview.setType(SpringView.Type.FOLLOW);
            springview.setHeader(new DefaultHeader(this));
            springview.setFooter(new DefaultFooter(this));
            springview.setListener(new SpringView.OnFreshListener() {
                @Override
                public void onRefresh() {
                    doSthRefresh();
                }

                @Override
                public void onLoadmore() {
                    doSthLoadMore();
                }
            });
        }


    @Event(value = {R.id.tv_search})
    private void getE(View v){
        switch (v.getId()){
            case R.id.tv_search:

                if (iscallback){
                    finish();
                }else {
                    if (searchStr.equalsIgnoreCase("null")){
                        UHelper.showToast(this,getString(R.string.please_input_yundanhao));
                        return;
                    }
                    doSthRefresh();
                }
                break;
        }
    }
    /**
     * 接口调用
     */
    private void doSthRefresh() {

        pageNum = 0;
        Map<String ,Object> map=new HashMap<String, Object>();
        map.put("pagenum",pageNum);
        map.put("fid",SessionManager.getInstance().getUser().getFid());
        map.put("searchStr",searchStr);
        loadingDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.SELECTPAGES, map, new MyCallBack<MainBean>() {
            @Override
            public void onSuccess(MainBean result) {
                super.onSuccess(result);
                Log.d("一场了",new Gson().toJson(result)+"");
                loadingDialog.dissDialog();
                mDatas.clear();
                if (result.isSuccess()) {
                    if (mDatas != null) {
                        mDatas.addAll(result.getObj());
                    }
                }else {
                    UHelper.showToast(SearchMainOrderActivity.this,result.getMsg());
                }
                mAdapter.notifyDataSetChanged();
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                Log.d("一场了",ex+"");
                springview.onFinishFreshAndLoad();
            }
        });
    }
    private void doSthLoadMore() {
        pageNum++;
        Map<String ,Object> map=new HashMap<String, Object>();
        map.put("pagenum",pageNum);
        map.put("fid",SessionManager.getInstance().getUser().getFid());
        map.put("searchStr",searchStr);
        loadingDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.SELECTPAGES, map, new MyCallBack<MainBean>() {
            @Override
            public void onSuccess(MainBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()) {
                    if (mDatas != null) {
                        mDatas.addAll(result.getObj());
                        mAdapter.notifyDataSetChanged();
                    }
                }else {
                    UHelper.showToast(SearchMainOrderActivity.this,result.getMsg());
                }
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }


    @Override
    public void CallBackQupaiche(int i) {
        Intent intent=new Intent();
        intent.setClass(SearchMainOrderActivity.this, DispatchCarActivity.class);
        intent.putExtra("fid", mDatas.get(i).getFid());
        intent.putExtra("fendcarno", mDatas.get(i).getFownersendcarno());
        startActivity(intent);
    }

    @Override
    public void onItemClick(int i) {
        Intent intent=new Intent();
        intent.setClass(SearchMainOrderActivity.this, DispatchCarActivity.class);
        intent.putExtra("fid", mDatas.get(i).getFid());
        intent.putExtra("fendcarno", mDatas.get(i).getFownersendcarno());
        startActivity(intent);
    }
}
