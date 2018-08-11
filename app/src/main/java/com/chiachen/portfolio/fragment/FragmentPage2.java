/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.chiachen.portfolio.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.FragmentWithAnimActivity;

/**
 * Created by janisharali on 27/01/17.
 */

public class FragmentPage2 extends Fragment {
    public static final String TAG = "FragmentPage2";
    private TextView mTitleTxt;
    public static FragmentPage2 newInstance() {
        return new FragmentPage2();
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i("Jason", this.getClass().getSimpleName() +" : onCreateView");
        return inflater.inflate(R.layout.fragment_page_2, container, false);
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");
        mTitleTxt = view.findViewById(R.id.title_text);
        mTitleTxt.setText(getArguments().getString(FragmentWithAnimActivity.TAG));
        Log.i("Jason", this.getClass().getSimpleName() +" : onViewCreated");
    }
}
