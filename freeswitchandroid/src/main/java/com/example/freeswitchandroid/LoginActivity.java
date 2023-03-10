package com.example.freeswitchandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        intent.putExtra( "username", editText.getText().toString() );
        startActivity(intent);
    }
}