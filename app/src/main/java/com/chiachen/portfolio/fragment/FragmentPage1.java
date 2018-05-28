

package com.chiachen.portfolio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.FragmentWithAnimActivity;

public class FragmentPage1 extends Fragment {
    public static final String TAG = "FragmentPage1";

    public static FragmentPage1 newInstance() {
        return new FragmentPage1();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentWithAnimActivity) getActivity()).nextPage(FragmentPage2.newInstance(), FragmentPage2.TAG);
            }
        });
    }
}
