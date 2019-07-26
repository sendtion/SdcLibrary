package com.fb.sdclibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.core.view.MotionEventCompat;
import androidx.core.widget.NestedScrollView;

public class CustomNestedScrollView extends NestedScrollView {
    private float downX;
    private float downY;
    private int mTouchSlop;

    public CustomNestedScrollView(Context context) {
        this(context, null);
    }

    public CustomNestedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        boolean isIntercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                float newX = ev.getX();
                float newY = ev.getY();
                float diatanceX = Math.abs(downX - newX);
                float diatanceY = Math.abs(downY - newY);
                if (diatanceY > diatanceX && diatanceY > mTouchSlop) {
                    isIntercept = true;
                }
                break;
            }

            case MotionEvent.ACTION_DOWN: {
                downX = ev.getX();
                downY = ev.getY();
                break;
            }

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
            case MotionEventCompat.ACTION_POINTER_UP:
                break;
        }
        return isIntercept;
    }
}
