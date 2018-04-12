package com.chiachen.portfolio.utils.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chiachen.portfolio.R;

import java.util.ArrayList;

/**
 * Created by jianjiacheng on 09/04/2018.
 */

public class BottomDialogFragment extends DialogFragment {

    LinearLayout mLlFirstContainer;
    TextView mTv100Coins;
    ImageView mIv100Coins;
    LinearLayout mLlSecondContainer;
    TextView mTv2Yuan;
    ImageView mIv2Yuan;
    LinearLayout mLlThirdContainer;
    TextView mTv8Yuan;
    ImageView mIv8Yuan;
    LinearLayout mLlForthContainer;
    TextView mTv12Yuan;
    ImageView mIv12Yuan;

    TextView mTvSend;
    TextView mTvCoinCount;

    private ArrayList<LinearLayout> mLayouts;
    private ArrayList<TextView> mTvTypes;
    private ArrayList<ImageView> mIvTypes;
    private int mType = 0;
    private int mCoinCount = 2310;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_bottom);
        dialog.setCanceledOnTouchOutside(true);

        //===== Close bottom
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        //=====

        initUI(dialog);
        initClickTypes();

        return dialog;
    }

    private void initUI(Dialog paraent) {
        mLlFirstContainer = paraent.findViewById(R.id.regards_ll_first_container);
        mTv100Coins = paraent.findViewById(R.id.regards_tv_100_coins);
        mIv100Coins = paraent.findViewById(R.id.regards_iv_100_coins);
        mTv2Yuan = paraent.findViewById(R.id.regards_tv_2_yuan);
        mIv2Yuan = paraent.findViewById(R.id.regards_iv_2_yuan);
        mLlThirdContainer = paraent.findViewById(R.id.regards_ll_third_container);
        mTv8Yuan = paraent.findViewById(R.id.regards_tv_8_yuan);
        mIv8Yuan = paraent.findViewById(R.id.regards_iv_8_yuan);
        mLlForthContainer = paraent.findViewById(R.id.regards_ll_forth_container);
        mLlSecondContainer = paraent.findViewById(R.id.regards_ll_second_container);
        mTv12Yuan = paraent.findViewById(R.id.regards_tv_12_yuan);
        mIv12Yuan = paraent.findViewById(R.id.regards_iv_12_yuan);
        mTvSend = paraent.findViewById(R.id.regards_tv_send);
        mTvCoinCount = paraent.findViewById(R.id.regards_tv_coin_count);
    }

    /**
     *
     */
    private void initClickTypes() {
        initViewArray();
        initLayout();

        mTvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typeStr = "Out of money";
                switch (mType) {
                    case 0:
                        if (countCoins(100)) typeStr = "Alert";
                        break;
                    case 1:
                        if (countCoins(200)) typeStr = "Dialer";
                        break;
                    case 2:
                        if (countCoins(800)) typeStr = "Info";
                        break;
                    case 3:
                        if (countCoins(1200)) typeStr = "Sync";
                        break;
                    default:
                        break;
                }

                Toast.makeText(v.getContext(), typeStr, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *
     */
    private void initLayout() {
        chooseRegardsType(mType); //

        for (int i = 0; i < mLayouts.size(); i++) {
            final int tmp = i;
            LinearLayout ll = mLayouts.get(i);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mType = tmp;
                    chooseRegardsType(mType);
                }
            });
        }
    }

    /**
     *
     *
     * @param count
     * @return
     */
    private boolean countCoins(int count) {
        int end = mCoinCount - count;
        if (end > 0) {
            mCoinCount = end;
            mTvCoinCount.setText(String.valueOf("您共有金币" + end));
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     */
    private void initViewArray() {
        mLayouts = new ArrayList<>();
        mTvTypes = new ArrayList<>();
        mIvTypes = new ArrayList<>();

        mLayouts.add(mLlFirstContainer);
        mLayouts.add(mLlSecondContainer);
        mLayouts.add(mLlThirdContainer);
        mLayouts.add(mLlForthContainer);

        mTvTypes.add(mTv100Coins);
        mTvTypes.add(mTv2Yuan);
        mTvTypes.add(mTv8Yuan);
        mTvTypes.add(mTv12Yuan);

        mIvTypes.add(mIv100Coins);
        mIvTypes.add(mIv2Yuan);
        mIvTypes.add(mIv8Yuan);
        mIvTypes.add(mIv12Yuan);
    }

    /**
     *
     *
     * @param type
     */
    private void chooseRegardsType(int type) {
        for (int i = 0; i < mTvTypes.size(); ++i) {
            mTvTypes.get(i).setEnabled((i != type));
            mIvTypes.get(i).setEnabled((i != type));
        }
    }

}