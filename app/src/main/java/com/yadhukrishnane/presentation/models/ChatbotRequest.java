package com.yadhukrishnane.presentation.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatbotRequest {

@SerializedName("lang")
@Expose
private String lang;
@SerializedName("query")
@Expose
private String query;
@SerializedName("sessionId")
@Expose
private String sessionId;

public String getLang() {
return lang;
}

public void setLang(String lang) {
this.lang = lang;
}

public String getQuery() {
return query;
}

public void setQuery(String query) {
this.query = query;
}

public String getSessionId() {
return sessionId;
}

public void setSessionId(String sessionId) {
this.sessionId = sessionId;
}

}