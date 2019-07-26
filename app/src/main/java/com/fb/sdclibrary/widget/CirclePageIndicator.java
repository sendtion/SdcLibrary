package com.fb.sdclibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fb.sdclibrary.R;
import com.fb.sdclibrary.utils.AppInfoUtil;

public class CirclePageIndicator extends View implements ViewPager.OnPageChangeListener {

    private int defaultHeight = 0;
    private int defaultWidth = 0;
    private int count = 0;
    private float radius;
    private final float defaultRadius = 5;
    private float circleDivideWidth;
    private final float defaultDivideWidth = 30;
    private int mCircleNormalColor;
    private final int mDefaultCircleNormalColor = Color.parseColor("#000000");
    private int mCircleSelectedColor;
    private final int mDefaultCircleSelectedColor = Color.parseColor("#ff0000");
    private int currentItem = 0;
    private Paint mPaintNormal = new Paint();
    private Paint mPaintSelected = new Paint();
    private float scrollPercent = 0;
    private int mPaddingTop;

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    public CirclePageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        try {
            defaultWidth = AppInfoUtil.getScreenWidth(getContext());
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.circlePageIndicator, defStyleAttr, 0);
            if (a != null) {
                circleDivideWidth = a.getDimension(R.styleable.circlePageIndicator_circleDivideWidth, defaultDivideWidth);
                mCircleNormalColor = a.getColor(R.styleable.circlePageIndicator_circleNormalColor, mDefaultCircleNormalColor);
                mCircleSelectedColor = a.getColor(R.styleable.circlePageIndicator_circleSelectedColor, mDefaultCircleSelectedColor);
                radius = a.getDimension(R.styleable.circlePageIndicator_radius, defaultRadius);
                mPaintNormal.setColor(mCircleNormalColor);
                mPaintNormal.setAntiAlias(true);
                mPaintSelected.setColor(mCircleSelectedColor);
                mPaintSelected.setAntiAlias(true);
                mPaddingTop = getPaddingTop();
                a.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int y;
        if (mPaddingTop <= 0) {
            y = getHeight() / 2;
        } else {
            y = (int) (mPaddingTop + radius / 2);
        }
        float firstX = (getWidth() - circleDivideWidth * (count - 1) - radius * count * 2) / 2 + radius;
        firstX = firstX < 0 ? 0 : firstX;
        for (int i = 0; i < count; i++) {
            canvas.drawCircle(firstX + i * (circleDivideWidth + radius * 2), y, radius, mPaintNormal);
        }
        if (count > 0) {
            canvas.drawCircle(firstX + (currentItem + scrollPercent) * (circleDivideWidth + radius * 2), y, radius, mPaintSelected);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasureWidth(widthMeasureSpec), getMeasureHeight(heightMeasureSpec));
    }

    private int getMeasureHeight(int heightMeasureSpec) {
        int ret = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            ret = size;
        } else {
            ret = defaultHeight;
        }
        return ret;
    }

    private int getMeasureWidth(int widthMeasureSpec) {
        int ret = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            ret = size;
        } else {
            ret = defaultWidth;
        }
        return ret;
    }

    public void setViewpager(ViewPager viewpager) {
        PagerAdapter adapter = viewpager.getAdapter();
        if (adapter != null) {
            count = adapter.getCount();
            viewpager.removeOnPageChangeListener(this);
            viewpager.addOnPageChangeListener(this);
            invalidate();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        setCurrentItem(position);
        scrollPercent = positionOffset;
        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
        setCurrentItem(position);
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }
}
