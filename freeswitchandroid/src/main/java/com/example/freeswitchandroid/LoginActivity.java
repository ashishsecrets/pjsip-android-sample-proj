package com.example.freeswitchandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

    }

    public void otpLoginClick(View view) {
        Intent intent = new Intent ( LoginActivity.this, OtpActivity.class );
        EditText editText = findViewById(R.id.editTextPhone);
        CountryCodePicker countryCodePicker = findViewById(R.id.countryPicker);
        SharedPreferences.Editor editor = getSharedPreferences("USER_DATA", MODE_PRIVATE).edit();
        editor.putString("username", editText.getText().toString());
        editor.putInt("country", countryCodePicker.getSelectedCountryCodeAsInt());
        editor.apply();
        startActivity(intent);
    }
}