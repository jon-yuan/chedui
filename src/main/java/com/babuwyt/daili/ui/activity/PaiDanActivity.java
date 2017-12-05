package com.babuwyt.daili.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.base.BaseActivity;
import com.babuwyt.daili.utils.util.Arith;
import com.babuwyt.daili.utils.util.InputMoney;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by lenovo on 2017/11/30.
 */
@ContentView(R.layout.activity_paidan)
public class PaiDanActivity extends BaseActivity {
    @ViewInject(R.id.tv_baojia)
    EditText tv_baojia;
    @ViewInject(R.id.tv_youkahui)
    EditText tv_youkahui;
    @ViewInject(R.id.tv_youkajine)
    EditText tv_youkajine;
    @ViewInject(R.id.tv_xianjinjine)
    EditText tv_xianjinjine;
    @ViewInject(R.id.tv_jiangli)
    EditText tv_jiangli;
    @ViewInject(R.id.tv_kouchu)
    EditText tv_kouchu;
    @ViewInject(R.id.tv_1)
    EditText tv_1;
    @ViewInject(R.id.tv_y1)
    EditText tv_y1;
    @ViewInject(R.id.tv_2)
    EditText tv_2;
    @ViewInject(R.id.tv_y2)
    EditText tv_y2;

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

        tv_ykh.setText(mYKH);

        tv_baojia.addTextChangedListener(new CustomTextWahcher(tv_baojia));
        tv_youkahui.addTextChangedListener(new CustomTextWahcher(tv_youkahui));
        tv_xianjinjine.addTextChangedListener(new CustomTextWahcher(tv_xianjinjine));
        tv_youkajine.addTextChangedListener(new CustomTextWahcher(tv_youkajine));
        tv_jiangli.addTextChangedListener(new CustomTextWahcher(tv_jiangli));
        tv_kouchu.addTextChangedListener(new CustomTextWahcher(tv_kouchu));
        tv_1.addTextChangedListener(new CustomTextWahcher(tv_1));
        tv_2.addTextChangedListener(new CustomTextWahcher(tv_2));
        tv_y1.addTextChangedListener(new CustomTextWahcher(tv_y1));
        tv_y2.addTextChangedListener(new CustomTextWahcher(tv_y2));

    }

    class CustomTextWahcher implements TextWatcher {
        private EditText et;

        public CustomTextWahcher(EditText editText) {
            et = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            et.setSelection(charSequence.length());
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (autochange) {
                autochange = false;
            } else {
                if (charSequence.length() > 0) {
                    if (String.valueOf(charSequence).equalsIgnoreCase(".")) {
                        autochange = true;
                        et.setText("0.");
                    } else if (String.valueOf(charSequence).equalsIgnoreCase("-")) {
                        autochange = true;
                        et.setText("");
                    } else if (Double.parseDouble(String.valueOf(charSequence)) == 0) {
                        autochange = true;
                        et.setText("0");
                    }
                    et.setSelection(et.getText().toString().length());

                    switch (et.getId()) {
                        case R.id.tv_baojia:
                            changeBaojia();
                            break;
                        case R.id.tv_youkahui:
                            if (Double.parseDouble(charSequence.toString()) > 100) {
                                autochange = true;
                                tv_youkahui.setText("100");
                            }
                            inputYKH();
                            break;
                        case R.id.tv_xianjinjine:
                            inputXJ();
                            break;
                        case R.id.tv_youkajine:
                            inputYk();
                            break;
                        case R.id.tv_jiangli:

                            other();
                            break;
                        case R.id.tv_kouchu:
                            other();
                            break;
                        case R.id.tv_1:
                            firstXJ(1);

                            break;
                        case R.id.tv_2:
                            firstXJ(2);

                            break;
                        case R.id.tv_y1:


                            break;
                        case R.id.tv_y2:


                            break;
                    }
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
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

    private void initChange(){
        String bj = "";
        String ykh = "";
        String yk = "";
        String xj = "";
        String jl = "";
        String kc = "";
        String fxj = "";
        String fyk = "";
        String sxj = "";
        String syk = "";
        String zje = "";
    }
    private void changeBaojia() {
        bj = tv_baojia.getText().toString().trim();
        ykh = tv_youkahui.getText().toString().trim();
        if (TextUtils.isEmpty(bj) || TextUtils.isEmpty(ykh)) {
            initChange();
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
        autochange = true;
        tv_jiangli.setText(jl);
        autochange = true;
        tv_kouchu.setText(String.valueOf(Math.abs(Double.parseDouble(kc))));
        autochange = true;
        tv_youkajine.setText(yk);
        autochange = true;
        tv_xianjinjine.setText(xj);
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
        if (Integer.parseInt(bj) <= 0) {
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
        autochange = true;
        tv_jiangli.setText(jl);
        autochange = true;
        tv_kouchu.setText(String.valueOf(Math.abs(Double.parseDouble(kc))));
        autochange = true;
        tv_youkajine.setText(yk);
        autochange = true;
        tv_xianjinjine.setText(xj);
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
        autochange = true;
        tv_y1.setText(Arith.div(ykje, "2", 2));
        autochange = true;
        tv_y2.setText(Arith.div(ykje, "2", 2));
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
        autochange = true;
        tv_1.setText(Arith.div(xjje, "2", 2));
        autochange = true;
        tv_2.setText(Arith.div(xjje, "2", 2));
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
        tv_zongjine.setText(zje);
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
                tv_1.setText(tv_xianjinjine.getText().toString().trim());
            }
            tv_2.setText(Arith.sub(xjje, tv_1.getText().toString().trim()));
        } else {
            if (Double.parseDouble(tv_2.getText().toString().trim()) > Double.parseDouble(tv_xianjinjine.getText().toString().trim())) {
                tv_2.setText(tv_xianjinjine.getText().toString().trim());
            }
            tv_1.setText(Arith.sub(xjje, tv_2.getText().toString().trim()));
        }
    }
}
