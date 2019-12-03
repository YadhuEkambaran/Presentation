
package com.yadhukrishnane.presentation.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Context {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lifespan")
    @Expose
    private Integer lifespan;
    @SerializedName("parameters")
    @Expose
    private Parameters_ parameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLifespan() {
        return lifespan;
    }

    public void setLifespan(Integer lifespan) {
        this.lifespan = lifespan;
    }

    public Parameters_ getParameters() {
        return parameters;
    }

    public void setParameters(Parameters_ parameters) {
        this.parameters = parameters;
    }

}
