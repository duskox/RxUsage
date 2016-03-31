package com.example.dvorkapic.rxusage.Pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dvorkapic on 31/03/16.
 */
public class Wind {

    private Double speed;
    private Integer deg;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
