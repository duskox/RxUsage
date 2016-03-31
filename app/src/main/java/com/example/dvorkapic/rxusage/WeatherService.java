package com.example.dvorkapic.rxusage;

import com.example.dvorkapic.rxusage.Pojo.CurrentWeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dvorkapic on 31/03/16.
 */
public interface WeatherService {
    @GET("weather")
    Call<CurrentWeatherData> getCurrentWeatherData(@Query("q") String city);
}
