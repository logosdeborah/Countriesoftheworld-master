package com.jeongeun.countriesoftheworld.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
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
import timber.log.Timber;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainActivity extends BaseActivity<MainPresenter, MainMvpView>
                                implements MainMvpView, ItemClickListener {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.toolbar) Toolbar mToolBar;
    @BindView(R.id.app_bar) AppBarLayout mAppBarLayout;

    private CountriesAdapter mCountriesAdapter;
    private MenuItem mSearchMenu;
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
    protected void onDestroy() {
        super.onDestroy();
        mBottomSheetDialog.dismiss();
        mBottomSheetDialog = null;
    }

    @Override
    public void setCountries(List<Country> countries) {
        progressBar.setVisibility(View.GONE);
        mSearchMenu.setVisible(true);
        mCountriesAdapter.setCountries(countries);
        mCountriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void highlightSearchString(String searchString) {
        mCountriesAdapter.setSearchString(searchString);
    }

    @Override
    public void showLoadingError(Throwable e) {
        Timber.d("Error" + e.getMessage());
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mSearchMenu = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) mSearchMenu.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getPresenter().searchCountries(s);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search) {
            /* When a user clicks search button, tool bar will be collapsed. */
            mAppBarLayout.setExpanded(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
