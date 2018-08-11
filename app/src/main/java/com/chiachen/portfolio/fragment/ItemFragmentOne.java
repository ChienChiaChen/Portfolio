package com.chiachen.portfolio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chiachen.portfolio.R;


public class ItemFragmentOne extends Fragment {
    public static final String TAG = "ItemFragmentOne";
    private Button tab1, tab2, tab3;

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment selectedFragment = null;
            switch (v.getId()) {
                case R.id.tab1: {
                    selectedFragment = new ItemFragmentTwo();
                    break;
                }
                case R.id.tab2: {
                    selectedFragment = new ItemFragmentThree();
                    break;
                }
                case R.id.tab3: {
                    selectedFragment = new ItemFragmentFour();
                    break;
                }
            }

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.child_frame_layout, selectedFragment);
            transaction.commit();
        }
    };

    public static Fragment newInstance() {
        return new ItemFragmentOne();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("Jason", ItemFragmentOne.class.getSimpleName() + " : onHiddenChanged");
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tab1 = view.findViewById(R.id.tab1);
        tab2 = view.findViewById(R.id.tab2);
        tab3 = view.findViewById(R.id.tab3);
        tab1.setOnClickListener(mListener);
        tab2.setOnClickListener(mListener);
        tab3.setOnClickListener(mListener);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.child_frame_layout, new ItemFragmentTwo());
        transaction.commit();
    }
}
