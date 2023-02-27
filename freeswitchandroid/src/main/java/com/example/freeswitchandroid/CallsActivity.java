package com.example.freeswitchandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.gotev.sipservice.CodecPriority;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipAccountTransport;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjmedia_srtp_use;

import java.util.ArrayList;
import java.util.List;

public class CallsActivity extends AppCompatActivity{

    ServiceCommunicator serviceCommunicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Context context = this;
        SipServiceCommand.enableSipDebugLogging(true);
        serviceCommunicator = new ServiceCommunicator();
        serviceCommunicator.startService(activityManager, context);

    }

    public void call(View view){

        String numberToCall = null;

        if(serviceCommunicator.username.equals("5294241166")){
            numberToCall = "1911899877";
        }
        else if(serviceCommunicator.username.equals("1911899877")){
            numberToCall = "09056925668";
        }


        SipServiceCommand.makeCall(this, serviceCommunicator.uri, "sip:" + numberToCall + "@" + serviceCommunicator.hostname, false, false, false);

        Toast.makeText(this, "Making a call !",
                Toast.LENGTH_LONG).show();

//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//
//        audioManager.setSpeakerphoneOn(false);


    }

    public void answer(View view){

        SipServiceCommand.acceptIncomingCall(this, serviceCommunicator.uri, serviceCommunicator.sipAccountData.getCallId(), false);

        Toast.makeText(this, "Receiving a call !",
                Toast.LENGTH_LONG).show();

//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//
//        audioManager.setSpeakerphoneOn(false);

    }

    public void hangUp(View view){

        SipServiceCommand.hangUpCall(this, serviceCommunicator.uri, serviceCommunicator.sipAccountData.getCallId());

        Toast.makeText(this, "Receiving a call !",
                Toast.LENGTH_LONG).show();

//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//
//        audioManager.setSpeakerphoneOn(false);

    }

}