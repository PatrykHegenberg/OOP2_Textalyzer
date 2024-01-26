package com.oop2.textalayzer;

        import android.util.Log;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

/**
 * A class that represents an API response.
 */
public class ApiResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
    private String system_fingerprint;

    public ApiResponse(String jsonResponse) {
        parseJsonResponse(jsonResponse);
    }

    /**
     * Retrieves the id of the object.
     *
     * @return the id of the object
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the object.
     *
     * @return         	the retrieved object
     */
    public String getObject() {
        return object;
    }

    /**
     * Get the timestamp of when the object was created.
     *
     * @return         	the timestamp of when the object was created
     */
    public long getCreated() {
        return created;
    }

    /**
     * Gets the model.
     *
     * @return  the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Retrieves the list of choices.
     *
     * @return the list of choices
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * Get the usage.
     *
     * @return         	the usage
     */
    public Usage getUsage() {
        return usage;
    }

    /**
     * Retrieves the system fingerprint.
     *
     * @return  the system fingerprint
     */
    public String getSystem_fingerprint() {
        return system_fingerprint;
    }

/**
 * Parses the given JSON response and populates the corresponding fields.
 *
 * @param  jsonResponse  the JSON response to be parsed
 */
private void parseJsonResponse(String jsonResponse) {
    try {
        // Create a JSONObject from the given JSON response
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Extract the "id" field from the JSON object and assign it to the "id" variable
        id = jsonObject.getString("id");

        // Extract the "object" field from the JSON object and assign it to the "object" variable
        object = jsonObject.getString("object");

        // Extract the "created" field from the JSON object and assign it to the "created" variable
        created = jsonObject.getLong("created");

        // Extract the "model" field from the JSON object and assign it to the "model" variable
        model = jsonObject.getString("model");

        // Extract the "choices" array from the JSON object
        JSONArray choicesArray = jsonObject.getJSONArray("choices");

        // Create an empty list to store the choices
        choices = new ArrayList<>();

        // Iterate over each element in the "choices" array
        for (int i = 0; i < choicesArray.length(); i++) {
            // Get the JSON object at the current index
            JSONObject choiceObj = choicesArray.getJSONObject(i);

            // Create a new Choice object using the JSON object and add it to the choices list
            Choice choice = new Choice(choiceObj);
            choices.add(choice);
        }

        // Extract the "usage" object from the JSON object
        JSONObject usageObj = jsonObject.getJSONObject("usage");

        // Create a new Usage object using the "usage" object and assign it to the "usage" variable
        usage = new Usage(usageObj);

        // Extract the "system_fingerprint" field from the JSON object and assign it to the "system_fingerprint" variable
        system_fingerprint = jsonObject.getString("system_fingerprint");
    } catch (JSONException e) {
        // Log an error message if there is an exception while parsing the JSON response
        Log.d("API Response", "Error parsing JSON response: " + e.getMessage());
        e.printStackTrace();
    }
}
    /**
     * Retrieves the content of the first choice message, if available.
     *
     * @return         	the content of the first choice message, or an empty string if no choices are available
     */
    public String getContent() {
        if (choices != null && !choices.isEmpty()) {
            return choices.get(0).getMessage().getContent();
        } else {
            return "";
        }
    }

    public static class Choice {
        private int index;
        private Message message;
        private Object logprobs;
        private String finish_reason;

        public Choice(JSONObject jsonObject) throws JSONException {
            index = jsonObject.getInt("index");
            JSONObject messageObj = jsonObject.getJSONObject("message");
            message = new Message(messageObj);
            logprobs = jsonObject.opt("logprobs");
            finish_reason = jsonObject.getString("finish_reason");
        }

        /**
         * Retrieves the message.
         *
         * @return  the message to be retrieved
         */
        public Message getMessage() {
            return message;
        }
        public static class Message {
            private String role;
            private String content;

            public Message(JSONObject jsonObject) throws JSONException {
                role = jsonObject.getString("role");
                content = jsonObject.getString("content");
            }

            /**
             * Gets the content of the object.
             *
             * @return         	the content of the object
             */
            public String getContent() {
                return content;
            }
        }
    }

    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;

        public Usage(JSONObject jsonObject) throws JSONException {
            prompt_tokens = jsonObject.getInt("prompt_tokens");
            completion_tokens = jsonObject.getInt("completion_tokens");
            total_tokens = jsonObject.getInt("total_tokens");
        }

    }
}