package com.yadhukrishnane.presentation.network;


import com.yadhukrishnane.presentation.models.ChatbotRequest;
import com.yadhukrishnane.presentation.models.ChatbotResponse;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by El nino yadhu on 15-08-2018.
 */

public class NetworkManager {


    public interface OnNetworkResponseListener {
        void successResponse(Response response);

        void failureResponse(Response response);

        void noInternet();
    }

    private static NetworkManager sManager;
    private static APIPrototype mAPIInstance;
    private OnNetworkResponseListener mListener;

    private NetworkManager() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIPrototype.API_BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mAPIInstance = retrofit.create(APIPrototype.class);

    }

    public static NetworkManager getInstance() {
        if (sManager == null) {
            sManager = new NetworkManager();
        }
        return sManager;
    }

    public void setNetworkListener(OnNetworkResponseListener mListener) {
        this.mListener = mListener;
    }

    public void removeNetworkListener() {
        this.mListener = null;
    }



    public void callAPIForMessage(String message) {

        ChatbotRequest request = new ChatbotRequest();
        request.setLang("en");
        request.setQuery(message);
        request.setSessionId("12345");

        Call<ChatbotResponse> tokenResponseCall = mAPIInstance.getChatbotResponse("Bearer 39bf34a5d1fe44238825b43b0544e813", "20191129", request);
        tokenResponseCall.enqueue(new Callback<ChatbotResponse>() {
            @Override
            public void onResponse(Call<ChatbotResponse> call, retrofit2.Response<ChatbotResponse> response) {
                successResponse(response);
            }

            @Override
            public void onFailure(Call<ChatbotResponse> call, Throwable t) {
                failureResponse(t);
            }
        });
    }



    private void successResponse(retrofit2.Response response) {
        Response res = new Response();
        res.body = response.body();
        res.status = response.isSuccessful();
        res.code = response.code();
        try {
            res.errorBody = (response.errorBody() == null ? null : response.errorBody().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (res.body != null) {
            if (mListener != null) {
                mListener.successResponse(res);
            }
        } else {
            if (mListener != null) {
                mListener.failureResponse(res);
            }
        }
    }

    private void failureResponse(Throwable t) {
        if (t instanceof SocketTimeoutException || t instanceof UnknownHostException) {
            if (mListener != null) {
                mListener.noInternet();
            }
        } else {
            Response response = new Response();
            response.errorBody = t.toString();
            if (mListener != null) {
                mListener.failureResponse(response);
            }
        }
    }
}