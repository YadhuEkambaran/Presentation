package com.yadhukrishnane.presentation.network;

import com.yadhukrishnane.presentation.models.ChatbotRequest;
import com.yadhukrishnane.presentation.models.ChatbotResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIPrototype {


    String API_BASE_URL = "https://api.dialogflow.com/v1/";

    @POST("query")
    Call<ChatbotResponse> getChatbotResponse(@Header("Authorization") String authToken, @Query("v") String query, @Body ChatbotRequest res);

}