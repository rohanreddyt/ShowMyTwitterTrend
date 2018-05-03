package com.example.rohan.showmytwittertrend.networking;




import com.example.rohan.showmytwittertrend.model.Trend;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkService {


    String BASE_URL = "https://api.twitter.com/";
    String API_KEY = "789d25f2b8cfa2c3f5a045e1dec5a2a1";


    @GET("1.1/trends/place.json?")
    Observable<List<Trend>> getAllTrends(@Query("id") long id);
//                                        @Query("appid") String appid);

}
