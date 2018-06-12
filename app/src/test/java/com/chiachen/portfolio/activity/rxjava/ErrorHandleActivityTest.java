package com.chiachen.portfolio.activity.rxjava;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by jianjiacheng on 2018/6/12.
 */
public class ErrorHandleActivityTest {
    ErrorHandleActivity mErrorHandleActivity;
    @Before
    public void setup(){
        mErrorHandleActivity = new ErrorHandleActivity();
    }

    @Test
    public void TestObject() throws Exception {
        mErrorHandleActivity.onClickExceptionResumeNext(null);
    }

    @Test
    public void TestObject1() {
        mErrorHandleActivity.onClickDoOnError(null);
    }

    @Test
    public void TestObject2() {
        mErrorHandleActivity.onClickErrorReturnItem(null);
    }

    @Test
    public void TestObject3() {
        mErrorHandleActivity.onClickErrorReturn(null);
    }

    @Test
    public void TestObject4() {
        mErrorHandleActivity.onClickRetry(null);
    }
}