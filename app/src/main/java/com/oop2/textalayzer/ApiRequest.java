package com.oop2.textalayzer;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiRequest {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private String prompt;
    private String choice;
    private String choiceContent;

    public ApiRequest(String prompt, String choice) {
        this.prompt = prompt;
        this.choice = choice;
    }

    private String getChoiceContent() {
        if (this.choice == "summerize") {
            return "Summarize content you are provided with."
        } else if (this.choice == "explain") {
            return "Explain content you are provided with."
        } else if (this.choice == "getTone") {
            return "..."
        } else {
            return ""
        }
    }

    public String makeRequest() {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String jsonBody = """
        {\"model\": \"gpt-3.5-turbo\",
            \"messages\": [
                {\"role\": \"system\",
                    \"content\":""" + getChoiceContent() + """
                },
                {\"role\": \"user\",
                    \"content\": """ + prompt + """
                }
            ]
        }
        """;
        RequestBody requestBody = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return "Fehler: " + response.code();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Fehler: Netzwerkfehler";
        }
    }
}


