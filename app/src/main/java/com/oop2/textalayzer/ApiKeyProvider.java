package com.oop2.textalayzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ApiKeyProvider {
    public static String getApiKey() {
        // Pfad zur Datei mit dem API-Schlüssel
        String filePath = "assets/config.txt";

        try {
            // Öffne die Datei als InputStream
            InputStream inputStream = ApiKeyProvider.class.getClassLoader().getResourceAsStream(filePath);

            if (inputStream != null) {
                // Verwende BufferedReader, um die Datei Zeile für Zeile zu lesen
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                // Gib den Inhalt der Datei als String zurück
                return stringBuilder.toString().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Hier könntest du eine spezifischere Exception werfen oder einen Standardwert zurückgeben.
        }

        return "";
    }
}
