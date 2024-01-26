package com.oop2.textalayzer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class represents an Android Activity where users can choose an action to
 * perform.
 * It uses the Singleton pattern to access the API client and makes API requests
 * based on user input.
 */
public class ChooseActionActivity extends AppCompatActivity {

    private ApiClient apiClient;
    private RadioGroup radioGroup;
    private String userInput;

    private ProgressDialog loadingDialog;

    /**
     * This method is called at creation time. It initializes the activity and sets
     * up listeners for user interactions.
     *
     * @param savedInstanceState the saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("Loading...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);

        apiClient = ApiClientInstance.getApiClient();
        radioGroup = findViewById(R.id.radioGroup);

        Intent intent = getIntent();
        userInput = intent.getStringExtra("userInput");
        initializeRadioButtons();

        Button btnExecuteAction = findViewById(R.id.btnExecuteAction);
        btnExecuteAction.setOnClickListener(new View.OnClickListener() {
            /**
             * This method handles the onClick event for the execute action button.
             * It retrieves the selected radio button, constructs an API request, and sends
             * it.
             *
             * @param v View object that was clicked
             */
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String choice = selectedRadioButton.getText().toString().toLowerCase();
                    if (choice == null || choice.isEmpty()) {
                        choice = "default";
                    }
                    loadingDialog.show();
                    ApiRequest request = new ApiRequest(userInput, choice, "gpt-4-1106-preview");

                    Call<ApiResponse> call = apiClient.getCompletion(request);

                    call.enqueue(new Callback<ApiResponse>() {
                        /**
                         * This method handles the API response. It dismisses the loading dialog, logs
                         * the response,
                         * and displays the result in the UI. If the request fails, it logs the failure
                         * and shows a toast message.
                         *
                         * @param call     the API call
                         * @param response the API response
                         * @return void
                         */
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            loadingDialog.dismiss();
                            if (response.isSuccessful()) {
                                // Loggen der API-Antwort
                                Log.d("API Response", response.toString());
                                Log.d("API Response", response.body().toString());
                                String choices = response.body().getContent();
                                Toast.makeText(ChooseActionActivity.this, choices, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ChooseActionActivity.this, DisplayResultActivity.class);
                                intent.putExtra("choices", choices);
                                startActivity(intent);
                            } else {
                                String error = "Fehler: " + response.code() + " " + response.message();
                                Log.e("API Error", error);
                                Toast.makeText(ChooseActionActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }

                        /**
                         * This method is called when the request could not be executed due to
                         * cancellation, a connectivity problem or timeout.
                         * It dismisses the loading dialog and logs the failure.
                         *
                         * @param call the call that was attempted
                         * @param t    the error encountered
                         * @return void
                         */
                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            loadingDialog.dismiss();
                            Log.e("API Failure", "Fehler bei der Anfrage", t);
                            Toast.makeText(ChooseActionActivity.this, "Netzwerkfehler", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(ChooseActionActivity.this, "Bitte wählen Sie eine Aktion aus.", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    /**
     * This method initializes the radio buttons with the given action options.
     * Each option is represented by a radio button in the radio group.
     *
     */
    private void initializeRadioButtons() {
        String[] actionOptions = { "Zusammenfassen", "Inhalt analysieren", "Stimmung analysieren" };
        for (String option : actionOptions) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setTextSize(12);
            radioButton.setText(option);
            radioGroup.addView(radioButton);
        }
    }
}
