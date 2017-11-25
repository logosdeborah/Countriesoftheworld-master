package com.jeongeun.countriesoftheworld.presenter;

import com.jeongeun.countriesoftheworld.data.CountryRepository;
import com.jeongeun.countriesoftheworld.data.model.Country;
import com.jeongeun.countriesoftheworld.presenter.base.BasePresenter;
import com.jeongeun.countriesoftheworld.ui.MainMvpView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainMvpView> {

    private CountryRepository mRepository;
    private Disposable mDisposable;

    public MainPresenter() {
    }

    public void setRepository(CountryRepository countryRepository) {
        mRepository = countryRepository;
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

        checkViewAttached();
        mRepository.getCountries()
                .subscribeOn(Schedulers.io())
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

    public void searchCountries(String name) {
        checkViewAttached();
        mRepository.searchCountriesByName(name)
                .subscribeOn(Schedulers.io())
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
}
