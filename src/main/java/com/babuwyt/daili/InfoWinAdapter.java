package com.babuwyt.daili;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;


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
    public InfoWinAdapter(Context context){
        mContext=context;
    }
    public void setData(String name,String address){
        TVname.setText(name);
        TVaddress.setText(address);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        initData(marker);
        View view = initView();
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
        TVname=view.findViewById(R.id.name);
        TVaddress=view.findViewById(R.id.address);
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
