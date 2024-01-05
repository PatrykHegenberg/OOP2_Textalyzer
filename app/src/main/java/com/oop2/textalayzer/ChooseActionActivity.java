package com.oop2.textalayzer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseActionActivity extends AppCompatActivity {

    private ApiClient apiClient;
    private RadioGroup radioGroup;
    private String userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);

        apiClient = ApiClientInstance.getApiClient();
        radioGroup = findViewById(R.id.radioGroup);

        Intent intent = getIntent();
        userInput = intent.getStringExtra("userInput"); // Holt den übertragenen Text

        initializeRadioButtons();

        Button btnExecuteAction = findViewById(R.id.btnExecuteAction);
        btnExecuteAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String choice = selectedRadioButton.getText().toString().toLowerCase();
                    if (choice == null || choice.isEmpty()) {
                        choice = "default"; // Ein Standardwert, falls kein RadioButton ausgewählt ist
                    }
                    ApiRequest request = new ApiRequest(userInput, choice, "gpt-4-1106-preview");

                    // String jsonRequest = request.toJson();

                    Call<ApiResponse> call = apiClient.getCompletion(request);

                    call.enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful()) {
                                String choices = response.body().getChoices();
                                Toast.makeText(ChooseActionActivity.this, choices, Toast.LENGTH_SHORT).show();
                            } else {
                                // Loggen des Statuscodes und der Fehlernachricht
                                String error = "Fehler: " + response.code() + " " + response.message();
                                Log.e("API Error", error);
                                Toast.makeText(ChooseActionActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            Toast.makeText(ChooseActionActivity.this, "Netzwerkfehler", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(ChooseActionActivity.this, "Bitte wählen Sie eine Aktion aus.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeRadioButtons() {
        // Hier RadioButtons dynamisch erstellen und zur RadioGroup hinzufügen
        // Beispiel:
        String[] actionOptions = {"Zusammenfassen", "Inhalt analysieren", "Stimmung analysieren:"};

        for (String option : actionOptions) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioGroup.addView(radioButton);
        }
    }
}
