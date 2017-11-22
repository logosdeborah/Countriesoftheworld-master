package com.jeongeun.countriesoftheworld.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.jeongeun.countriesoftheworld.util.Converters;

import java.util.List;

/**
 * Created by JeongEun on 2017-11-18.
 * Because Room library doesn't support AutoValue, it is necessary to create another class for country entity.
 */

@Entity(tableName = "countries")
@TypeConverters({Converters.class})
public class CountryEntity {

    @NonNull
    @PrimaryKey
    private String name;
    private String capital;
    private String region;
    private Integer population;
    private String flag;
    private List<String> timezones;

    public CountryEntity(@NonNull String name, String capital, String region, Integer population, String flag, List<String> timezones) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.flag = flag;
        this.timezones = timezones;
    }

    @NonNull
    public String name() {
        return name;
    }

    public String capital() {
        return capital;
    }

    public String region() {
        return region;
    }

    public Integer population() {
        return population;
    }

    public String flag() {
        return flag;
    }

    public List<String> timezones() {
        return timezones;
    }
}
