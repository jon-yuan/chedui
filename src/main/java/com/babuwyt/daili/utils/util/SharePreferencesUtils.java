package com.babuwyt.daili.utils.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by lenovo on 2017/6/30.
 */

public class SharePreferencesUtils {
    /**
     * 保存 int
     *
     * @param context 上下文环境
     * @param xmlName xml文件名
     * @param key     要保存的 key
     * @param value   要保存的 value
     */
    public static final void saveInt(Context context, String xmlName, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();

    }


    /**
     * 保存 String
     *
     * @param context 上下文环境
     * @param xmlName xml文件名
     * @param key     要保存的 key
     * @param value   要保存的 value
     */
    public static final void saveString(Context context, String xmlName, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

    }

    /**
     * 保存 boolean
     *
     * @param context 上下文环境
     * @param xmlName xml文件名
     * @param key     要保存的 key
     * @param value   要保存的 value
     */
    public static final void saveBoolean(Context context, String xmlName, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();

    }

    /**
     * 保存 float
     *
     * @param context 上下文环境
     * @param xmlName xml文件名
     * @param key     要保存的 key
     * @param value   要保存的 value
     */
    public static final void saveFloat(Context context, String xmlName, String key, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();

    }

    /**
     * 保存 long
     *
     * @param context 上下文环境
     * @param xmlName xml文件名
     * @param key     要保存的 key
     * @param value   要保存的 value
     */
    public static final void saveLong(Context context, String xmlName, String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();

    }

    /**
     * 保存 Set<String>
     *
     * @param context 上下文环境
     * @param xmlName xml文件名
     * @param key     要保存的 key
     * @param value   要保存的 value
     */
    public static final void saveSet(Context context, String xmlName, String key, Set<String> value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, value);
        editor.commit();

    }

    /**
     * 清除当前xml 某一个key对应的值
     *
     * @param context 上下文环境
     * @param xmlName xml文件名
     * @param key     要清除的key
     */
    public static void clearOne(Context context, String xmlName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除xml中所有的值
     *
     * @param context 上下文环境
     * @param xmlName xml文件名
     */
    public static void clearAll(Context context, String xmlName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 获取int 值
     *
     * @param context      上下文环境
     * @param xmlName      xml文件名
     * @param key          要获取的key
     * @param defaultValue 默认值
     * @return
     */
    public static int getInt(Context context, String xmlName, String key, int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        int value = sharedPreferences.getInt(key, defaultValue);
        return value;
    }

    /**
     * 获取 String 值
     *
     * @param context      上下文环境
     * @param xmlName      xml文件名
     * @param key          要获取的key
     * @param defaultValue 默认值
     * @return
     */
    public static String getString(Context context, String xmlName, String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, defaultValue);
        return value;
    }

    /**
     * 获取Boolean 值
     *
     * @param context      上下文环境
     * @param xmlName      xml文件名
     * @param key          要获取的key
     * @param defaultValue 默认值
     * @return
     */
    public static boolean getBoolean(Context context, String xmlName, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        boolean value = sharedPreferences.getBoolean(key, defaultValue);
        return value;
    }

    /**
     * 获取long 值
     *
     * @param context      上下文环境
     * @param xmlName      xml文件名
     * @param key          要获取的key
     * @param defaultValue 默认值
     * @return
     */
    public static long getLong(Context context, String xmlName, String key, long defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        long value = sharedPreferences.getLong(key, defaultValue);
        return value;
    }

    /**
     * 获取float 值
     *
     * @param context      上下文环境
     * @param xmlName      xml文件名
     * @param key          要获取的key
     * @param defaultValue 默认值
     * @return
     */
    public static float getFloat(Context context, String xmlName, String key, float defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        float value = sharedPreferences.getFloat(key, defaultValue);
        return value;
    }
}
