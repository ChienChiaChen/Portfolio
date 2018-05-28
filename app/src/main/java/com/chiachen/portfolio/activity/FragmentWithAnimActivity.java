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
        nextPage(FragmentPage1.newInstance(), FragmentPage1.TAG);
    }

    public void nextPage(Fragment fragment, String tag) {
        FragmentUtils.nextFragment(getSupportFragmentManager(), fragment, tag, R.id.root_view);
    }

    @Override
    public void onBackPressed() {
        if (0 != getSupportFragmentManager().getBackStackEntryCount()) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }
}
