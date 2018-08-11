

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

public class FragmentPage1 extends Fragment {
    public static final String TAG = "FragmentPage1";
    private FragmentWithAnimActivity animActivity;
    private TextView mTitleText;

    public static FragmentPage1 newInstance() {
        return new FragmentPage1();
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
	    Log.i("Jason", this.getClass().getSimpleName() +" : onAttach");
        if (context instanceof FragmentWithAnimActivity) {
            animActivity = (FragmentWithAnimActivity) context;
        }
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
	    Log.i("Jason", this.getClass().getSimpleName() +" : onCreateView");
        return inflater.inflate(R.layout.fragment_page_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
	    Log.i("Jason", this.getClass().getSimpleName() +" : onViewCreated");
        String title = getArguments().getString(FragmentWithAnimActivity.TAG);
    
        mTitleText = view.findViewById(R.id.title_text);
        mTitleText.setText(title);
        
        view.findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != animActivity) {
                    Fragment fragment = FragmentPage2.newInstance();
                    Bundle args = new Bundle();
                    args.putString(FragmentWithAnimActivity.TAG, getArguments().getString(FragmentWithAnimActivity.TAG));
                    fragment.setArguments(args);
    
                    animActivity.getSupportFragmentManager()
                            .beginTransaction()
                            .addToBackStack(null)
                            .add(R.id.root_view, fragment, FragmentPage2.TAG)
                            .commit();
                }
            }
        });
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        animActivity = null;
    }
}
