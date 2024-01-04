package com.oop2.textalayzer;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiResponse {
    private String choices;

    public ApiResponse(String jsonResponse) {
        parseJsonResponse(jsonResponse);
    }

    public String getChoices() {
        return choices;
    }

    private void parseJsonResponse(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);

            choices = jsonObject.optString("choices", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
