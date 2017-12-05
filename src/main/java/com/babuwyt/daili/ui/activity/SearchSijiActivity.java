package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.MySijiAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.ui.views.LoadingDialog;
import com.babuwyt.daili.ui.views.MakeTrueDialog;
import com.babuwyt.daili.ui.views.RatesDialog;
import com.babuwyt.daili.ui.views.RecycleViewDivider;
import com.babuwyt.daili.utils.util.UHelper;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by lenovo on 2017/8/24.
 */
@ContentView(R.layout.activity_searchsiji)
public class SearchSijiActivity extends BaseActivity implements MySijiAdapter.MySijiAdapterOnItemClick{
    @ViewInject(R.id.et_search)
    EditText et_search;
    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;
    private RecyclerView.LayoutManager manager;
    private MySijiAdapter mAdapter;
    private int show=0;//默认进来之后为查看司机
    private RatesDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        loadingDialog=new RatesDialog(this);
        show=getIntent().getIntExtra(MySijiActivity.MYSIJI_TYPE,0);
//        toolbar.setTitle(getString(R.string.search_siji));
//        toolbar.setNavigationIcon(R.drawable.goback);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

        manager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        mAdapter=new MySijiAdapter(this,show);
        recyclerview.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.main_gray_line)));
        recyclerview.setAdapter(mAdapter);
        mAdapter.setItemClick(this);





    }

    @Override
    public void onItemClick(int position, View view) {
        loadingDialog.showDialog();
    }

    @Override
    public void CallBackPhone(int i) {

    }

    @Override
    public void CallBackSelect(int i) {

    }

    @Event(value = {R.id.img_back,R.id.tv_search})
    private void gete(View view){
        switch (view.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_search:
                UHelper.showToast(this,"搜索");
                break;
        }
    }


}
