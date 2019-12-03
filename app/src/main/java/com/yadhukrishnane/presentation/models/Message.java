
package com.yadhukrishnane.presentation.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("speech")
    @Expose
    private String speech;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

}
