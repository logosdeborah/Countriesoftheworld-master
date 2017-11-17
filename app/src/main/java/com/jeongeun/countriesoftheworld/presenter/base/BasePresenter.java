package com.jeongeun.countriesoftheworld.presenter.base;

import com.jeongeun.countriesoftheworld.ui.base.MvpView;

/**
 * Created by JeongEun on 2017-11-14.
 */

public abstract class BasePresenter<T extends MvpView> implements Presenter<T> {
    private T mMvpView;

    public T getMvpView() {
        return mMvpView;
    }

    public void attachView(T view) {
        mMvpView = view;
    }

    public void detachView() {
        mMvpView = null;
    }

    public void onDestroyed() {}

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("MainMvpView is not attached. " +
                    "Please attach it before requesting data from the presenter");
        }
    }
}
