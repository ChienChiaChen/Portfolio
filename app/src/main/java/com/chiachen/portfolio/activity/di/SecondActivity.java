package com.chiachen.portfolio.activity.di;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.activity.di.component.DaggerSecondComponent;
import com.chiachen.portfolio.activity.di.component.SecondComponent;
import com.chiachen.portfolio.activity.di.module.SecondModule;

import javax.inject.Inject;

public class SecondActivity extends AppCompatActivity {
    @Inject
    Cloth blueCloth;
    @Inject
    ClothHandler clothHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SecondComponent component = DaggerSecondComponent.builder().secondModule(new SecondModule()).build();
        component.inject(this);
        ((TextView) findViewById(R.id._text)).setText(""+clothHandler.handle(blueCloth)+"  -> \naddr  :"+clothHandler);
    }
}
