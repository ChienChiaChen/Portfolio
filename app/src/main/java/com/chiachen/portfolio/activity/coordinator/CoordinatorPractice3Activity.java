package com.chiachen.portfolio.activity.coordinator;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.adapter.ViewPagerAdapter;
import com.chiachen.portfolio.fragment.ItemFragmentOne;
import com.chiachen.portfolio.fragment.ItemFragmentThree;
import com.chiachen.portfolio.fragment.ItemFragmentTwo;

public class CoordinatorPractice3Activity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_practice3);
        initTab();
    }

    private void initTab() {
        mTabLayout = findViewById(R.id.tabs);
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.white));
        mTabLayout.setBackgroundColor(getResources().getColor(android.R.color.black));
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
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (adapter.getItem(position) instanceof ItemFragmentOne) {
                    Toast.makeText(CoordinatorPractice3Activity.this, ItemFragmentOne.TAG, Toast.LENGTH_SHORT).show();
                } else if (adapter.getItem(position) instanceof ItemFragmentTwo) {
                    Toast.makeText(CoordinatorPractice3Activity.this, ItemFragmentTwo.TAG, Toast.LENGTH_SHORT).show();
                } else if (adapter.getItem(position) instanceof ItemFragmentThree) {
                    Toast.makeText(CoordinatorPractice3Activity.this, ItemFragmentThree.TAG, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return mViewPager;
    }

}
