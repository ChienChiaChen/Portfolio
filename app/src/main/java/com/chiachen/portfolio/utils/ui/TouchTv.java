package com.chiachen.portfolio.utils.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.test.TouchUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class TouchTv extends TextView {
	
	public TouchTv(Context context) {
		this(context, null);
	}
	
	public TouchTv(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public TouchTv(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean result = super.dispatchTouchEvent(ev);
		Log.e("Jason", "### TouchTv dispatchTouchEvent result = " + result + "\n");
		return result;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		boolean result = super.onTouchEvent(ev);
		Log.e("Jason", "### TouchTv onTouchEvent result = " + result + "\n");
		return result;
	}
}
