package com.oop2.textalayzer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class represents an Android Activity where users can enter text.
 * It extends AppCompatActivity, which is a common base class for activities
 * that use the support library action bar features.
 */
public class EnterTextActivity extends AppCompatActivity {

    /**
     * This method is called when the activity is first created.
     * It sets the content view to the layout file 'activity_enter_text'.
     *
     * @param savedInstanceState the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     *                           This is used to restore the state of the activity
     *                           after it was previously destroyed.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_text);
    }

    /**
     * This method processes the text entered by the user in the EditText field.
     * It retrieves the text, creates an Intent for the ChooseActionActivity, adds
     * the user's input as an extra in the intent,
     * and then starts the ChooseActionActivity.
     *
     * @param view the view containing the text to be processed. This is typically
     *             the button that triggers this method.
     */
    public void processText(View view) {
        EditText editText = findViewById(R.id.editText);
        String text = editText.getText().toString();

        Intent intent = new Intent(this, ChooseActionActivity.class);
        intent.putExtra("userInput", text);
        startActivity(intent);
    }
}
