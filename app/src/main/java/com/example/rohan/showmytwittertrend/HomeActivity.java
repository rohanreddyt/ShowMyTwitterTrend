package com.example.rohan.showmytwittertrend;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import android.widget.ProgressBar;


import com.example.rohan.showmytwittertrend.model.Trend;
import com.example.rohan.showmytwittertrend.networking.Service;


import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends BaseApp implements HomeView {

    @Inject
    public Service service;
    ProgressBar progressBar;
    HomePresenter presenter;
    public static final String PREFS_NAME = "MyApp_Settings";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    public static final String DEFAULT = "Inglewood";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = settings.edit();

        presenter = new HomePresenter(service, this);
        presenter.getTrend();
    }




    @Override
    public void showWait() {
//        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
//        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getTrendsSuccess(List<Trend> trends) {

        //get the response here

        if(trends!=null) {
            Log.e("Rohan", "size is :" + trends.size());
        }
        else{
            Log.e("Rohan","No trnds");
        }

    }
}
