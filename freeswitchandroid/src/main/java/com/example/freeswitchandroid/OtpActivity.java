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

    boolean isOTPEntered = false;
    OtpTextView otpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        otpTextView = findViewById(R.id.otp_view);

        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {
                // fired when user types something in the Otpbox
            }
            @Override
            public void onOTPComplete(String otp) {
                // fired when user has entered the OTP fully.
                isOTPEntered = true;
                Intent intent = new Intent(OtpActivity.this, CallsHistory.class);
                startActivity(intent);
            }
        });


    }

    public void otpVerifyClick(View view) {
        if(isOTPEntered){
            Intent intent = new Intent(OtpActivity.this, CallsHistory.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        otpTextView.setOTP("");
        isOTPEntered = false;
    }
}