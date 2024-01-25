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

public class DisplayResultActivity extends AppCompatActivity {

    private String choices;
    private ProgressDialog loadingDialog;

    /**
     * onCreate method for initializing the activity and setting up the UI components.
     *
     * @param  savedInstanceState  the saved instance state
     * @return                   void
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
             * onClick method for handling click events.
             *
             * @param  v    the view that was clicked
             * @return      void, no return value
             */
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
        shareButton.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method for handling click events.
             *
             * @param  v   the view that was clicked
             * @return     void, no return value
             */
            @Override
            public void onClick(View v) {
                shareContent();
            }
        });
    }

    /**
     * Method to navigate back to the main activity.
     *
     */
    public void backToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    /**
     * A method to share content using an Intent.
     */
    public void shareContent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, choices);
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}
