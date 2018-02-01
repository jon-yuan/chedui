package com.babuwyt.daili.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.ClientApp;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.bean.VersionBean;
import com.babuwyt.daili.entity.VersionEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.inteface.MyProgressCallBack;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by lenovo on 2017/6/28.
 */
@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {
    @ViewInject(R.id.btn_logout)
    TextView btn_logout;
    @ViewInject(R.id.tv_xiugaimima)
    LinearLayout xiugaimima;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.layout_version)
    LinearLayout layout_version;
    @ViewInject(R.id.tv_version)
    TextView tv_version;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        toolbar.setTitle(getString(R.string.setting));
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_version.setText(UHelper.getAppVersionInfo(this,UHelper.TYPE_VERSION_NAME));
    }
    @Event(value = {R.id.btn_logout,R.id.tv_xiugaimima,R.id.layout_version})
    private void getE(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:
//                get();
                isLogout();
                break;
            case R.id.tv_xiugaimima:
                startActivity(new Intent(this,ChengePsdActivity.class));
                break;
            case R.id.layout_version:
                getVersion();


                break;
        }
    }

    /**
     * 退出登录，清楚缓存
     */
    private void logOut(){
        ((ClientApp)getApplication()).clearLoginUser();
        Intent intent=new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    /**
     * 提示用户是否确定退出登录
     */
    @SuppressLint("ResourceAsColor")
    private void isLogout(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setTitle("退出登录");
        builder.setTitle(getString(R.string.logout));
        builder.setCancelable(true);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                logOut();
            }
        });
        builder.create().show();
    }


    //版本检测
    private void getVersion() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(3 + "");
        XUtil.GetPing(BaseURL.CHECKVERSION, list, new MyCallBack<VersionBean>() {
            @Override
            public void onSuccess(VersionBean o) {
                if (o.isSuccess()) {
                    setVersion(o.getObj());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
        });
    }

    private void setVersion(final VersionEntity entity) {
        String vsersionCode = UHelper.getAppVersionInfo(this, UHelper.TYPE_VERSION_CODE);
        if (entity.getFversion() > Integer.parseInt(vsersionCode)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.new_version));
            builder.setMessage(entity.getFupdateinfo());
            builder.setPositiveButton(getString(R.string.updata), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    DownLoadFile(entity.getFurl());
                }
            });
            if (entity.getFisforceupdate()) {
                builder.setCancelable(false);
            } else {
                builder.setCancelable(true);
                builder.setNegativeButton(getString(R.string.cancal), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
            }
            builder.create().show();
        }else {
            UHelper.showToast(this,getString(R.string.is_new_version));
        }
    }

    /**
     * 下载现版本APP
     */
    private File filepath;

    private void DownLoadFile(String url) {

        final String fileName = "chedui.apk";
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // 获取SD卡的目录
            String path=Environment.getExternalStorageDirectory().getPath()+ File.separator + "apk" + File.separator;
            File file1=new File(path);
            if (!file1.exists()){
                file1.mkdir();
            }
            filepath=new File(path+fileName);
        }
        // 准备进度条Progress弹窗
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setTitle("下载中...");
        //Progress弹窗设置为水平进度条
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条

        //"http://acj3.pc6.com/pc6_soure/2017-8/me.ele_190.apk"
        XUtil.DownLoadFile(url, filepath.getPath().toString(), new MyProgressCallBack<File>() {
            @Override
            public void Started() {
                dialog.show();
            }

            @Override
            public void Success(File o) {
                dialog.dismiss();
                install(filepath);
            }

            @Override
            public void Loading(long total, long current, boolean isDownloading) {
                dialog.setMax((int) total);
                dialog.setProgress((int) current);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialog.dismiss();
            }
        });

    }


    private void install(File filePath) {
        File apkFile = filePath;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(
                    this
                    , "com.babuwyt.daili.fileprovider"
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }


}
