package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

import com.babuwyt.daili.InfoWinAdapter;
import com.babuwyt.daili.MapUtil;
import com.babuwyt.daili.R;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.bean.WeizhiBean;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/19.
 * 实时位置
 */
@ContentView(R.layout.activity_weizhi)
public class WeiZhiActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.mapView)
    MapView mapView;
    private AMap aMap;
    private InfoWinAdapter mAdapter;
    private Marker oldMarker;
    private String fsendcarno;
    private String name;
    MapUtil mapUtil;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        fsendcarno=getIntent().getStringExtra("fid");
        name=getIntent().getStringExtra("name");
        init();
        initMap();
        getHttp();
    }
    private void init() {
        toolbar.setTitle(getString(R.string.shishiweizhi));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    //设置地图
    private void initMap() {
        aMap=mapView.getMap();
        mAdapter=new InfoWinAdapter(this);
       mapUtil=MapUtil.getInstance(this,aMap);
        mapUtil.setMapZoomTo(20);
        aMap.setInfoWindowAdapter(mAdapter);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    private Marker addMarkerToMap(LatLng latLng, String title, String snippet) {
        return aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(latLng)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_car))
        );
    }

    private void getHttp(){
        ArrayList<String> list=new ArrayList<String>();
        list.add(fsendcarno);
//        list.add("D11020170721001");
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.WEIZHI,list,new MyCallBack<WeizhiBean>(){
            @Override
            public void onSuccess(WeizhiBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                Gson gson=new Gson();
                String s=gson.toJson(result);
                if (result.isSuccess() && result.getEntity()!=null && result.getEntity().getGps()!=null){
                    Marker marker=addMarkerToMap(new LatLng(result.getEntity().getGps().getWgLat(), result.getEntity().getGps().getWgLon()),"","");
                    mapUtil.moveMapCenter(new LatLng(result.getEntity().getGps().getWgLat(), result.getEntity().getGps().getWgLon()));
                    marker.showInfoWindow();
                    mAdapter.setData(name,result.getEntity().getAddress());
                    UHelper.showToast(WeiZhiActivity.this,result.getMsg());
                }else {
                    UHelper.showToast(WeiZhiActivity.this,result.getMsg());
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                Log.d("11111",ex+"");
                UHelper.showToast(WeiZhiActivity.this,getString(R.string.NET_ERROR));
            }
        });
    }
}
