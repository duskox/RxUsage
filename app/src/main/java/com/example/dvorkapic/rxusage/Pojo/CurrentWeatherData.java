package com.example.dvorkapic.rxusage.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvorkapic on 31/03/16.
 */
public class CurrentWeatherData {
    @SerializedName("coord")
    @Expose
    private Coord coord;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather = new ArrayList<Weather>();

    @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("main")
    @Expose
    private MainInfo mainInfo;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("clouds")
    @Expose
    private Clouds clouds;

    @SerializedName("rain")
    @Expose
    private Rain rain;

    @SerializedName("dt")
    @Expose
    private Integer dt;

    @SerializedName("sys")
    @Expose
    private Sys sys;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("cod")
    @Expose
    private Integer cod;
}
