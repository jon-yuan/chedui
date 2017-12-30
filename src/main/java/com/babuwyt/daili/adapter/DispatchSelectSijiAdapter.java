package com.babuwyt.daili.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.entity.SijiEntity;
import com.babuwyt.daili.utils.util.UHelper;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/12/29.
 */

public class DispatchSelectSijiAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<SijiEntity> mList;

    public DispatchSelectSijiAdapter(Context context){
        mContext=context;
        mList=new ArrayList<SijiEntity>();
    }

    public void setmList(ArrayList<SijiEntity> list){
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
            view=LayoutInflater.from(mContext).inflate(R.layout.item_dispatchselectsiji,null);
            x.view().inject(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }

        if (i==0){
            holder.btn_addsiji.setText(mContext.getString(R.string.add_zsiji));
            holder.tv_delete.setVisibility(View.GONE);
        }else {
            holder.btn_addsiji.setText(mContext.getString(R.string.add_fsiji));
            holder.tv_delete.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(mList.get(i).getFdrivername())){
            holder.layout_item.setVisibility(View.GONE);
            holder.btn_addsiji.setVisibility(View.VISIBLE);
        }else {
            holder.layout_item.setVisibility(View.VISIBLE);
            holder.btn_addsiji.setVisibility(View.GONE);
        }
        holder.tv_name.setText(TextUtils.isEmpty(mList.get(i).getFdrivername())?"":mContext.getString(R.string.siji1)+" "+mList.get(i).getFdrivername());
        holder.tv_dianhua.setText(TextUtils.isEmpty(mList.get(i).getFphonenum())?"":mContext.getString(R.string.dianhua1)+" "+mList.get(i).getFphonenum());
        holder.tv_chepaihao.setText(TextUtils.isEmpty(mList.get(i).getFplateno())?"":mContext.getString(R.string.chepaihao1)+" "+mList.get(i).getFplateno());

        holder.btn_addsiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.addSiji(i);
            }
        });
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.delSiji(i);
            }
        });
        holder.tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.updateSiji(i);
            }
        });
        return view;
    }
    class ViewHolder{
        @ViewInject(R.id.tv_name)
        TextView tv_name;
        @ViewInject(R.id.tv_dianhua)
        TextView tv_dianhua;
        @ViewInject(R.id.tv_chepaihao)
        TextView tv_chepaihao;
        @ViewInject(R.id.tv_update)
        TextView tv_update;
        @ViewInject(R.id.tv_delete)
        TextView tv_delete;
        @ViewInject(R.id.btn_addsiji)
        TextView btn_addsiji;
        @ViewInject(R.id.layout_item)
        LinearLayout layout_item;
    }

    public interface addSijiCallBack{
        void addSiji(int ponsition);
        void updateSiji(int ponsition);
        void delSiji(int ponsition);
    }
    private addSijiCallBack callBack;
    public void setCallBack(addSijiCallBack callBack){
        this.callBack=callBack;
    }
}
