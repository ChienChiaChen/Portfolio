package com.chiachen.portfolio.global;

import com.chiachen.portfolio.module.ApiModule;
import com.chiachen.portfolio.module.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jianjiacheng on 15/03/2018.
 */

@Singleton
@Component(modules =  { MainModule.class, ApiModule.class })
public interface BaseComponent extends BaseGraph {
    final class Initializer {
        private Initializer() {
        } // No instances.


        public static BaseComponent init(BaseApplication app) {
            return  DaggerBaseComponent
                    .builder()
                    .mainModule(new MainModule(app))
                    .build();
        }
    }
}
