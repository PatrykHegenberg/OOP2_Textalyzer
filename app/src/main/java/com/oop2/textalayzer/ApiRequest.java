package com.oop2.textalayzer;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiRequest {
    private static final String API_URL = "https://deine.api/endpoint";  // Ersetze dies durch den tatsächlichen Endpunkt deiner API
    private String prompt;

    public ApiRequest(String prompt) {
        this.prompt = prompt;
    }

    public String makeRequest() {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        // Erstelle den Anfrage-Body (JSON im Beispiel)
        String jsonBody = "{\"prompt\": \"" + prompt + "\"}";
        RequestBody requestBody = RequestBody.create(mediaType, jsonBody);

        // Erstelle die HTTP-Anfrage
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .build();

        try {
            // Führe die Anfrage aus
            Response response = client.newCall(request).execute();

            // Überprüfe, ob die Anfrage erfolgreich war (Statuscode 200)
            if (response.isSuccessful()) {
                return response.body().string();  // Gib die Antwort zurück
            } else {
                return "Fehler: " + response.code();  // Gib den Fehlercode zurück
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Fehler: Netzwerkfehler";  // Gib einen allgemeinen Netzwerkfehler zurück
        }
    }
}


