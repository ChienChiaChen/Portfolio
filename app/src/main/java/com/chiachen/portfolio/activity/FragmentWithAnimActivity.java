package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.fragment.FragmentPage1;
import com.chiachen.portfolio.fragment.FragmentUtils;

public class FragmentWithAnimActivity extends AppCompatActivity {
    public static final String TAG = "FragmentWithAnimActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_with_anim);
        Fragment fragment = FragmentPage1.newInstance();
        Bundle args = new Bundle();
        args.putString(FragmentWithAnimActivity.TAG, FragmentWithAnimActivity.class.getSimpleName());
        fragment.setArguments(args);
    
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.root_view, fragment, FragmentPage1.TAG)
                .commit();
    }
}
