package com.jeongeun.countriesoftheworld.ui;

import com.jeongeun.countriesoftheworld.data.local.CountryEntity;
import com.jeongeun.countriesoftheworld.ui.base.MvpView;

import java.util.List;

/**
 * Created by JeongEun on 2017-11-14.
 */

public interface MainMvpView extends MvpView {

    void setCountries(List<CountryEntity> countries);
    void showLoadingError(Throwable e);
}
