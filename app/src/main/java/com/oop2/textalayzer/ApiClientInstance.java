package com.oop2.textalayzer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientInstance {

    private static final String BASE_URL = "https://api.openai.com/";

    private static ApiClient apiClient;

    public static ApiClient getApiClient() {
        if (apiClient == null) {
            // Logging Interceptor erstellen
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // OkHttpClient mit dem Logging Interceptor erstellen
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            // Retrofit-Instanz mit OkHttpClient erstellen
            apiClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(ApiClient.class);
        }
        return apiClient;
    }
}
