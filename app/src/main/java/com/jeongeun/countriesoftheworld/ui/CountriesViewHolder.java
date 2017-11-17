package com.jeongeun.countriesoftheworld.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jeongeun.countriesoftheworld.R;
import com.jeongeun.countriesoftheworld.ui.base.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JeongEun on 2017-11-13.
 */

public class CountriesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.nameView) TextView nameTextView;
    @BindView(R.id.capitalView) TextView capitalTextView;

    public CountriesViewHolder(View view, ItemClickListener listener) {
        super(view);
        ButterKnife.bind(this, view);

        view.setOnClickListener(v -> {
            listener.onItemClicked(v, getAdapterPosition());
        });
    }
}
