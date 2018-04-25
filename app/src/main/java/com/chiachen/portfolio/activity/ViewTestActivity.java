package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import com.chiachen.portfolio.R;

public class ViewTestActivity extends AppCompatActivity {

    private String mContent;
    private TextView mTvText;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        mTvText = (TextView) findViewById(R.id.main_tv_text);
        showTotal();
        setupGestureDetector();


    }

    private void setupGestureDetector() {
        mGestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                Log.e("JASON_CHIEN", "\nTouch screen");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Log.e("JASON_CHIEN", "\nStill in screen");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.e("JASON_CHIEN", "\nOne tap");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.e("JASON_CHIEN", "\nScroll");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.e("JASON_CHIEN", "\nLong Press");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.e("JASON_CHIEN", "\nFling");
                return false;
            }
        });

        mGestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.e("JASON_CHIEN", "\nonSingleTapConfirmed");
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Log.e("JASON_CHIEN", "\nonDoubleTap");
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                Log.e("JASON_CHIEN", "\nonDoubleTapEvent");
                return false;
            }
        });
    }

    private void showTotal() {
        mContent = "";

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = getResources().getDisplayMetrics().density;

        mContent += "Width:" + metrics.widthPixels + "(Pixel)"
                + (metrics.widthPixels / density) + "(DP);\n";
        mContent += "Height:" + metrics.heightPixels + "(Pixel)"
                + (metrics.heightPixels / density) + "(DP);";
        // mContent += "\nXVelocity: " + mXVelocity + ", YVelocity: " + mYVelocity;

        mTvText.setText(mContent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            Log.e("JASON_CHIEN", "\nMotionEvent.ACTION_DOWN");
        } else if (MotionEvent.ACTION_UP == event.getAction()) {
            Log.e("JASON_CHIEN", "\nMotionEvent.ACTION_UP");
        } else if (MotionEvent.ACTION_MOVE == event.getAction()) {
            Log.e("JASON_CHIEN", "\nMotionEvent.ACTION_MOVE");
        }
        Log.e("JASON_CHIEN", "\nevent.getX() " + event.getX());
        Log.e("JASON_CHIEN", "\nevent.getY() " + event.getY());
        Log.e("JASON_CHIEN", "\nevent.getRawX() " + event.getRawX());
        Log.e("JASON_CHIEN", "\nevent.getRawY() " + event.getRawY());

        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
