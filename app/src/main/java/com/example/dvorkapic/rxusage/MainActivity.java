package com.example.dvorkapic.rxusage;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dvorkapic.rxusage.Pojo.CurrentWeatherData;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
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

    private final String apiKey = "9330706858110721d507c1057cae7b61";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        srlRefresh.setOnRefreshListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .build();


        weatherService = retrofit.create(WeatherService.class);




    }

    @Override
    public void onRefresh() {
        observable =  weatherService.getCurrentWeatherData(etCity.getText().toString(), apiKey);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CurrentWeatherData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CurrentWeatherData currentWeatherData) {
                        tvHumidity.setText(currentWeatherData.getMainInfo().getTemp().toString());
                        tvHumidity.setText(currentWeatherData.getMainInfo().getHumidity().toString());
                        tvPRessure.setText(currentWeatherData.getMainInfo().getPressure().toString());
                        tvSunrise.setText(currentWeatherData.getSys().getSunrise());
                        tvSunset.setText(currentWeatherData.getSys().getSunset());
                    }
                });
    }

    private void showSnack(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }
}
