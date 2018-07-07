package com.chiachen.portfolio.utils.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.utils.tool.TypefaceManager;


/**
 * Created by pascal on 2017/10/26.
 */

public class WeightDashboardView extends View {
    private static final int LINE_COUNT = 100; //总刻度数
    protected static final int UNDONE_LINE_COLOR = 2660;
    protected static final int WEIGHT_DONE_LINE_COLOR = 2704;

    protected Paint mPaint;
    protected int mWidth, mHeight;

    public WeightDashboardView(Context context) {
        super(context);
        initPaint();
    }

    public WeightDashboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDashboard(canvas, 30);
    }

    private void drawDashboard(Canvas canvas, int percent) {
        canvas.save();
        canvas.translate(mWidth / 2f, mHeight / 2f);

        float y = mHeight / 3f + mHeight / 22f;
        float lineSize = mHeight / 40f;

        canvas.rotate(45, 0f, 0f);

        for (int linePosition = 0; linePosition < LINE_COUNT; linePosition++) {
            if (linePosition < percent) {
                setPaint(WEIGHT_DONE_LINE_COLOR, 0, mHeight / 100f);
            } else {
                setPaint(UNDONE_LINE_COLOR, 0, mHeight / 100f);
            }
            canvas.drawLine(0f, y, 0f, y + lineSize, mPaint);
            canvas.rotate(2.75f, 0f, 0f);
        }
        canvas.restore();
    }

    protected void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.mWidth = w;
        this.mHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    protected void setPaint(int color, float textSize, float strokeWidth) {
        Typeface typeface = TypefaceManager.getTypeface(getContext(), TypefaceManager.GOTHAM_RND_BOLD);
        switch (color) {
            case UNDONE_LINE_COLOR: {
                mPaint.setColor(getResources().getColor(R.color.mainDashboardViewUndoneLine));
                break;
            }

            case WEIGHT_DONE_LINE_COLOR: {
                mPaint.setColor(getResources().getColor(R.color.mainWeightDashboardViewPrimary));
                break;
            }
        }

        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setTextSize((float) textSize * getResources().getDisplayMetrics().scaledDensity);
        mPaint.setTypeface(typeface);
    }
}
