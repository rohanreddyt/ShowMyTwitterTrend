package com.example.rohan.showmytwittertrend.networking;




import com.example.rohan.showmytwittertrend.model.Trend;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getTrend(final GetTrendCallback callback) {

        return networkService.getAllTrends(1)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Trend>>>() {
                    @Override
                    public Observable<? extends List<Trend>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<List<Trend>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(List<Trend> trends) {
                        callback.onSuccess(trends);

                    }
                });
    }

    public interface GetTrendCallback{
        void onSuccess(List<Trend> trends);

        void onError(NetworkError networkError);
    }
}
