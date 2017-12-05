package com.babuwyt.daili.utils.util;

import android.content.Context;

/**
 * 屏幕分辨率
 *
 * @author 吳同
 */
public class DensityUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getApplicationContext().getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getApplicationContext().getResources()
                .getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽度px
     *
     * @param context
     * @return
     */
    public static int deviceWidthPX(Context context) {
        return context.getApplicationContext().getResources()
                .getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度px
     *
     * @param context
     * @return
     */
    public static int deviceHeightPX(Context context) {
        return context.getApplicationContext().getResources()
                .getDisplayMetrics().heightPixels;
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
