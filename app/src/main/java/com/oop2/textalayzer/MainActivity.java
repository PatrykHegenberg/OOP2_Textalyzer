package com.oop2.textalayzer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class represents the main activity of the application.
 * It extends AppCompatActivity, which is a base class for activities that use
 * the support library action bar features.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This method is called when the activity is first created.
     * It sets the content view to the layout file associated with this activity.
     *
     * @param savedInstanceState the saved state of the activity, used to restore
     *                           the previous state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is triggered when the PDF screen button is clicked.
     * It starts the LoadPdfActivity, which presumably displays a PDF document.
     *
     * @param view the view that was clicked to trigger this method
     */
    public void loadPdfScreen(View view) {
        Intent intent = new Intent(this, LoadPdfActivity.class);
        startActivity(intent);
    }

    /**
     * This method is triggered when the text entry screen button is clicked.
     * It starts the EnterTextActivity, which presumably allows the user to enter
     * text.
     *
     * @param view the view that was clicked to trigger this method
     */
    public void enterTextScreen(View view) {
        Intent intent = new Intent(this, EnterTextActivity.class);
        startActivity(intent);
    }
}
