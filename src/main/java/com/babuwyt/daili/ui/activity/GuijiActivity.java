package com.babuwyt.daili.ui.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.babuwyt.daili.MapUtil;
import com.babuwyt.daili.R;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.bean.GuijiBean;
import com.babuwyt.daili.entity.resultEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;


/**
 * Created by lenovo on 2017/7/18.
 */
@ContentView(R.layout.activity_guiji)
public class GuijiActivity extends BaseActivity {




    @ViewInject(R.id.mapview)
    MapView mapView;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    private AMap aMap;
    private Polyline polyline;
    private String fid;
    private ArrayList<LatLng> latLngs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        fid = getIntent().getStringExtra("fid");
        init();
        initMap();
        getGuiji();
    }

    private void initMap() {
        aMap = mapView.getMap();
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式
        latLngs = new ArrayList<LatLng>();
//        aMap.moveCamera(CameraUpdateFactory.zoomTo(10));
//        aMap.setTrafficEnabled(true);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT


        aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {


            }
        });
    }

    public void setMarker(LatLng latLng, int drawID) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), drawID)));
        markerOptions.visible(true);
        aMap.addMarker(markerOptions);
    }

    private void init() {
        toolbar.setTitle(getString(R.string.xingshiguiji));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getGuiji() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(fid);
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.GUIJI, list, new MyCallBack<GuijiBean>() {
            @Override
            public void onSuccess(GuijiBean o) {

                loadingDialog.dissDialog();
                if (o.isSuccess()) {
                    latLngs.clear();
                for (resultEntity entity:o.getObj().getResult()){
                    latLngs.add(new LatLng(entity.getWgLat(),entity.getWgLon()));
                }
                MapUtil mapUtil = MapUtil.getInstance(GuijiActivity.this, aMap);
                ArrayList<MarkerOptions> markerOptions = new ArrayList<MarkerOptions>();
                markerOptions.add(mapUtil.setMarker(latLngs.get(0), R.drawable.marker_start, false));
                markerOptions.add(mapUtil.setMarker(latLngs.get(latLngs.size() - 1), R.drawable.marker_end, false));
                mapUtil.addMarkers(markerOptions);
                mapUtil.setPolyline(latLngs);
                mapUtil.setMapwithBounds(latLngs, 20);
            }else{
                    UHelper.showToast(GuijiActivity.this,getString(R.string.has_no_data));
                    loadingDialog.dissDialog();
            }

        }

        @Override
        public void onError (Throwable ex,boolean isOnCallback){
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
