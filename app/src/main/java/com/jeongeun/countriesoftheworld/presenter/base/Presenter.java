package com.jeongeun.countriesoftheworld.presenter.base;

import com.jeongeun.countriesoftheworld.ui.base.MvpView;

/**
 * Created by JeongEun on 2017-11-17.
 */

public interface Presenter<T extends MvpView> {

    void attachView(T view);

    void detachView();

    void onDestroyed();
}
