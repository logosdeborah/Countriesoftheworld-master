package com.jeongeun.countriesoftheworld.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.jeongeun.countriesoftheworld.Injection;
import com.jeongeun.countriesoftheworld.presenter.base.Presenter;
import com.jeongeun.countriesoftheworld.presenter.base.PresenterFactory;
import com.jeongeun.countriesoftheworld.presenter.base.PresenterLoader;

/**
 * Created by JeongEun on 2017-11-16.
 * To survive during configuration change, Loader will be used.
 * Loader stores presenter and will provide when it is needed.
 */

public abstract class BaseActivity<P extends Presenter, V extends MvpView> extends AppCompatActivity{

    private P presenter;
    private static final int LOADER_ID = 99;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Loader<P> loader = getSupportLoaderManager().getLoader(LOADER_ID);
        if (loader == null) {
            initLoader();
        } else {
            presenter = ((PresenterLoader<P>)loader).getPresenter();
        }

    }

    /**
     * Init the loader with unique ID. And callbacks will be in charge of communicating with activity.
     */
    private void initLoader() {
        getSupportLoaderManager().initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public Loader<P> onCreateLoader(int id, Bundle args) {
                return new PresenterLoader<>(BaseActivity.this, getPresenterFactory());
            }

            @Override
            public void onLoadFinished(Loader<P> loader, P presenter) {
                BaseActivity.this.presenter = presenter;
            }

            @Override
            public void onLoaderReset(Loader<P> loader) {
                BaseActivity.this.presenter = null;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView((V)this);
    }

    @Override
    protected void onStop() {
        presenter.detachView();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    protected abstract PresenterFactory<P> getPresenterFactory();

    protected P getPresenter() {
        return presenter;
    }

}
