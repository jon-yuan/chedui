package com.babuwyt.daili.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.babuwyt.daili.R;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.finals.SharePrefKeys;
import com.babuwyt.daili.utils.util.SharePreferencesUtils;

import org.xutils.view.annotation.ContentView;

/**
 * Created by lenovo on 2017/6/30.
 */


@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity {

    /**
     * 打开Preferences，名称为firstLogin，如果存在则打开它，否则创建新的Preferences
     */
    private SharedPreferences XML_PREFERENCES = null;
    /**
     * 定义 first_use 判断是否第一次使用
     */
    private boolean first_use;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init(){
        timeDown();

    }


    //两秒后页面切换
    private void timeDown(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpToActivity();
            }
        }, 2000);
    }

    /**
     * 判断是否需要进入登录页面
     */
    private void isLogin(){
        //todo 判断是否已经登陆
        if (SessionManager.getInstance().isLogin()){
            startActivity(new Intent(this,MainActivity.class));
        }else {
            startActivity(new Intent(this,LoginActivity.class));

        }
        finish();
    }

    /**
     * 判断是否已经第一次使用
     */
    private boolean isFirst(){
        /*打开Preferences，如果存在则打开它，否则创建新的Preferences*/
        XML_PREFERENCES = getSharedPreferences(SharePrefKeys.XML_PREFERENCES, 0);
                /*取得相应的值，如果没有该值，说明还未写入，用true作为默认值 */
        return first_use = SharePreferencesUtils.getBoolean(WelcomeActivity.this, SharePrefKeys.XML_FIRST_USE
                , SharePrefKeys.XML_FIRST_USE, true);
    }
    /**
     * 判断逻辑
     *
     */

    private void jumpToActivity(){
        isLogin();
//        if (isFirst()) {
//            //如果是第一次使用 则需要进入引导
//            goGuide();
//
//        } else {
//            isLogin();
//        }
    }

    //进入引导页面
    private void goGuide(){
        startActivity(new Intent(this,GuideActivity.class));
        finish();

    }
}
