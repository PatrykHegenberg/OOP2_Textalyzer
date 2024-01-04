package com.oop2.textalayzer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EnterTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_text);
    }

    public void processText(View view) {
        // Implement text processing logic
        Intent intent = new Intent(this, ChooseActionActivity.class);
        startActivity(intent);
    }
}
