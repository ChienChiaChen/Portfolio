package com.chiachen.portfolio.activity.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.page.path.AbstractPath;
import com.chiachen.portfolio.activity.page.path.PageA;
import com.chiachen.portfolio.activity.page.path.PageB;
import com.chiachen.portfolio.activity.page.path.PageC;
import com.chiachen.portfolio.activity.page.path.PageD;
import com.chiachen.portfolio.utils.tool.PreferenceHelper;

public class RoutingPageAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing_page_a);
        findViewById(R.id.bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoutingPageAActivity.this, RoutingPageBActivity.class));
            }
        });

        AbstractPath pageA = new PageA(AbstractPath.GoToA, RoutingPageAActivity.this);
        AbstractPath pageB = new PageB(AbstractPath.GoToB,RoutingPageAActivity.this);
        AbstractPath pageC = new PageC(AbstractPath.GoToC,RoutingPageAActivity.this);
        AbstractPath pageD = new PageD(AbstractPath.GoToD,RoutingPageAActivity.this);

        pageA.setNext(pageB);
        pageB.setNext(pageC);
        pageC.setNext(pageD);


        pageA.goNext(PreferenceHelper.onGetPrefInt(AbstractPath.PAGE, AbstractPath.PAGE, RoutingPageAActivity.this));

    }
}
