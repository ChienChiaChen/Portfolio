package com.chiachen.portfolio.activity.page;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.page.path.AbstractPath;
import com.chiachen.portfolio.utils.tool.PreferenceHelper;

public class RoutingPageDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing_page_d);
        findViewById(R.id.bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceHelper.onSetPrefIntSetting(AbstractPath.PAGE, AbstractPath.PAGE, AbstractPath.GoToA, RoutingPageDActivity.this);
            }
        });

        PreferenceHelper.onSetPrefIntSetting(AbstractPath.PAGE, AbstractPath.PAGE, AbstractPath.GoToD, RoutingPageDActivity.this);
    }
}
