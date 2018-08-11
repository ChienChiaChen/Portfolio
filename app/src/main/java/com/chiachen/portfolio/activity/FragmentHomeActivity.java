package com.chiachen.portfolio.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.page.path.Router;

public class FragmentHomeActivity extends AppCompatActivity {
	
	private TextView mTitleTxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_home);
		mTitleTxt = findViewById(R.id.title_text);
		mTitleTxt.setText(FragmentHomeActivity.class.getSimpleName());
		mTitleTxt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(FragmentHomeActivity.this, FragmentWithAnimActivity.class));
			}
		});
	}
}
