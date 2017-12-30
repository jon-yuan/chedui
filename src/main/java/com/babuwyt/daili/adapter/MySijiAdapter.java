package com.babuwyt.daili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.entity.SijiEntity;
import com.babuwyt.daili.utils.util.DensityUtils;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/1.
 */

public class MySijiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private int show;
    private ArrayList<SijiEntity> mList;
    public MySijiAdapter (Context context,int b){
        this.context=context;
        this.show=b;
        this.mList=new ArrayList<SijiEntity>();
    }
    public void setmList(ArrayList<SijiEntity> list){
        if (list!=null){
            mList=list;
        }
    }
    public interface MySijiAdapterOnItemClick{
        void onItemClick(int position,View view);
        void CallBackPhone(int i);
        void CallBackSelect(int i);
    }
    private MySijiAdapterOnItemClick itemClick;
    public void setItemClick(MySijiAdapterOnItemClick itemClick){
        this.itemClick=itemClick;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.adapter_mysiji_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1= (ViewHolder) holder;
        holder1.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClick!=null){
                    itemClick.onItemClick(position,view);
                }
            }
        });
        holder1.layout_item.getLayoutParams().width= DensityUtils.deviceWidthPX(context);
        if (show==0){
            holder1.tv_select.setVisibility(View.GONE);
        }else {
            holder1.tv_select.setVisibility(View.VISIBLE);
        }
        holder1.img_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.CallBackPhone(position);
            }
        });
        holder1.tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.CallBackSelect(position);
            }
        });

        holder1.tv_name.setText(mList.get(position).getFdrivername());
        holder1.tv_dianhua.setText(context.getString(R.string.dianhua)+": "+mList.get(position).getFphonenum());
        holder1.tv_chepaihao.setText(context.getString(R.string.chepaihao)+": "+mList.get(position).getFplateno());
        if (mList.get(position).isIsselect()){
            holder1.tv_select.setEnabled(false);
            holder1.tv_select.setText(context.getString(R.string.isselected));
        }else {
            holder1.tv_select.setEnabled(true);
            holder1.tv_select.setText(context.getString(R.string.select));
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout layout_item;
        public TextView tv_select;
        public ImageView img_phone;
        public TextView tv_name;
        public TextView tv_dianhua;
        public TextView tv_chepaihao;
        public ViewHolder(View itemView) {
            super(itemView);
            layout_item=itemView.findViewById(R.id.layout_item);
            tv_select=itemView.findViewById(R.id.tv_select);
            img_phone=itemView.findViewById(R.id.img_phone);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_dianhua=itemView.findViewById(R.id.tv_dianhua);
            tv_chepaihao=itemView.findViewById(R.id.tv_chepaihao);
        }
    }
}
