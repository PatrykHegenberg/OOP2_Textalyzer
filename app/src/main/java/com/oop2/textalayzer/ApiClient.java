package com.oop2.textalayzer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * This interface, ApiClient, is designed to facilitate communication with the
 * OpenAI API via HTTP POST requests.
 */
public interface ApiClient {
    /**
     * This method sends a POST request to the OpenAI API endpoint
     * "v1/chat/completions". The JSON body of the request is passed as a parameter.
     * The response from the API is encapsulated in an instance of ApiResponse.
     *
     * @param jsonRequest An instance of ApiRequest containing the data to be sent
     *                    in the body of the POST request.
     * @return A Call object representing the HTTP request. This can be used to send
     *         the request and handle the response.
     */
    @Headers({
            "Content-Type: application/json",
    })
    @POST("v1/chat/completions")
    Call<ApiResponse> getCompletion(@Body ApiRequest jsonRequest);
}
