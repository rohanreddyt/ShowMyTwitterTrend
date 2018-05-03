package com.example.rohan.showmytwittertrend.networking;


import android.util.Base64;
import android.util.Log;

import com.example.rohan.showmytwittertrend.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


@Module
public class NetworkModule {

    private String token = null;
    @Provides
    @Singleton
    Retrofit provideCall() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {

                        Request request = chain.request();
                        if (token == null) {
                            ResponseBody body = chain.proceed(getToken()).body();

                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                token = "Bearer " + jsonObject.optString("access_token");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d(NetworkModule.class.getName(), "Error fetching token");
                            }
                        }
                        request = request.newBuilder()
                                .addHeader("Authorization", token)
                                .build();

                        return chain.proceed(request);
                    }
                })

                .build();


        return new Retrofit.Builder()
                .baseUrl(NetworkService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkService providesNetworkService(
             Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }
    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public Service providesService(
            NetworkService networkService) {
        return new Service(networkService);
    }
    private Request getToken() {
        String bearerToken = BuildConfig.CONSUMER_KEY +
                ":" + BuildConfig.CONSUMER_SECRET;

        String base64BearerToken = "Basic " + Base64.encodeToString(bearerToken.getBytes(), Base64.NO_WRAP);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8"), "grant_type=client_credentials");

        return new Request.Builder()
                .url(BuildConfig.AUTH_END_POINT)
                .post(requestBody)
                .header("Authorization", base64BearerToken)
                .header("Content-Encoding", "gzip")
                .header("User-Agent", "My Twitter App v1.0.23")
                .header("Content-type", "application/x-www-form-urlencoded;charset=UTF-8")
                .build();
    }
}
