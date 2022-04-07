package com.mirea.chubuka_v_a.mireaapp_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Settings_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        String name = findViewById(R.id.textView10).toString();
    }
    public void onMyButtonClick(View view)
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // выводим сообщение
        Toast.makeText(this, "смена темы", Toast.LENGTH_SHORT).show();
    }
    public void onMyButtonClick2(View view)
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        // выводим сообщение
        Toast.makeText(this, "смена темы", Toast.LENGTH_SHORT).show();
    }
}