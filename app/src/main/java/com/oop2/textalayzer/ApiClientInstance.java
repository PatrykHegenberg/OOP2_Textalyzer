package com.oop2.textalayzer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientInstance {

    private static final String BASE_URL = "https://api.openai.com/v1/chat/completions/";

    private static ApiClient apiClient;

    public static ApiClient getApiClient() {
        if (apiClient == null) {
            apiClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiClient.class);
        }
        return apiClient;
    }
}
