package com.oop2.textalayzer;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiResponse {
    private String choices;

    // Konstruktor für die Erstellung einer ApiResponse aus dem JSON-String der API-Antwort
    public ApiResponse(String jsonResponse) {
        parseJsonResponse(jsonResponse);
    }

    public String getChoices() {
        return choices;
    }

    // Diese Methode analysiert den JSON-String und setzt die entsprechenden Felder
    private void parseJsonResponse(String jsonResponse) {
        try {
            // Erstelle ein JSONObject aus dem JSON-String
            JSONObject jsonObject = new JSONObject(jsonResponse);

            // Extrahiere das gewünschte Feld ("choices" im Beispiel)
            choices = jsonObject.optString("choices", "");
        } catch (JSONException e) {
            e.printStackTrace();
            // Hier könntest du eine Ausnahmebehandlung für einen ungültigen JSON-String hinzufügen
        }
    }
}
