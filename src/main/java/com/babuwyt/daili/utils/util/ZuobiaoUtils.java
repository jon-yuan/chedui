package com.babuwyt.daili.utils.util;

import android.util.Log;


import java.text.NumberFormat;

/**
 * Created by lenovo on 2017/8/28.
 */

public class ZuobiaoUtils {
    static double x_PI = 3.14159265358979324 * 3000.0 / 180.0;
    static double PI = 3.1415926535897932384626;
    static double a = 6378245.0;
    static double ee = 0.00669342162296594323;

    public static String bd09togcj02 (double bd_lon,double bd_lat){
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_PI);
        Log.d("坐标转换",z * Math.cos(theta) + "," + z * Math.sin(theta));
        gcj02towgs84(z * Math.cos(theta),z * Math.sin(theta));
        return z * Math.cos(theta) + "," + z * Math.sin(theta);
    }

    /**
     * /**
     * WGS84转GCj02
     * @param lng
     * @param lat
     * @returns {*[]}
     */


//    public static Gps wgs84togcj02(double lng, double lat) {
//        Gps gps=new Gps();
//        NumberFormat numberFormat=NumberFormat.getNumberInstance();
//        numberFormat.setGroupingUsed(false);
//        if (out_of_china(lng, lat)) {
//            gps.setLng(lng);
//            gps.setLat(lat);
//            return gps;
//        }
//        else {
//            double dlat = transformlat(lng - 105.0, lat - 35.0);
//            double dlng = transformlng(lng - 105.0, lat - 35.0);
//            double radlat = lat / 180.0 * PI;
//            double magic = Math.sin(radlat);
//            magic = 1 - ee * magic * magic;
//            double sqrtmagic = Math.sqrt(magic);
//            dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * PI);
//            dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * PI);
//            double mglat = lat + dlat;
//            double mglng = lng + dlng;
//            gps.setLng(mglng);
//            gps.setLat(mglat);
//            return gps;
//        }
//    }


    public static String gcj02towgs84(double lng, double lat) {
        if (out_of_china(lng, lat)) {
            Log.d("坐标转换1",lng+"=="+lat);
            return lng+"=="+lat;
        }
        else {
            double dlat = transformlat(lng - 105.0, lat - 35.0);
            double dlng = transformlng(lng - 105.0, lat - 35.0);
            double radlat = lat / 180.0 * PI;
            double magic = Math.sin(radlat);
            magic = 1 - ee * magic * magic;
            double sqrtmagic = Math.sqrt(magic);
            dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * PI);
            dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * PI);
             double mglat = lat + dlat;
            double mglng = lng + dlng;
            Log.d("坐标转换2",mglat+"=="+mglng);
            return mglat+"=="+mglng;
        }
    }

    private static double transformlat(double lng, double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * PI) + 40.0 * Math.sin(lat / 3.0 * PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * PI) + 320 * Math.sin(lat * PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformlng(double lng,double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * PI) + 40.0 * Math.sin(lng / 3.0 * PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * PI) + 300.0 * Math.sin(lng / 30.0 * PI)) * 2.0 / 3.0;
        return ret;
    }

    private static boolean out_of_china(double lng,double lat) {
        return (lng < 72.004 || lng > 137.8347) || ((lat < 0.8293 || lat > 55.8271) || false);
    }
}
