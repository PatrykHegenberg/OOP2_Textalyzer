package com.oop2.textalayzer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tom_roush.pdfbox.android.PDFBoxResourceLoader;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;
public class LoadPdfActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_PDF = 1;

    // Use ActivityResultLauncher for handling the file picker result
    private ActivityResultLauncher<Intent> filePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    handleFilePickerResult(result.getData());
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_pdf);
        checkAndRequestPermission();

        // Intent überprüfen
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_VIEW.equals(action) && "application/pdf".equals(type)) {
            // PDF-Datei behandeln
            Uri pdfUri = intent.getData();
            // Hier kannst du den URI für die PDF-Datei verwenden und deine eigene Logik implementieren
            handleFilePickerResult(intent);
        }

    }

    private void checkAndRequestPermission() {
        // Check if the permission is granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            // If not, request the permission from the user
            showPermissionDialog();
        } else {
            // If the permission is already granted, start the file picker process
            startFilePicker();
        }
    }

    private void showPermissionDialog() {
        // Show a dialog explaining that permission is not required due to SAF
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The app uses the Storage Access Framework (SAF) to access files, and explicit permission is not required.")
                .setTitle("Permission Notice")
                .setPositiveButton("Continue", (dialog, which) -> startFilePicker())
                .setCancelable(false)
                .show();
    }

    public void choosePdfFile(View view) {
        // Check if the permission is granted (Not needed for SAF)
        startFilePicker();
    }

    private void startFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");

        // Start the file picker using ActivityResultLauncher
        filePickerLauncher.launch(intent);
    }

    // No need to handle onRequestPermissionsResult for READ_EXTERNAL_STORAGE

    private void handleFilePickerResult(Intent data) {
        // You can use the selected PDF file here
        Uri uri = data.getData();
        String filePath = uri.toString();
        Toast.makeText(this, "PDF selected: " + filePath, Toast.LENGTH_SHORT).show();

        // Extract text from the selected PDF file
        String pdfText = extractTextFromPdf(uri);

        // Pass the extracted text to ChooseActionActivity
        Intent intent = new Intent(this, ChooseActionActivity.class);
        intent.putExtra("userInput", pdfText);
        startActivity(intent);
    }
    private String extractTextFromPdf(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            // Implement the logic to extract text from the InputStream (PDF file)
            // For example, you can use a library like Apache PDFBox or others
            // Here, we assume a placeholder method extractTextFromInputStream
            String extractedText = extractTextFromInputStream(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return extractedText;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
   private String extractTextFromInputStream(InputStream inputStream) {
       PDFBoxResourceLoader.init(getApplicationContext());
       try {
           PDDocument document = PDDocument.load(inputStream);
           PDFTextStripper pdfTextStripper = new PDFTextStripper();
           String text = pdfTextStripper.getText(document);
           document.close();
           return text;
       } catch (IOException e) {
           e.printStackTrace();
           return null;
       }
   }

    public void processPdf(View view) {
        // Implement the logic to process the selected PDF file here
        Intent intent = new Intent(this, ChooseActionActivity.class);
        startActivity(intent);
    }
}