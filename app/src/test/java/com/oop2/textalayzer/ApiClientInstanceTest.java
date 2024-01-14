package com.oop2.textalayzer;


import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;

public class ApiClientInstanceTest {

    private MockWebServer mockWebServer;

    @Before
    public void setUp() throws IOException {
        // Initialisiere den MockWebServer
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @Test
    public void getApiClient_ReturnsSameInstance() {
        ApiClient apiClient1 = ApiClientInstance.getApiClient();
        ApiClient apiClient2 = ApiClientInstance.getApiClient();

        assertSame("ApiClient instances should be the same", apiClient1, apiClient2);
    }
    @After
    public void tearDown() throws IOException {
        // Beende den MockWebServer
        mockWebServer.shutdown();
    }
}
