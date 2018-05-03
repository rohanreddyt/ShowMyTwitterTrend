package com.example.rohan.showmytwittertrend;


import com.example.rohan.showmytwittertrend.model.Trend;

import java.util.List;

public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getTrendsSuccess(List<Trend>  trends);

}
