package com.babuwyt.daili.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by yw on 2015/1/21.
 * 不带滚动的gridview
 */
public class CustomGridView extends GridView {

    public CustomGridView(Context context) {
        super(context);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }


}
