package com.example.dvorkapic.rxusage;

import com.example.dvorkapic.rxusage.Pojo.CurrentWeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dvorkapic on 31/03/16.
 */
public interface WeatherService {
    @GET("weather")
    Observable<CurrentWeatherData> getCurrentWeatherData(@Query("q") String city, @Query("appId") String apiKey);
}
