package com.chiachen.portfolio.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chiachen.portfolio.R;

public class TouchInterceptActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch_intercept);
		View myView = findViewById(R.id.my_button);
		
		ValueAnimator colorAnim = ObjectAnimator.ofInt(myView,
				"backgroundColor", /* Red */
				0xFFFF8080, /* Blue */0xFF8080FF);
		colorAnim.setDuration(3000);
		colorAnim.setEvaluator(new ArgbEvaluator());
		colorAnim.setRepeatCount(ValueAnimator.INFINITE);
		colorAnim.setRepeatMode(ValueAnimator.REVERSE);
		colorAnim.start();
		
		ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myView, "scaleX",
				0.5f);
		objectAnimator.setDuration(3000);
		objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
		objectAnimator.start();
		
		Log.e("Jason", "### Activiti中getWindow()获取的类型是 : " + this.getWindow());
		
		// state list
		StateListDrawable stateListDrawable = new StateListDrawable();
		stateListDrawable.addState(new int[]{
				android.R.attr.state_enabled
		}, getResources().getDrawable(R.mipmap.ic_launcher));
		stateListDrawable.addState(new int[]{
				android.R.attr.state_pressed
		}, getResources().getDrawable(R.mipmap.ic_launcher));
	}
}
