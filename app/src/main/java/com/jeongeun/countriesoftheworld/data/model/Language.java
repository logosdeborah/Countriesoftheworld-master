package com.jeongeun.countriesoftheworld.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JeongEun on 2017-11-15.
 */

public class Language {

    @NonNull
    @SerializedName("nativeName")
    public String nativeName;

    @SerializedName("iso639_2")
    public String iso6392;

    @SerializedName("name")
    public String name;

    @SerializedName("iso639_1")
    public String iso6391;

    public Language(@NonNull String nativeName, String iso6392, String name, String iso6391) {
        this.nativeName = nativeName;
        this.iso6392 = iso6392;
        this.name = name;
        this.iso6391 = iso6391;
    }

    @NonNull
    public String nativeName() {
        return nativeName;
    }

    public String iso6392() {
        return iso6392;
    }

    public String name() {
        return name;
    }

    public String iso6391() {
        return iso6391;
    }

}
