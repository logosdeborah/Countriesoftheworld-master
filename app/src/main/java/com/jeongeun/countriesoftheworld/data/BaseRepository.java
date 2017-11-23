package com.jeongeun.countriesoftheworld.data;

import com.jeongeun.countriesoftheworld.data.model.Country;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by JeongEun on 2017-11-20.
 */

public interface BaseRepository {

    Observable<List<Country>> getCountries();
    Observable<List<Country>> getCountriesFromApi();
    Observable<List<Country>> getCountriesFromDB();
    Observable<List<Country>> searchCountriesByName(String name);
    void storeCountriesInDb(List<Country> countries);

}
