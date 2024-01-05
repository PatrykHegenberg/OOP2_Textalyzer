package com.oop2.textalayzer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiClient {
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer"
    })
    @POST("v1/chat/completions")
    Call<ApiResponse> getCompletion(@Body ApiRequest jsonRequest);
}
