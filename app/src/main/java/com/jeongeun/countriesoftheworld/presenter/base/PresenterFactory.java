package com.jeongeun.countriesoftheworld.presenter.base;

/**
 * Created by JeongEun on 2017-11-16.
 */

public interface PresenterFactory<P extends Presenter> {
    P create();
}
