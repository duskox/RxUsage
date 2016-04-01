package com.example.dvorkapic.rxusage;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dvorkapic.rxusage.Pojo.CurrentWeatherData;
import com.google.gson.GsonBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.internal.util.RxThreadFactory;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.et_city)
    EditText etCity;

    @Bind(R.id.tv_temperature)
    TextView tvTemperature;

    @Bind(R.id.tv_humidity)
    TextView tvHumidity;

    @Bind(R.id.tv_pressure)
    TextView tvPRessure;

    @Bind(R.id.tv_sunrise)
    TextView tvSunrise;

    @Bind(R.id.tv_sunset)
    TextView tvSunset;

    @Bind(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;

    Observable<CurrentWeatherData> observable;
    WeatherService weatherService;
    Retrofit retrofit;
    OkHttpClient loggingClient;

    private final String apiKey = "9330706858110721d507c1057cae7b61";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        srlRefresh.setOnRefreshListener(this);

        //Logging purpose
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        loggingClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(loggingClient)
                .build();


        weatherService = retrofit.create(WeatherService.class);
    }

    @Override
    public void onRefresh() {
        String city = etCity.getText().toString();
        observable = weatherService.getCurrentWeatherData(city, apiKey);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CurrentWeatherData>() {
                    @Override
                    public void onCompleted() {
                        srlRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showSnack(e.getMessage());
                        srlRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onNext(CurrentWeatherData currentWeatherData) {
                        tvHumidity.setText(currentWeatherData.getMainInfo().getTemp().toString());
                        tvHumidity.setText(currentWeatherData.getMainInfo().getHumidity().toString());
                        tvPRessure.setText(currentWeatherData.getMainInfo().getPressure().toString());
                        tvSunrise.setText(currentWeatherData.getSys().getSunrise());
                        tvSunset.setText(currentWeatherData.getSys().getSunset());
                        srlRefresh.setRefreshing(false);
                    }
                });
    }

    private void showSnack(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        observable.unsubscribeOn(Schedulers.io());
    }
}
