package com.chiachen.portfolio.presenter;

import com.chiachen.portfolio.base._BasePresenter;
import com.chiachen.portfolio.view.CountryWallView;

/**
 * Created by jianjiacheng on 06/05/2018.
 */

public class CountryWallPresenter extends _BasePresenter<CountryWallView> {

    public CountryWallPresenter(CountryWallView countryWallView) {
        attachView(countryWallView);
    }
}
