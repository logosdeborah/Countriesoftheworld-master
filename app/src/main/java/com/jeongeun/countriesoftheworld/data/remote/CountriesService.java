package com.jeongeun.countriesoftheworld.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jeongeun.countriesoftheworld.data.local.CountryEntity;
import com.jeongeun.countriesoftheworld.util.MyGsonTypeAdapterFactory;

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
    Observable<List<CountryEntity>> getCountries(@Query(FIELDS) String fields);

    class Creator {

        public static CountriesService CreateCountriesService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(END_POINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(CountriesService.class);
        }
    }
}
