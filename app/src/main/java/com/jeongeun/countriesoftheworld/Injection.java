package com.jeongeun.countriesoftheworld;

import android.content.Context;

import com.jeongeun.countriesoftheworld.data.CountryRepository;
import com.jeongeun.countriesoftheworld.data.local.CountryDatabase;
import com.jeongeun.countriesoftheworld.data.remote.CountriesService;

/**
 * Created by JeongEun on 2017-11-20.
 */

public class Injection {

    /**
     * To provide repository class instance.
     * To create its instance, application context is required.
     * @param context Application context
     * @return CountryRepository instance
     */
    public static CountryRepository provideCountryRepository(Context context) {
        CountryDatabase database = CountryDatabase.getInstance(context);
        return new CountryRepository(CountriesService.Creator.CreateCountriesService(),
                database.getCountryDao());
    }
}
