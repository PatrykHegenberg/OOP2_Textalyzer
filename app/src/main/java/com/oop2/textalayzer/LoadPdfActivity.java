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

        // Implement the logic to process the selected PDF file here
    }

    public void processPdf(View view) {
        // Implement the logic to process the selected PDF file here
        Intent intent = new Intent(this, ChooseActionActivity.class);
        startActivity(intent);
    }
}
