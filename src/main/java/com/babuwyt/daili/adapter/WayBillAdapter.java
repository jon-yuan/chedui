package com.babuwyt.daili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.entity.WaybillTrackingEntity;
import com.babuwyt.daili.ui.activity.GuijiActivity;
import com.babuwyt.daili.ui.activity.OrderDetailsActivity;
import com.babuwyt.daili.ui.activity.StateGenzongActivity;
import com.babuwyt.daili.ui.activity.WeiZhiActivity;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/19.
 */

public class WayBillAdapter extends RecyclerView.Adapter<WayBillAdapter.WayBillViewHolder> {
    private static final int VIEW_TYPE = -1;
    private Context mContext;
    private ArrayList<WaybillTrackingEntity> mList;

    public WayBillAdapter(Context context, ArrayList<WaybillTrackingEntity> datas) {
        this.mContext = context;
        this.mList = datas;
    }

    @Override
    public WayBillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_waybilltrack, null);
        View emptyview = LayoutInflater.from(mContext).inflate(R.layout.empty_view, parent, false);
        if (VIEW_TYPE == viewType) {
            return new WayBillViewHolder(emptyview);
        }
        return new WayBillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WayBillAdapter.WayBillViewHolder holder, final int position) {
        if (mList.size() <= 0) {
            return;
        }
        holder.tv_name.setText(mList.get(position).getFdrivername());
        holder.tv_yundan.setText(mList.get(position).getFsendcarno());
        switch (mList.get(position).getFtaskstate()) {
            case 1:
                holder.tv_state.setText("装货已签到");
                break;
            case 2:
                holder.tv_state.setText("装货已拍照");
                break;
            case 3:
                holder.tv_state.setText("已装货（装货照片已审核）");
                break;
            case 4:
                holder.tv_state.setText("卸货已签到");
                break;
            case 5:
                holder.tv_state.setText("已卸货（签收单照片已审核）");
                break;
            case 6:
                holder.tv_state.setText("签收单已交回");
                break;
            case 7:
                holder.tv_state.setText("签收单已确认");
                break;
            default:
                holder.tv_state.setText("未作业");
                break;
        }
        holder.btn_genzong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, StateGenzongActivity.class);
                intent.putExtra("fwonid", mList.get(position).getFwonid());
                mContext.startActivity(intent);
            }
        });
        holder.btn_guiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, GuijiActivity.class);
                intent.putExtra("fid", mList.get(position).getFid());
                mContext.startActivity(intent);
            }
        });
        holder.btn_weizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, WeiZhiActivity.class);
                intent.putExtra("fid", mList.get(position).getFsendcarno());
                intent.putExtra("name", mList.get(position).getFdrivername());
                mContext.startActivity(intent);
            }
        });
        holder.layout_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, OrderDetailsActivity.class);
                intent.putExtra("fsendcarno", mList.get(position).getFsendcarno());
                intent.putExtra("fwonid", mList.get(position).getFwonid());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size() > 0 ? mList.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.size() <= 0) {
            return VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    class WayBillViewHolder extends RecyclerView.ViewHolder {
        LinearLayout btn_genzong;
        TextView tv_name;
        TextView tv_yundan;
        TextView tv_state;
        LinearLayout btn_guiji;
        LinearLayout btn_weizhi;
        RelativeLayout layout_details;

        public WayBillViewHolder(View itemView) {
            super(itemView);
            if (mList.size() <= 0) {
                return;
            }
            btn_genzong = itemView.findViewById(R.id.btn_genzong);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_yundan = itemView.findViewById(R.id.tv_yundan);
            tv_state = itemView.findViewById(R.id.tv_state);
            btn_guiji = itemView.findViewById(R.id.btn_guiji);
            btn_weizhi = itemView.findViewById(R.id.btn_weizhi);
            layout_details = itemView.findViewById(R.id.layout_details);

        }
    }
}
