package com.babuwyt.daili.utils.util;

import android.util.Log;
import android.widget.ImageView;

import com.babuwyt.daili.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by lenovo on 2017/6/28.
 */

public class XUtil {

    public static RequestParams XRequestParams(){
        RequestParams params=new RequestParams();
        params.addQueryStringParameter("Token","");
        params.addQueryStringParameter("userid","");

        return params;
    }

    /**
     * 发送get请求
     * @param <T>
     */
    public static <T> Cancelable Get(RequestParams params, CommonCallback<T> callback){
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }
    public static <T> Cancelable GetJson(String url, JSONObject jsonObject, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject.toString());
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }
    //有webtoken  要在headers里面添加
    public static <T> Cancelable GetJsonObj(String url, Map<String,Object> map, CommonCallback<T> callback){
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象

        if(null!=map){
            try{
                for(Map.Entry<String, Object> entry : map.entrySet()){
                    js_request.put(entry.getKey(),entry.getValue());
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }


        RequestParams params=new RequestParams(url);
        params.setAsJsonContent(true);
        params.setBodyContent(new Gson().toJson(map));


        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }

    /**
     * 该方法比较奇葩，由于服务求要求get参数为uri/参数1/参数2,因此 参数只要参数值拼接到后面即可
     * @param url
     * @param list
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> Cancelable GetPing(String url, ArrayList<String> list, CommonCallback<T> callback){
        StringBuffer stringBuffer=new StringBuffer(url);
        if(null!=list){
            for(String entry : list){
                stringBuffer.append("/");
                stringBuffer.append(entry);
            }
        }
        RequestParams params=new RequestParams(stringBuffer.toString());
        Log.d("=stringBuffer=",stringBuffer.toString());
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }
    public static <T> Cancelable GetPing(String url, ArrayList<String> list,String token, CommonCallback<T> callback){
        StringBuffer stringBuffer=new StringBuffer(url);
        if(null!=list){
            for(String entry : list){
                stringBuffer.append("/");
                stringBuffer.append(entry);
            }
        }
        RequestParams params=new RequestParams(stringBuffer.toString());
        if (token!=null){
            params.addHeader("webtoken",token);
        }
        Log.d("=stringBuffer=",stringBuffer.toString());
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }

    public static <T> Cancelable PostPing(String url, ArrayList<String> list, CommonCallback<T> callback){
        StringBuffer stringBuffer=new StringBuffer(url);
        if(null!=list){
            for(String entry : list){
                stringBuffer.append("/");
                stringBuffer.append(entry);
            }
        }
        RequestParams params=new RequestParams(stringBuffer.toString());
        Log.d("=stringBuffer=",stringBuffer.toString());
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }

    /**
     * 发送post请求
     * @param <T>
     */
    public static <T> Cancelable Post(String url, Map<String,String> map, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        if(null!=map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }
    public static <T> Cancelable PostJsonObj(String url, Map<String,Object> map, CommonCallback<T> callback){
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        if(null!=map){
            try{
                for(Map.Entry<String, Object> entry : map.entrySet()){
                    js_request.put(entry.getKey(),entry.getValue());
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        RequestParams params=new RequestParams(url);
        params.setAsJsonContent(true);
        params.setBodyContent(new Gson().toJson(map));
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }
    public static <T> Cancelable PostJsonObj(String url, Map<String,Object> map,String token, CommonCallback<T> callback){
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        if(null!=map){
            try{
                for(Map.Entry<String, Object> entry : map.entrySet()){
                    js_request.put(entry.getKey(),entry.getValue());
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        RequestParams params=new RequestParams(url);
        if (token!=null){
            params.addHeader("webtoken",token);
        }
        params.setAsJsonContent(true);
        params.setBodyContent(new Gson().toJson(map));
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }
    public static <T> Cancelable Post(String url, ArrayList<String> list,String token, CommonCallback<T> callback){
        StringBuffer stringBuffer=new StringBuffer(url);
        if(null!=list){
            for(String entry : list){
                stringBuffer.append("/");
                stringBuffer.append(entry);
            }
        }
        RequestParams params=new RequestParams(stringBuffer.toString());
        if (token!=null){
            params.addHeader("webtoken",token);
        }
        Log.d("=stringBuffer=",stringBuffer.toString());
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }
    public static <T> Cancelable PostJson(String url, Map<String,String> map, CommonCallback<T> callback){
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象

        if(null!=map){
            try{
                for(Map.Entry<String, String> entry : map.entrySet()){
                    js_request.put(entry.getKey(),entry.getValue());
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        RequestParams params=new RequestParams(url);
        params.setAsJsonContent(true);
        params.setBodyContent(js_request.toString());
        Log.d("===postparams===",params+"");
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }
    public static <T> Cancelable Post(String url, ArrayList<String> list, CommonCallback<T> callback){
        StringBuffer stringBuffer=new StringBuffer(url);
        if(null!=list){
            for(String entry : list){
                stringBuffer.append("/");
                stringBuffer.append(entry);
            }
        }
        RequestParams params=new RequestParams(stringBuffer.toString());
        Log.d("=stringBuffer=",stringBuffer.toString());
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }
    public static <T> Cancelable PostJson(RequestParams params, CommonCallback<T> callback){
//        params.setAsJsonContent(true);
//        params.setBodyContent(jsonObject.toString());
//        params.addBodyParameter(jsonObject+);
        Log.d("===postparams===",params+"");
//        Log.d("===jsonObject===",jsonObject.toString()+"");
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }
    /**
     * 上传文件
     * @param <T>
     */
    public static <T> Cancelable UpLoadFile(String url, Map<String,Object> map, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        if(null!=map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }

    /**
     * 下载文件
     * @param <T>
     */
    public static <T> Cancelable DownLoadFile(String url, String filepath, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }

    /**
     * 图片未加载出来之前显示的默认图片
     */
    public static ImageOptions options(boolean Circular){
        ImageOptions options=new ImageOptions.Builder().
                setLoadingDrawableId(R.drawable.icon_normal_pic)
                .setUseMemCache(true).setCircular(Circular)
                .build();
        return options;
    }
    public static ImageOptions options(ImageView.ScaleType imageScaleType){
        ImageOptions options=new ImageOptions.Builder().
                setLoadingDrawableId(R.color.main_gray_line)
                .setUseMemCache(true)
                .setImageScaleType(imageScaleType)
                .build();
        return options;
    }
}







