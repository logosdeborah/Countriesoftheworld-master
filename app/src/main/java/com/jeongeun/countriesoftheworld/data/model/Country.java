package com.jeongeun.countriesoftheworld.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by JeongEun on 2017-11-13.
 */

@AutoValue
public abstract class Country implements Parcelable {

    public abstract String name();
    public abstract String capital();
    public abstract String region();
    public abstract Integer population();
    public abstract String flag();
    public abstract List<Language> languages();

    public static TypeAdapter<Country> typeAdapter (Gson gson) {
        return new AutoValue_Country.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Country.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setName(String name);
        public abstract Builder setCapital(String capital);
        public abstract Builder setRegion(String region);
        public abstract Builder setPopulation(Integer population);
        public abstract Builder setFlag(String flag);
        public abstract Builder setLanguages(List<Language> languages);
        public abstract Country build();
    }
}
