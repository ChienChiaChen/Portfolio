package com.chiachen.portfolio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.chiachen.portfolio.R;

public class GestureDetectorActivity extends AppCompatActivity {

    protected GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);
        mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener(){
            @Override
            //e1代表的是手指剛開始滑動的動作，e2代表手指滑動完了的事件
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
                if(motionEvent.getRawX() - motionEvent1.getRawX() > 200){
                    showNext();
                    return true;
                }

                if(motionEvent1.getRawX() - motionEvent.getRawX() > 200){
                    showPre();
                    return true;
                }
                return super.onFling(motionEvent, motionEvent1, velocityX, velocityY);
            }
        });
    }

    public void showNext() {
        Intent intent = new Intent(this, TabLayoutActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
    }

    public void showPre() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
