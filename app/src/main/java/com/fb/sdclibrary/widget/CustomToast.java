package com.fb.sdclibrary.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fb.sdclibrary.R;
import com.fb.sdclibrary.utils.ScreenUtils;

public class CustomToast {
    private static CustomToast customToast;
    private TextView mMessage;
    private Toast mToast;
    private View mToastRoot;
    private int mScreenHeight;
    private ImageView mIcon;

    public CustomToast(Context context) {
        mToast = new Toast(context);
        init(context);
    }

    private void init(Context context) {
        //加载Toast布局
        mToastRoot = LayoutInflater.from(context).inflate(R.layout.comm_toast, null);
        //初始化布局控件
        mMessage = (TextView) mToastRoot.findViewById(R.id.toast_message);
        mIcon = ((ImageView) mToastRoot.findViewById(R.id.toast_icon));
        mScreenHeight = ScreenUtils.getScreenHeight();
    }

    public static CustomToast getInstance(Context context){
        if (customToast == null){
            synchronized (CustomToast.class){
                if (customToast == null){
                    customToast = new CustomToast(context);
                }
            }
        }
        return customToast;
    }

    public void showToastLong(String message) {
        //为控件设置属性
        mMessage.setText(message);
        mToast.setGravity(Gravity.TOP, 0, mScreenHeight / 3);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setView(mToastRoot);
        mToast.show();
    }

    public void showToastShort(String message) {
        mMessage.setText(message);
        mToast.setGravity(Gravity.TOP, 0, mScreenHeight / 3);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(mToastRoot);
        mToast.show();
    }

    public void showToastBottomLong(String message) {
        mMessage.setText(message);
        mToast.setGravity(Gravity.BOTTOM, 0, mScreenHeight / 5);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setView(mToastRoot);
        mToast.show();
    }

    public void showToastBottomShort(String message) {
        mMessage.setText(message);
        mToast.setGravity(Gravity.BOTTOM, 0, mScreenHeight / 5);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(mToastRoot);
        mToast.show();
    }

    public CustomToast setIcon(int imgSource) {
        mIcon.setImageResource(imgSource);
        mIcon.setVisibility(View.VISIBLE);
        return this;
    }
}
