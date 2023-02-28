package com.example.freeswitchandroid;

import static net.gotev.sipservice.SipTlsUtils.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.CodecPriority;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipAccountTransport;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjmedia_srtp_use;
import org.pjsip.pjsua2.pjsip_inv_state;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CallsActivity extends AppCompatActivity{

    ServiceCommunicator serviceCommunicator;
    String accountID;
    int callID;
    String displayName;
    String remoteUri;
    boolean isVideo;

    Button answer;

    Button hangup;
    Button call;
    Button hold;
    Button transfer;

    TextView tv;

    Context context;
    boolean isHold = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Context context = this;
        SipServiceCommand.enableSipDebugLogging(true);
        serviceCommunicator = new ServiceCommunicator();
        serviceCommunicator.startService(activityManager, context);
        tv = (TextView) findViewById(R.id.textView1);
        tv.setMovementMethod(new ScrollingMovementMethod());

        mReceiver.register(this);

        this.context = this;
        call = findViewById(R.id.call);
        answer = findViewById(R.id.answer);
        hangup = findViewById(R.id.hangup);
        hold = findViewById(R.id.hold);
        transfer = findViewById(R.id.transfer);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReceiver.unregister(this);
    }


    public void showLogsOnPhone(View view){
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
            }
            addMessage(String.valueOf(log));
        } catch (IOException e) {
            // Handle Exception
        }
    }

    private void addMessage(String msg) {
        // append the new string
        tv.append(msg + "\n");
        // find the amount we need to scroll.  This works by
        // asking the TextView's internal layout for the position
        // of the final line and then subtracting the TextView's height
        final int scrollAmount = tv.getLayout().getLineTop(tv.getLineCount()) - tv.getHeight();
        // if there is no need to scroll, scrollAmount will be <=0
        if (scrollAmount > 0)
            tv.scrollTo(0, scrollAmount);
        else
            tv.scrollTo(0, 0);
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
        call.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.light_blue_900)));
        Toast.makeText(this, "Making a call !",
                Toast.LENGTH_LONG).show();

//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//
//        audioManager.setSpeakerphoneOn(false);


    }

    public void answer(View view){

        SipServiceCommand.acceptIncomingCall(this, accountID, String.valueOf(callID), isVideo);

        answer.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.teal_700)));
        hangup.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.red)));

        Toast.makeText(this, "Receiving a call !",
                Toast.LENGTH_LONG).show();

//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//
//        audioManager.setSpeakerphoneOn(false);

    }

    public void hangUp(View view){

        SipServiceCommand.hangUpActiveCalls(this, serviceCommunicator.uri);

        Toast.makeText(this, "Hanging up !",
                Toast.LENGTH_LONG).show();

        answer.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.teal_700)));
        hangup.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.light_blue_A200)));
        call.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.light_blue_600)));

//        new CountDownTimer(1000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//            }
//
//            public void onFinish() {
//                hangup.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.red)));
//            }
//
//        }.start();

//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//
//        audioManager.setSpeakerphoneOn(false);

    }

    public void hold(View view){

        if(!isHold) {

            SipServiceCommand.holdActiveCalls(this, serviceCommunicator.uri);

            hold.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.cardview_dark_background)));

            Toast.makeText(this, "Putting call on hold !",
                    Toast.LENGTH_LONG).show();

        }
          else if(isHold) {

                if(serviceCommunicator.sipAccountData.getCallId() == null || serviceCommunicator.sipAccountData.getCallId().length() < 2){
                    serviceCommunicator.sipAccountData.setCallId(String.valueOf(this.callID));
                }

                SipServiceCommand.setCallHold(this, serviceCommunicator.uri, Integer.parseInt(serviceCommunicator.sipAccountData.getCallId()), false);

                hold.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.grey)));

                Toast.makeText(this, "Unholding call !",
                        Toast.LENGTH_LONG).show();

            }
          isHold = !isHold;


//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//
//        audioManager.setSpeakerphoneOn(false);

    }

    BroadcastEventReceiver mReceiver = new BroadcastEventReceiver()

    {

    @Override
    public void onRegistration(String accountID, int registrationStateCode) {
        super.onRegistration(accountID, registrationStateCode);
        Log.i(TAG, "onRegistration: ");

        if(registrationStateCode == 200){
            Toast.makeText(context, "New Registration successful", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Registration code：" + registrationStateCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCallState(String accountID, int callID, int callStateCode, int callStatusCode, long connectTimestamp) {
        super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp);

        if(callStateCode == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED){
            call.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.light_blue_600)));
            answer.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.teal_700)));
            hangup.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.light_blue_A200)));
            hold.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.grey)));
            Toast.makeText(context, "Call State Disconnected", Toast.LENGTH_SHORT).show();

        }

        if(callStateCode == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED){
            call.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.light_blue_600)));
            answer.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.teal_700)));
            hangup.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.red)));
            hold.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.grey)));
            Toast.makeText(context, "Call State Disconnected", Toast.LENGTH_SHORT).show();

        }

//        Logger.debug(LOG_TAG, "onCallState - accountID: " + getValue(getReceiverContext(), accountID) +
//                ", callID: " + callID +
//                ", callStateCode: " + callStateCode +
//                ", callStatusCode: " + callStatusCode +
//                ", connectTimestamp: " + connectTimestamp);
    }


        @Override
        public void onIncomingCall (String accountID,int callID, String displayName, String
        remoteUri,boolean isVideo){
        super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo);

             CallsActivity.this.accountID = accountID;
             CallsActivity.this.callID = callID;
             CallsActivity.this.displayName = displayName;
             CallsActivity.this.remoteUri = remoteUri;
             CallsActivity.this.isVideo = isVideo;

            answer.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.teal_200)));
    }

        @Override
        public void onOutgoingCall (String accountID,int callID, String number,boolean isVideo,
        boolean isVideoConference, boolean isTransfer){
        super.onOutgoingCall(accountID, callID, number, isVideo, isVideoConference, isTransfer);
        //CallActivity.startActivityOut(getReceiverContext(), accountID, callID, number, isVideo, isVideoConference);
        }
    };
}