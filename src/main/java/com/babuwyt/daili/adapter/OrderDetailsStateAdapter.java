package com.babuwyt.daili.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.entity.Tworktrack;
import com.babuwyt.daili.utils.util.DateUtils;
import com.babuwyt.daili.utils.util.DensityUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/14.
 */

public class OrderDetailsStateAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Tworktrack> mList;
    private ViewHolder holder;
    private boolean isShow;
    public OrderDetailsStateAdapter(Context context){
        this.context=context;
        mList=new ArrayList<Tworktrack>();
    }
    public void setmList(ArrayList<Tworktrack> list){
        if (list!=null){
            mList=list;
        }
    }
    public void showView(boolean b){
        isShow=b;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return isShow == true ? mList.size() : 0;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.adapter_orderdetalis_state_item,null);
            x.view().inject(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        int sWidth= DensityUtils.deviceWidthPX(context);
        int space=DensityUtils.dip2px(context,(float) 40);
//        holder.image.getLayoutParams().height=(sWidth-space)/5;
//        holder.image.getLayoutParams().width=(sWidth-space)/5;
        holder.layout_item.getLayoutParams().width=(sWidth-space)/5;
        if (mList.get(i).getFstate()>3){
            holder.image.setImageResource(R.drawable.icon_state_false);
            holder.address.setTextColor(context.getResources().getColor(R.color.black_98));
            holder.time.setTextColor(context.getResources().getColor(R.color.black_98));
        }else {
            holder.image.setImageResource(R.drawable.icon_state_true);
            holder.address.setTextColor(context.getResources().getColor(R.color.black_333));
            holder.time.setTextColor(context.getResources().getColor(R.color.black_333));
            holder.time.setText(DateUtils.timedate(mList.get(i).getFcreatetime()));
        }
        holder.address.setText(state(mList.get(i).getFstate()));
        return view;
    }
    private String state(int i){
        String str = "";
        switch (i) {
            case 1:
                str="装货已签到";
                break;
            case 2:
                str="装货已拍照";
                break;
            case 3:
                str="已装货（装货照片已审核）";
                break;
            case 4:
                str="卸货已签到";
                break;
            case 5:
                str="已卸货（签收照片已审核）";
                break;
            case 6:
                str="签收单已交回";
                break;
            case 7:
                str="签收单已确认";
                break;
        }
        return str;
    }
    class ViewHolder{
        @ViewInject(R.id.image)
        ImageView image;
        @ViewInject(R.id.layout_item)
        LinearLayout layout_item;
        @ViewInject(R.id.time)
        TextView time;
        @ViewInject(R.id.address)
        TextView address;


    }

}
