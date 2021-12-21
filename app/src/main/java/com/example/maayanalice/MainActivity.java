package com.example.maayanalice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    NavController navigationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment nav_host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.baseNavhost);
        try {
            navigationController = nav_host.getNavController();
            NavigationUI.setupActionBarWithNavController(this,navigationController);
        } catch(Exception e) {
            Log.d("ERROR", e.getMessage());
        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                navigationController.navigateUp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}