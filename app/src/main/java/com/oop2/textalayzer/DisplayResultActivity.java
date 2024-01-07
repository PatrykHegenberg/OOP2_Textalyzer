package com.oop2.textalayzer;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayResultActivity extends AppCompatActivity {

    private String choises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        choises = getIntent().getStringExtra("choises");
        Log.d("Response", "Reponse: " + choises);
        // Retrieve and display API response in TextView or other UI elements
    }
}
