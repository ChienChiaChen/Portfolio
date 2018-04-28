package com.chiachen.portfolio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chiachen.portfolio.R;

/**
 * Created by jianjiacheng on 27/04/2018.
 */

public class FragmentTab extends Fragment {
    private TextView mTvText;

    private View mViewContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mViewContent == null) {
            mViewContent = inflater.inflate(R.layout.fragment_tab, container, false);
        }

        ViewGroup parent = (ViewGroup) mViewContent.getParent();
        if (parent != null) {
            Log.e("JASON_CHIEN", "\nparent != null");
            parent.removeView(mViewContent);
        }

        mViewContent = inflater.inflate(R.layout.fragment_tab, container, false);
        return mViewContent;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvText = view.findViewById(R.id.tab_tv_text);
        mTvText.setText(String.valueOf("Page: " + getTag()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
