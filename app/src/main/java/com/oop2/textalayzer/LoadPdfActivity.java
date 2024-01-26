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

/**
 * A class that represents an activity for loading a PDF file.
 */
public class LoadPdfActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_PDF = 1;

    private ActivityResultLauncher<Intent> filePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    handleFilePickerResult(result.getData());
                }
            });

    /**
     * onCreate method to handle the creation of the activity, including
     * checking and requesting permissions, getting the intent, and handling
     * PDF file viewing.
     *
     * @param  savedInstanceState  the saved state of the activity
     * @return                   void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_pdf);
        checkAndRequestPermission();

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_VIEW.equals(action) && "application/pdf".equals(type)) {
            Uri pdfUri = intent.getData();
            handleFilePickerResult(intent);
        }

    }

    /**
     * Method to check and request permission.
     *
     */
    private void checkAndRequestPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            showPermissionDialog();
        } else {
            startFilePicker();
        }
    }

    /**
     * Show permission dialog to inform user about storage access and prompt for action.
     */
    private void showPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The app uses the Storage Access Framework (SAF) to access files, and explicit permission is not required.")
                .setTitle("Permission Notice")
                .setPositiveButton("Continue", (dialog, which) -> startFilePicker())
                .setCancelable(false)
                .show();
    }

    /**
     * Choose a PDF file.
     *
     * @param  view	the view triggering the file selection
     * @return     	void
     */
    public void choosePdfFile(View view) {
        startFilePicker();
    }

    /**
     * Starts the file picker to allow the user to select a PDF file.
     */
    private void startFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");

        filePickerLauncher.launch(intent);
    }

    /**
     * Handles the result from the file picker.
     *
     * @param  data  the intent data containing the file URI
     */
    private void handleFilePickerResult(Intent data) {
        Uri uri = data.getData();
        String filePath = uri.toString();
        Toast.makeText(this, "PDF selected: " + filePath, Toast.LENGTH_SHORT).show();

        String pdfText = extractTextFromPdf(uri);

        Intent intent = new Intent(this, ChooseActionActivity.class);
        intent.putExtra("userInput", pdfText);
        startActivity(intent);
    }
    /**
     * Extracts text from a PDF file given its URI.
     *
     * @param  uri  the URI of the PDF file
     * @return      the extracted text from the PDF file
     */
    private String extractTextFromPdf(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
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
   /**
    * Extracts text from the input stream.
    *
    * @param  inputStream  the input stream from which to extract text
    * @return              the extracted text, or null if an error occurs
    */
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

    /**
     * Process the PDF by starting the ChooseActionActivity.
     *
     * @param  view	The view parameter for processing the PDF
     */
    public void processPdf(View view) {
        Intent intent = new Intent(this, ChooseActionActivity.class);
        startActivity(intent);
    }
}