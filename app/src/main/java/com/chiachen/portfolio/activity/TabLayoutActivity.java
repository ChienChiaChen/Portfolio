package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.adapter.ViewPagerAdapter;
import com.chiachen.portfolio.fragment.ItemFragmentOne;
import com.chiachen.portfolio.fragment.ItemFragmentThree;
import com.chiachen.portfolio.fragment.ItemFragmentTwo;

public class TabLayoutActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        mViewPager = findViewById(R.id.viewpager);
        initToolbar();
        initTab();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getString(R.string.app_name));
        }
    }

    private void initTab() {
        mTabLayout = findViewById(R.id.tabs);
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.white));
        mTabLayout.setupWithViewPager(getViewPager());
        setupTabIcons();
    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_person);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_group);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_infor);
    }


    public ViewPager getViewPager() {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(ItemFragmentOne.newInstance(), ItemFragmentOne.TAG);
        adapter.addFrag(ItemFragmentTwo.newInstance(), ItemFragmentTwo.TAG);
        adapter.addFrag(ItemFragmentThree.newInstance(), ItemFragmentThree.TAG);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (adapter.getItem(position) instanceof ItemFragmentOne) {
                    Toast.makeText(TabLayoutActivity.this, ItemFragmentOne.TAG, Toast.LENGTH_SHORT).show();
                } else if (adapter.getItem(position) instanceof ItemFragmentTwo) {
                    Toast.makeText(TabLayoutActivity.this, ItemFragmentTwo.TAG, Toast.LENGTH_SHORT).show();
                } else if (adapter.getItem(position) instanceof ItemFragmentThree) {
                    Toast.makeText(TabLayoutActivity.this, ItemFragmentThree.TAG, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return mViewPager;
    }
}
