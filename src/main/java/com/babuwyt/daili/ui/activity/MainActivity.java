package com.babuwyt.daili.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.MainAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.bean.MainBean;
import com.babuwyt.daili.bean.NumBean;
import com.babuwyt.daili.bean.VersionBean;
import com.babuwyt.daili.entity.MainEntity;
import com.babuwyt.daili.entity.VersionEntity;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.inteface.MainAdapterCallBack;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.inteface.MyProgressCallBack;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainAdapterCallBack {
    @ViewInject(R.id.tv_hehuoren)
    TextView tv_hehuoren;
    @ViewInject(R.id.tv_sijinum)
    TextView tv_sijinum;
    @ViewInject(R.id.tv_ordernum)
    TextView tv_ordernum;
    @ViewInject(R.id.tv_versionName)
    TextView tv_versionName;
    @ViewInject(R.id.name)
    TextView name;
    @ViewInject(R.id.relayout_msg)
    RelativeLayout layout_msg;
    TextView tv_msgnum;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @ViewInject(R.id.linear_siji)
    LinearLayout linear_siji;
    @ViewInject(R.id.linear_yundan)
    LinearLayout linear_yundan;
    @ViewInject(R.id.linear_tuiguang)
    LinearLayout linear_tuiguang;
    @ViewInject(R.id.linear_setting)
    LinearLayout linear_setting;
    @ViewInject(R.id.linear_jiance)
    LinearLayout linear_jiance;
    /**
     * 下拉刷新
     */
    @ViewInject(R.id.springview)
    SpringView springview;
    @ViewInject(R.id.tv_yundangenzong)
    TextView tv_yundangenzong;
    @ViewInject(R.id.recycler_view)
    RecyclerView mRecyclerView;

    /**
     * 布局管理器
     */
    private RecyclerView.LayoutManager mManager;
    /**
     * 数据
     */
    private List<MainEntity> mDatas;

    /**
     * 适配器
     */
    private MainAdapter mAdapter;
    private Intent intent;
    private int pageNum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initRecyclerView();
        doSthRefresh();
        getNum();
        getVersion();

    }

    /**
     * 设置RecyclerView
     */
    private void initRecyclerView() {
        mDatas = new ArrayList<MainEntity>();
        mManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new MainAdapter(mDatas);
        mAdapter.setCallBack(this);
        mRecyclerView.setAdapter(mAdapter);
        springview.setType(SpringView.Type.FOLLOW);
        springview.setHeader(new DefaultHeader(this));
        springview.setFooter(new DefaultFooter(this));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                doSthRefresh();
            }

            @Override
            public void onLoadmore() {
                doSthLoadMore();
            }
        });
    }

    /**
     * 接口调用
     */
    private void doSthRefresh() {

        pageNum = 0;
        ArrayList<String> list = new ArrayList<String>();
        list.add(pageNum + "");
        list.add(SessionManager.getInstance().getUser().getFid());
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.SELECTPAGE, list, new MyCallBack<MainBean>() {
            @Override
            public void onSuccess(MainBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()) {
                    if (mDatas != null) {
                        mDatas.clear();
                        mDatas.addAll(result.getObj());
                        mAdapter.notifyDataSetChanged();
                    }
                }else {
                    UHelper.showToast(MainActivity.this,result.getMsg());
                }
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }

    private void doSthLoadMore() {
        pageNum++;
        ArrayList<String> list = new ArrayList<String>();
        list.add(pageNum + "");
        list.add(SessionManager.getInstance().getUser().getFid());
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.SELECTPAGE, list, new MyCallBack<MainBean>() {
            @Override
            public void onSuccess(MainBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()) {
                    if (mDatas != null) {
                        mDatas.addAll(result.getObj());
                        mAdapter.notifyDataSetChanged();
                    }
                }else {
                    UHelper.showToast(MainActivity.this,result.getMsg());
                }
                springview.onFinishFreshAndLoad();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }

    private void initView() {
        intent = new Intent();
        toolbar.setTitle(getString(R.string.daipaiche));
        toolbar.setNavigationIcon(R.drawable.icon_user_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        tv_msgnum = findViewById(R.id.tv_msgnum);
        tv_msgnum.setText("10");
        //显示当前APP版本名称
        tv_versionName.setText(UHelper.getAppVersionInfo(this, UHelper.TYPE_VERSION_NAME));
        name.setText(SessionManager.getInstance().getUser().getFloginname());
//        tv_hehuoren.setText("您是万运通第" + SessionManager.getInstance().getUser().getFforwardercode() + "个合伙人");
    }

    @Event(value = {R.id.linear_jiance,R.id.tv_yundangenzong, R.id.linear_siji, R.id.linear_yundan, R.id.linear_tuiguang, R.id.linear_setting})
    private void gete(View view) {
        switch (view.getId()) {
//            case R.id.relayout_msg:
//                jumpto(MsgActivity.class);
//                break;
            case R.id.tv_yundangenzong:
                jumpto(WaybillTrackingActivity.class);
                break;
            case R.id.linear_siji:
                intent.setClass(MainActivity.this, MySijiActivity.class);
                intent.putExtra(MySijiActivity.MYSIJI_TYPE, MySijiActivity.LOOKSIJI);
                startActivity(intent);
                break;
            case R.id.linear_yundan:
                jumpto(MyOrderActivity.class);
                break;
            case R.id.linear_tuiguang:
                jumpto(QRcodeActivity.class);
                break;
            case R.id.linear_setting:
                jumpto(SettingActivity.class);
                break;
                case R.id.linear_jiance:
                jumpto(BeidouCheckActivity.class);
                break;
        }
    }

    /**
     * 跳转
     */
    private void jumpto(Class c) {
        intent.setClass(MainActivity.this, c);
        startActivity(intent);
    }


    /**
     * 点击两次退出应用
     * 通过记录按键时间计算时间差实现
     */
    long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                if (System.currentTimeMillis() - exitTime > 2000) {
                    Toast.makeText(MainActivity.this, getString(R.string.onclickisexit), Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
            }
        }
        return false;
    }

    @Override
    public void CallBackQupaiche(int i) {
        intent.setClass(MainActivity.this, MySijiActivity.class);
        intent.putExtra(MySijiActivity.MYSIJI_TYPE, MySijiActivity.SELECTSIJI);
        intent.putExtra(MySijiActivity.MYSIJI_ADDRESS, mDatas.get(i).getFfromaddress());
        intent.putExtra("fid", mDatas.get(i).getFid());
        intent.putExtra("shijian", mDatas.get(i).getFpickuptime());
        intent.putExtra("xianlu", mDatas.get(i).getFfromaddress() + mDatas.get(i).getFtoaddress());
        startActivity(intent);
    }

    @Override
    public void onItemClick(int i) {
        intent.setClass(MainActivity.this, DispatchCarActivity.class);
        intent.putExtra("fid", mDatas.get(i).getFid());
        startActivity(intent);
    }

    /**
     * 获取侧滑栏数据
     */

    private void getNum() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(SessionManager.getInstance().getUser().getFid());
        list.add(SessionManager.getInstance().getUser().getFforwardercode() + "");
        XUtil.GetPing(BaseURL.FINDCOUNT, list, new MyCallBack<NumBean>() {
            @Override
            public void onSuccess(NumBean result) {
                super.onSuccess(result);
                if (result.isSuccess()) {
                    tv_ordernum.setText("订单：" + result.getObj().getOrdercount());
                    tv_sijinum.setText("司机：" + result.getObj().getDrivercount());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void getVersion() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(3 + "");
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.CHECKVERSION, list, new MyCallBack<VersionBean>() {
            @Override
            public void onSuccess(VersionBean o) {
                loadingDialog.dissDialog();
                if (o.isSuccess()) {
                    setVersion(o.getObj());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
            }
        });
    }
    private void setVersion(final VersionEntity entity) {
        String vsersionCode=UHelper.getAppVersionInfo(this,UHelper.TYPE_VERSION_CODE);
        if (entity.getFversion()>Integer.parseInt(vsersionCode)){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.new_version));
            builder.setMessage(entity.getFupdateinfo());
            builder.setPositiveButton(getString(R.string.updata), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    DownLoadFile(entity.getFurl());
                }
            });
            if (entity.getFisforceupdate()){
                builder.setCancelable(false);
            }else {
                builder.setCancelable(true);
                builder.setNegativeButton(getString(R.string.canal), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
            }
            builder.create().show();
        }
    }

    /**
     * 下载现版本APP
     */
    private File filepath;
    private void DownLoadFile(String url) {
        UHelper.showToast(this,"下载apk");
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // 获取SD卡的目录
            String path=Environment.getExternalStorageDirectory().getPath();
            filepath = new File(path + File.separator + "apk" + File.separator + "chedui.apk");//仅创建路径的File对象
            if (!filepath.exists()) {
                filepath.mkdir();//如果路径不存在就先创建路径
            }
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
