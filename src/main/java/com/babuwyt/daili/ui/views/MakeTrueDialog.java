package com.babuwyt.daili.ui.views;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.daili.R;
import com.babuwyt.daili.entity.SystemPramer;
import com.babuwyt.daili.utils.util.DensityUtils;


/**
 * @describe 自定义居中弹出dialog
 */
public class MakeTrueDialog extends Dialog{

    private Context mContext;
    private TextView quxiao;
    private TextView queren;
    private TextView siji;
    private TextView bianhao;
    private TextView xianlu;
    private TextView shijian;
    private TextView baojia;
    private TextView youkahui;
    private TextView youka;
    private TextView xianjin;
    private TextView jiangli;
    private TextView kouchu;
    private TextView zongjia;
    private SystemPramer pramer;
    public MakeTrueDialog(Context context) {
        super(context, R.style.dialog_custom);
        this.mContext = context;
    }
    public void setData(SystemPramer pramer){
        this.pramer=pramer;
        siji.setText(pramer.getSijiname());
        bianhao.setText(pramer.getFid());
        xianlu.setText(pramer.getXianlu());
        shijian.setText(pramer.getShijian());
        baojia.setText(pramer.getFfreight());
        youkahui.setText(pramer.getFacceptratio()+"");
        youka.setText(pramer.getFoilcardValue());
        zongjia.setText(pramer.getFtotalCost());
        xianjin.setText(pramer.getFreight());
        jiangli.setText(pramer.getFgiveoilcardValue());
        kouchu.setText(pramer.getFkouchu());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAttributes();
    }
    private void setAttributes(){
//        setContentView(R.layout.loading_dialog);
        View view= LayoutInflater.from(mContext).inflate(R.layout.maketrue_dialog,null);
        setContentView(view);

        quxiao=view.findViewById(R.id.quxiao);
        queren=view.findViewById(R.id.queren);
        siji=view.findViewById(R.id.siji);
        bianhao=view.findViewById(R.id.bianhao);
        xianlu=view.findViewById(R.id.xianlu);
        shijian=view.findViewById(R.id.shijian);
        baojia=view.findViewById(R.id.baojia);
        youkahui=view.findViewById(R.id.youkahui);
        youka=view.findViewById(R.id.youka);
        xianjin=view.findViewById(R.id.xianjin);
        jiangli=view.findViewById(R.id.jiangli);
        kouchu=view.findViewById(R.id.kouchu);
        zongjia=view.findViewById(R.id.zongjia);

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit.commit(pramer);
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
        setCanceledOnTouchOutside(true);
    }

    public void showDialog(){
        if (!isShowing()){
//

            show();
        }
    }
    public void dissDialog(){
        if (isShowing()){
            dismiss();
        }
    }

    public interface Commit{
        void commit(SystemPramer pramer);
    }

    private Commit commit;
    public void setCommit(Commit commit){
        this.commit=commit;
    }
}

