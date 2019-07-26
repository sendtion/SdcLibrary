package com.fb.sdclibrary.widget.ninegridview;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ImageView which has click effect
 */
public class NineGridImageView extends AppCompatImageView {
    public NineGridImageView(Context context) {
        super(context);
        setClickable(true);
    }

    public NineGridImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setAlpha(0.7f);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setAlpha(1.0f);
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
