package com.oop2.textalayzer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ApiRequest {
    private String model;
    private JsonArray messages;

    public ApiRequest(String prompt, String choice, String model) {
        this.model = model;
        this.messages = new JsonArray();

        // Füge zuerst die Systemnachricht und dann die Benutzernachricht hinzu
        addMessage("system", getChoiceContent(choice));
        addMessage("user", prompt);
    }

    public String toJson() {
        JsonObject root = new JsonObject();
        root.addProperty("model", model);
        root.add("messages", messages);
        return root.toString();
    }

    private void addMessage(String role, String content) {
        JsonObject message = new JsonObject();
        message.addProperty("role", role);
        message.addProperty("content", content);
        messages.add(message);
    }

    private String getChoiceContent(String choice) {
        switch (choice) {
            case "summerize":
                return "Summarize the content provided.";
            case "explain":
                return "Explain the content provided.";
            case "getTone":
                return "Determine the tone of the content provided.";
            default:
                return "Default Content"; // Standardwert, wenn keine Auswahl getroffen wurde
        }
    }
}
