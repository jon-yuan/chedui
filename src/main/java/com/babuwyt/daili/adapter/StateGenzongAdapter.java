package com.babuwyt.daili.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.entity.StateGenzongEntity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/24.
 */

public class StateGenzongAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<StateGenzongEntity> mList;
    ViewHolder holder=null;
    public StateGenzongAdapter(Context context){
        this.context=context;
        this.mList=new ArrayList<StateGenzongEntity>();
    }
    public void setmList(ArrayList<StateGenzongEntity> list){
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view==null){
            holder=new ViewHolder();

            view= LayoutInflater.from(context).inflate(R.layout.adapter_stategenzong,null);
            x.view().inject(holder,view);

            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tv_time.setText(TextUtils.isEmpty(mList.get(i).getChecktims()) ?mList.get(i).getTypetemp():mList.get(i).getChecktims());
        holder.tv_state.setText(mList.get(i).getTypetemp());
        holder.tv_addrss.setText(TextUtils.isEmpty(mList.get(i).getChecktims()) ? "" : mList.get(i).getSsq() + mList.get(i).getFaddress());
        holder.v_1.setEnabled(!TextUtils.isEmpty(mList.get(i).getChecktims()));
        holder.tv_state.setVisibility(TextUtils.isEmpty(mList.get(i).getChecktims()) ? View.GONE : View.VISIBLE);
        holder.img_state.setVisibility(TextUtils.isEmpty(mList.get(i).getChecktims()) ? View.VISIBLE : View.GONE);


        return view;
    }


    class ViewHolder{
        @ViewInject(R.id.tv_time)
        TextView tv_time;
        @ViewInject(R.id.v_1)
        TextView v_1;
        @ViewInject(R.id.tv_state)
        TextView tv_state;
        @ViewInject(R.id.tv_addrss)
        TextView tv_addrss;
        @ViewInject(R.id.img_state)
        ImageView img_state;
    }
}
