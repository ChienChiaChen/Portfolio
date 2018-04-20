package com.chiachen.portfolio.activity.di;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.chiachen.portfolio.R;

import javax.inject.Inject;

public class Dagger2ExampleActivity extends AppCompatActivity {

    @Inject
    Cloth mCloth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_example);

        MainComponent build = DaggerMainComponent
                                .builder()
                                .mainModule(new MainModule())
                                .build();

        build.inject(this);
        ((TextView) findViewById(R.id._text)).setText(mCloth.getColor());


    }
}
