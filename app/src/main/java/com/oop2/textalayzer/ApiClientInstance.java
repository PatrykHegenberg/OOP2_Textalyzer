package com.oop2.textalayzer;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientInstance {

    private static final String BASE_URL = "https://api.openai.com/";

    private static ApiClient apiClient;

/**
 * Returns the API client, creating it if it doesn't exist.
 *
 * @return          the API client
 */
public static ApiClient getApiClient() {
    // Check if the API client has been instantiated
    if (apiClient == null) {
        // Create an HTTP logging interceptor to log request and response information
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Create an OkHttpClient with the logging interceptor to make network calls
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                // Add an interceptor to add the Authorization header with the API key
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    Request newRequest = originalRequest.newBuilder()
                            .header("Authorization", "Bearer " + ApiKeyProvider.getApiKey())
                            .build();
                    return chain.proceed(newRequest);
                })
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        // Create a Retrofit instance with the base URL, Gson converter, and OkHttpClient
        apiClient = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiClient.class);
    }
    // Return the API client
    return apiClient;
}
}
