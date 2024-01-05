package com.oop2.textalayzer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EnterTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_text);
    }

    public void processText(View view) {
        EditText editText = findViewById(R.id.editText); // Ersetzen Sie yourEditTextId mit der tatsächlichen ID Ihres EditText
        String text = editText.getText().toString();

        Intent intent = new Intent(this, ChooseActionActivity.class);
        intent.putExtra("userInput", text); // Fügen Sie den Text als Extra zum Intent hinzu
        startActivity(intent);
    }
}
