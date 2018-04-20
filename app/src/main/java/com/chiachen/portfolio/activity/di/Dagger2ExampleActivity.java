package com.chiachen.portfolio.activity.di;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.chiachen.portfolio.R;

import javax.inject.Inject;
import javax.inject.Named;

public class Dagger2ExampleActivity extends AppCompatActivity {

    @Inject
    Cloth mCloth;

    @Inject
    Shoe mShoe;

    @Inject
    Clothes mClothes;

    @Inject
    @Named("red")
    Cloth mRedCloth;

    @Inject
    @Named("blue")
    Cloth mblueCloth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_example);

        MainComponent build = DaggerMainComponent
                                .builder()
                                .mainModule(new MainModule())
                                .build();

        build.inject(this);
        // ((TextView) findViewById(R.id._text)).setText("我现在有" + mCloth + "和" + mShoe+"   "+mClothes);
        // ((TextView) findViewById(R.id._text)).setText( mRedCloth + "和" + mblueCloth);
        ((TextView) findViewById(R.id._text)).setText("redCloth = clothes中的cloth嗎?:" + (mCloth == mClothes.getCloth()));

    }
}
