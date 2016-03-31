package com.example.dvorkapic.rxusage.Pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dvorkapic on 31/03/16.
 */
public class Coord {

    private Double lon;
    private Double lat;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
