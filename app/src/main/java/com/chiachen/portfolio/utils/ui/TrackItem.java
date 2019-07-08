package com.chiachen.portfolio.utils.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chiachen.portfolio.R;

public class TrackItem extends RelativeLayout {
	private static final int DEFAULT_LAYOUT = R.layout.custom_track_item;
	
	private TextView mTrackItemIndex;
	private TextView mTrackItemTitle;
	private TextView mTrackItemSubTitle;
	
	public TrackItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater.from(context).inflate(TrackItem.DEFAULT_LAYOUT, this, true);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TrackItem, 0, 0);
		
		mTrackItemIndex = findViewById(R.id.track_item_index);
		String trackItemIndex = typedArray.getString(R.styleable.TrackItem_track_item_index);
		if (null != trackItemIndex) {
			mTrackItemIndex.setText(trackItemIndex);
		}
		
		mTrackItemTitle = findViewById(R.id.track_item_title);
		String trackItemTitle = typedArray.getString(R.styleable.TrackItem_track_item_title);
		if (null != trackItemTitle) {
			mTrackItemTitle.setText(trackItemTitle);
		}
		mTrackItemSubTitle = findViewById(R.id.track_item_sub_title);
		String trackItemSubTitle = typedArray.getString(R.styleable.TrackItem_track_item_subtitle);
		if (null != trackItemSubTitle) {
			mTrackItemSubTitle.setText(trackItemSubTitle);
		}
		typedArray.recycle();
	}
	
}
