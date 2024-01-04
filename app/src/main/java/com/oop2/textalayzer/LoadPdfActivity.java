package com.oop2.textalayzer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LoadPdfActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_PDF = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_pdf);
        checkAndRequestPermission();
    }
    private void checkAndRequestPermission() {
        // Überprüfe, ob die Berechtigung erteilt ist
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            // Wenn nicht, frage den Benutzer nach der Berechtigung
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PICK_PDF);
        } else {
            // Wenn die Berechtigung bereits erteilt wurde, starte den Dateiauswahlprozess
            startFilePicker();
        }
    }
    public void choosePdfFile(View view) {
        // Überprüfe, ob die Berechtigung erteilt ist
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            // Wenn nicht, frage den Benutzer nach der Berechtigung
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PICK_PDF);
        } else {
            // Wenn die Berechtigung bereits erteilt wurde, starte den Dateiauswahlprozess
            startFilePicker();
        }
    }

    private void startFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, REQUEST_PICK_PDF);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PICK_PDF) {
            if (grantResults.length > 0 && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                // Wenn die Berechtigung erteilt wurde, starte den Dateiauswahlprozess
                startFilePicker();
            } else {
                // Wenn die Berechtigung nicht erteilt wurde, zeige eine Meldung an
                Toast.makeText(this, "Berechtigung zum Lesen von Dateien wurde verweigert.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_PDF && resultCode == RESULT_OK && data != null) {
            // Hier kannst du die ausgewählte PDF-Datei verwenden
            // Der Dateipfad kann mit data.getData().getPath() abgerufen werden
            Toast.makeText(this, "PDF ausgewählt: " + data.getData().getPath(), Toast.LENGTH_SHORT).show();

            // Implementiere hier die Logik zum Verarbeiten der ausgewählten PDF-Datei
        }
    }

    public void processPdf(View view) {
        // Implementiere hier die Logik zum Verarbeiten der ausgewählten PDF-Datei
        Intent intent = new Intent(this, ChooseActionActivity.class);
        startActivity(intent);
    }
}


