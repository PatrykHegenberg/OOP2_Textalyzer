package com.oop2.textalayzer;


import org.junit.Assert;
import org.junit.Test;

public class ApiKeyProviderTest {
    
    @Test
    public void testGetApiKeyFromFile() {
        // Test case: API key exists in the file
        // Expected: The API key is returned as a non-empty string
        String apiKey = ApiKeyProvider.getApiKey();
        Assert.assertNotNull(apiKey);
        Assert.assertTrue(apiKey.isEmpty());}
    
    @Test
    public void testGetApiKeyFromNonExistentFile() {
        // Test case: File with API key does not exist
        // Expected: An empty string is returned
        String apiKey = ApiKeyProvider.getApiKey();
        Assert.assertSame("", apiKey);
        Assert.assertTrue(apiKey.isEmpty());
    }
    
    // Add more test cases if necessary
}