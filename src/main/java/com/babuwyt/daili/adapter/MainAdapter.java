package com.babuwyt.daili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.entity.MainEntity;
import com.babuwyt.daili.inteface.MainAdapterCallBack;

import java.util.List;

/**
 * Created by lenovo on 2017/7/31.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE = -1;
    private Context context;
    private MainAdapterCallBack callBack;
    /**
     * 数据
     */
    private List<MainEntity> mDatas;

    public MainAdapter(List<MainEntity> datas) {
        this.mDatas = datas;
    }
    public void setCallBack(MainAdapterCallBack c){
        callBack=c;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.size() <= 0) {
            return VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size()>0?mDatas.size():1;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main_item, parent, false);
        View emptyview = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false);
        if (VIEW_TYPE == viewType) {
            return new MainViewHolder(emptyview);
        }
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (mDatas.size()>0){


            MainViewHolder mholder= (MainViewHolder) holder;
            mholder.tv_yundancode.setText(mDatas.get(position).getFownersendcarno());
            mholder.tv_form.setText(mDatas.get(position).getFfromaddress());
            mholder.tv_to.setText(mDatas.get(position).getFtoaddress());
            mholder.tv_chexing.setText(mDatas.get(position).getTtype());
            mholder.tv_huowu.setText(mDatas.get(position).getGoods());
            mholder.tv_time.setText(mDatas.get(position).getPicktime());
            mholder.tv_qupaiche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.CallBackQupaiche(position);
                }
            });
            mholder.layout_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.onItemClick(position);
                }
            });
            }
    }


    class EmptyHolder extends RecyclerView.ViewHolder{

        public EmptyHolder(View itemView) {
            super(itemView);
        }
    }
    class MainViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_yundancode;
        public TextView tv_qupaiche;
        public TextView tv_form;
        public TextView tv_to;
        public TextView tv_chexing;
        public TextView tv_time;
        public TextView tv_huowu;
        public LinearLayout layout_item;


        public MainViewHolder(View itemView) {
            super(itemView);
            if (mDatas.size()>0){
                tv_yundancode=itemView.findViewById(R.id.tv_yundancode);
                tv_qupaiche=itemView.findViewById(R.id.tv_qupaiche);
                tv_form=itemView.findViewById(R.id.tv_form);
                tv_to=itemView.findViewById(R.id.tv_to);
                tv_chexing=itemView.findViewById(R.id.tv_chexing);
                tv_huowu=itemView.findViewById(R.id.tv_huowu);
                tv_time=itemView.findViewById(R.id.tv_time);
                layout_item=itemView.findViewById(R.id.layout_item);
            }

        }
    }
}
