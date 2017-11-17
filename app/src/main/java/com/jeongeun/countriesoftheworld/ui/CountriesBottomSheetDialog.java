package com.jeongeun.countriesoftheworld.ui;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jeongeun.countriesoftheworld.R;
import com.jeongeun.countriesoftheworld.data.model.Country;
import com.jeongeun.countriesoftheworld.util.SvgSoftwareLayerSetter;
import com.jeongeun.countriesoftheworld.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by JeongEun on 2017-11-14.
 */

public class CountriesBottomSheetDialog extends BottomSheetDialog {

    @BindView(R.id.flagImageView) ImageView flag;
    @BindView(R.id.nameTextView) TextView name;
    @BindView(R.id.capitalTextView) TextView capital;
    @BindView(R.id.regionTextView) TextView region;
    @BindView(R.id.langTextView) TextView language;
    @BindView(R.id.populationTextView) TextView population;

    public CountriesBottomSheetDialog(@NonNull Context context, int layoutId) {
        super(context, R.style.ReaderBottomSheelDialog);
        View view = getLayoutInflater().inflate(layoutId, null);
        setContentView(view);
        ButterKnife.bind(this, view);
    }

    public void setCountryInformation(Country country) {
        name.setText(country.name());
        name.setSelected(true);
        capital.setText(country.capital());
        region.setText(country.region());
        language.setText(country.languages().get(0).name());
        population.setText(ViewUtil.getFormattedNumber(country.population()));

        Glide.with(flag.getContext())
                .as(PictureDrawable.class)
                .listener(new SvgSoftwareLayerSetter())
                .load(Uri.parse(country.flag())).into(flag);
    }
}
