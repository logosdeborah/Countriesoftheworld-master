package com.jeongeun.countriesoftheworld.presenter.base;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by JeongEun on 2017-11-16.
 * A base class for PresenterLoader.
 */

public class PresenterLoader<P extends Presenter> extends Loader<P> {

    private PresenterFactory<P> mFactory;
    private P mPresenter;

    public PresenterLoader(Context context, PresenterFactory factory) {
        super(context);
        mFactory = factory;
    }

    /**
     * It is called by the Framework when onStart() is reached.
     * Here it is checked whether we hold a Presenter instance or the Presenter needs to be created.
     */
    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mPresenter != null) {
            deliverResult(mPresenter);
            return;
        }

        forceLoad();
    }

    /**
     * It is called when forceLoad() is invoked.
     * Presenter factory will create it's instance and deliver the result.
     */
    @Override
    protected void onForceLoad() {
        super.onForceLoad();

        mPresenter = mFactory.create(getContext());
        deliverResult(mPresenter);
    }

    /**
     * This is called whenever the loader is destroyed and need reset.
     */
    @Override
    protected void onReset() {
        super.onReset();
        mPresenter.onDestroyed();
        mPresenter = null;
    }

    public P getPresenter() {
        return mPresenter;
    }
}
