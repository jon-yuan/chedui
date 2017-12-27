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
 * Created by lenovo on 2017/12/27.
 */

public class DispatchCarAdapter extends BaseAdapter {

    private ArrayList<LoadpickEntity> mList;
    private Context mContext;
    private boolean isOpen = false;

    public DispatchCarAdapter(Context context) {
        this.mContext = context;
        this.mList = new ArrayList<LoadpickEntity>();
    }

    public void setmList(ArrayList<LoadpickEntity> list) {
        if (list != null) {
            mList = list;
        }
    }

    public void setOpen(boolean b) {
        isOpen = b;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return isOpen == true ? mList.size() : 0;
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
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_dispatchcar, null);
            x.view().inject(holder, view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.linman.setText(mList.get(i).getFlinkman());
        holder.address.setText(mList.get(i).getFaddress());
        if (mList.get(i).getFseq()==1){
            holder.tv_fseq.setText(mContext.getString(R.string.zhuang1));
        }else {
            holder.tv_fseq.setText(mContext.getString(R.string.xie1));
        }
        return view;
    }
    class ViewHolder {
        @ViewInject(R.id.linman)
        TextView linman;
        @ViewInject(R.id.address)
        TextView address;
        @ViewInject(R.id.tv_fseq)
        TextView tv_fseq;
    }
}
