package com.babuwyt.daili.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.entity.CarType;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/1.
 */

public class MySijiPopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<CarType> list;

    public MySijiPopAdapter(Context context, ArrayList<CarType> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_mysijipop_item, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.cartype.setText(list.get(position).getCartype()+"m");
        holder1.cartype.setTextColor(list.get(position).isSelect() ? context.getResources().getColor(R.color.white):context.getResources().getColor(R.color.black_333));
        holder1.cartype.setBackgroundResource(list.get(position).isSelect() ? R.drawable.blue_5radius_background : R.drawable.white_gray_stroke_5radius_background);
        holder1.cartype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CarType carType:list){
                    carType.setSelect(false);
                }
                list.get(position).setSelect(true);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cartype;

        public ViewHolder(View itemView) {
            super(itemView);
            cartype = itemView.findViewById(R.id.cartype);
        }
    }
}
