package com.chiachen.portfolio.activity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chiachen.portfolio.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
	private static final int TUTORIAL_PAGE_COUNT = 4;
	
	private int mPagePosition;
	private ViewPager mViewPager;
	private View mLeftArrowBtn;
	private View mRightArrowBtn;
	private LinearLayout mIndicatorView;
	
	private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageSelected(int position) {
			mPagePosition = position;
			int indicatorPosition = position % TUTORIAL_PAGE_COUNT;
			if (indicatorPosition < mIndicatorView.getChildCount()) {
				Integer prevPos = (Integer) mIndicatorView.getTag();
				if (prevPos != null) {
					if (prevPos == indicatorPosition)
						return;
					
					View view = mIndicatorView.getChildAt(prevPos);
					if (view != null)
						view.setSelected(false);
				}
				
				View view = mIndicatorView.getChildAt(indicatorPosition);
				if (view != null)
					view.setSelected(true);
				
				mIndicatorView.setTag(indicatorPosition);
			}
		}
		
		@Override
		public void onPageScrolled(int position, float offset, int offsetPixels) {
		}
		
		@Override
		public void onPageScrollStateChanged(int state) {
			// updateArrowButtonVisibility(false);
		}
	};
	
	private void initIndicator(int count) {
		if (null == mIndicatorView) {
			return;
		}
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		for (int i = 0; i < count; i++) {
			ImageView view = (ImageView) inflater.inflate(R.layout.view_item_tutorial_indicator, mIndicatorView, false);
			mIndicatorView.addView(view);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pager);
		
		final PromotePagerAdapter tutorialPagerAdapter = new PromotePagerAdapter();
		mViewPager = (ViewPager) findViewById(R.id.promoteViewPager);
		
		mIndicatorView = (LinearLayout) findViewById(R.id.promoteIndicatorView);
		initIndicator(TUTORIAL_PAGE_COUNT);
		
		Integer init = -1;
		mIndicatorView.setTag(init);
		View view = mIndicatorView.getChildAt(0);
		if (view != null) {
			view.setSelected(true);
		}
		mViewPager.setAdapter(tutorialPagerAdapter);
		mViewPager.setCurrentItem(tutorialPagerAdapter.getCount() / 2);
		mViewPager.addOnPageChangeListener(mOnPageChangeListener);
		
		mLeftArrowBtn = findViewById(R.id.leftArrowBtn);
		mLeftArrowBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				movePageBackward();
			}
		});
		
		mRightArrowBtn = findViewById(R.id.rightArrowBtn);
		mRightArrowBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				movePageForward();
			}
		});
		
		mOnPageChangeListener.onPageSelected(tutorialPagerAdapter.getCount() / 2);
	}
	
	private void movePageForward() {
		int index = mViewPager.getCurrentItem();
		mViewPager.setCurrentItem((index + 1), true);
	}
	
	private void movePageBackward() {
		int index = mViewPager.getCurrentItem();
		mViewPager.setCurrentItem((index - 1), true);
	}
	
	private class PromotePagerAdapter extends PagerAdapter {
		private final List<Integer> mTutorialPageList;
		
		PromotePagerAdapter() {
			mTutorialPageList = new ArrayList<>();
			
			mTutorialPageList.add(R.layout.iap_promote_page_a);
			mTutorialPageList.add(R.layout.iap_promote_page_b);
			mTutorialPageList.add(R.layout.iap_promote_page_c);
			mTutorialPageList.add(R.layout.iap_promote_page_d);
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
		
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
			int layoutId = mTutorialPageList.get(position % mTutorialPageList.size());
			View view = layoutInflater.inflate(layoutId, null);
			
			container.addView(view);
			return view;
		}
		
		@Override
		public int getCount() {
			return 200;
		}
		
		@Override
		public int getItemPosition(Object object) {
			return PagerAdapter.POSITION_UNCHANGED;
		}
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}
}
