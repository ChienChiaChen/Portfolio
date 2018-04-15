package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.global.ResourceService;
import com.chiachen.portfolio.presenter.LoginPresenter;
import com.chiachen.portfolio.utils.Constant;
import com.chiachen.portfolio.utils.tool.DensityUtil;
import com.chiachen.portfolio.view.ILoginView;

// Refer:https://medium.com/joe-tsai/%E7%94%A8-constraintlayout-%E8%A7%A3%E6%B1%BA%E9%8D%B5%E7%9B%A4%E6%93%8B%E4%BD%8F%E8%BC%B8%E5%85%A5%E6%A1%86%E5%8F%8A%E6%8C%89%E9%88%95-642fe2a9b855
public class EditTextActivity extends BaseActivity implements ILoginView {

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        ResourceService.setCurrentPageContext(this);
        mPresenter = new LoginPresenter();
        startHeaderAnim();
        startFooterAnim();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unbindView();
    }

    private void startHeaderAnim() {
        ViewCompat.animate(findViewById(R.id.layout_header))
                .translationY(-(DensityUtil.dp2px(EditTextActivity.this,80)))// 80dp
                .setStartDelay(Constant.STARTUP_DELAY)
                .setDuration(Constant.ANIM_ITEM_DURATION)
                .setInterpolator(new DecelerateInterpolator(1.2f))
                .start();
    }

    private void startFooterAnim() {
        for (int i = 0; i < ((ViewGroup) findViewById(R.id.layout_footer)).getChildCount(); i++) {
            View view = ((ViewGroup) findViewById(R.id.layout_footer)).getChildAt(i);
            if (!(view instanceof Button)) {
                ViewCompat.animate(view)
                        .translationY(50)
                        .alpha(1)
                        .setStartDelay((Constant.ITEM_DELAY * i) + 500)
                        .setDuration(Constant.ANIM_ITEM_DURATION)
                        .setInterpolator(new DecelerateInterpolator())
                        .start();
            } else {
                ViewCompat.animate(view)
                        .scaleY(1)
                        .scaleX(1)
                        .setStartDelay((Constant.ITEM_DELAY * i) + 500)
                        .setDuration(Constant.ANIM_ITEM_DURATION / 2)
                        .setInterpolator(new DecelerateInterpolator())
                        .start();
            }
        }
    }

    public void requestLogin(View view) {
        mPresenter.requestLogin("jjason", ((EditText) findViewById(R.id.et_pwd)).getText().toString());
    }

    @Override
    public void loginSuccessfully() {
        Toast.makeText(EditTextActivity.this, "loginSuccessfully", Toast.LENGTH_SHORT).show();
    }
}
