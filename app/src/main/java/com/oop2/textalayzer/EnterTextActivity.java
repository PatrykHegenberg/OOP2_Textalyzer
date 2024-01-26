package com.oop2.textalayzer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * A class that represents an activity for entering text.
 */
public class EnterTextActivity extends AppCompatActivity {

    /**
     * onCreate method called when the activity is starting. 
     *
     * @param  savedInstanceState	the data it most recently supplied in onSaveInstanceState(Bundle). 
     * @return         	void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_text);
    }

    /**
     * Process the text from the given view, and start the ChooseActionActivity with the
     * user's input as an extra in the intent.
     *
     * @param  view  the view containing the text to be processed
     * @return       void
     */
    public void processText(View view) {
        EditText editText = findViewById(R.id.editText);
        String text = editText.getText().toString();

        Intent intent = new Intent(this, ChooseActionActivity.class);
        intent.putExtra("userInput", text);
        startActivity(intent);
    }
}
