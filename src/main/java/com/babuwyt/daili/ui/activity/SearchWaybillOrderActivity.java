package com.babuwyt.daili.ui.activity;

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
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/30.
 */

@ContentView(R.layout.activity_searchwaybillorder)
public class SearchWaybillOrderActivity extends BaseActivity {
    @ViewInject(R.id.et_search)
    EditText et_search;
    @ViewInject(R.id.tv_search)
    TextView tv_search;
    boolean iscallback=true;

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
    private String searchStr="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
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
                    getHttpRefresh();
                }
                break;
        }
    }
    private void getHttpRefresh() {
        pageNum=0;
//        ArrayList<String> list = new ArrayList<String>();
//        list.add(pageNum + "");
//        list.add(SessionManager.getInstance().getUser().getFid());
//        list.add(SessionManager.getInstance().getUser().getFforwardercode() + "");
//        list.add(searchStr);

        Map<String ,Object> map=new HashMap<String, Object>();
        map.put("pagenum",pageNum);
        map.put("fid",SessionManager.getInstance().getUser().getFid());
        map.put("code",SessionManager.getInstance().getUser().getFforwardercode());
        map.put("searchStr",searchStr);

        loadingDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.FINDCARS, map, new MyCallBack<WaybillTrackingBean>() {
            @Override
            public void onSuccess(WaybillTrackingBean baseBean) {
                loadingDialog.dissDialog();
                mList.clear();
                if (baseBean.isSuccess()) {
                    if (mList != null) {
                        mList.addAll(baseBean.getObj());
                    }
                }else {
                    UHelper.showToast(SearchWaybillOrderActivity.this,baseBean.getMsg());
                }
                mAdapter.notifyDataSetChanged();
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
        Map<String ,Object> map=new HashMap<String, Object>();
        map.put("pagenum",pageNum);
        map.put("fid",SessionManager.getInstance().getUser().getFid());
        map.put("code",SessionManager.getInstance().getUser().getFforwardercode());
        map.put("searchStr",searchStr);
        loadingDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.FINDCAR, map, new MyCallBack<WaybillTrackingBean>() {
            @Override
            public void onSuccess(WaybillTrackingBean baseBean) {
                loadingDialog.dissDialog();
                if (baseBean.isSuccess()) {
                    if (mList != null) {
                        mList.addAll(baseBean.getObj());
                        mAdapter.notifyDataSetChanged();
                    }
                }else {
                    UHelper.showToast(SearchWaybillOrderActivity.this,baseBean.getMsg());
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
