package com.example.freeswitchandroid;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;


public class OtpActivity extends AppCompatActivity {

    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        actionBar.setTitle("");
        actionBar.show();

        OtpTextView otpTextView;
        otpTextView = findViewById(R.id.otp_view);

        Intent i = getIntent();
        text = i.getStringExtra( "username" );

        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {
                // fired when user types something in the Otpbox
            }
            @Override
            public void onOTPComplete(String otp) {
                // fired when user has entered the OTP fully.
                Toast.makeText(OtpActivity.this, "The OTP is " + otp,  Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void otpVerifyClick(View view) {
        Intent intent = new Intent(OtpActivity.this, CallsHistory.class);
        intent.putExtra ( "username", text);
        startActivity(intent);
    }



}