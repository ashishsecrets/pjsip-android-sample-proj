package com.example.freeswitchandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

public class OtpActivity extends AppCompatActivity {

    boolean isOTPEntered = false;
    OtpTextView otpTextView;
    CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        layout = findViewById(R.id.layout_otp);
        otpTextView = findViewById(R.id.otp_view);
        otpTextView.requestFocusOTP();

//        Retrofit retrofit = RetrofitData.getRetrofit();
//
//        retrofitAPI = retrofit.create(PressOneAPI.class);

        SharedPreferences shared = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        String mobileNumber = shared.getString("username", "");

        //mobileNumber = mobileNumber.replaceFirst("^0+(?!$)", "");

        String countryCode = String.valueOf(shared.getInt("country", 91));

        TextView mobileText = findViewById(R.id.text_number);
        mobileText.setText(mobileNumber);

//        mobile = new Mobile("+" + countryCode + mobileNumber);
//        // calling a method to create a post and passing our modal class.
//        Call<Void> call = retrofitAPI.getMyData(mobile);

        // on below line we are executing our method.
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                // this method is called when we get response from our api.
//                Snackbar.make(layout, response.message(), Snackbar.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                // we get error response from API.
//                Snackbar.make(layout, t.toString(), Snackbar.LENGTH_SHORT).show();
//            }
//        });

        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {
                // fired when user types something in the Otpbox
            }

            @Override
            public void onOTPComplete(String otp) {
                // fired when user has entered the OTP fully.
                isOTPEntered = true;

            }

        });
    }

    public void otpVerifyClick(View view) {
//        if(isOTPEntered){
//
//            MobileData mobileData = new MobileData(mobile.getMobile(), otpTextView.getOTP());
//
//            Call<Token> call2 = retrofitAPI.getAuthToken(mobileData);
//
//            call2.enqueue(new Callback<Token>() {
//                @Override
//                public void onResponse(Call<Token> call, Response<Token> response) {
//                    Token responseFromAPI = response.body();
//                    assert responseFromAPI != null;
//                    SharedPreferences.Editor editor = getSharedPreferences("USER_DATA", MODE_PRIVATE).edit();
//                    editor.putString("token", responseFromAPI.getToken());
//                    editor.apply();
//
//                    if(response.code() == 200){
//                        Intent intent = new Intent(OtpActivity.this, CallsActivity.class);
//                        startActivity(intent);
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<Token> call, Throwable t) {
//                    otpTextView.setOTP("");
//                    isOTPEntered = false;
//                    Toast.makeText(OtpActivity.this, "Incorrect OTP or Number", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
//                    startActivity(intent);
//
//                }
//            });
//        }
        Intent intent = new Intent(OtpActivity.this, CallsActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        otpTextView.setOTP("");
        isOTPEntered = false;
    }
}