package com.jeongeun.countriesoftheworld.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jeongeun.countriesoftheworld.data.model.Country;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by JeongEun on 2017-11-18.
 * This is the main class for database interactions. Various queries included.
 */

@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries")
    Single<List<Country>> getAllCountries();

    @Query("SELECT * FROM countries WHERE name LIKE '%' || :subName || '%'")
    Single<List<Country>> searchForName(String subName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Country> countries);

}
