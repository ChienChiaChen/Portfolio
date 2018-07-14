package com.chiachen.portfolio.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.chiachen.portfolio.utils.Constant;

/**
 * Created by jianjiacheng on 2018/7/14.
 */

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super(Constant.SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int taskFlag = intent.getIntExtra(Constant.FLAG_TASK, 1);
        ResultReceiver receiver = intent.getParcelableExtra(Constant.FLAG_RECEIVER);
        int rand = (int) (Math.random() * 3 + 1);
        try {
            Thread.sleep(rand * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (taskFlag == 1) {
            Bundle bundle = new Bundle();
            bundle.putString(Constant.RETURN_TASK, Constant.TASK1_MSG);
            receiver.send(Constant.RESULT_CODE, bundle);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(Constant.RETURN_TASK, Constant.TASK2_MSG);
            receiver.send(Constant.RESULT_CODE, bundle);
        }
    }
}
