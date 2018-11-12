package com.fb.sdclibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fb.sdclibrary.R;
import com.fb.sdclibrary.utils.ScreenUtils;

public class UpdateDialog extends Dialog {
    private TextView mMessage;
    private TextView mCancel;
    private TextView mConfirm;
    private TextView mTitle;

    public UpdateDialog(Context context) {
        super(context, R.style.custom_dialog);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.comm_update_dialog, null);
        ViewGroup.LayoutParams layoutParams = view.findViewById(R.id.update_dialog).getLayoutParams();
        layoutParams.height = ScreenUtils.getScreenHeight();
        layoutParams.width = ScreenUtils.getScreenWidth();
        mMessage = ((TextView) view.findViewById(R.id.update_dialog_content_log));
        mTitle = ((TextView) view.findViewById(R.id.update_dialog_title));
        mCancel = ((TextView) view.findViewById(R.id.update_dialog_cancel));
        mConfirm = ((TextView) view.findViewById(R.id.update_dialog_confirm));
        setContentView(view);
    }

    public void setTitle(String title){
        mTitle.setText(title);
    }

    public void setMessage(String message) {
        mMessage.setText(message);
    }

    public void setMessageColor(int color) {
        mMessage.setTextColor(color);
    }

    public void setCancelListener(View.OnClickListener onClickListener) {
        mCancel.setOnClickListener(onClickListener);
    }

    public void setConfirmListener(View.OnClickListener onClickListener) {
        mConfirm.setOnClickListener(onClickListener);
    }

    public void setConfirmText(String text) {
        mConfirm.setText(text);
    }

    public void setConfirmColor(int color) {
        mConfirm.setTextColor(color);
    }

    public void setCancelText(String text) {
        mCancel.setText(text);
    }

    public void setCancelColor(int color) {
        mCancel.setTextColor(color);
    }

    public void setForceUpdate(boolean isForceUpdate) {
        if (isForceUpdate) {
            setCancelable(false);
            setCanceledOnTouchOutside(false);
            mCancel.setVisibility(View.GONE);
        }
    }
}
