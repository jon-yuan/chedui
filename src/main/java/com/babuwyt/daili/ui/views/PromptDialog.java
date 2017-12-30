package com.babuwyt.daili.ui.views;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.utils.util.DensityUtils;


/**
 * @describe 自定义居中弹出dialog
 */
public class PromptDialog extends Dialog{

    private Context mContext;
    private TextView tv_title, tv_msg,tv_maketrue,tv_canal;
    private View view_line;

    private String title,msg,btn1,btn2;
    private boolean Cancel;
    public PromptDialog(Context context) {
        super(context, R.style.dialog_custom);
        this.mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAttributes();
    }

    private void setAttributes() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_commit, null);
        setContentView(view);
        tv_title=view.findViewById(R.id.tv_title);
        tv_msg=view.findViewById(R.id.tv_msg);
        tv_canal=view.findViewById(R.id.tv_canal);
        tv_maketrue=view.findViewById(R.id.tv_maketrue);
        view_line=view.findViewById(R.id.view_line);
        tv_title.setText(title);
        tv_msg.setText(msg);
        tv_maketrue.setText(btn1);
        if (TextUtils.isEmpty(btn1)){
            tv_maketrue.setVisibility(View.GONE);
            view_line.setVisibility(View.GONE);
        }
        tv_maketrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (onClick1!=null){
                    onClick1.onClick();
                }
            }
        });
        tv_canal.setText(btn2);
        if (TextUtils.isEmpty(btn2)){
            tv_canal.setVisibility(View.GONE);
            view_line.setVisibility(View.GONE);
        }
        tv_canal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (onClick2!=null){
                    onClick2.onClick();
                }
            }
        });
        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        layoutParams.alpha = 1;//alpha在0.0f到1.0f之间。1.0完全不透明，0.0f完全透明，自身不可见  (dialog自身的透明度）
//        layoutParams.dimAmount = 0;//dialog所在窗体的背景  dimAmount在0.0f和1.0f之间，0.0f完全不暗，即背景是可见的 ，1.0f时候，背景全部变黑暗
        getWindow().setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
//        getWindow().setWindowAnimations(R.style.bottom_menu_animation);//设置dialog所在窗体的动画(即show和dismiss的动画效果）

        WindowManager windowManager = ((Activity) mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        lp.width = DensityUtils.deviceWidthPX(mContext); // 设置dialog宽度为屏幕的4/5
//        lp.height = DensityUtils.deviceHeightPX(mContext); // 设置dialog宽度为屏幕的4/5
        setCanceledOnTouchOutside(Cancel);
        setCancelable(Cancel);
    }
    public void setMsg(String msg){
        this.msg=msg;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setCanceledTouchOutside(boolean b){
        this.Cancel=b;
    }

    public void showDialog() {
        if (!isShowing()) {
            show();
        }
    }

    public void dissDialog() {
        if (isShowing()) {
            dismiss();
        }
    }

    public interface Btn1OnClick{
        void onClick();
    }
    public interface Btn2OnClick{
        void onClick();
    }

    private Btn1OnClick onClick1;
    private Btn2OnClick onClick2;
    public void setOnClick1(String s,Btn1OnClick click1){
        this.onClick1=click1;
        this.btn1=s;
    }
    public void setOnClick2(String s,Btn2OnClick click2){
        this.onClick2=click2;
        this.btn2=s;
    }

}


