package com.chiachen.portfolio.utils.ui;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TouchLayout extends FrameLayout {
	
	private String TAG = TouchLayout.class.getSimpleName();
	
	public TouchLayout(Context context) {
		super(context);
		
	}
	
	public TouchLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public TouchLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean dispatch = super.dispatchTouchEvent(ev);
		Log.e("Jason", "TouchLayout dispatchTouchEvent: " + dispatch + "\n");
		return dispatch;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// boolean intercept = super.onInterceptTouchEvent(ev);
		boolean intercept = true;
		Log.e("Jason", "TouchLayout onInterceptTouchEvent: " + intercept + "\n");
		return intercept;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		boolean touchEvent = super.onInterceptTouchEvent(ev);
		Log.e("Jason", "TouchLayout onTouchEvent: " + touchEvent + "\n");
		return touchEvent;
	}
}