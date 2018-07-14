package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import com.chiachen.portfolio.R;

//https://www.jianshu.com/p/85f9020173b3
public class MergeViewStubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_view_stub);
        ViewStub viewStub = (ViewStub) findViewById(R.id.view_stub);
        View view = viewStub.inflate();
    }
}
