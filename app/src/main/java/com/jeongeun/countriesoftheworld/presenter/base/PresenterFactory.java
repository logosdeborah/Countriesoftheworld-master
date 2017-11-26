package com.jeongeun.countriesoftheworld.presenter.base;

import android.content.Context;

/**
 * Created by JeongEun on 2017-11-16.
 */

public interface PresenterFactory<P extends Presenter> {
    P create(Context context);
}
