package com.babuwyt.daili.utils.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2017/9/11.
 */

public class CameraUtils {
    public final static int HANKLE_START=0x00101;
    public final static int HANKLE_END=0x00102;
    //保存bitmap到手机SDCard上 并返回绝对路径
    public static String getPath(Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            // 获取 SD 卡根目录
            String saveDir = Environment.getExternalStorageDirectory() + "/wyt_photos";
            // 新建目录
            File dir = new File(saveDir);
            if (!dir.exists()) dir.mkdir();
            // 生成文件名
            SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");
            String filename = (t.format(new Date())) + ".jpg";
            // 新建文件
            File file = new File(saveDir, filename);
            // 打开文件输出流
            fileOutputStream = new FileOutputStream(file);
            // 生成图片文件
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            // 相片的完整路径
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 压缩
     * @param path
     * @return
     */
    public static Bitmap yasuo(String path){
        long Size=FilesSizeUtil.getFileOrFileslongSize(path);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 只获取图片的大小信息，而不是将整张图片载入在内存中，避免内存溢出
        BitmapFactory.decodeFile(path, options);
        int height = options.outHeight;
        int width= options.outWidth;

        int inSampleSize = 2; // 默认像素压缩比例，压缩为原图的1/2
        int minLen = Math.min(height, width); // 原图的最小边长
//        if(minLen > 200) { // 如果原始图像的最小边长大于100dp（此处单位我认为是dp，而非px）
//            float ratio = (float)minLen / 200.0f; // 计算像素压缩比例
//            inSampleSize = (int)ratio;
//        }
        if (Size>1048576){//大于1MB
            float ratio = (float)minLen / 200.0f; // 计算像素压缩比例
            inSampleSize = (int)ratio;
//            inSampleSize=2;
        }else {
            inSampleSize=0;
        }
        options.inJustDecodeBounds = false; // 计算好压缩比例后，这次可以去加载原图了
        options.inSampleSize = inSampleSize; // 设置为刚才计算的压缩比例
        Bitmap bm = BitmapFactory.decodeFile(path, options); // 解码文件
        return bm;
    }


    public static String getPath(String path,Handler handler){
        handler.sendEmptyMessage(HANKLE_START);
        Bitmap bm =yasuo(path);
        FileOutputStream fileOutputStream = null;
        try {
            // 获取 SD 卡根目录
            String saveDir = Environment.getExternalStorageDirectory() + "/wyt_photos";
            // 新建目录
            File dir = new File(saveDir);
            if (!dir.exists()) dir.mkdir();
            // 生成文件名
            SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");
            String filename = (t.format(new Date())) + ".jpg";
            // 新建文件
            File file = new File(saveDir, filename);
            // 打开文件输出流
            fileOutputStream = new FileOutputStream(file);
            // 生成图片文件
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            // 相片的完整路径
            path=file.getPath();
            handler.sendEmptyMessage(HANKLE_END);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            handler.sendEmptyMessage(HANKLE_END);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                    handler.sendEmptyMessage(HANKLE_END);
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(HANKLE_END);
                }
            }
            handler.sendEmptyMessage(HANKLE_END);
        }
        return "";
    }
}
