package com.babuwyt.daili.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.utils.util.XUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/11.
 */

public class MsgAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> mList;
    private ViewHolder holder;
    public MsgAdapter(Context context){
        this.context=context;
        this.mList=new ArrayList<String>();

    }

    public void setmList(ArrayList<String> list){
        if (list!=null){
            this.mList=list;
        }
    }
    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(this.context).inflate(R.layout.adapter_msg_item, null);
            x.view().inject(holder,convertView);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        x.image().bind(holder.image, "http://i0.hdslb.com/bfs/face/497620c688bf982738a32c1c00f078b7d198adb7.jpg", XUtil.options(true));
        return convertView;
    }
    class ViewHolder{
        @ViewInject(R.id.tv_msg)
        TextView tv_msg;
        @ViewInject(R.id.tv_time)
        TextView tv_time;
        @ViewInject(R.id.image)
        ImageView image;

    }

}
