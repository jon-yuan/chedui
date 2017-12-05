package com.babuwyt.daili.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.entity.LoadpickEntity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/24.
 */

public class YundanDetailsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<LoadpickEntity> mList;

    public YundanDetailsAdapter(Context context,ArrayList<LoadpickEntity> list){
        this.context=context;
        this.mList=list;
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
        ViewHoder hoder=null;
        if (view==null){
            hoder=new ViewHoder();
            view= LayoutInflater.from(context).inflate(R.layout.adapter_yundandetails,null);
            x.view().inject(hoder,view);
            view.setTag(hoder);
        }else {
            hoder= (ViewHoder) view.getTag();
        }

        hoder.address.setText(mList.get(i).getFaddress());
        hoder.name.setText("现场人员:"+mList.get(i).getFlinkman());
        hoder.phone.setText("电话:"+mList.get(i).getFphone());



        return view;
    }
    class ViewHoder{
        @ViewInject(R.id.address)
        TextView address;
        @ViewInject(R.id.name)
        TextView name;
        @ViewInject(R.id.phone)
        TextView phone;
    }
}
