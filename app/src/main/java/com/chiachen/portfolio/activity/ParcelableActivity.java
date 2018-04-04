package com.chiachen.portfolio.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.data.UserParcelable;

public class ParcelableActivity extends AppCompatActivity {

    public static final String PASS_PARCEL_FILTER = "PASS_PARCEL_FILTER";
    public static final String PARCEL_EXTRA = "PARCEL_EXTRA";

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.tv_select_female: {
                    findViewById(R.id.tv_select_female).setSelected(true);
                    findViewById(R.id.tv_select_male).setSelected(false);
                    break;
                }

                case R.id.tv_select_male: {
                    findViewById(R.id.tv_select_female).setSelected(false);
                    findViewById(R.id.tv_select_male).setSelected(true);
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable);

        findViewById(R.id.tv_select_female).setOnClickListener(mOnClickListener);
        findViewById(R.id.tv_select_male).setOnClickListener(mOnClickListener);
        registerReceiver(mParcelReceiver, new IntentFilter(PASS_PARCEL_FILTER));
    }

    public void parcelSend(View view) {
        boolean isMale = findViewById(R.id.tv_select_male).isSelected();
        String name = ((EditText) findViewById(R.id.et_name)).getText().toString();
        Intent intent = new Intent(PASS_PARCEL_FILTER);
        intent.putExtra(PARCEL_EXTRA, new UserParcelable(0, name, isMale));
        sendBroadcast(intent);
    }

    private BroadcastReceiver mParcelReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            UserParcelable newUser = intent.getParcelableExtra(PARCEL_EXTRA);
            if (newUser != null) {
                String content = "ID: " + newUser.userId + ", Name: " + newUser.userName + ", Gender: " + (newUser.isMale ? "男" : "女");
                Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
