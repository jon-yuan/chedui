package com.babuwyt.daili.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.GuideAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.finals.SharePrefKeys;
import com.babuwyt.daili.utils.util.SharePreferencesUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/6/30.
 * 引导页面
 */


@ContentView(R.layout.activity_guide)
public class GuideActivity extends BaseActivity {
    @ViewInject(R.id.guide_viewpager)
    ViewPager guide_viewpager;
    @ViewInject(R.id.guide_tiyan)
    TextView guide_tiyan;

    private ArrayList<View> mList = null;
    private GuideAdapter mAdapter;
    private static final int[] drawables = {R.drawable.setting, R.drawable.side_nav_bar, R.drawable.setting};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        mList = new ArrayList<View>();
        for (int i = 0; i < 3; i++) {
            View guide1 = View.inflate(getApplicationContext(), R.layout.guide, null);
            ImageView imageView = (ImageView) guide1.findViewById(R.id.image);
            imageView.setImageResource(drawables[i]);
            mList.add(guide1);
        }
        mAdapter = new GuideAdapter(mList);
        guide_viewpager.setAdapter(mAdapter);
        guide_viewpager.setSelected(true);

        guide_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                guide_tiyan.setVisibility(position == 2 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Event(value = {R.id.guide_tiyan})
    private void gete(View v){
        switch (v.getId()){
            case R.id.guide_tiyan:
                stateChenge();
                break;
        }
    }
    //进入登陆页面之前保存状态为 非第一次使用软件
    private void stateChenge(){
        SharePreferencesUtils.saveBoolean(GuideActivity.this, SharePrefKeys.XML_FIRST_USE
            , SharePrefKeys.XML_FIRST_USE, false);
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
