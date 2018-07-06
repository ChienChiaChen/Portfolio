package com.chiachen.portfolio.utils.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.chiachen.portfolio.utils.FormatUtils;


/**
 * Created by pascal on 2017/10/26.
 */

public class WeightDashboardView extends BasicCustomDrawView {
    private static final float mBigTextSizeSP = 64f;
    private static final float mSmallTextSizeSP = 18f;
    private static final int LINE_COUNT = 100; //总刻度数
    private int mPercent;
    private float mStartWeight, mEndWeight, mNowWeight;
    private String mUnit = "";

    public WeightDashboardView(Context context) {
        super(context);
    }

    public WeightDashboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawText(canvas);

        drawDashboard(canvas, mPercent);

    }

    public void setDashboardData(float nowWeight, float startWeight, float endWeight, String unit, int percent) {
        mNowWeight = nowWeight;
        mStartWeight = startWeight;
        mEndWeight = endWeight;
        mUnit = unit;
        mPercent = percent;
        this.invalidate();
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

    private void drawText(Canvas canvas) {
        canvas.save();
        //至中
        canvas.translate(mWidth / 2f, mHeight / 2f);//+ mHeight / 10f
        //文字
        setPaint(WEIGHT_SEC_PRIMARY_DARK_COLOR, mSmallTextSizeSP);
        canvas.drawText(FormatUtils.formatWeight(mStartWeight), -mHeight / 6f, mHeight / 2f - mHeight / 6f, mPaint);
        canvas.drawText(FormatUtils.formatWeight(mEndWeight), mHeight / 6f, mHeight / 2f - mHeight / 6f, mPaint);
        setPaint(WEIGHT_PRIMARY_COLOR, mBigTextSizeSP);
        int textNowHeight = getTextHeight(FormatUtils.formatWeight(mNowWeight), mPaint);
        canvas.drawText(FormatUtils.formatWeight(mNowWeight), 0f, textNowHeight / 4f + 0f, mPaint);
        setPaint(WEIGHT_PRIMARY_DARK_COLOR, mSmallTextSizeSP);
        canvas.drawText(mUnit, 0f, textNowHeight, mPaint);
        canvas.restore();
    }
}
