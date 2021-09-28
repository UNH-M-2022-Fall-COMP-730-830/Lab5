package com.example.weather.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.weather.R;
import com.example.weather.data.Forecast;
import com.example.weather.details.DetailsActivity;
import com.example.weather.listView.ListView;
import com.example.weather.network.NetworkRequestCallback;
import com.example.weather.network.WeatherAPI;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkRequestCallback<List<Forecast>>, OnForecastCLickListener {

    private Toolbar toolbar;
    private ListView listView;
    private LinearLayout errorView;
    private MaterialButton retryButton;
    private ProgressBar progress;
    private ForecastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initListView();
        initProgress();
        initErrorView();

        refreshForecast();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Manchester, NH");
    }

    private void initListView() {
        listView = findViewById(R.id.listView);
        adapter = new ForecastAdapter();
        adapter.setForecastClickListener(this);
        listView.setAdapter(adapter);
    }

    private void initProgress() {
        progress = findViewById(R.id.progress);
    }

    private void initErrorView() {
        errorView = findViewById(R.id.errorView);
        retryButton = findViewById(R.id.retryButton);
        retryButton.setOnClickListener(v -> refreshForecast());
    }

    private void showProgress() {
        progress.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
    }

    private void showError(Exception error) {
        progress.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    private void showData(List<Forecast> data) {
        adapter.setData(data);
        listView.notifyDataChanged();

        progress.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    private void refreshForecast() {
        showProgress();
        WeatherAPI.requestForecast(this);
    }

    @Override
    public void onResult(List<Forecast> data) {
        showData(data);
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
        showError(e);
    }

    @Override
    public void onForecastClick(Forecast item) {
        Intent activity = new Intent(this, DetailsActivity.class);
        activity.putExtra(DetailsActivity.FORECAST_EXTRA, item);
        startActivity(activity);
    }
}