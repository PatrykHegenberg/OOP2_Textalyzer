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

    /**
     * A method called when the activity is first created.
     *
     * @param  savedInstanceState	the saved state of the activity
     * @return         	void, no return value
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * loadPdfScreen function loads the Pdf screen.
     *
     * @param  view	the view to load the Pdf screen on
     * @return     	void, no return value
     */
    public void loadPdfScreen(View view) {
        Intent intent = new Intent(this, LoadPdfActivity.class);
        startActivity(intent);
    }

    /**
     * A method for entering the text screen.
     *
     * @param  view	The view parameter for the screen
     */
    public void enterTextScreen(View view) {
        Intent intent = new Intent(this, EnterTextActivity.class);
        startActivity(intent);
    }
}
