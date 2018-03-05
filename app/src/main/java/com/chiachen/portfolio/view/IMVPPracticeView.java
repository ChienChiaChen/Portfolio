package com.chiachen.portfolio.view;

import com.chiachen.portfolio.models.Counter;

import java.util.List;

/**
 * Created by jianjiacheng on 04/03/2018.
 */

public interface IMVPPracticeView extends IBaseView {

    void showEmpty();

    void showCounters(List<Counter> model);
}
