package com.fb.sdclibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fb.sdclibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 涟漪效果，水波扩散效果
 * CreateTime: 2018/8/9 10:04
 * Author: ShengDecheng
 */

public class RippleView extends View {

    private Context mContext;

    // 画笔对象
    private Paint mPaint;

    // View宽
    private float mWidth;

    // View高
    private float mHeight;

    // 声波的圆圈集合
    private List<Circle> mRipples;

    private int sqrtNumber;

    // 圆圈扩散的速度
    private int mSpeed;

    // 圆圈之间的密度
    private int mDensity;

    // 圆圈的颜色
    private int mColor;

    // 圆圈是否为填充模式
    private boolean mIsFill;

    // 圆圈是否为渐变模式
    private boolean mIsAlpha;

    public RippleView(Context context) {
        this(context, null);
    }

    public RippleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获取用户配置属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RippleView);
        mColor = ta.getColor(R.styleable.RippleView_rvColor, Color.BLUE);
        mSpeed = ta.getInt(R.styleable.RippleView_rvSpeed, 1);
        mDensity = ta.getInt(R.styleable.RippleView_rvDensity, 10);
        mIsFill = ta.getBoolean(R.styleable.RippleView_rvIsFill, false);
        mIsAlpha = ta.getBoolean(R.styleable.RippleView_rvIsAlpha, false);
        ta.recycle();

        init();
    }

    private void init() {
        mContext = getContext();

        // 设置画笔样式
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(dip2px(mContext, 1));
        if (mIsFill) {
            mPaint.setStyle(Paint.Style.FILL);
        } else {
            mPaint.setStyle(Paint.Style.STROKE);
        }
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);

        // 添加第一个圆圈
        mRipples = new ArrayList<>();
        Circle c = new Circle(0, 255);
        mRipples.add(c);

        mDensity = dip2px(mContext, mDensity);

        // 设置View的圆为半透明
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int myWidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int myWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int myHeightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int myHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        // 获取宽
        if (myWidthSpecMode == MeasureSpec.EXACTLY) {
            // match_parent/精确值
            mWidth = myWidthSpecSize;
        } else {
            // wrap_content
            mWidth = dip2px(mContext, 120);
        }

        // 获取高
        if (myHeightSpecMode == MeasureSpec.EXACTLY) {
            // match_parent/精确值
            mHeight = myHeightSpecSize;
        } else {
            // wrap_content
            mHeight = dip2px(mContext, 120);
        }

        // 设置该view的宽高
        setMeasuredDimension((int) mWidth, (int) mHeight);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 内切正方形
        drawInCircle(canvas);

        // 外切正方形
        // drawOutCircle(canvas);
    }

    /**
     * 圆到宽度
     *
     * @param canvas
     */
    private void drawInCircle(Canvas canvas) {
        canvas.save();

        // 处理每个圆的宽度和透明度
        for (int i = 0; i < mRipples.size(); i++) {
            Circle c = mRipples.get(i);
            mPaint.setAlpha(c.alpha);// （透明）0~255（不透明）
            canvas.drawCircle(mWidth / 2, mHeight / 2, c.width - mPaint.getStrokeWidth(), mPaint);

            // 当圆超出View的宽度后删除
            if (c.width > mWidth / 2) {
                mRipples.remove(i);
            } else {
                // 计算不透明的数值，这里有个小知识，就是如果不加上double的话，255除以一个任意比它大的数都将是0
                if (mIsAlpha) {
                    double alpha = 255 - c.width * (255 / ((double) mWidth / 2));
                    c.alpha = (int) alpha;
                }
                // 修改这个值控制速度
                c.width += mSpeed;
            }
        }


        // 里面添加圆
        if (mRipples.size() > 0) {
            // 控制第二个圆出来的间距
            if (mRipples.get(mRipples.size() - 1).width > dip2px(mContext, mDensity)) {
                mRipples.add(new Circle(0, 255));
            }
        }


        invalidate();

        canvas.restore();
    }


    /**
     * 圆到对角线
     *
     * @param canvas
     */
    private void drawOutCircle(Canvas canvas) {
        canvas.save();

        // 使用勾股定律求得一个外切正方形中心点离角的距离
        sqrtNumber = (int) (Math.sqrt(mWidth * mWidth + mHeight * mHeight) / 2);

        // 变大
        for (int i = 0; i < mRipples.size(); i++) {

            // 启动圆圈
            Circle c = mRipples.get(i);
            mPaint.setAlpha(c.alpha);// （透明）0~255（不透明）
            canvas.drawCircle(mWidth / 2, mHeight / 2, c.width - mPaint.getStrokeWidth(), mPaint);

            // 当圆超出对角线后删掉
            if (c.width > sqrtNumber) {
                mRipples.remove(i);
            } else {
                // 计算不透明的度数
                double degree = 255 - c.width * (255 / (double) sqrtNumber);
                c.alpha = (int) degree;
                c.width += 1;
            }
        }

        // 里面添加圆
        if (mRipples.size() > 0) {
            // 控制第二个圆出来的间距
            if (mRipples.get(mRipples.size() - 1).width == 50) {
                mRipples.add(new Circle(0, 255));
            }
        }
        invalidate();
        canvas.restore();
    }

    class Circle {
        int width;
        int alpha;

        Circle(int width, int alpha) {
            this.width = width;
            this.alpha = alpha;
        }
    }
}
