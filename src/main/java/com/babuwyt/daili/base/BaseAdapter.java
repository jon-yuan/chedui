package com.babuwyt.daili.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babuwyt.daili.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/10/16.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected static final int VIEW_NODATA = -1;//没有数据
    protected Context mContext;
    /** 数据源 */
    protected ArrayList<T> mData=new ArrayList<T>();
    public BaseAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.size() <= 0) {
            return VIEW_NODATA;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View emptyview = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false);
        if (VIEW_NODATA == viewType) {
            return new EmptyHolder(emptyview);
        }
        return onBaseCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (VIEW_NODATA != getItemViewType(position)) {
            onBaseBindViewHolder(holder,position);
        }
    }

    @Override
    public int getItemCount() {
        if (mData.size()>0){
            return getBaseItemCount();
        }
        return 1;
    }
    public abstract void setmList(ArrayList<T> t);
    public abstract RecyclerView.ViewHolder onBaseCreateViewHolder(ViewGroup parent, int viewType);
    public abstract void onBaseBindViewHolder(RecyclerView.ViewHolder holder, int position);
    public abstract int getBaseItemCount();
    class EmptyHolder extends RecyclerView.ViewHolder {
        public EmptyHolder(View itemView) {
            super(itemView);
        }
    }
}
