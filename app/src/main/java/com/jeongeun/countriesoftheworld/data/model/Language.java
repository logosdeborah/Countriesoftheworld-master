package com.jeongeun.countriesoftheworld.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by JeongEun on 2017-11-15.
 */

@AutoValue
public abstract class Language implements Parcelable {

    @Nullable
    @SerializedName("nativeName")
    public abstract String nativeName();

    @Nullable
    @SerializedName("iso639_2")
    public abstract String iso6392();

    @Nullable
    @SerializedName("name")
    public abstract String name();

    @Nullable
    @SerializedName("iso639_1")
    public abstract String iso6391();

    public static TypeAdapter<Language> typeAdapter(Gson gson) {
        return new $AutoValue_Language.GsonTypeAdapter(gson);
    }
}
