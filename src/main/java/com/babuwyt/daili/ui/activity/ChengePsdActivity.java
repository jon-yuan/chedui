package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.babuwyt.daili.R;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.utils.util.MD5Utils;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lenovo on 2017/8/8.
 */
@ContentView(R.layout.activity_chengepsd)
public class ChengePsdActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.btn_chenge)
    Button btn_chenge;
    @ViewInject(R.id.et_phone)
    EditText et_phone;
    @ViewInject(R.id.et_code)
    EditText et_code;
    @ViewInject(R.id.tv_code)
    TextView tv_code;
    @ViewInject(R.id.et_psd)
    EditText et_psd;
    @ViewInject(R.id.et_psd_mt)
    EditText et_psd_mt;
    private int time=60;
    private Timer timer;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(time>0){
                tv_code.setText("("+time+") 秒后获取");
                tv_code.setEnabled(false);
                tv_code.setBackgroundResource(R.color.main_gray_line);
            }else{

                timer.cancel();
                timer=null;
                time=60;
                tv_code.setText("获取验证码");
                tv_code.setEnabled(true);
                tv_code.setBackgroundResource(R.color.colorPrimary);
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        toolbar.setTitle(getString(R.string.chengePsd));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /**
         * 默认初始状态不能点击
         */
        tv_code.setEnabled(false);
        tv_code.setBackgroundResource(R.color.main_gray_line);
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()<11 || timer!=null){
                    tv_code.setBackgroundResource(R.color.main_gray_line);
                    tv_code.setEnabled(false);
                }else {
                    tv_code.setBackgroundResource(R.color.colorPrimary);
                    tv_code.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Event(value = {R.id.tv_code,R.id.btn_chenge})
    private void getE(View v){
        switch (v.getId()){
            case R.id.tv_code:
                sendCode();
                break;
            case R.id.btn_chenge:
                changePsd();
                break;
        }
    }

    /**
     * 60秒倒计时
     */

    private void timeDown(){
        if (!UHelper.isPhone(et_phone.getText().toString().trim())){
            UHelper.showToast(this,getString(R.string.phone_fail));
            return;
        }
        timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                time--;
                Message msg=handler.obtainMessage();
                msg.what=1;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask,0,1000);

    }
    private String ischengePsd(String chid){

        if (chid.equalsIgnoreCase("1")){
            if (!UHelper.isPhone(et_phone.getText().toString().trim())){
                return getString(R.string.phone_fail);
            }
        }else {
            if (!UHelper.isPhone(et_phone.getText().toString().trim())){
                return getString(R.string.phone_fail);
            }
            if (TextUtils.isEmpty(et_code.getText().toString().trim())){
                return getString(R.string.autecode_null);
            }
            if (TextUtils.isEmpty(et_psd.getText().toString().trim())){
                return getString(R.string.psd_null);
            }
            if (et_psd.getText().toString().trim().length()<6){
                return getString(R.string.psd_xiaoyu_6);
            }
            if (!UHelper.isPsd(et_psd.getText().toString().trim())){
                return getString(R.string.psd_geshi_fail);
            }
            if (!et_psd.getText().toString().trim().equals(et_psd_mt.getText().toString().trim())){
                return getString(R.string.two_input_psd_is_no_same);
            }
        }
        return "";
    }

    //修改密码 1获取验证码  2 修改密码
    private void changePsd(){
        if (!TextUtils.isEmpty(ischengePsd("2"))){
            UHelper.showToast(ChengePsdActivity.this,ischengePsd("2"));
            return;
        }
        Map<String ,String> map=new HashMap<String, String>();
        map.put("fid", SessionManager.getInstance().getUser().getFid());
        map.put("password",MD5Utils.encrypt(et_psd_mt.getText().toString().trim()));
        map.put("checkid","2");
        map.put("tel",et_phone.getText().toString().trim());
        map.put("token",et_code.getText().toString().trim());


        ArrayList<String> list=new ArrayList<String>();
        list.add(et_phone.getText().toString().trim());
        list.add(MD5Utils.encrypt(et_psd_mt.getText().toString().trim()));
        list.add(et_code.getText().toString().trim());

        XUtil.Post(BaseURL.CHENGEPSD,list,new MyCallBack<BaseBean>(){
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                Gson gson = new Gson();
                String obj2 = gson.toJson(result);
                Log.d("floginname",obj2);
                if (result.isSuccess()){
                    UHelper.showToast(ChengePsdActivity.this,"密码修改成功");
                    finish();
                }else {
                   UHelper.showToast(ChengePsdActivity.this,result.getMsg());
//
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                Log.d("失败",ex+"");
                UHelper.showToast(ChengePsdActivity.this,getString(R.string.login_neterr));
            }
        });
    }

    //修改密码 1获取验证码  2 修改密码
    private void sendCode(){
        ArrayList<String> list=new ArrayList<String>();
        list.add(et_phone.getText().toString().trim());
        XUtil.GetPing(BaseURL.GETAUTHCODE,list,new MyCallBack<BaseBean>(){
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()){
                    timeDown();
                    UHelper.showToast(ChengePsdActivity.this,"验证码已发送到该手机");
                }else {
                    UHelper.showToast(ChengePsdActivity.this,result.getMsg());
//
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                Log.d("失败",ex+"");
                UHelper.showToast(ChengePsdActivity.this,getString(R.string.login_neterr));
            }
        });
    }


}
