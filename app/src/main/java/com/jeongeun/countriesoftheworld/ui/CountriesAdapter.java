package com.jeongeun.countriesoftheworld.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeongeun.countriesoftheworld.R;
import com.jeongeun.countriesoftheworld.data.model.Country;
import com.jeongeun.countriesoftheworld.ui.base.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JeongEun on 2017-11-13.
 */

public class CountriesAdapter extends RecyclerView.Adapter<CountriesViewHolder> {

    private List<Country> mCountries;
    private ItemClickListener mListener;

    public CountriesAdapter(ItemClickListener listener) {
        mCountries = new ArrayList<>();
        mListener = listener;
    }

    public void setCountries(List<Country> list) {
        mCountries = list;
    }

    @Override
    public CountriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new CountriesViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(CountriesViewHolder holder, int position) {
        Country country = mCountries.get(position);
        holder.nameTextView.setText(country.name());
        holder.capitalTextView.setText(country.capital());
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public Country getCountry(int position) {
        return mCountries.get(position);
    }

}
