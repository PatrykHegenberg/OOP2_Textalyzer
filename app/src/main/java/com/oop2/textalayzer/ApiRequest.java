package com.oop2.textalayzer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * This class encapsulates an API request.
 * It includes a model and a list of messages, which are represented as a
 * JsonArray.
 */
public class ApiRequest {
    private String model;
    private JsonArray messages;

    /**
     * Constructs an ApiRequest object.
     * The constructor takes three parameters: prompt, choice, and model.
     * These parameters are used to create a system message and a user message,
     * which are added to the messages list.
     *
     * @param prompt the prompt for the API request
     * @param choice the choice for content retrieval
     * @param model  the model for the API request
     */
    public ApiRequest(String prompt, String choice, String model) {
        this.model = model;
        this.messages = new JsonArray();

        addMessage("system", getChoiceContent(choice));
        addMessage("user", prompt);
    }

    /**
     * Converts the ApiRequest object to a JSON string.
     * The conversion process involves creating a new JsonObject, adding the model
     * and messages properties to it,
     * and then returning the string representation of the JsonObject.
     *
     * @return a JSON representation of the ApiRequest object
     */
    public String toJson() {
        // Create a new JSON object
        JsonObject root = new JsonObject();

        // Add the "model" property to the JSON object
        root.addProperty("model", model);

        // Add the "messages" property to the JSON object
        root.add("messages", messages);

        // Return the JSON string representation of the JSON object
        return root.toString();
    }

    /**
     * Adds a message to the messages list.
     * Each message is represented as a JsonObject with a role and content property.
     *
     * @param role    the role of the message
     * @param content the content of the message
     */
    private void addMessage(String role, String content) {
        // Create a new JSON object to represent the message
        JsonObject message = new JsonObject();
        // Add the role and content properties to the JSON object
        message.addProperty("role", role);
        message.addProperty("content", content);
        // Add the message to the messages list
        messages.add(message);
    }

    /**
     * Retrieves the content based on the provided choice.
     * The choice parameter is checked using a switch statement, and the
     * corresponding content is returned.
     * If the choice does not match any of the cases, a default content is returned.
     *
     * @param choice the choice for content retrieval
     * @return the content based on the provided choice
     */
    private String getChoiceContent(String choice) {
        // Use switch statement to check the value of the choice parameter
        switch (choice) {
            // If the choice is "zusammenfassen", return the corresponding content
            case "zusammenfassen":
                return "Fasse den bereitgestellten Inhalt zusammen";
            // If the choice is "inhalt analysieren", return the corresponding content
            case "inhalt analysieren":
                return "Analyisiere den bereitgestellten Inhalt.";
            // If the choice is "stimmung analysieren", return the corresponding content
            case "stimmung analysieren":
                return "Analyisieren sie Stimmung des bereitsgestellten Inhalts.";
            // If the choice does not match any of the above, return the default content
            default:
                return "Default Content";
        }
    }
}
