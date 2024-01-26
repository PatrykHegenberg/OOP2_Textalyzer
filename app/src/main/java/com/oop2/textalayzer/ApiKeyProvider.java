package com.oop2.textalayzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A class that provides an API key from a file.
 */
public class ApiKeyProvider {
/**
 * Retrieves the API key from the specified file path.
 *
 * @return the API key as a string
 */
public static String getApiKey() {
    String filePath = "assets/config.txt";  // Set the file path

    try {
        // Attempt to open the file as an input stream
        InputStream inputStream = ApiKeyProvider.class.getClassLoader().getResourceAsStream(filePath);

        // If the input stream is not null, read the file and return its contents as a string
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            // Read each line of the file and append it to the string builder
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString().trim();  // Return the file contents as a string
        }
    } catch (IOException e) {
        e.printStackTrace();  // Print the stack trace if an IOException occurs
    }

    return "";  // Return an empty string if the file cannot be read
}
}
