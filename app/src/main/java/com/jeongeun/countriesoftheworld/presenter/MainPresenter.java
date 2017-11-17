package com.jeongeun.countriesoftheworld.presenter;

import com.jeongeun.countriesoftheworld.data.CountriesService;
import com.jeongeun.countriesoftheworld.data.model.Country;
import com.jeongeun.countriesoftheworld.presenter.base.BasePresenter;
import com.jeongeun.countriesoftheworld.ui.MainMvpView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainMvpView> {
    String CUSTOM_FIELDS = "name;capital;region;population;flag;languages";

    private CountriesService mCountriesService;
    private Disposable mDisposable;

    public MainPresenter() {
        mCountriesService = CountriesService.Creator.CreateCountriesService();
    }

    @Override
    public void attachView(MainMvpView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public void loadCountries() {

        mCountriesService.getCountries(CUSTOM_FIELDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Country>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(List<Country> countries) {
                        getMvpView().setCountries(countries);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showLoadingError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void loadFlags() {

    }



}
