package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.MsgAdapter;
import com.babuwyt.daili.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/11.
 * 消息页面
 */
@ContentView(R.layout.activity_msg)
public class MsgActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.listview)
    ListView listview;
    private MsgAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        toolbar.setTitle("消息");
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter=new MsgAdapter(this);
        ArrayList<String> mList=new ArrayList<String>();
        for (int i=0;i<20;i++){
            mList.add("测试"+i);
        }
        mAdapter.setmList(mList);
        listview.setAdapter(mAdapter);
    }
}
