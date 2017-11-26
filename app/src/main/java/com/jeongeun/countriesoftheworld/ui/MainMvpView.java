package com.jeongeun.countriesoftheworld.ui;

import com.jeongeun.countriesoftheworld.data.model.Country;
import com.jeongeun.countriesoftheworld.ui.base.MvpView;

import java.util.List;

/**
 * Created by JeongEun on 2017-11-14.
 */

public interface MainMvpView extends MvpView {

    void setCountries(List<Country> countries);
    void showLoadingError(Throwable e);
    void highlightSearchString(String searchString);
}
