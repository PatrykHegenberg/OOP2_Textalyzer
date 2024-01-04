package com.oop2.textalayzer;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.oop2.textalayzer.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadPdfScreen(View view) {
        Intent intent = new Intent(this, LoadPdfActivity.class);
        startActivity(intent);
    }

    public void enterTextScreen(View view) {
        Intent intent = new Intent(this, EnterTextActivity.class);
        startActivity(intent);
    }
}
