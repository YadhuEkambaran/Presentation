
package com.yadhukrishnane.presentation.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("intentId")
    @Expose
    private String intentId;
    @SerializedName("intentName")
    @Expose
    private String intentName;
    @SerializedName("webhookUsed")
    @Expose
    private String webhookUsed;
    @SerializedName("webhookForSlotFillingUsed")
    @Expose
    private String webhookForSlotFillingUsed;
    @SerializedName("isFallbackIntent")
    @Expose
    private String isFallbackIntent;

    public String getIntentId() {
        return intentId;
    }

    public void setIntentId(String intentId) {
        this.intentId = intentId;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public String getWebhookUsed() {
        return webhookUsed;
    }

    public void setWebhookUsed(String webhookUsed) {
        this.webhookUsed = webhookUsed;
    }

    public String getWebhookForSlotFillingUsed() {
        return webhookForSlotFillingUsed;
    }

    public void setWebhookForSlotFillingUsed(String webhookForSlotFillingUsed) {
        this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
    }

    public String getIsFallbackIntent() {
        return isFallbackIntent;
    }

    public void setIsFallbackIntent(String isFallbackIntent) {
        this.isFallbackIntent = isFallbackIntent;
    }

}
