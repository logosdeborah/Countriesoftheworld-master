package com.jeongeun.countriesoftheworld.presenter;

import com.jeongeun.countriesoftheworld.presenter.base.Presenter;
import com.jeongeun.countriesoftheworld.presenter.base.PresenterFactory;

/**
 * Created by JeongEun on 2017-11-17.
 */

public class MainPresenterFactory implements PresenterFactory {
    @Override
    public Presenter create() {
        return new MainPresenter();
    }
}
