// DisplayResultActivity.java
package com.oop2.textalayzer;

import android.content.Intent;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class represents an Android Activity that displays the results of the
 * Analysis task.
 * It extends AppCompatActivity, which is a common base class for activities
 * that use the support library action bar features.
 */
public class DisplayResultActivity extends AppCompatActivity {

    private String choices;
    private ProgressDialog loadingDialog;

    /**
     * This method is called at creation time for the activity. It sets up the user
     * interface and retrieves any data passed into the activity.
     *
     * @param savedInstanceState the saved instance state, used to restore the
     *                           previous state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("Loading...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        Intent intent = getIntent();
        choices = intent.getStringExtra("choices");
        Log.d("DisplayResultActivity", "Received choices: " + choices);

        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText(choices);

        Button backToMainButton = findViewById(R.id.backToMainButton);
        Button shareButton = findViewById(R.id.shareButton);
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the back button is clicked.
             * It navigates the user back to the main activity.
             *
             * @param v the view that was clicked
             */
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
        shareButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the share button is clicked.
             * It shares the content of the 'choices' variable.
             *
             * @param v the view that was clicked
             */
            @Override
            public void onClick(View v) {
                shareContent();
            }
        });
    }

    /**
     * This method navigates the user back to the main activity.
     * It creates a new Intent for the MainActivity and starts it.
     */
    public void backToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * This method shares the content of the 'choices' variable.
     * It creates a new Intent with the ACTION_SEND action and the text to be
     * shared, then starts an activity chooser.
     */
    public void shareContent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, choices);
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}
