package com.babuwyt.daili.ui.views;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.base.SessionManager;
import com.babuwyt.daili.entity.SystemPramer;
import com.babuwyt.daili.utils.util.DensityUtils;
import com.babuwyt.daili.utils.util.UHelper;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @describe 自定义居中弹出dialog
 */
public class RatesDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private TextView tv_xiayibu;
    private SystemPramer params;
    private EditText tv_youkahui;//油卡汇
    private EditText tv_xianjinjine;//现金金额
    private EditText tv_youkajine;//现金金额
    private EditText tv_baojia;//报价
    private EditText tv_jiangli;//奖励
    private EditText tv_kouchu;//扣除
    private EditText tv_1;//扣除
    private EditText tv_2;//扣除
    private EditText tv_y1;//扣除
    private EditText tv_y2;//扣除
    private TextView tv_zongjine;//总金额
    private ImageView img_x;//总金额

    public RatesDialog(Context context) {
        super(context, R.style.dialog_custom);
        this.mContext = context;
    }

    public void setParams(SystemPramer params) {
        this.params = params;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAttributes();
    }

    private void setAttributes() {
//        setContentView(R.layout.loading_dialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.rates_dialog, null);
        setContentView(view);
        tv_xiayibu = view.findViewById(R.id.tv_xiayibu);
        tv_youkahui = view.findViewById(R.id.tv_youkahui);
        tv_baojia = view.findViewById(R.id.tv_baojia);
        tv_xianjinjine = view.findViewById(R.id.tv_xianjinjine);
        tv_youkajine = view.findViewById(R.id.tv_youkajine);
        tv_jiangli = view.findViewById(R.id.tv_jiangli);
        tv_zongjine = view.findViewById(R.id.tv_zongjine);
        tv_kouchu = view.findViewById(R.id.tv_kouchu);
        tv_1 = view.findViewById(R.id.tv_1);
        tv_2 = view.findViewById(R.id.tv_2);
        tv_y1 = view.findViewById(R.id.tv_y1);
        tv_y2 = view.findViewById(R.id.tv_y2);
        img_x = view.findViewById(R.id.img_x);


        setAddChenge();


        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        layoutParams.alpha = 1;//alpha在0.0f到1.0f之间。1.0完全不透明，0.0f完全透明，自身不可见  (dialog自身的透明度）
//        layoutParams.dimAmount = 0;//dialog所在窗体的背景  dimAmount在0.0f和1.0f之间，0.0f完全不暗，即背景是可见的 ，1.0f时候，背景全部变黑暗
        getWindow().setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置为居中
        getWindow().setWindowAnimations(R.style.bottom_menu_animation);//设置dialog所在窗体的动画(即show和dismiss的动画效果）

        WindowManager windowManager = ((Activity) mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        lp.width = DensityUtils.deviceWidthPX(mContext); // 设置dialog宽度为屏幕的4/5
//        lp.height = DensityUtils.deviceHeightPX(mContext); // 设置dialog宽度为屏幕的4/5
        setCanceledOnTouchOutside(true);
    }

    public void showDialog() {
        if (!isShowing()) {
            show();
        }
        setData();
    }

    public void dissDialog() {
        if (isShowing()) {
            dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_xiayibu:
                this.params.setFfreight(TextUtils.isEmpty(tv_baojia.getText().toString())?"0":tv_baojia.getText().toString());
                this.params.setFkouchu(TextUtils.isEmpty(tv_kouchu.getText().toString())?"0":tv_kouchu.getText().toString());
                this.params.setFreight(TextUtils.isEmpty(tv_xianjinjine.getText().toString())?"0":tv_xianjinjine.getText().toString());
                this.params.setFoilcardValue(TextUtils.isEmpty(tv_youkajine.getText().toString())?"0":tv_youkajine.getText().toString());
                this.params.setFtotalCost(TextUtils.isEmpty(tv_zongjine.getText().toString())?"0":tv_zongjine.getText().toString());
                this.params.setfMoneyRecharge1(TextUtils.isEmpty(tv_1.getText().toString())?"0":tv_1.getText().toString());
                this.params.setfMoneyRecharge2(TextUtils.isEmpty(tv_2.getText().toString())?"0":tv_2.getText().toString());
                this.params.setFoilcardrecharge1(TextUtils.isEmpty(tv_y1.getText().toString())?"0":tv_y1.getText().toString());
                this.params.setFoilcardrecharge2(TextUtils.isEmpty(tv_y2.getText().toString())?"0":tv_y2.getText().toString());
                this.params.setFgiveoilcardValue(TextUtils.isEmpty(tv_jiangli.getText().toString())?"0":tv_jiangli.getText().toString());
                this.params.setUserid(SessionManager.getInstance().getUser().getFid());
                callBack.callBack(this.params);
                break;
            case R.id.img_x:
                dismiss();
                break;
        }
    }

    public interface RatesDialogCallBack {
        void callBack(SystemPramer pramer);
    }

    private RatesDialogCallBack callBack;

    public void setCallBack(RatesDialogCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 根据输入进行计算
     * 初始化设置默认油卡汇比例
     */
    private void setData() {
        if (params == null) {
            return;
        }
        tv_youkahui.setText(params.getFacceptratio() + "");
//        youka = new BigDecimal(String.valueOf(params.getFacceptratio().divide(new BigDecimal(100))));
//        jiangli = new BigDecimal(String.valueOf(params.getFreturnratio().divide(new BigDecimal(100))));
//        kouchu = new BigDecimal(String.valueOf(params.getDiscountratio().divide(new BigDecimal(100))));
        youka = params.getFacceptratio();
        jiangli = params.getFreturnratio();
        kouchu = params.getDiscountratio();
    }

    /**
     * 设置监听
     */

    private String totalPrice;//总额
    private String xianjinjine;//现金金额
    private String youkajine;//油卡金额
    private String jianglijine;//奖励金额
    private String kouchujine;//奖励金额


    private int youka;//油卡比例/100
    private int jiangli;//油卡奖励系数/100
    private int kouchu;//现金扣除系数/100

    Baojia baojialister=new Baojia();
    Youkahui youkahuilister=new Youkahui();
    Xianjin xianjinlister=new Xianjin();
    Youka youkalister=new Youka();
    Jiangli jianglilister=new Jiangli();
    Kouchu kouchulister=new Kouchu();
    private void setAddChenge() {
        tv_xiayibu.setOnClickListener(this);
        img_x.setOnClickListener(this);
        tv_baojia.addTextChangedListener(baojialister);
        tv_youkahui.addTextChangedListener(youkahuilister);
        tv_xianjinjine.addTextChangedListener(xianjinlister);
        tv_youkajine.addTextChangedListener(youkalister);
        tv_jiangli.addTextChangedListener(jianglilister);
        tv_kouchu.addTextChangedListener(kouchulister);
    }
    //根据油卡汇计算各金额

    private void zongeToprice() {
        totalPrice=tv_baojia.getText().toString();
        if (totalPrice.length()<=0) {
            setEmpty();
            return;
        }
        BigDecimal price = new BigDecimal(totalPrice);//总金额
        int bili = Integer.parseInt(tv_youkahui.getText().toString());
        BigDecimal b = new BigDecimal(bili).divide(new BigDecimal(100));
        youkajine = price.multiply(b) + "";
        xianjinjine = price.subtract(price.multiply(b)) + "";
        //获取到输入的油卡比例
        //如果系统比例大于输入比例
        if (youka > bili) {
            kouchujine = price.multiply(new BigDecimal(kouchu).divide(new BigDecimal(100))).multiply(new BigDecimal(Math.abs(youka - bili)).divide(new BigDecimal(100))) + "";
            jianglijine = BigDecimal.valueOf(0) + "";
        } else if (youka == bili) {
            kouchujine = BigDecimal.valueOf(0) + "";
            jianglijine = BigDecimal.valueOf(0) + "";
        } else {
            kouchujine = BigDecimal.valueOf(0) + "";
            jianglijine = price.multiply(new BigDecimal(jiangli).divide(new BigDecimal(100))).multiply(new BigDecimal(Math.abs(bili - youka)).divide(new BigDecimal(100))) + "";
        }
        tv_youkajine.setText(youkajine);
        tv_xianjinjine.setText(xianjinjine);
        tv_jiangli.setText(jianglijine);
        tv_kouchu.setText(kouchujine);
        tv_zongjine.setText(new BigDecimal(youkajine).add(new BigDecimal(xianjinjine)).add(new BigDecimal(jianglijine)).subtract(new BigDecimal(kouchujine))+"");
    }

    private void youkaToprice() {
        totalPrice=tv_baojia.getText().toString();
        int bili=0;
        if (!TextUtils.isEmpty(tv_youkahui.getText().toString())){
            bili=Integer.parseInt(tv_youkahui.getText().toString());
        }
        if (TextUtils.isEmpty(totalPrice)){
            setEmpty();
            return;
        }
        BigDecimal b=new BigDecimal(bili).divide(new BigDecimal(100));
        BigDecimal price = new BigDecimal(totalPrice);//总金额
        youkajine = price.multiply(b)+ "";
        xianjinjine = price.subtract(price.multiply(b)) + "";
        if (youka>bili){
            kouchujine = price.multiply(new BigDecimal(kouchu).divide(new BigDecimal(100))).multiply(new BigDecimal(Math.abs(youka - bili)).divide(new BigDecimal(100))) + "";
            jianglijine = BigDecimal.valueOf(0) + "";
        }else if (youka==bili){
            kouchujine = BigDecimal.valueOf(0) + "";
            jianglijine = BigDecimal.valueOf(0) + "";
        }else {
            kouchujine = BigDecimal.valueOf(0) + "";
            jianglijine = price.multiply(new BigDecimal(jiangli).divide(new BigDecimal(100))).multiply(new BigDecimal(Math.abs(bili - youka)).divide(new BigDecimal(100))) + "";
        }
        tv_youkajine.setText(youkajine);
        tv_xianjinjine.setText(xianjinjine);
        tv_jiangli.setText(jianglijine);
        tv_kouchu.setText(kouchujine);
        tv_zongjine.setText(new BigDecimal(youkajine).add(new BigDecimal(xianjinjine)).add(new BigDecimal(jianglijine)).subtract(new BigDecimal(kouchujine))+"");
    }
    //算总金额
    private void addPrice(){
        double youka=Double.parseDouble(tv_youkajine.getText().toString().equals("")?"0":tv_youkajine.getText().toString());
        double xianjin=Double.parseDouble(tv_xianjinjine.getText().toString().equals("")?"0":tv_xianjinjine.getText().toString());
        double jiangli=Double.parseDouble(tv_jiangli.getText().toString().equals("")?"0":tv_jiangli.getText().toString());
        double kouchu=Double.parseDouble(tv_kouchu.getText().toString().equals("")?"0":tv_kouchu.getText().toString());
        tv_zongjine.setText(youka+xianjin+jiangli-kouchu+"");
    }
    private void inputyouka(){

        BigDecimal baojia=new BigDecimal(returnStr(tv_baojia.getText().toString()));
        BigDecimal youka=new BigDecimal(returnStr(tv_youkajine.getText().toString()));
        BigDecimal xianjin=baojia.subtract(youka);
        BigDecimal jiangli=new BigDecimal(returnStr(tv_jiangli.getText().toString()));
        BigDecimal kouchu=new BigDecimal(returnStr(tv_kouchu.getText().toString()));

        tv_zongjine.setText(youka.add(xianjin).add(jiangli).subtract(kouchu)+"");
    }
    private void inputxianjin(){

        BigDecimal baojia=new BigDecimal(returnStr(tv_baojia.getText().toString()));
        BigDecimal xianjin=new BigDecimal(returnStr(tv_xianjinjine.getText().toString()));
        BigDecimal youka=baojia.subtract(xianjin);

        BigDecimal jiangli=new BigDecimal(returnStr(tv_jiangli.getText().toString()));
        BigDecimal kouchu=new BigDecimal(returnStr(tv_kouchu.getText().toString()));

        tv_zongjine.setText(youka.add(xianjin).add(jiangli).subtract(kouchu)+"");
    }
    private void inputjiangli(){

        BigDecimal jiangli=new BigDecimal(returnStr(tv_jiangli.getText().toString()));
        BigDecimal youka=new BigDecimal(returnStr(tv_youkajine.getText().toString()));
        BigDecimal xianjin=new BigDecimal(returnStr(tv_xianjinjine.getText().toString()));
        BigDecimal kouchu=new BigDecimal(returnStr(tv_kouchu.getText().toString()));


        tv_zongjine.setText(youka.add(xianjin).add(jiangli).subtract(kouchu)+"");
    }
    private void inputkouchu(String s){

        BigDecimal jiangli=new BigDecimal(returnStr(tv_jiangli.getText().toString()));
        BigDecimal youka=new BigDecimal(returnStr(tv_youkajine.getText().toString()));
        BigDecimal xianjin=new BigDecimal(returnStr(tv_xianjinjine.getText().toString()));
        BigDecimal kouchu=new BigDecimal(returnStr(s));

        tv_zongjine.setText(youka.add(xianjin).add(jiangli).subtract(kouchu)+"");
    }
    private boolean isbjEmpty(){
        String xianjin=tv_baojia.getText().toString();
        if (TextUtils.isEmpty(xianjin)){
            UHelper.showToast(mContext,"请先输入报价");
            return true;
        }
        return false;
    }

    //获取输入框字符串，如果为空字符串则返回“0” 否则直接返回
    private String returnStr(String s){
        if (TextUtils.isEmpty(s)){
            return "0";
        }
        return s;
    }

    //设置为空
    private void setEmpty() {
        tv_youkajine.setText(new BigDecimal(0) + "");
        tv_xianjinjine.setText(new BigDecimal(0) + "");
        tv_jiangli.setText(new BigDecimal(0) + "");
        tv_kouchu.setText(new BigDecimal(0) + "");
    }

    class Baojia implements TextWatcher{
        int l=0;////////记录字符串被删除字符之前，字符串的长度
        int location=0;//记录光标的位置
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            l=charSequence.length();
            location=tv_youkahui.getSelectionStart();

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            zongeToprice();
        }

        @Override
        public void afterTextChanged(Editable editable) {
//
        }
    }
    class Youkahui implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Pattern p = Pattern.compile("^(100|[1-9]\\d|\\d)$");
            Matcher m =p.matcher(charSequence.toString());
            if(m.find() || ("").equals(charSequence.toString())){
                youkaToprice();
            }else{
                System.out.print("False!");
                tv_youkahui.setText("100");
                UHelper.showToast(mContext,"油卡惠为0-100之间");
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
//
        }
    }
    class Xianjin implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                inputxianjin();
        }

        @Override
        public void afterTextChanged(Editable editable) {
//
        }
    }
    class Youka implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            inputyouka();
        }

        @Override
        public void afterTextChanged(Editable editable) {
//
        }
    }
    class Jiangli implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            inputjiangli();
        }

        @Override
        public void afterTextChanged(Editable editable) {
//
        }
    }
    class Kouchu implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            inputkouchu();
            inputkouchu(String.valueOf(charSequence));
        }

        @Override
        public void afterTextChanged(Editable editable) {
//
        }
    }

}


