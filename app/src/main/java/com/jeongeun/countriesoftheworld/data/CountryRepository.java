package com.jeongeun.countriesoftheworld.data;

import com.jeongeun.countriesoftheworld.data.model.Country;
import com.jeongeun.countriesoftheworld.data.local.CountryDao;
import com.jeongeun.countriesoftheworld.data.remote.CountriesService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by JeongEun on 2017-11-19.
 * This is connection class between Presenter and DAO.
 */

public class CountryRepository implements BaseRepository {

    private final String CUSTOM_FIELDS = "name;alpha2Code;capital;region;population;flag;timezones;languages";
    private CountriesService mCountriesService;
    private CountryDao mCountryDao;

    public CountryRepository(CountriesService countriesService, CountryDao countryDao) {
        mCountriesService = countriesService;
        mCountryDao = countryDao;
    }

    /**
     * Check local DB first and if there's not db exist, then download from API.
     * @return
     */
    @Override
    public Observable<List<Country>> getCountries() {
        return Observable.concatArray(
                getCountriesFromDB(),
                getCountriesFromApi());
    }

    @Override
    public Observable<List<Country>> getCountriesFromApi() {
        return mCountriesService.getCountries(CUSTOM_FIELDS)
                .doOnNext(it -> {
                    Timber.d("Dispatching %d countries from API...", it.size());
                    storeCountriesInDb(it);
                });
    }

    @Override
    public Observable<List<Country>> getCountriesFromDB() {
        return mCountryDao.getAllCountries()
                .filter(it -> !it.isEmpty())
                .toObservable()
                .doOnNext(it -> {
                    Timber.d("Dispatching %d countries from DB...", it.size());
                });
    }

    @Override
    public void storeCountriesInDb(List<Country> countries) {
        Observable.fromCallable(() -> mCountryDao.insertAll(countries))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(it -> {
                   Timber.d("Inserted %d countries from API in DB...", it.size());
                });
    }

    @Override
    public Observable<List<Country>> searchCountriesByName(String name) {
        return mCountryDao.searchForName(name)
                .toObservable()
                .doOnNext(it -> {
                    Timber.d("Searched %d countries from DB...", it.size());
                });
    }
}
