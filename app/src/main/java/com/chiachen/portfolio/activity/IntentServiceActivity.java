package com.chiachen.portfolio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.service.MyIntentService;
import com.chiachen.portfolio.utils.Constant;

public class IntentServiceActivity extends AppCompatActivity {

    private Button task1;
    private Button task2;
    private TextView result;
    private ResultReceiver receiver;
    private StringBuffer stringBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        initData();
        initView();
    }

    private void initData() {
        stringBuffer = new StringBuffer();
        receiver = new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == Constant.RESULT_CODE) {
                    String returnStr = resultData.getString(Constant.RETURN_TASK);
                    if (returnStr != null) {
                        stringBuffer.append(returnStr + "\n");
                        result.setText(stringBuffer);
                    }
                }
            }
        };
    }

    private void initView() {
        task1 = (Button) findViewById(R.id.send_task1);
        task2 = (Button) findViewById(R.id.send_task2);
        result = (TextView) findViewById(R.id.result);
        task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentServiceActivity.this, MyIntentService.class);
                intent.putExtra(Constant.FLAG_TASK, Constant.TASK1);
                intent.putExtra(Constant.FLAG_RECEIVER, receiver);
                startService(intent);
            }
        });

        task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentServiceActivity.this, MyIntentService.class);
                intent.putExtra(Constant.FLAG_TASK, Constant.TASK2);
                intent.putExtra(Constant.FLAG_RECEIVER, receiver);
                startService(intent);
            }
        });
    }
}