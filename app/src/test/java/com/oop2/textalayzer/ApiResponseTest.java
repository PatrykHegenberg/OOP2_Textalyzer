package com.oop2.textalayzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ApiResponseTest {
    // TODO
    @Test
    public void testParseJsonResponse_validResponse() {
        String jsonResponse = "{\n" +
                " \"id\":\"123\",\n" +
                " \"object\":\"object\",\n" +
                " \"created\":123456,\n" +
                " \"model\":\"model\",\n" +
                " \"choices\":[\n" +
                " {\"index\":1,\"message\":{\"role\":\"role\",\"content\":\"content\"},\"logprobs\":[0.1,0.2,0.3],\"finish_reason\":\"finish_reason\"},\n" +
                " {\"index\":2,\"message\":{\"role\":\"role\",\"content\":\"content\"},\"logprobs\":[0.1,0.2,0.3],\"finish_reason\":\"finish_reason\"}\n" +
                " ],\n" +
                " \"usage\":{\"prompt_tokens\":10,\"completion_tokens\":20,\"total_tokens\":30},\n" +
                " \"system_fingerprint\":\"fingerprint\"\n" +
                "}";

        ApiResponse apiResponse = new ApiResponse(jsonResponse);

        assertEquals("123", apiResponse.getId());
        assertEquals("object", apiResponse.getObject());
        assertEquals(123456, apiResponse.getCreated());
        assertEquals("model", apiResponse.getModel());
        assertEquals(2, apiResponse.getChoices().size());
        assertEquals("A", apiResponse.getChoices().get(0).getMessage().getContent());
        assertEquals("B", apiResponse.getChoices().get(1).getMessage().getContent());
        assertEquals(100, apiResponse.getUsage());
        assertEquals("fingerprint", apiResponse.getSystem_fingerprint());
    }

    // TODO
    @Test
    public void testParseJsonResponse_missingFields() {
        String jsonResponse = "{\n" +
                " \"id\":\"123\",\n" +
                " \"object\":\"object\",\n" +
                " \"created\":123456,\n" +
                " \"model\":\"model\",\n" +
                " \"choices\":[\n" +
                " {\"index\":1,\"message\":{\"role\":\"role\",\"content\":\"content\"},\"logprobs\":[0.1,0.2,0.3],\"finish_reason\":\"finish_reason\"},\n" +
                " {\"index\":2,\"message\":{\"role\":\"role\",\"content\":\"content\"},\"logprobs\":[0.1,0.2,0.3],\"finish_reason\":\"finish_reason\"}\n" +
                " ],\n" +
                " \"usage\":{\"prompt_tokens\":10,\"completion_tokens\":20,\"total_tokens\":30},\n" +
                " \"system_fingerprint\":\"fingerprint\"\n" +
                "}";

        ApiResponse apiResponse = new ApiResponse(jsonResponse);

        assertEquals("123", apiResponse.getId());
        assertEquals("object", apiResponse.getObject());
        assertEquals(123456, apiResponse.getCreated());
        assertNull(apiResponse.getModel());
        assertTrue(apiResponse.getChoices().isEmpty());
        assertNull(apiResponse.getUsage());
        assertNull(apiResponse.getSystem_fingerprint());
    }

}
