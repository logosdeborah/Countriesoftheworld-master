package com.jeongeun.countriesoftheworld.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by JeongEun on 2017-11-18.
 */

@Database(entities = { CountryEntity.class }, version = 1)
public abstract class CountryDatabase extends RoomDatabase {
    private static final String DB_NAME = "countryDatabase.db";
    private static volatile CountryDatabase INSTANCE;

    public static CountryDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CountryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CountryDatabase.class, DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CountryDao getCountryDao();
}
