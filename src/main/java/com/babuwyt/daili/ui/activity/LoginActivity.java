package com.babuwyt.daili.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.babuwyt.daili.R;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.ClientApp;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.bean.PersonInfoBean;
import com.babuwyt.daili.entity.UserInfoEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.utils.jpush.TagAliasOperatorHelper;
import com.babuwyt.daili.utils.util.MD5Utils;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/6/29.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.et_name)
    EditText et_name;
    @ViewInject(R.id.et_psd)
    EditText et_psd;
    @ViewInject(R.id.tv_login)
    Button tv_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
    }

    @Event(value ={R.id.tv_login})
    private void gete(View v){
        switch (v.getId()){
            case R.id.tv_login:
//                saveInfo();
                isEmpty();
//                getDate();
                break;
        }
    }

    /**
     * 非空判断
     */
    private void isEmpty(){
        if (TextUtils.isEmpty(et_name.getText().toString())){
            UHelper.showToast(this,getString(R.string.PROMPT_USE_IS_NOT_EMPTY));
            return;
        }
        if (TextUtils.isEmpty(et_psd.getText().toString())){
            UHelper.showToast(this,getString(R.string.PROMPT_PSD_IS_NOT_EMPTY));
            return;
        }
        if (et_psd.getText().toString().length()<6){
            UHelper.showToast(this,getString(R.string.PROMPT_PSD_IS_TOO_SHORT));
            return;
        }
        getDate();
    }


    /**
     * 登陆保存用户登陆状态的信息
     */

    private void saveInfo(UserInfoEntity userInfoEntity){
        ((ClientApp)getApplication()).saveLoginUser(userInfoEntity);
        setAlias();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void getDate(){
        if (TextUtils.isEmpty(et_name.getText().toString())){
            UHelper.showToast(this,getString(R.string.please_input_zhanghao));
            return;
        }
        if (TextUtils.isEmpty(et_psd.getText().toString())){
            UHelper.showToast(this,getString(R.string.please_input_psd));
            return;
        }

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("floginname",et_name.getText().toString());
        map.put("fpassword", MD5Utils.encrypt(et_psd.getText().toString()));
        map.put("faliasState",1);
        loadingDialog.showDialog();

        XUtil.PostJsonObj(BaseURL.LOGIN,map,new MyCallBack<PersonInfoBean>(){
            @Override
            public void onSuccess(PersonInfoBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()){
                    saveInfo(result.getObj());
                }
                UHelper.showToast(LoginActivity.this,result.getMsg());
//
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
            }
        });
    }
    private void setAlias() {
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        tagAliasBean.action = TagAliasOperatorHelper.ACTION_SET;
        tagAliasBean.alias= SessionManager.getInstance().getUser().getFid();
        tagAliasBean.isAliasAction = true;
        TagAliasOperatorHelper.getInstance().handleAction(this,1,tagAliasBean );
    }

}
