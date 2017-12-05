package com.babuwyt.daili.utils.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.daili.R;


/**
 * Created by lenovo on 2017/8/22.
 */

public class ViewUtils {
    private static ViewUtils viewUtils;
    private static Context mContext;
    public static ViewUtils getInstance(Context context) {

        if (viewUtils == null) {
            viewUtils = new ViewUtils();
        }
        return viewUtils;
    }
    @SuppressLint("ResourceAsColor")
    public void setEmptyListView(ListView view,String s){
        TextView emptyView = new TextView(mContext);
        emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        emptyView.setText(s);
        emptyView.setTextColor(R.color.black_666);
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setGravity(Gravity.CENTER);
        ((ViewGroup)view.getParent()).addView(emptyView);
        view.setEmptyView(emptyView);
    }
    @SuppressLint("ResourceAsColor")
    public static void setEmptyListView(Context context,ListView view,String s){
        TextView emptyView = new TextView(context);
        emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        emptyView.setText(s);
        emptyView.setTextColor(R.color.black_666);
        emptyView.setVisibility(View.GONE);
        emptyView.setGravity(Gravity.CENTER);
        ((ViewGroup)view.getParent()).addView(emptyView);
        view.setEmptyView(emptyView);
    }
}
