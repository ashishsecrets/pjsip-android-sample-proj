package com.example.freeswitchandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//        SipAccountData sipAccountData = new SipAccountData();
//        sipAccountData.setUsername("");
//        sipAccountData.setRealm("sip.linphone.org");
//        sipAccountData.setPassword("");
//        sipAccountData.setHost("sip.linphone.org");
//
//        //Toast.makeText(this, SipServiceCommand.setAccount(this, sipAccountData),
//        //Toast.LENGTH_LONG).show();
//
//        Context context = this;
//
//        String uri = SipServiceCommand.setAccount(context, sipAccountData);
//        SipServiceCommand.start(context);
//
//
//        Toast.makeText(this, "Receiving a call !",
//                Toast.LENGTH_LONG).show();
//
//        SipServiceCommand.makeCall(this, uri, "sip:@sip.linphone.org", false, false, false);
    }
}