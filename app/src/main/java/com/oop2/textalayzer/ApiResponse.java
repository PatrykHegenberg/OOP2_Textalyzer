package com.oop2.textalayzer;

        import android.util.Log;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

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

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public long getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public String getSystem_fingerprint() {
        return system_fingerprint;
    }

    private void parseJsonResponse(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            id = jsonObject.getString("id");
            object = jsonObject.getString("object");
            created = jsonObject.getLong("created");
            model = jsonObject.getString("model");

            JSONArray choicesArray = jsonObject.getJSONArray("choices");
            choices = new ArrayList<>();
            for (int i = 0; i < choicesArray.length(); i++) {
                JSONObject choiceObj = choicesArray.getJSONObject(i);
                Choice choice = new Choice(choiceObj);
                choices.add(choice);
            }

            JSONObject usageObj = jsonObject.getJSONObject("usage");
            usage = new Usage(usageObj);

            system_fingerprint = jsonObject.getString("system_fingerprint");
        } catch (JSONException e) {
            Log.d("API Response", "Error parsing JSON response: " + e.getMessage());
            e.printStackTrace();
        }
    }
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

        // Getter und Setter für jedes Feld
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

            // Getter und Setter für jedes Feld
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

        // Getter und Setter für jedes Feld
    }
}