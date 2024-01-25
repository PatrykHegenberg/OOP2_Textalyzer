package com.oop2.textalayzer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiClient {
    /**
     * A description of the entire Java function.
     *
     * @param  jsonRequest	description of parameter
     * @return         	description of return value
     */
    @Headers({
            "Content-Type: application/json",
    })
    @POST("v1/chat/completions")
    Call<ApiResponse> getCompletion(@Body ApiRequest jsonRequest);
}
