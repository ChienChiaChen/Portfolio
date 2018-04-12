package com.chiachen.portfolio.activity.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.page.path.AbstractPath;
import com.chiachen.portfolio.utils.tool.PreferenceHelper;

public class RoutingPageCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing_page_c);
        findViewById(R.id.bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoutingPageCActivity.this, RoutingPageDActivity.class));
            }
        });

        PreferenceHelper.onSetPrefIntSetting(AbstractPath.PAGE, AbstractPath.PAGE, AbstractPath.GoToC, RoutingPageCActivity.this);
    }
}
