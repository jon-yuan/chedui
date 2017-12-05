package com.babuwyt.daili;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.entity.LocationEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lenovo on 2017/7/19.
 */

public class MapUtil implements AMapLocationListener{

    private static MapUtil mMapUtil;
    private static Context mContext;
    private static AMap mAMap;
    private AMapLocationClient mapLocationClient;

    public static MapUtil getInstance(Context context, AMap aMap) {
        mContext = context;
        mAMap = aMap;
        if (mMapUtil == null) {
            mMapUtil = new MapUtil();
        }
        return mMapUtil;
    }
    public static MapUtil getInstance(Context context) {
        mContext = context;
        if (mMapUtil == null) {
            mMapUtil = new MapUtil();
        }
        return mMapUtil;
    }
    public void setmAMap(AMap aMap){
        this.mAMap=aMap;
    }

    /**
     * 设置marker
     *
     * @param latLng
     * @param drawID
     * @param isCenter 设置marker 中心点位置在latlng上
     */
    public void addMarker(LatLng latLng, int drawID, boolean isCenter) {
        mAMap.addMarker(setMarker(latLng, drawID, isCenter));
    }

    /**
     * 添加markers
     *
     * @param Markers
     */
    public void addMarkers(ArrayList<MarkerOptions> Markers) {
        if (Markers == null && Markers.size() == 0) {
            return;
        }
        for (MarkerOptions markerOptions : Markers) {
            mAMap.addMarker(markerOptions);
        }
    }

    /**
     * 设置自定义marker
     *
     * @param latLng
     * @param drawID
     * @return
     */
    public MarkerOptions setMarker(LatLng latLng, int drawID, boolean isCenter) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(false);
        if (isCenter) {
            markerOptions.anchor((float) 0.5, (float) 0.5);
        }
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(mContext.getResources(), drawID)));
        return markerOptions;
    }

    /**
     * 地图划线
     *
     * @param latLngs
     */
    @SuppressLint("NewApi")
    public void setPolyline(ArrayList<LatLng> latLngs) {
        mAMap.addPolyline((new PolylineOptions())
                .addAll(latLngs)
                .width(5).color(Color.argb(255, 255, 64, 129)));
    }

    /**
     * 设置某个坐标为中线点
     *
     * @param latLng
     */
    public void moveMapCenter(LatLng latLng) {
        mAMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
    }

    /**
     * 根据地图设置的经纬度 自动缩放级别
     *
     * @param latLngs 地图上设置的经纬度集合
     * @param size    经纬度距离上下左右屏幕间隔
     */
    public void setMapwithBounds(ArrayList<LatLng> latLngs, int size) {
        LatLngBounds.Builder bounds = new LatLngBounds.Builder();
        for (LatLng latLng:latLngs){
            bounds.include(latLng);
        }
        mAMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), size));
    }

    /**
     * 设置地图缩放级别
     *
     * @param zoom (4-20)
     */
    public void setMapZoomTo(float zoom) {
        mAMap.moveCamera(CameraUpdateFactory.zoomTo(zoom));
    }

    /**
     * 根据当前经纬度设置地图缩放级别
     *
     * @param latLng
     * @param zoom   (4-20)
     */
    public void setMapnewLatLngZoom(LatLng latLng, float zoom) {
        mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    public void addCircle(LatLng latLng) {
        mAMap.addCircle(new CircleOptions().
                center(latLng).
                radius(50).
                fillColor(Color.argb(125, 63, 81, 181)).
                strokeColor(Color.argb(0, 0, 0, 0)).
                strokeWidth(5)
        );

    }

    /**
     * 定位
     */

    public void Location() {
        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = null;
        mapLocationClient = new AMapLocationClient(mContext);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mapLocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(5000);
        //定位超时时间
        mLocationOption.setHttpTimeOut(20000);
        //设置是否强制刷新WIFI，默认为true，强制刷新。
        mLocationOption.setWifiActiveScan(false);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //设置定位参数
        mapLocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mapLocationClient.startLocation();
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation!=null){
            LocationEntity locationEntity=new LocationEntity();
            locationEntity.setAdCode(aMapLocation.getAdCode());
            locationEntity.setAccuracy(aMapLocation.getAccuracy());
            locationEntity.setAddress(aMapLocation.getAddress());
            locationEntity.setAoiName(aMapLocation.getAoiName());
            locationEntity.setBuildingId(aMapLocation.getBuildingId());
            locationEntity.setCity(aMapLocation.getCity());
            locationEntity.setCityCode(aMapLocation.getCityCode());
            locationEntity.setProvince(aMapLocation.getProvince());
            locationEntity.setCountry(aMapLocation.getCountry());
            locationEntity.setDistrict(aMapLocation.getDistrict());
            locationEntity.setLatitude(aMapLocation.getLatitude());
            locationEntity.setLongitude(aMapLocation.getLongitude());
            SessionManager.getInstance().setEntity(locationEntity);
            mapLocationClient.stopLocation();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(aMapLocation.getTime());
            df.format(date);
            Log.d("=aMapLocation=",df+"");
            Log.d("=aMapLocation=",date+"");
        }
    }
}
