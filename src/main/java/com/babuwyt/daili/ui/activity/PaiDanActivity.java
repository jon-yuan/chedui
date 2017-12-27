package com.babuwyt.daili.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.ui.views.InPutDialog;
import com.babuwyt.daili.utils.util.Arith;
import com.babuwyt.daili.utils.util.InputMoney;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by lenovo on 2017/11/30.
 */
@ContentView(R.layout.activity_paidan)
@SuppressLint("NewApi")
public class PaiDanActivity extends BaseActivity {
    @ViewInject(R.id.tv_baojia)
    TextView tv_baojia;
    @ViewInject(R.id.tv_youkahui)
    TextView tv_youkahui;
    @ViewInject(R.id.tv_youkajine)
    TextView tv_youkajine;
    @ViewInject(R.id.tv_xianjinjine)
    TextView tv_xianjinjine;
    @ViewInject(R.id.tv_jiangli)
    TextView tv_jiangli;
    @ViewInject(R.id.tv_kouchu)
    TextView tv_kouchu;
    @ViewInject(R.id.tv_1)
    TextView tv_1;
    @ViewInject(R.id.tv_y1)
    TextView tv_y1;
    @ViewInject(R.id.tv_2)
    TextView tv_2;
    @ViewInject(R.id.tv_y2)
    TextView tv_y2;

    @ViewInject(R.id.tv_zongjine)
    TextView tv_zongjine;
    @ViewInject(R.id.tv_xiayibu)
    TextView tv_xiayibu;
    @ViewInject(R.id.tv_ykh)
    TextView tv_ykh;


    private boolean autochange = false;
    private String mYKH = "50";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mYKH=getIntent().getStringExtra("mYKH");
        init();

    }

    private void init() {
        tv_youkahui.setText(mYKH);
    }

    @Event(value = {R.id.tv_baojia, R.id.tv_youkahui, R.id.tv_xianjinjine, R.id.tv_youkajine, R.id.tv_jiangli,
            R.id.tv_kouchu, R.id.tv_1, R.id.tv_2, R.id.tv_y1, R.id.tv_y2})
    private void getE(View v) {
        switch (v.getId()) {
            case R.id.tv_baojia:
                showInput(tv_baojia, 1, tv_baojia.getText().toString().trim(), "运费报价");
                break;
            case R.id.tv_youkahui:
                showInput(tv_youkahui, 2, tv_youkahui.getText().toString().trim(), "油卡汇比例");
                break;
            case R.id.tv_xianjinjine:
                showInput(tv_xianjinjine, 3, tv_xianjinjine.getText().toString().trim(), "现金金额");
                break;
            case R.id.tv_youkajine:
                showInput(tv_youkajine, 4, tv_youkajine.getText().toString().trim(), "油卡金额");
                break;
            case R.id.tv_jiangli:
                showInput(tv_jiangli, 5, tv_jiangli.getText().toString().trim(), "油卡奖励金额");
                break;
            case R.id.tv_kouchu:
                showInput(tv_kouchu, 6, tv_kouchu.getText().toString().trim(), "现金扣除金额");
                break;
            case R.id.tv_1:
                showInput(tv_1, 7, tv_1.getText().toString().trim(), "第一次现金金额");
                break;
            case R.id.tv_2:
                showInput(tv_2, 8, tv_2.getText().toString().trim(), "第二次现金金额");
                break;
            case R.id.tv_y1:
                showInput(tv_y1, 9, tv_y1.getText().toString().trim(), "第一次油卡金额");
                break;
            case R.id.tv_y2:
                showInput(tv_y2, 10, tv_y2.getText().toString().trim(), "第二次油卡金额");
                break;
        }
    }

    private void showInput(final TextView textView, final int type, String text, String title) {
        InPutDialog dialog = new InPutDialog(this);
        dialog.settext(text);
        dialog.setTitle(title);
        if (type==2){
            dialog.isBili(true);
        }
        dialog.addInptuCallBack(new InPutDialog.InputCallBack() {
            @Override
            public void CallBackText(String text) {
                textView.setText(text);
                computeAllPrice(type);
            }
        });
        dialog.create();
        dialog.showDialog();
    }

    //计算所有金额
    private void computeAllPrice(int type) {
        switch (type) {
            case 1:
                changeBaojia();
                break;
            case 2:
                inputYKH();
                break;
            case 3:
                inputXJ();
                break;
            case 4:
                inputYk();

                break;
            case 5:

                break;
            case 6:
                break;
            case 7:
                firstXJ(1);
                break;
            case 8:
                firstXJ(2);
                break;
            case 9:
                firstYK(1);
                break;
            case 10:
                firstYK(2);
                break;
        }
        other();
    }


    /**
     * 输入报价时候 油卡金额 现金金额 根据油卡汇计算
     * 第一次油卡/现金  第二次油卡/现金都改变   默认一半一半
     */
    String bj = "";//报价
    String ykh = "";//油卡汇
    String yk = "";//油卡金额
    String xj = "";//现金金额
    String jl = "";//奖励
    String kc = "";//扣除
    String fxj = "";//第一次现金
    String fyk = "";//第一次油卡
    String sxj = "";//第二次现金
    String syk = "";//第二次油卡
    String zje = "";//总金额

    private void changeBaojia() {
        bj = tv_baojia.getText().toString().trim();
        ykh = tv_youkahui.getText().toString().trim();
        yk = Arith.mul(bj, Arith.div(ykh, "100", 2));
        xj = Arith.sub(bj, yk);
        if (Integer.parseInt(mYKH) == Integer.parseInt(ykh)) {
            jl = "0";
            kc = "0";
        } else if (Integer.parseInt(ykh) > Integer.parseInt(mYKH)) {
            String yh = Arith.sub(ykh, mYKH);
            jl = Arith.mul(bj, Arith.div(yh, "100", 2));
            kc = "0";
        } else {
            String yh = Arith.sub(ykh, mYKH);
            kc = Arith.mul(bj, Arith.div(yh, "100", 2));
            jl = "0";
        }
        tv_jiangli.setText(Double.parseDouble(jl)==0?"0":String.valueOf(Math.abs(Double.parseDouble(jl))));
        tv_kouchu.setText(Double.parseDouble(kc)==0?"0":String.valueOf(Math.abs(Double.parseDouble(kc))));
        tv_youkajine.setText(Double.parseDouble(yk)==0?"0":String.valueOf(Math.abs(Double.parseDouble(yk))));
        tv_xianjinjine.setText(Double.parseDouble(xj)==0?"0":String.valueOf(Math.abs(Double.parseDouble(xj))));
        inputYk();
        inputXJ();
    }

    /**
     * 油卡汇计算
     * 当输入油卡汇后  对应 油卡金额 现金金额 跟距比例大小计算
     * 奖励  扣除 根据比例大小 计算
     * <p>
     * 第一次油卡/现金  第二次油卡/现金都改变   默认一半一半
     */
    private void inputYKH() {
        String bj = tv_baojia.getText().toString().trim();
        String ykh = tv_youkahui.getText().toString().trim();
        if (TextUtils.isEmpty(bj) || TextUtils.isEmpty(ykh)) {
            return;
        }
        if (Double.parseDouble(bj) <= 0) {
            return;
        }
        yk = Arith.mul(bj, Arith.div(ykh, "100", 2));
        xj = Arith.sub(bj, yk);


        if (Integer.parseInt(mYKH) == Integer.parseInt(ykh)) {
            jl = "0";
            kc = "0";
        } else if (Integer.parseInt(ykh) > Integer.parseInt(mYKH)) {
            String yh = Arith.sub(ykh, mYKH);
            jl = Arith.mul(bj, Arith.div(yh, "100", 2));
            kc = "0";
        } else {
            String yh = Arith.sub(ykh, mYKH);
            kc = Arith.mul(bj, Arith.div(yh, "100", 2));
            jl = "0";
        }
        tv_jiangli.setText(Double.parseDouble(jl)==0?"0":String.valueOf(Math.abs(Double.parseDouble(jl))));
        tv_kouchu.setText(Double.parseDouble(kc)==0?"0":String.valueOf(Math.abs(Double.parseDouble(kc))));
        tv_youkajine.setText(Double.parseDouble(yk)==0?"0":String.valueOf(Math.abs(Double.parseDouble(yk))));
        tv_xianjinjine.setText(Double.parseDouble(xj)==0?"0":String.valueOf(Math.abs(Double.parseDouble(xj))));
        inputYk();
        inputXJ();
    }

    /**
     * 手动输入油卡
     * <p>
     * 第一次油卡  第二次油卡 两次想加要等于输入的油卡  默认一半一半
     */
    private void inputYk() {
        String ykje = tv_youkajine.getText().toString().trim();
        if (TextUtils.isEmpty(ykje)) {
            tv_y1.setText("");
            tv_y2.setText("");
            return;
        }
        tv_y1.setText(Double.parseDouble(Arith.div(ykje, "2", 2))==0?"0":String.valueOf(Double.parseDouble(Arith.div(ykje, "2", 2))));
        tv_y2.setText(Double.parseDouble(Arith.div(ykje, "2", 2))==0?"0":String.valueOf(Double.parseDouble(Arith.div(ykje, "2", 2))));
    }

    /**
     * 手动输入现金
     * <p>
     * 第一次现金  第二次现金 两次想加要等于输入的油卡  默认一半一半
     */
    private void inputXJ() {
        String xjje = tv_xianjinjine.getText().toString().trim();
        if (TextUtils.isEmpty(xjje)) {
            tv_1.setText("");
            tv_2.setText("");
            return;
        }
        tv_1.setText(Double.parseDouble(Arith.div(xjje, "2", 2))==0?"0":String.valueOf(Double.parseDouble(Arith.div(xjje, "2", 2))));
        tv_2.setText(Double.parseDouble(Arith.div(xjje, "2", 2))==0?"0":String.valueOf(Double.parseDouble(Arith.div(xjje, "2", 2))));
    }

    /**
     * 手动输入  奖励 和扣除  影响最终总金额
     */
    private void other() {
        yk = tv_youkajine.getText().toString().trim();
        xj = tv_xianjinjine.getText().toString().trim();
        jl = tv_jiangli.getText().toString().trim();
        kc = tv_kouchu.getText().toString().trim();
        if (TextUtils.isEmpty(yk) || TextUtils.isEmpty(xj) || TextUtils.isEmpty(jl) || TextUtils.isEmpty(kc)) {
            return;
        }
        String zje = Arith.add(Arith.add(yk, xj), Arith.sub(jl, kc));
        tv_zongjine.setText(Double.parseDouble(zje)==0?"0":String.valueOf(Double.parseDouble(zje)));
    }

    /**
     * 手动输入第一次付款 和和第二次付款
     */

    private void firstXJ(int i) {
        String xjje = tv_xianjinjine.getText().toString().trim();
        if (TextUtils.isEmpty(xjje)) {
            return;
        }

        if (i == 1) {
            if (Double.parseDouble(tv_1.getText().toString().trim()) > Double.parseDouble(tv_xianjinjine.getText().toString().trim())) {
                tv_1.setText(Double.parseDouble(tv_xianjinjine.getText().toString().trim())==0?"0":String.valueOf(Double.parseDouble(tv_xianjinjine.getText().toString().trim())));
            }
            tv_2.setText(Double.parseDouble(Arith.sub(xjje, tv_1.getText().toString().trim()))==0?"":String.valueOf(Double.parseDouble(Arith.sub(xjje, tv_1.getText().toString().trim()))));
        } else {
            if (Double.parseDouble(tv_2.getText().toString().trim()) > Double.parseDouble(tv_xianjinjine.getText().toString().trim())) {
                tv_2.setText(Double.parseDouble(tv_xianjinjine.getText().toString().trim())==0?"0":String.valueOf(Double.parseDouble(tv_xianjinjine.getText().toString().trim())));
            }
            tv_1.setText(Double.parseDouble(Arith.sub(xjje, tv_2.getText().toString().trim()))==0?"0":String.valueOf(Double.parseDouble(Arith.sub(xjje, tv_2.getText().toString().trim()))));
        }
    }

    private void firstYK(int i) {
        String YK = tv_youkajine.getText().toString().trim();
        if (TextUtils.isEmpty(YK)) {
            return;
        }

        if (i == 1) {
            if (Double.parseDouble(tv_y1.getText().toString().trim()) > Double.parseDouble(tv_youkajine.getText().toString().trim())) {
                tv_y1.setText(Double.parseDouble(tv_xianjinjine.getText().toString().trim())==0?"0":String.valueOf(Double.parseDouble(tv_xianjinjine.getText().toString().trim())));
            }
            tv_y2.setText(Double.parseDouble(Arith.sub(YK, tv_y1.getText().toString().trim()))==0?"0":Arith.sub(YK, tv_y1.getText().toString().trim()));
        } else {
            if (Double.parseDouble(tv_y2.getText().toString().trim()) > Double.parseDouble(tv_youkajine.getText().toString().trim())) {
                tv_y2.setText(Double.parseDouble(tv_youkajine.getText().toString().trim())==0?"0":String.valueOf(Double.parseDouble(tv_youkajine.getText().toString().trim())));
            }
            tv_y1.setText(Double.parseDouble(Arith.sub(YK, tv_y2.getText().toString().trim()))==0?"0":String.valueOf(Double.parseDouble(Arith.sub(YK, tv_y2.getText().toString().trim()))));
        }
    }
}
