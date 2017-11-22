package com.jeongeun.countriesoftheworld.ui;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jeongeun.countriesoftheworld.R;
import com.jeongeun.countriesoftheworld.data.local.CountryEntity;
import com.jeongeun.countriesoftheworld.util.SvgSoftwareLayerSetter;
import com.jeongeun.countriesoftheworld.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JeongEun on 2017-11-14.
 */

public class CountriesBottomSheetDialog extends BottomSheetDialog {

    @BindView(R.id.flagImageView) ImageView flag;
    @BindView(R.id.nameTextView) TextView name;
    @BindView(R.id.capitalTextView) TextView capital;
    @BindView(R.id.regionTextView) TextView region;
    @BindView(R.id.timezonesTextView) TextView timezone;
    @BindView(R.id.populationTextView) TextView population;

    public CountriesBottomSheetDialog(@NonNull Context context, int layoutId) {
        super(context, R.style.ReaderBottomSheelDialog);
        View view = getLayoutInflater().inflate(layoutId, null);
        setContentView(view);
        ButterKnife.bind(this, view);
    }

    public void setCountryInformation(CountryEntity country) {
        name.setText(country.name());
        name.setSelected(true);
        capital.setText(ViewUtil.replaceIfEmpty(country.capital()));
        region.setText(ViewUtil.replaceIfEmpty(country.region()));
        timezone.setText(country.timezones().toString());
        population.setText(ViewUtil.getFormattedNumber(country.population()));

        Glide.with(flag.getContext())
                .as(PictureDrawable.class)
                .listener(new SvgSoftwareLayerSetter())
                .load(Uri.parse(country.flag())).into(flag);
    }
}
