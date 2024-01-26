package com.oop2.textalayzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class is responsible for retrieving the API key from a file.
 * It uses the Singleton pattern to ensure only one instance of the API key is
 * created.
 */
public class ApiKeyProvider {

    /**
     * This method retrieves the API key from a specified file path.
     * It opens the file as an input stream, reads its contents, and returns them as
     * a string.
     * If the file cannot be opened or read, it prints the stack trace and returns
     * an empty string.
     *
     * Usage example:
     * String apiKey = ApiKeyProvider.getApiKey();
     * System.out.println(apiKey);
     *
     * @return the API key as a string
     */
    public static String getApiKey() {
        String filePath = "assets/config.txt"; // Set the file path

        try {
            // Attempt to open the file as an input stream
            InputStream inputStream = ApiKeyProvider.class.getClassLoader().getResourceAsStream(filePath);

            // If the input stream is not null, read the file and return its contents as a
            // string
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                // Read each line of the file and append it to the string builder
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                return stringBuilder.toString().trim(); // Return the file contents as a string
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an IOException occurs
        }

        return ""; // Return an empty string if the file cannot be read
    }
}
