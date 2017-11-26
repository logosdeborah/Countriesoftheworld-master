package com.jeongeun.countriesoftheworld.presenter;

import android.content.Context;

import com.jeongeun.countriesoftheworld.Injection;
import com.jeongeun.countriesoftheworld.presenter.base.Presenter;
import com.jeongeun.countriesoftheworld.presenter.base.PresenterFactory;

/**
 * Created by JeongEun on 2017-11-17.
 */

public class MainPresenterFactory implements PresenterFactory {

    @Override
    public Presenter create(Context context) {
        return new MainPresenter(Injection.provideCountryRepository(context.getApplicationContext()));
    }
}
