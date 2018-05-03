package com.example.rohan.showmytwittertrend;


import com.example.rohan.showmytwittertrend.model.Trend;
import com.example.rohan.showmytwittertrend.networking.NetworkError;
import com.example.rohan.showmytwittertrend.networking.Service;


import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter {
    private final Service service;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(Service service, HomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getTrend() {
        view.showWait();

        Subscription subscription = service.getTrend(new Service.GetTrendCallback() {
            @Override
            public void onSuccess(List<Trend> trends) {
                view.removeWait();
                view.getTrendsSuccess(trends);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }
}
