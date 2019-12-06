package com.zoyo.data.chart.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zoyo.data.utils.ScreenSizeUtil;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @Description: java类作用描述
 * @CreateDate: 2019/11/21 10:15
 */
public class PieView extends View {


    //数值
    private float[] mValues;
    //每个数值的占比
    private float[] mValuePercents;
    private NumberFormat numberFormat;
    //当前进度角度
    private float mProcessAngle;
    private float mArcIntervalAngle;

    //每个点的名称
    private String[] mNames;
    //每个数据的颜色
    private int[] mColors;
    //默认位数
    private final static int DEFAULT_DISPLAY_DIGITS = 0;
    private NumberFormat percentFormat;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //数值格式化
        numberFormat = NumberFormat.getInstance(Locale.CHINA);
        numberFormat.setMaximumFractionDigits(DEFAULT_DISPLAY_DIGITS);
        numberFormat.setMinimumFractionDigits(DEFAULT_DISPLAY_DIGITS);
        //百分比格式化
        percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(DEFAULT_DISPLAY_DIGITS);
        percentFormat.setMaximumFractionDigits(DEFAULT_DISPLAY_DIGITS);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getSize(ScreenSizeUtil.dp2px(getContext(), 300f), widthMeasureSpec),
                getSize(ScreenSizeUtil.dp2px(getContext(), 300f), heightMeasureSpec));
    }

    private int getSize(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            //非精确测量模式
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                result = defaultSize;
                break;
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            default:
                break;
        }

        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mValues == null || mValues.length == 0) {
            return;
        }
        RectF drawBounds = new RectF();
        //绘制区域位置: 相对于自定义控件的位置
        drawBounds.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());

        float width = drawBounds.width();
        float height = drawBounds.height();
        float centerX = width / 2;
        float centerY = height / 2;

        canvas.translate(centerX, centerY);
        measurePieRadius(width, height);
        //起始度数
        float startAngle = -90;
        float totalAngle = 360;

        for (int i = 0; i < mValues.length; i++) {
            float percent = mValuePercents[i];
            String dValue = numberFormat.format(mValues[i]);
            String dPercent = percentFormat.format(percent);
            if (percent != 0) {
                float sweepAngle;

                if (i == mValues.length - 1) {
                    sweepAngle = mProcessAngle - 90 - startAngle - mArcIntervalAngle;
                } else {
                    sweepAngle = percent * (mProcessAngle - mArcIntervalAngle * mValues.length);
                }

                //绘制圆弧
                draPieArc(canvas, dValue, dPercent, mNames[i], mColors[i], startAngle, sweepAngle);
                //修改下一个圆弧的起点
                startAngle += sweepAngle + mArcIntervalAngle;

            }
        }


    }

    private void draPieArc(Canvas canvas, String dValue, String dPercent, String mName, int mColor, float startAngle, float sweepAngle) {

    }

    /**
     * 测量半径
     *
     * @param width  绘制区域宽
     * @param height 绘制区域高
     */
    private void measurePieRadius(float width, float height) {
        RectF pieBounds = new RectF();

        float minSize = Math.min(width / 2, height / 2);
        float minPieRadius = minSize / 4;


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }
}
