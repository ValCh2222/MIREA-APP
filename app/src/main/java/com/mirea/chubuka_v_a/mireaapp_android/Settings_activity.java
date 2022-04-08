package com.mirea.chubuka_v_a.mireaapp_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings_activity extends AppCompatActivity {
    private EditText text;
    private TextView texT;
    private SharedPreferences preferences;
    final String SAVED_NAME = "saved_name";
    public File file = new File("name") ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        String name = findViewById(R.id.textView10).toString();
        texT = findViewById(R.id.textView10);

        text = findViewById(R.id.textView10);


        preferences = getPreferences(MODE_PRIVATE);
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
    public void onMyButtonClick3_Save(View view)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SAVED_NAME, text.getText().toString());
        editor.apply();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }
    public void onMyButtonClick4_Show(View view)
    {
        String name = preferences.getString(SAVED_NAME, "Empty");
        texT.setText(name);
        TextView textView = findViewById(R.id.textView8);
        textView.setText(name);

    }
}