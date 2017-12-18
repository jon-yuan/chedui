package com.babuwyt.daili.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lenovo on 2017/11/6.
 */

public class BannerAdapter extends PagerAdapter {

    private List<View> viewList;
    private int size;
    private final int cacheCount = 3;

    public BannerAdapter(List<View> viewList) {
        this.viewList = viewList;
        size = viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        if (viewList.size() > cacheCount){
//            container.removeView(viewList.get(position%size));
//        }
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        ViewGroup parent = (ViewGroup) viewList.get(position%size).getParent();
//        if (parent != null) {
//            parent.removeView(viewList.get(position%size));
//        }
//        container.addView(viewList.get(position%size));
//        return viewList.get(position%size);
        container.addView(viewList.get(position));

        return viewList.get(position);
    }

    @Override
    public int getCount() {
//        return Integer.MAX_VALUE;
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
