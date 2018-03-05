package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.fragment.ItemFragmentFour;
import com.chiachen.portfolio.fragment.ItemFragmentOne;
import com.chiachen.portfolio.fragment.ItemFragmentThree;
import com.chiachen.portfolio.fragment.ItemFragmentTwo;
import com.chiachen.portfolio.presenter.BottomPresenter;
import com.chiachen.portfolio.presenter.IBottomPresenter;
import com.chiachen.portfolio.view.IBottomView;

public class BottomTabActivity extends AppCompatActivity implements IBottomView {
    private IBottomPresenter mPresenter;
    private FragmentManager fm;
    private Fragment mFragmentNow;

    private View.OnClickListener mOnTabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.clearTabStatus();
            Fragment selectedFragment = null;
            String tag = "";
            switch (v.getId()) {
                case R.id.bottom_tab_diary: {
                    getDiary().setSelected(true);
                    tag = ItemFragmentOne.TAG;
                    selectedFragment = getSelectedFragment(ItemFragmentOne.TAG, ItemFragmentOne.newInstance());
                    break;
                }

                case R.id.bottom_tab_report: {
                    getReport().setSelected(true);
                    tag = ItemFragmentTwo.TAG;
                    selectedFragment = getSelectedFragment(ItemFragmentTwo.TAG, ItemFragmentTwo.newInstance());
                    break;
                }

                case R.id.bottom_tab_coach: {
                    getCoach().setSelected(true);
                    tag = ItemFragmentThree.TAG;
                    selectedFragment = getSelectedFragment(ItemFragmentThree.TAG, ItemFragmentThree.newInstance());
                    break;
                }

                case R.id.bottom_tab_more: {
                    getMore().setSelected(true);
                    tag = ItemFragmentFour.TAG;
                    selectedFragment = getSelectedFragment(ItemFragmentFour.TAG, ItemFragmentFour.newInstance());
                    break;
                }
            }

            if (null == selectedFragment) return;
            switchContent(mFragmentNow, selectedFragment, tag);
        }

        @NonNull
        private Fragment getSelectedFragment(String tag, Fragment fragment) {
            return (null != fm.findFragmentByTag(tag)) ? fm.findFragmentByTag(tag) : fragment;
        }
    };

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new BottomPresenter(this);

        setContentView(R.layout.activity_bottom_tab);
        getDiary().setOnClickListener(mOnTabClickListener);
        getReport().setOnClickListener(mOnTabClickListener);
        getCamera().setOnClickListener(mOnTabClickListener);
        getCoach().setOnClickListener(mOnTabClickListener);
        getMore().setOnClickListener(mOnTabClickListener);
        fm = getSupportFragmentManager();
        switchContent(null, ItemFragmentOne.newInstance(), ItemFragmentOne.TAG);
    }

    @Override
    public void clearTabStatus() {
        getDiary().setSelected(false);
        getReport().setSelected(false);
        getCamera().setSelected(false);
        getCoach().setSelected(false);
        getMore().setSelected(false);
    }

    private void switchContent(Fragment from, Fragment to, String tag) {
        if (mFragmentNow == to) return;


        mFragmentNow = to;
        if (!to.isAdded() && null == from) {
            fm.beginTransaction().add(R.id.frame_layout, to, tag).commit();
        } else if (!to.isAdded() && null != from) { // Hide current fragment and Add new fragment
            fm.beginTransaction().hide(from).add(R.id.frame_layout, to, tag).commit();
        } else { // Hide current fragment and Show it.
            fm.beginTransaction().hide(from).show(to).commit();
        }
    }

    private View getMore() {
        return findViewById(R.id.bottom_tab_more);
    }

    private View getCamera() {
        return findViewById(R.id.bottom_tab_camera);
    }

    private View getCoach() {
        return findViewById(R.id.bottom_tab_coach);
    }

    private View getReport() {
        return findViewById(R.id.bottom_tab_report);
    }

    private View getDiary() {
        return findViewById(R.id.bottom_tab_diary);
    }
}
