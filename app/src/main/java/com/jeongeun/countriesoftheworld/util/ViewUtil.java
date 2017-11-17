package com.jeongeun.countriesoftheworld.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by JeongEun on 2017-11-16.
 */

public class ViewUtil {

    public static String getFormattedNumber(int number){
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }
}
