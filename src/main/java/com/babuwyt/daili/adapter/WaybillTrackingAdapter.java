package com.babuwyt.daili.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.entity.WaybillTrackingEntity;
import com.babuwyt.daili.entity.WeizhiEntity;
import com.babuwyt.daili.ui.activity.GuijiActivity;
import com.babuwyt.daili.ui.activity.StateGenzongActivity;
import com.babuwyt.daili.ui.activity.WeiZhiActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/22.
 */

public class WaybillTrackingAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<WaybillTrackingEntity> mList;
    public WaybillTrackingAdapter(Context context){
        this.mContext=context;
        this.mList=new ArrayList<WaybillTrackingEntity>();
    }
    public void setmList(ArrayList<WaybillTrackingEntity> list){
        if (list!=null){
            mList=list;
        }
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.adapter_waybilltrack,null);
            x.view().inject(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.btn_genzong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(mContext, StateGenzongActivity.class);
                intent.putExtra("Fwonid",mList.get(i).getFwonid());
                mContext.startActivity(intent);
            }
        });
        holder.btn_guiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(mContext, GuijiActivity.class);
                intent.putExtra("fid",mList.get(i).getFid());
                mContext.startActivity(intent);
            }
        });
        holder.btn_weizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(mContext, WeiZhiActivity.class);
                intent.putExtra("fid",mList.get(i).getFsendcarno());
                intent.putExtra("name",mList.get(i).getFdrivername());
                mContext.startActivity(intent);
            }
        });


        holder.tv_name.setText(mList.get(i).getFdrivername());
        holder.tv_yundan.setText(mList.get(i).getFsendcarno());
        switch (mList.get(i).getFtaskstate()) {
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
        }
        return view;
    }
    class ViewHolder{
        @ViewInject(R.id.btn_genzong)
        LinearLayout btn_genzong;
        @ViewInject(R.id.tv_name)
        TextView tv_name;
        @ViewInject(R.id.tv_yundan)
        TextView tv_yundan;
        @ViewInject(R.id.tv_state)
        TextView tv_state;
        @ViewInject(R.id.btn_guiji)
        LinearLayout btn_guiji;
        @ViewInject(R.id.btn_weizhi)
        LinearLayout btn_weizhi;
    }
}
