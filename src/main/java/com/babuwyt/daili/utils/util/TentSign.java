package com.babuwyt.daili.utils.util;

import android.text.TextUtils;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lenovo on 2017/9/27.
 */

public class TentSign {
    public static String appId="1253538594";
    public static String bucket="bbkj";
    public static String secretId="AKIDb7TktwNXbefUDoTYo6EBmLjwyqcG44FE";
    public static String secretKey="0pUyHUYZQLsFap2VmsmVHxToyAB2lKiu";
    public static String region="gz";

    /**
     * APP签名
     * @return
     *
     * appid:'1253538594',
        bucket : 'bbkj',
        sid : 'AKIDb7TktwNXbefUDoTYo6EBmLjwyqcG44FE',
        skey : '0pUyHUYZQLsFap2VmsmVHxToyAB2lKiu',
        region : 'gz',//地域信息 必填参数 华南地区填gz 华东填sh 华北填tj
     */
    /**
     *
     * @param costPath  为null时为多次签名  有资源路径时为单次签名（用于删除文件）
     * @return
     */
    public static String getSingImg (String costPath){
        Calendar cal = Calendar.getInstance();
        String retrunsign = "";
        cal.add(Calendar.MONTH, 3);
        long e=0;
        String fileId="";
        try {
            long t = (new Date()).getTime()/1000;
            if (costPath==null){
                e = (cal.getTime().getTime())/1000;
            }else {
                fileId="/"+appId+"/"+bucket+"/"+costPath;
                fileId=HMACSHA1.toURLEncoded(fileId);
            }
            String getsign = "a="+appId+"&b="+bucket+"&k="+secretId+"&e="+e+"&t="+t+"&r="+HMACSHA1.getRandom()+"&u=0&f="+fileId;
            byte[] bytetemp = HMACSHA1.HmacSHA1Encrypt(getsign,secretKey);
            String sign = HMACSHA1.encodeData(HMACSHA1.unitByteArray(bytetemp, getsign.getBytes()));
            if(!TextUtils.isEmpty(sign)){
                retrunsign = sign ;
                Log.d("retrunsign=",""+retrunsign);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return retrunsign;
    }
}
