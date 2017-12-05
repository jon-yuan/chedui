package com.babuwyt.daili.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.babuwyt.daili.R;
import com.babuwyt.daili.adapter.MySijiAdapter;
import com.babuwyt.daili.adapter.MySijiPopAdapter;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.bean.SijiListBean;
import com.babuwyt.daili.bean.SystemPramerBean;
import com.babuwyt.daili.entity.CarType;
import com.babuwyt.daili.entity.SijiEntity;
import com.babuwyt.daili.entity.SystemPramer;
import com.babuwyt.daili.finals.BaseURL;
import com.babuwyt.daili.ui.views.MakeTrueDialog;
import com.babuwyt.daili.ui.views.RatesDialog;
import com.babuwyt.daili.inteface.MyCallBack;
import com.babuwyt.daili.ui.views.RecycleViewDivider;
import com.babuwyt.daili.utils.util.UHelper;
import com.babuwyt.daili.utils.util.XUtil;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/1.
 */
@ContentView(R.layout.activity_siji)
public class MySijiActivity extends BaseActivity implements MySijiAdapter.MySijiAdapterOnItemClick, RatesDialog.RatesDialogCallBack, MakeTrueDialog.Commit{
    public static String MYSIJI_TYPE = "sijit_type";  //查看司机
    public static String MYSIJI_ADDRESS = "sijit_address";  //地址
    public static int LOOKSIJI = 0;  //查看司机
    public static int SELECTSIJI = 1;//选择司机
    private String title;
    private String address = "";
    private int show = 0;//默认进来之后为查看司机
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerview;
    @ViewInject(R.id.layout_address)
    LinearLayout layout_address;
    @ViewInject(R.id.tv_address)
    TextView tv_address;
    @ViewInject(R.id.tv_search)
    TextView tv_search;
    @ViewInject(R.id.et_search)
    EditText et_search;
    @ViewInject(R.id.springview)
    SpringView springview;
    private RecyclerView.LayoutManager manager;
    private MySijiAdapter mAdapter;
    private PopupWindow popup;
    private View contentView;
    private Intent intent;
    private String fid;//订单ID
    private String shijian;//订单ID
    private String xianlu;//订单ID
    private ArrayList<SijiEntity> mList;
    private RatesDialog dialog;
    private MakeTrueDialog makeTrueDialog;
    private String phonenum;
    private int pageName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initview();
        initPopup();
        getHttpRefresh();
    }

    private void initview() {
        dialog = new RatesDialog(this);
        dialog.setCallBack(this);
        makeTrueDialog = new MakeTrueDialog(this);
        makeTrueDialog.setCommit(this);
        manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        mAdapter = new MySijiAdapter(this, show);
        mList = new ArrayList<SijiEntity>();
        mAdapter.setmList(mList);
        recyclerview.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.main_gray_line)));
        recyclerview.setAdapter(mAdapter);
        mAdapter.setItemClick(this);
    }

    private void init() {
        intent = new Intent();
        show = getIntent().getIntExtra(MYSIJI_TYPE, 0);
        if (show == LOOKSIJI) {
            title = getString(R.string.my_siji);
            layout_address.setVisibility(View.GONE);
        } else {
            address = getIntent().getStringExtra(MYSIJI_ADDRESS);
            fid = getIntent().getStringExtra("fid");
            shijian = getIntent().getStringExtra("shijian");
            xianlu = getIntent().getStringExtra("xianlu");
            title = getString(R.string.select_siji);
            layout_address.setVisibility(View.VISIBLE);
            tv_address.setText(address);
        }
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.goback_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        springview.setType(SpringView.Type.FOLLOW);
        springview.setHeader(new DefaultHeader(this));
        springview.setFooter(new DefaultFooter(this));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getHttpRefresh();
            }

            @Override
            public void onLoadmore() {
                getHttpLoadMore();
            }
        });
    }

    public void initPopup() {
        //从指定的xml添加一个view
        contentView = getLayoutInflater().inflate(R.layout.popup_window, null);
        popup = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setFocusable(true);//设置焦点
        popup.setBackgroundDrawable(new BitmapDrawable());//设置背景位图
//        popup.setAnimationStyle(R.style.bottom_popwindow_animation);//设置动画
    }

    private void showPopWindow() {
        ArrayList<CarType> list = new ArrayList<CarType>();
        for (int i = 0; i < 20; i++) {
            CarType carType = new CarType();
            if (i == 0) {
                carType.setCartype((17.5 + i) + "");
                carType.setSelect(true);
            } else {
                carType.setCartype((17.5 + i) + "");
                carType.setSelect(false);
            }
            list.add(carType);
        }

        RecyclerView recyclerview = contentView.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(new MySijiPopAdapter(this, list));
        popup.showAsDropDown(toolbar);//在父容器的位置
    }

    @Override
    public void onItemClick(int position, View view) {
        Intent intent = new Intent();
        intent.setClass(this, SijiDetailsActivity.class);
        intent.putExtra("id", mList.get(position).getFdriverid());
        startActivity(intent);
    }

    @Override
    public void CallBackPhone(int i) {
        phonenum=mList.get(i).getFphonenum();
        if (ContextCompat.checkSelfPermission(MySijiActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(MySijiActivity.this,
                    Manifest.permission.CALL_PHONE)) {
                // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                // 弹窗需要解释为何需要该权限，再次请求授权
                Toast.makeText(MySijiActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                // 帮跳转到该应用的设置界面，让用户手动授权
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                // 不需要解释为何需要该权限，直接请求授权
                ActivityCompat.requestPermissions(MySijiActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        1);
            }
        } else {
            // 已经获得授权，可以打电话
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phonenum));
            startActivity(intent);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + phonenum));
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setMessage("您没有授权成功，无法使用拨打电话功能");
                    builder.setTitle("授权失败");
                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNegativeButton("好", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent =  new Intent(Settings.ACTION_SETTINGS);
                            startActivity(intent);
                        }
                    });
                    builder.setCancelable(false);
                    builder.create().show();
                }
                break;
            }
        }

    }


    @Override
    public void CallBackSelect(int i) {

        getSystem(mList.get(i));
    }

    @Event(value = {R.id.tv_search})
    private void gete(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                getHttpRefresh();
                break;
        }
    }
    private void getHttpRefresh() {
        pageName=0;
        Map<String, String> map = new HashMap<String, String>();
        map.put("pagenum", pageName+"0");
        map.put("key", et_search.getText().toString());
        map.put("code", SessionManager.getInstance().getUser().getFforwardercode() + "");
        loadingDialog.showDialog();
        XUtil.PostJson(BaseURL.SEARCHSIJI, map, new MyCallBack<SijiListBean>() {
            @Override
            public void onSuccess(SijiListBean sijiListBean) {
                loadingDialog.dissDialog();
                if (sijiListBean.isSuccess()) {
                    mList.clear();
                    mList.addAll(sijiListBean.getObj());
                    mAdapter.notifyDataSetChanged();
                } else {
                    UHelper.showToast(MySijiActivity.this, sijiListBean.getMsg());
                }
                springview.onFinishFreshAndLoad();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }

    private void getHttpLoadMore() {
        pageName++;
        Map<String, String> map = new HashMap<String, String>();
        map.put("pagenum", pageName+"0");
        map.put("key", et_search.getText().toString());
        map.put("code", SessionManager.getInstance().getUser().getFforwardercode() + "");
        loadingDialog.showDialog();
        XUtil.PostJson(BaseURL.SEARCHSIJI, map, new MyCallBack<SijiListBean>() {
            @Override
            public void onSuccess(SijiListBean sijiListBean) {
                loadingDialog.dissDialog();
                if (sijiListBean.isSuccess()) {
                    mList.addAll(sijiListBean.getObj());
                    mAdapter.notifyDataSetChanged();
                } else {
                    UHelper.showToast(MySijiActivity.this, sijiListBean.getMsg());
                }
                springview.onFinishFreshAndLoad();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loadingDialog.dissDialog();
                springview.onFinishFreshAndLoad();
            }
        });
    }

    private void getSystem(final SijiEntity entity) {
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.SYSTEM_PARAMS, new ArrayList<String>(), new MyCallBack<SystemPramerBean>() {
            @Override
            public void onSuccess(SystemPramerBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()) {
                    SystemPramer pramer = result.getObj();
                    pramer.setFtruckid(entity.getCarid());
                    pramer.setFmanageid(entity.getFdriverid());
                    pramer.setFid(fid);
                    pramer.setSijiname(entity.getFdrivername());
                    pramer.setShijian(shijian);
                    pramer.setXianlu(xianlu);
                    dialog.setParams(pramer);
                    dialog.showDialog();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
            }
        });
    }

    @SuppressLint("NewApi")
    @Override
    public void callBack(SystemPramer pramer) {
        dialog.dissDialog();
        makeTrueDialog.showDialog();
        makeTrueDialog.setData(pramer);
    }

    @Override
    public void commit(SystemPramer pramer) {
        makeTrueDialog.dissDialog();
        getCommit(pramer);

    }

    private void getCommit(SystemPramer pramer) {

        Log.d("参数", new Gson().toJson(pramer));
        RequestParams params = new RequestParams(BaseURL.PUBLISHSENDCAR);
        params.setAsJsonContent(true);
        params.setBodyContent(new Gson().toJson(pramer));
        loadingDialog.showDialog();
        x.http().post(params, new MyCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()) {
                    Intent intent = new Intent(MySijiActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                UHelper.showToast(MySijiActivity.this, result.getMsg());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
            }
        });
    }
}
