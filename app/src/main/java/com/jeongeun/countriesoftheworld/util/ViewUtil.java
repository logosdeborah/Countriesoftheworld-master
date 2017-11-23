package com.jeongeun.countriesoftheworld.util;

import android.graphics.Color;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;

import com.jeongeun.countriesoftheworld.data.model.Language;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by JeongEun on 2017-11-16.
 */

public class ViewUtil {

    public static final String FLAG_BASE_URI = "https://raw.githubusercontent.com/hjnilsson/country-flags/master/png100px/";
    public static final String PNG_FILE = ".png";
    public static final String KOSOVO_ALOHA2CODE = "XK";
    public static final String KOSOVO_NAME= "kosovo";

    public static String getFormattedNumber(int number){
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }

    /**
     * Some field in the country information doesn't have value. So put dash instead.
     * @param value
     * @return
     */
    public static String replaceIfEmpty(String value) {
        return value.isEmpty() ? "-" : value;
    }

    /**
     * To get highlighted text with the result that matches search string.
     * @param name country name
     * @param highlighted A string that need highlight
     * @return highlighted text
     */
    public static Spannable getHighlightedText(String name, String highlighted) {

        Spannable spanString = Spannable.Factory.getInstance().newSpannable(name);
        name = name.toLowerCase();

        if (highlighted != null && !highlighted.isEmpty() && name.contains(highlighted)) {
            int startPos = name.indexOf(highlighted);
            int endPos = startPos + highlighted.length();

            spanString.setSpan(new ForegroundColorSpan(Color.RED), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spanString;
    }

    /**
     * Return language list with one string. The string only contains its name.
     * @param languages
     * @return
     */
    public static String getLanguageList(List<Language> languages) {
        StringBuilder builder = new StringBuilder();
        if (languages != null) {
            languages.forEach(language -> builder.append(language.name).append(" "));
        }
        return builder.toString();
    }

    public static String getFlagURI(String alpha2Code) {
        StringBuilder builder = new StringBuilder();
        builder.append(FLAG_BASE_URI)
                .append(getExceptionalCode(alpha2Code))
                .append(PNG_FILE);
        return builder.toString();
    }

    /**
     * Some countries might have different ISO country code than the site we need to download it's flag image.
     * (e.g., Kosovo doesn't have ISO country code. So just put it's name instead.
     * @param alpha2Code
     * @return
     */
    public static String getExceptionalCode(String alpha2Code) {
        /* To change Kosovo alph2code to kosovo name for downloading flag image.*/
        if (alpha2Code.equals(KOSOVO_ALOHA2CODE)) {
            return KOSOVO_NAME;
        }
        return alpha2Code.toLowerCase();
    }
}
