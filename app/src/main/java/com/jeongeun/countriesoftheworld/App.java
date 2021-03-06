package com.jeongeun.countriesoftheworld;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by JeongEun on 2017-11-20.
 * It is only for logging.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
