// DisplayResultActivity.java
package com.oop2.textalayzer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayResultActivity extends AppCompatActivity {

    private String choices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        Intent intent = getIntent();
        choices = intent.getStringExtra("choices");
        Log.d("DisplayResultActivity", "Received choices: " + choices); // Überprüfe die Log-Ausgabe

        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText(choices);

        Button backToMainButton = findViewById(R.id.backToMainButton);
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    public void backToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Optional, depending on your navigation requirements
    }
}
