package com.zoyo.data.motionlayout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/8/20 13:50
 */
public class BoundsImageView extends AppCompatImageView {

    private Paint paint;

    public BoundsImageView(Context context) {
        this(context, null);
    }

    public BoundsImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoundsImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setARGB(255, 200, 0, 0);
        paint.setStrokeWidth(4f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0f, 0f, getWidth(), getHeight(), paint);
        canvas.drawLine(0f, getHeight(), getWidth(), 0f, paint);
    }
}
