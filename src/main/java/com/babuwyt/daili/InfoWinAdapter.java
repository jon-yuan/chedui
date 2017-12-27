package com.babuwyt.daili;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.babuwyt.daili.entity.WeizhiObjEntity;


/**
 * Created by Teprinciple on 2016/8/23.
 * 地图上自定义的infowindow的适配器
 */
public class InfoWinAdapter implements AMap.InfoWindowAdapter, View.OnClickListener {
    private Context mContext;
    private LatLng latLng;
    private String agentName;
    private String snippet;

    private TextView TVname;
    private TextView TVaddress;
    private TextView carno;
    private TextView time;
    private LinearLayout layout_name;
    public InfoWinAdapter(Context context){
        mContext=context;
    }
//    public void setData(String name,String address){
//        TVname.setText(name);
//        TVaddress.setText(address);
//    }

    @Override
    public View getInfoWindow(Marker marker) {
        WeizhiObjEntity res = (WeizhiObjEntity) marker.getObject();
        View view = initView();

        TVname=view.findViewById(R.id.name);
        carno=view.findViewById(R.id.carno);
        time=view.findViewById(R.id.time);
        TVaddress=view.findViewById(R.id.address);
        layout_name=view.findViewById(R.id.layout_name);

        TVname.setText(res.getDrivename());
        TVaddress.setText(res.getAddress());
        carno.setText(res.getDrivecard());
        time.setText(res.getUtc());
        if (TextUtils.isEmpty(res.getDrivename())){
            layout_name.setVisibility(View.GONE);
        }else {
            layout_name.setVisibility(View.VISIBLE);
        }

        initData(marker);

        return view;
    }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    private void initData(Marker marker) {
        latLng = marker.getPosition();
        snippet = marker.getSnippet();
        agentName = marker.getTitle();
    }

    @NonNull
    private View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_infowindow, null);

        return view;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
//            case R.id.navigation_LL:  //点击导航
//                UHelper.showToast(mContext,"导航");
//                break;
//
//            case R.id.call_LL:  //点击打电话
//                UHelper.showToast(mContext,"电话");
//                break;
        }
    }
}
