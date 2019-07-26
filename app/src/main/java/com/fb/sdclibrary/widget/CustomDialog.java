package com.fb.sdclibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fb.sdclibrary.R;

public class CustomDialog extends Dialog {
    private TextView mMessage;
    private TextView mCancel;
    private TextView mConfirm;
    private TextView mContent;
    private EditText mInput;
    private View mDivide;
    private View.OnClickListener mCancelListeners;
    private View.OnClickListener mConfirmListeners;

    public CustomDialog(Context context) {
        super(context, R.style.custom_dialog);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.comm_dialog, null);
        mMessage = ((TextView) view.findViewById(R.id.custom_dialog_message));
        mDivide = view.findViewById(R.id.dialog_divide);
        mCancel = ((TextView) view.findViewById(R.id.custom_dialog_cancel));
        mConfirm = ((TextView) view.findViewById(R.id.custom_dialog_confirm));
        mContent = ((TextView) view.findViewById(R.id.custom_dialog_content));
        mInput = (EditText) view.findViewById(R.id.custom_dialog_input);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCancelListeners != null) {
                    mCancelListeners.onClick(v);
                }
                dismiss();
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConfirmListeners != null) {
                    mConfirmListeners.onClick(v);
                }
                dismiss();
            }
        });

        setContentView(view);
    }

    public CustomDialog setMessage(String message) {
        mMessage.setText(message);
        return this;
    }

    public CustomDialog setMessageColor(int color) {
        mMessage.setTextColor(color);
        return this;
    }

    public CustomDialog setContent(String content) {
        mContent.setVisibility(View.VISIBLE);
        mContent.setText(content);
        return this;
    }

    public CustomDialog setContentColor(int color) {
        mContent.setTextColor(color);
        return this;
    }

    public CustomDialog setCancelListener(View.OnClickListener onClickListener) {
            mCancelListeners=onClickListener;
        return this;
    }

    public CustomDialog setConfirmListener(View.OnClickListener onClickListener) {
            mConfirmListeners=onClickListener;
        return this;
    }

    public CustomDialog setConfirmText(String text) {
        mConfirm.setText(text);
        return this;
    }

    public CustomDialog setCancelText(String text) {
        mCancel.setText(text);
        return this;
    }

    public CustomDialog setConfirmColor(int color) {
        mConfirm.setTextColor(color);
        return this;
    }

    public CustomDialog setCancelColor(int color) {
        mCancel.setTextColor(color);
        return this;
    }

    public CustomDialog setContentTextGravity(int gravity) {
        mContent.setGravity(gravity);
        return this;
    }

    public CustomDialog setMessageTextSize(int unit, float size) {
        mMessage.setTextSize(unit, size);
        return this;
    }

    public CustomDialog setContentTextSize(int unit, float size) {
        mContent.setTextSize(unit, size);
        return this;
    }

    public CustomDialog setConfirmTextSize(int unit, float size) {
        mConfirm.setTextSize(unit, size);
        return this;
    }

    public CustomDialog setCancelTextSize(int unit, float size) {
        mCancel.setTextSize(unit, size);
        return this;
    }

    public CustomDialog setCancelVisibility(int visibility) {
        mCancel.setVisibility(visibility);
        if (visibility == View.VISIBLE && mConfirm.getVisibility() == View.VISIBLE) {
            mDivide.setVisibility(View.VISIBLE);
        } else {
            mDivide.setVisibility(View.GONE);
        }
        return this;
    }

    public CustomDialog setConfirmVisibility(int visibility) {
        mConfirm.setVisibility(visibility);
        if (visibility == View.VISIBLE && mCancel.getVisibility() == View.VISIBLE) {
            mDivide.setVisibility(View.VISIBLE);
        } else {
            mDivide.setVisibility(View.GONE);
        }
        return this;
    }

    public CustomDialog setCancelEnable(boolean enable) {
        setCancelable(enable);
        return this;
    }

    public CustomDialog setMessageGravity(int gravity) {
        mMessage.setGravity(gravity);
        return this;
    }

    public CustomDialog setMessagePadding(int left, int top, int right, int bottom) {
        mMessage.setPadding(left, top, right, bottom);
        return this;
    }

    public CustomDialog setInputVisibility(int visibility) {
        mInput.setVisibility(visibility);
        return this;
    }

    public String getInputContent(){
        return mInput.getText().toString();
    }
}
