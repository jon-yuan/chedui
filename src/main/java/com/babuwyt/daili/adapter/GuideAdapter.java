package com.babuwyt.daili.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by lenovo on 2017/6/30.
 */

public class GuideAdapter extends PagerAdapter {
    private List<View> mlist;

    public GuideAdapter(List<View> mlist) {
        this.mlist = mlist;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mlist.get(position));
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
