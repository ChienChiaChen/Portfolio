package com.chiachen.portfolio.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.adapter.ViewPagerAdapter;
import com.chiachen.portfolio.adapter.custom_adapter.MyAdapter;
import com.chiachen.portfolio.fragment.ItemFragmentOne;
import com.chiachen.portfolio.fragment.ItemFragmentThree;
import com.chiachen.portfolio.fragment.ItemFragmentTwo;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorPracticeActivity extends AppCompatActivity {

//    private RecyclerView recyclerView;
//    private MyAdapter myAdapter;
//    private List<String> myList;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_practice);

//        myList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            myList.add("item " + (i + 1));
//        }
//        myAdapter = new MyAdapter(myList);
//        recyclerView = findViewById(R.id.rv_content);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(myAdapter);

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
                    Toast.makeText(CoordinatorPracticeActivity.this, ItemFragmentOne.TAG, Toast.LENGTH_SHORT).show();
                } else if (adapter.getItem(position) instanceof ItemFragmentTwo) {
                    Toast.makeText(CoordinatorPracticeActivity.this, ItemFragmentTwo.TAG, Toast.LENGTH_SHORT).show();
                } else if (adapter.getItem(position) instanceof ItemFragmentThree) {
                    Toast.makeText(CoordinatorPracticeActivity.this, ItemFragmentThree.TAG, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return mViewPager;
    }

}
