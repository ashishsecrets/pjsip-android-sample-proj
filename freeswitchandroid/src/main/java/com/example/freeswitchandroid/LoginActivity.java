package com.example.freeswitchandroid;

import static com.example.freeswitchandroid.ServiceCommunicator.permissionsDone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        if(!permissionsDone) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Permissions")
                    .setMessage("Please enable Appear on Top Permission in settings")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                            dialog.dismiss();
                            permissionsDone = true;
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_menu_call)
                    .show();
        }

    }

    public void otpLoginClick(View view) {
        EditText editText = findViewById(R.id.editTextPhone);
        if(!editText.getText().toString().isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
            CountryCodePicker countryCodePicker = findViewById(R.id.countryPicker);
            SharedPreferences.Editor editor = getSharedPreferences("USER_DATA", MODE_PRIVATE).edit();
            editor.putString("username", editText.getText().toString());
            editor.putInt("country", countryCodePicker.getSelectedCountryCodeAsInt());
            editor.apply();
            startActivity(intent);
        }
        else{
            Toast.makeText(LoginActivity.this, "Please enter your number first without + or 0.", Toast.LENGTH_SHORT).show();
        }
    }
}