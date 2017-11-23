package com.jeongeun.countriesoftheworld.data.remote;

import com.jeongeun.countriesoftheworld.data.model.Country;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by JeongEun on 2017-11-13.
 */

public interface CountriesService {

    String END_POINT = "https://restcountries.eu/rest/v2/";
    String FIELDS = "fields";

    @GET("all")
    Observable<List<Country>> getCountries(@Query(FIELDS) String fields);

    class Creator {

        public static CountriesService CreateCountriesService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(CountriesService.class);
        }
    }
}
