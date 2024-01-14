package com.oop2.textalayzer;


import static org.junit.Assert.assertEquals;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.Test;

public class ApiRequestTest {

    @Test
    public void toJson_ReturnsCorrectJson() {
        // Erzeuge eine Beispiel-ApiRequest-Instanz
        String prompt = "Beispiel-Prompt";
        String choice = "zusammenfassen";
        String model = "exampleModel";
        ApiRequest apiRequest = new ApiRequest(prompt, choice, model);

        // Erzeuge das erwartete JSON
        JsonObject expectedJson = new JsonObject();
        expectedJson.addProperty("model", model);

        JsonArray messagesArray = new JsonArray();
        JsonObject systemMessage = new JsonObject();
        systemMessage.addProperty("role", "system");
        systemMessage.addProperty("content", "Fasse den bereitgestellten Inhalt zusammen");
        messagesArray.add(systemMessage);

        JsonObject userMessage = new JsonObject();
        userMessage.addProperty("role", "user");
        userMessage.addProperty("content", prompt);
        messagesArray.add(userMessage);

        expectedJson.add("messages", messagesArray);

        // Vergleiche das erzeugte JSON mit dem erwarteten JSON
        assertEquals(expectedJson.toString(), apiRequest.toJson());
    }


}
