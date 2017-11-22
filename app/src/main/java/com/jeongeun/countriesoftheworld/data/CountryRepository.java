package com.jeongeun.countriesoftheworld.data;

import com.jeongeun.countriesoftheworld.data.local.CountryDao;
import com.jeongeun.countriesoftheworld.data.local.CountryEntity;
import com.jeongeun.countriesoftheworld.data.remote.CountriesService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by JeongEun on 2017-11-19.
 */

public class CountryRepository implements BaseRepository {

    String CUSTOM_FIELDS = "name;capital;region;population;flag;timezones";
    private CountriesService countriesService;
    private CountryDao countryDao;

    public CountryRepository(CountriesService countriesService, CountryDao countryDao) {
        this.countriesService = countriesService;
        this.countryDao = countryDao;
    }

    public Observable<List<CountryEntity>> getCountries() {
        return Observable.concatArray(
                getCountriesFromDB(),
                getCountriesFromApi());
    }

    public Observable<List<CountryEntity>> getCountriesFromApi() {
        return countriesService.getCountries(CUSTOM_FIELDS)
                .doOnNext(it -> {
                    Timber.d("Dispatching %d countries from API...", it.size());
                    storeCountriesInDb(it);
                });
    }

    public Observable<List<CountryEntity>> getCountriesFromDB() {
        return countryDao.getAllCountries()
                .filter(it -> !it.isEmpty())
                .toObservable()
                .doOnNext(it -> {
                    Timber.d("Dispatching %d countries from DB...", it.size());
                });
    }

    public void storeCountriesInDb(List<CountryEntity> countries) {
        Observable.fromCallable(() -> countryDao.insertAll(countries))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(it -> {
                   Timber.d("Inserted %d countries from API in DB...", it.size());
                });
    }

    public Observable<List<CountryEntity>> searchCountriesByName(String name) {
        return countryDao.searchForName(name)
                .toObservable()
                .doOnNext(it -> {
                    Timber.d("Searched %d countries from DB...", it.size());
                });
    }
}
