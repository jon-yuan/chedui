package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.entity.PicEntity;
import com.babuwyt.daili.entity.pictureEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.ui.views.BannerView;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/29.
 */
@ContentView(R.layout.activity_loolbigpicture)
public class LookBigPictureActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.bannerview)
    BannerView bannerview;

    private ArrayList<View> vList;
    private ArrayList<pictureEntity> mList=new ArrayList<pictureEntity>();
    private int index=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        index=getIntent().getIntExtra("index",0);
        mList.addAll((ArrayList<pictureEntity>) getIntent().getSerializableExtra("list"));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        vList=new ArrayList<View>();
        for (int i=0;i<mList.size();i++){
            ImageView imageView=new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            x.image().bind(imageView, BaseURL.BASE_IMAGE_URI+mList.get(i).getFpicture(), XUtil.options(ImageView.ScaleType.FIT_CENTER));
            vList.add(imageView);
        }

        bannerview.setViewList(vList);
        bannerview.setCurrentItem(index);

    }
}
