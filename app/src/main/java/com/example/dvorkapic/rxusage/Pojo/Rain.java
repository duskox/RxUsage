package com.example.dvorkapic.rxusage.Pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dvorkapic on 31/03/16.
 */
public class Rain {

    private Integer _3h;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer get_3h() {
        return _3h;
    }

    public void set_3h(Integer _3h) {
        this._3h = _3h;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
