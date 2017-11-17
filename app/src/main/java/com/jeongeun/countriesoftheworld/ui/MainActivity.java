package com.jeongeun.countriesoftheworld.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.jeongeun.countriesoftheworld.R;
import com.jeongeun.countriesoftheworld.data.model.Country;
import com.jeongeun.countriesoftheworld.presenter.MainPresenter;
import com.jeongeun.countriesoftheworld.presenter.MainPresenterFactory;
import com.jeongeun.countriesoftheworld.presenter.base.PresenterFactory;
import com.jeongeun.countriesoftheworld.ui.base.BaseActivity;
import com.jeongeun.countriesoftheworld.ui.base.ItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainActivity extends BaseActivity<MainPresenter, MainMvpView>
                                implements MainMvpView, ItemClickListener {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.toolbar) Toolbar mToolBar;

    private CountriesAdapter mCountriesAdapter;
    private CountriesBottomSheetDialog mBottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);

        mCountriesAdapter = new CountriesAdapter(this);
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mRecyclerView.setAdapter(mCountriesAdapter);
        mRecyclerView.addItemDecoration(decoration);
        mBottomSheetDialog = new CountriesBottomSheetDialog(this, R.layout.bottom_sheet);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().loadCountries();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void setCountries(List<Country> countries) {
        progressBar.setVisibility(View.GONE);
        mCountriesAdapter.setCountries(countries);
        mCountriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingError(Throwable e) {
        Log.d("Error", e.getMessage());
    }

    @Override
    public void onItemClicked(View v, int position) {
        mBottomSheetDialog.setCountryInformation(mCountriesAdapter.getCountry(position));
        mBottomSheetDialog.show();
    }

    @Override
    protected PresenterFactory getPresenterFactory() {
        return new MainPresenterFactory();
    }

}
