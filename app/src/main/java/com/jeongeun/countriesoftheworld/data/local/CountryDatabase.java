package com.jeongeun.countriesoftheworld.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.jeongeun.countriesoftheworld.data.model.Country;

/**
 * Created by JeongEun on 2017-11-18.
 */

@Database(entities = { Country.class }, version = 1)
public abstract class CountryDatabase extends RoomDatabase {
    private static final String DB_NAME = "countryDatabase.db";
    private static volatile CountryDatabase sInstance;

    public static CountryDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (CountryDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            CountryDatabase.class, DB_NAME)
                            .build();
                }
            }
        }
        return sInstance;
    }

    public abstract CountryDao getCountryDao();
}
