package com.chiachen.portfolio.utils.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.chiachen.portfolio.R;

import java.lang.ref.WeakReference;

//https://blog.csdn.net/lmj623565791/article/details/42094215
@SuppressLint("AppCompatCustomView")
public class RoundImageViewByXfermode extends ImageView {
    private Paint mPaint;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private Bitmap mMaskBitmap;

    private WeakReference<Bitmap> mWeakBitmap;

    /**
     * type
     */

    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;
    private int type;

    /**
     * RADIUS
     */
    private static final int BORDER_RADIUS_DEFAULT = 10;
    private int mBorderRadius;

    public RoundImageViewByXfermode(Context context) {
        this(context, null);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    public RoundImageViewByXfermode(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageViewByXfermode);
        mBorderRadius = typedArray.getDimensionPixelSize(R.styleable.RoundImageViewByXfermode_borderRadius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BORDER_RADIUS_DEFAULT, getResources().getDisplayMetrics()));
        type = typedArray.getInt(R.styleable.RoundImageViewByXfermode_type, TYPE_CIRCLE);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (type == TYPE_CIRCLE) {
            int min = Math.min(getMeasuredWidth(), getMeasuredHeight());
            setMeasuredDimension(min, min);
        }

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = mWeakBitmap == null ? null : mWeakBitmap.get();
        if (null == bitmap || bitmap.isRecycled()) {
            Drawable drawable = getDrawable();
            int dWidth = drawable.getIntrinsicWidth();
            int dHeight = drawable.getIntrinsicHeight();
            if (null != drawable) {
                bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                float scale = 1.0f;
                Canvas drawCanvas = new Canvas(bitmap);
                if (type == TYPE_ROUND) {
                    scale = Math.max(getWidth() * 1.0f / dWidth, getHeight() * 1.0f / dHeight);
                } else {
                    scale = getWidth() * 1.0F / Math.min(dWidth, dHeight);
                }
                drawable.setBounds(0, 0, (int) (scale * dWidth), (int) (scale * dHeight));
                drawable.draw(drawCanvas);

                if (mMaskBitmap == null || mMaskBitmap.isRecycled()) {
                    mMaskBitmap = getBitmap();
                }

                mPaint.reset();
                mPaint.setFilterBitmap(false);
                mPaint.setXfermode(mXfermode);

                drawCanvas.drawBitmap(mMaskBitmap, 0, 0, mPaint);
                mPaint.setXfermode(null);

                canvas.drawBitmap(bitmap, 0, 0, null);

                mWeakBitmap = new WeakReference<>(bitmap);
            }
        }

        if (bitmap != null) {
            mPaint.setXfermode(null);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint);
            return;
        }

    }

    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        if (type == TYPE_ROUND) {
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), mBorderRadius, mBorderRadius, paint);
        } else {
            canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, paint);
        }

        return bitmap;
    }

    @Override
    public void invalidate() {
        mWeakBitmap = null;
        if (mMaskBitmap != null) {
            mMaskBitmap.recycle();
            mMaskBitmap = null;
        }
        super.invalidate();
    }

}
