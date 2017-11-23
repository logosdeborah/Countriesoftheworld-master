package com.jeongeun.countriesoftheworld.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.jeongeun.countriesoftheworld.util.Converters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JeongEun on 2017-11-18.
 * Because Room library doesn't work well with AutoValue, it is necessary to create another class for country entity.
 */

@Entity(tableName = "countries")
@TypeConverters({Converters.class})
public class Country {

    @NonNull
    @PrimaryKey
    private String name;
    private String alpha2Code;
    private String capital;
    private String region;
    private Integer population;
    private String flag;
    private List<String> timezones;
    private List<Language> languages = new ArrayList<>();

    public Country(@NonNull String name, String alpha2Code, String capital, String region,
                   Integer population, String flag, List<String> timezones, List<Language> languages) {
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.flag = flag;
        this.timezones = timezones;
        this.languages = languages;
    }

    @NonNull
    public String name() {
        return name;
    }

    public String alpha2Code() {
        return alpha2Code;
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

    public List<Language> languages() {
        return languages;
    }
}
