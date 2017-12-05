package com.babuwyt.daili.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.entity.WaybillTrackingEntity;

import java.util.List;

/**
 * Created by lenovo on 2017/8/1.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;
    /**
     * 数据
     */
    private List<WaybillTrackingEntity> mDatas;
    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public MyOrderAdapter(List<WaybillTrackingEntity> datas) {
        this.mDatas = datas;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_myorder_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == TYPE_HEADER)
            return;
        MyOrderAdapter.ViewHolder viewHolder = (ViewHolder) holder;
        final int pos = getRealPosition(viewHolder);

        viewHolder.tv_time.setText("时间: " + mDatas.get(position).getPicktime());
        viewHolder.tv_orderno.setText("订单号: " +mDatas.get(position).getFsendcarno());
        viewHolder.tv_luxian.setText("路线: " + mDatas.get(position).getFfromaddress()+"-"+mDatas.get(position).getFtoaddress());
        viewHolder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(position);
            }
        });
    }
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position-1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_orderno;
        public TextView tv_time;
        public TextView tv_luxian;
        public LinearLayout layout_item;


        public ViewHolder(View itemView) {
            super(itemView);
            if(itemView == mHeaderView) return;
            tv_orderno = itemView.findViewById(R.id.tv_orderno);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_luxian = itemView.findViewById(R.id.tv_luxian);
            layout_item = itemView.findViewById(R.id.layout_item);

        }
    }
}
