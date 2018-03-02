package com.chiachen.portfolio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chiachen.portfolio.R;


public class ItemFragmentThree extends Fragment {
    public static final String TAG = "ItemThreeFragment";

    public static Fragment newInstance() {
        return new ItemFragmentThree();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("JASON_CHIEN", "\nonViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("JASON_CHIEN", "\nonActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("JASON_CHIEN", "\nonStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("JASON_CHIEN", "\nonResume");
    }

}
