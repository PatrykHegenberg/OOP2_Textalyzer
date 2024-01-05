package com.oop2.textalayzer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiClient {
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer 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"
    })
    @POST("completions")
    Call<ApiResponse> getCompletion(@Body ApiRequest request);
}
