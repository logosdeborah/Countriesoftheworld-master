package com.jeongeun.countriesoftheworld.util;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by JeongEun on 2017-11-21.
 */

public class Converters {

    @TypeConverter
    public static List<String> fromString(String value) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromLists(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
