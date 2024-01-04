package com.oop2.textalayzer;


// ChooseActionActivity.java

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseActionActivity extends AppCompatActivity {

    private ApiClient apiClient;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);

        apiClient = ApiClientInstance.getApiClient();
        radioGroup = findViewById(R.id.radioGroup);

        // Hier RadioButton-Elemente initialisieren
        initializeRadioButtons();

        Button btnExecuteAction = findViewById(R.id.btnExecuteAction);
        btnExecuteAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String prompt = selectedRadioButton.getText().toString();

                    ApiRequest request = new ApiRequest(prompt);

                    Call<ApiResponse> call = apiClient.getCompletion(request);
                    call.enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful()) {
                                // Verarbeite die Antwort hier
                                String choices = response.body().getChoices();
                                // ...
                                Toast.makeText(ChooseActionActivity.this, choices, Toast.LENGTH_SHORT).show();
                            } else {
                                // Fehlerbehandlung
                                Toast.makeText(ChooseActionActivity.this, "Fehler bei der API-Anfrage", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            // Fehlerbehandlung
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
