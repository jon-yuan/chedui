package com.babuwyt.daili.ui.views;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
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
import com.babuwyt.daili.utils.util.DensityUtils;


/**
 * @describe 自定义居中弹出dialog
 */

public class InPutDialog extends Dialog {

    private Context mContext;
    private TextView title, queren, quxiao;
    private EditText et_input;

    private String mTitle = "";
    private String mText = "";
    private boolean isBili = false;

    public InPutDialog(Context context) {
        super(context, R.style.dialog_custom);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAttributes();
    }

    private void setAttributes() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.input_dialog, null);
        setContentView(view);

        title = view.findViewById(R.id.title);
        queren = view.findViewById(R.id.queren);
        quxiao = view.findViewById(R.id.quxiao);
        et_input = view.findViewById(R.id.et_input);
        title.setText(mTitle);
        et_input.setText(mText);
        et_input.setHint("请输入" + mTitle);
        et_input.setSelection(mText.length());
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() <= 0) {
                    return;
                }
                if (isBili) {
                    if (Double.parseDouble(charSequence.toString()) > 100) {
                        et_input.setText("100");
                    }
                } else {
                    if (String.valueOf(charSequence).equalsIgnoreCase(".")) {
                        et_input.setText("0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_input.getText().toString().trim())) {
                    return;
                }
                if (isBili) {
                    inputCallBack.CallBackText(et_input.getText().toString().trim());
                } else {
                    inputCallBack.CallBackText(Double.parseDouble(et_input.getText().toString().trim()) == 0 ? "0" : String.valueOf(Double.parseDouble(et_input.getText().toString().trim())));
                }
                dismiss();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        getWindow().setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        WindowManager windowManager = ((Activity) mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        lp.width = DensityUtils.deviceWidthPX(mContext) * 2 / 3; // 设置dialog宽度为屏幕的4/5
//        lp.height = DensityUtils.deviceHeightPX(mContext); // 设置dialog宽度为屏幕的4/5
        setCanceledOnTouchOutside(true);
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void settext(String text) {
        mText = text;
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

    public void isBili(boolean b) {
        isBili = b;
    }

    public interface InputCallBack {
        void CallBackText(String text);
    }

    private InputCallBack inputCallBack;

    public void addInptuCallBack(InputCallBack inputCallBack) {
        this.inputCallBack = inputCallBack;
    }
}

