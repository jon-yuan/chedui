package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
import com.babuwyt.daili.entity.WeizhiObjEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by bbkj on 2017/12/8.
 */
@ContentView(R.layout.activity_beidoucheck)
public class BeidouCheckActivity extends BaseActivity{
    @ViewInject(R.id.mapview)
    MapView mapView;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.et_carno)
    EditText et_carno;
    @ViewInject(R.id.tv_search)
    TextView tv_search;
    private InfoWinAdapter mAdapter;
    private AMap aMap;
    private MapUtil mapUtil;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
        initMap();
    }
    private void init() {
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initMap() {
        aMap = mapView.getMap();
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式
//
        mAdapter=new InfoWinAdapter(this);
        mapUtil=MapUtil.getInstance(this,aMap);
        mapUtil.setMapZoomTo(20);
        aMap.setInfoWindowAdapter(mAdapter);



    }
    private Marker addMarkerToMap(LatLng latLng, String title, String snippet) {
        return aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(latLng)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location_blue))
        );
    }

    @Event(value = {R.id.tv_search})
    private void getE(View view){
        switch (view.getId()){
            case R.id.tv_search:
                getCarNo();
                break;
        }
    }

    private void getCarNo(){
        String caoNo=et_carno.getText().toString().trim();
        if (TextUtils.isEmpty(caoNo)){
            UHelper.showToast(BeidouCheckActivity.this,getString(R.string.please_input_carno));
            return;
        }
        if (!UHelper.isCar(caoNo)){
            UHelper.showToast(BeidouCheckActivity.this,getString(R.string.carno_error));
            return;
        }
        getCarLocation(caoNo);
    }
    //获取车辆位置
    private void getCarLocation(final String carNo){
        ArrayList<String> list=new ArrayList<String>();
        list.add(carNo);
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.GET_LOCATIONIN_APP,list,new MyCallBack<WeizhiBean>(){
            @Override
            public void onSuccess(WeizhiBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()){
                    if (result.getEntity()!=null){
                        WeizhiObjEntity entity=result.getEntity();
                        String address=entity.getAddress();
                        double lat=0;
                        double lon=0;
                        if (result.getEntity().getGps()!=null){
                            lat=result.getEntity().getGps().getWgLat();
                            lon=result.getEntity().getGps().getWgLon();
                        }
                        Marker marker=addMarkerToMap(new LatLng(lat,lon ),"","");
                        mapUtil.moveMapCenter(new LatLng(lat,lon));
                        marker.showInfoWindow();
                        mAdapter.setData(carNo,address);
                    }
                }else {
                    UHelper.showToast(BeidouCheckActivity.this,result.getMsg());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
            }
        });
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
}
