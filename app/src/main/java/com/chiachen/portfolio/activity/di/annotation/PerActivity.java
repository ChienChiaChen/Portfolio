package com.chiachen.portfolio.activity.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by jianjiacheng on 21/04/2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}