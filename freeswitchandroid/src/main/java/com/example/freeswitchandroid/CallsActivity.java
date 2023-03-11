package com.example.freeswitchandroid;

import static net.gotev.sipservice.SipTlsUtils.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjsip_inv_state;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CallsActivity extends AppCompatActivity{

    ServiceCommunicator serviceCommunicator;
    String accountID;
    int callID1;
    int callID2;
    String displayName;
    String remoteUri;
    boolean isVideo;

    ImageButton answer;

    ImageButton hangup;
    ImageButton hold;
    ImageButton transfer;
    EditText number;
    Context context;
    boolean isHold = false;

    /////////////// Adding Final UI Items ////////////////

    //dial_pad1 dial pad to dial numbers - linear layout //Visibility Toggle
    // linearLayout1 for ongoing call time and linearLayout2 for call options //Visibility Toggle
    // dtmf_keypad linear layout //Visibility Toggle
    // call_options linear layout //Visibility Toggle
    // call_horizontal_layout linear layout //Visibility Toggle

    LinearLayout dialPad1Layout;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout dtmfKeyPadLayout;
    LinearLayout callOptionsLayout;
    LinearLayout callHorizontalLayout;

    ImageButton deleteBtn;
    TextView callTime; //Visibility Toggle
    TextView tvName; //Caller's name
    TextView tvNumber; //Caller's number
    ImageButton muteBtn; //Highlight on press toggle
    ImageButton speakerBtn; //Highlight on press toggle
    ImageButton keypadBtn; //Highlight on press toggle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);

        // Removing action bar

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Context context = this;
        SipServiceCommand.enableSipDebugLogging(true);

        Intent i = getIntent();
        String username = i.getStringExtra ( "username" );

        serviceCommunicator = new ServiceCommunicator();
        serviceCommunicator.username = username;
        serviceCommunicator.startService(activityManager, context);
        number = findViewById(R.id.number);
        mReceiver.register(this);

        this.context = this;

        answer = findViewById(R.id.call);
        hangup = findViewById(R.id.hangUp);
        hold = findViewById(R.id.hold);
        transfer = findViewById(R.id.transfer);
        deleteBtn = findViewById(R.id.delete);
        callTime = findViewById(R.id.call_time);
        tvName = findViewById(R.id.tv_name);
        tvNumber = findViewById(R.id.tv_number);
        muteBtn = findViewById(R.id.mute);
        speakerBtn = findViewById(R.id.speaker);
        keypadBtn = findViewById(R.id.keypad);
        dialPad1Layout = findViewById(R.id.dial_pad1);
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        dtmfKeyPadLayout = findViewById(R.id.dtmf_keypad);
        callOptionsLayout = findViewById(R.id.call_options);
        callHorizontalLayout = findViewById(R.id.call_horizontal_layout);

        //Add default visibility at the start of activity.

         dialPad1Layout.setVisibility(View.VISIBLE);
         linearLayout1.setVisibility(View.GONE);
         linearLayout2.setVisibility(View.GONE);
         dtmfKeyPadLayout.setVisibility(View.GONE);
         callOptionsLayout.setVisibility(View.GONE);
         callHorizontalLayout.setVisibility(View.VISIBLE);
         answer.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReceiver.unregister(this);
    }


    /////////////////////////////////////// UI Functions /////////////////////////////////////////////////

    public void onePressed(View v){

    }

    public void deleteBtnPressed(View v){

    }

    //////////////////////////////////////// Calls related functions below(Call Options) //////////////////////////////////////////////////////////

    public void terminate(View v){

    }

    public void speaker(View v){

    }

    public void mute(View v){

    }

    public void keypad(View v){
        if(dtmfKeyPadLayout.getVisibility() == View.GONE) {

            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.VISIBLE);
            callOptionsLayout.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);

            keypadBtn.setImageResource(R.drawable.keypad_active);
        }
        else{
            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);

            keypadBtn.setImageResource(R.drawable.keypad);
        }
    }

    public void call(){

        String numberToCall = number.getText().toString();

        if(!numberToCall.isEmpty()) {
            SipServiceCommand.makeCall(this, serviceCommunicator.uri, "sip:" + numberToCall + "@" + serviceCommunicator.hostname, false, false, false);
            Toast.makeText(this, "Making a call !",
                    Toast.LENGTH_LONG).show();
        }
        //        call.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.light_blue_900)));


//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//
//        audioManager.setSpeakerphoneOn(false);


    }

    public void answer(View view){

        if(dialPad1Layout.getVisibility() == View.VISIBLE){
            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);

            //call();
        }

   ////     SipServiceCommand.acceptIncomingCall(this, accountID, String.valueOf(callID1), isVideo);

//        answer.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.teal_700)));
//        hangup.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.red)));

   ////     Toast.makeText(this, "Receiving a call !", Toast.LENGTH_LONG).show();

//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//
//        audioManager.setSpeakerphoneOn(false);

    }

    public void hangUp(View view){

        SipServiceCommand.hangUpActiveCalls(this, serviceCommunicator.uri);

        Toast.makeText(this, "Hanging up !",
                Toast.LENGTH_LONG).show();

//        answer.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.teal_700)));
//        hangup.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.light_blue_A200)));
//        call.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.light_blue_600)));

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
                    serviceCommunicator.sipAccountData.setCallId(String.valueOf(this.callID1));
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

    public void transfer(View view){

            if(!isHold) {

                SipServiceCommand.toggleCallHold(this, serviceCommunicator.uri, callID1);

                hold.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.cardview_dark_background)));

                Toast.makeText(this, "Holding before transfer !",
                        Toast.LENGTH_LONG).show();

                SipServiceCommand.makeCall(this, serviceCommunicator.uri, number.getText().toString(), true);
            }
            else if(isHold){

                SipServiceCommand.attendedTransferCall(this, serviceCommunicator.uri, callID1, callID2);

                Toast.makeText(this, "Making attended transfer !",
                        Toast.LENGTH_LONG).show();
            }

            isHold = !isHold;
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
//            call.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.light_blue_600)));
//            answer.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.teal_700)));
//            hangup.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.light_blue_A200)));
//            hold.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.grey)));
            Toast.makeText(context, "Call State Disconnected", Toast.LENGTH_SHORT).show();

        }

        if(callStateCode == pjsip_inv_state.PJSIP_INV_STATE_CONNECTING){
//            call.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.light_blue_600)));
//            answer.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.teal_700)));
//            hangup.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.red)));
//            hold.setBackgroundTintList(ColorStateList.valueOf(CallsActivity.this.getResources().getColor(R.color.grey)));

            CallsActivity.this.accountID = accountID;
            CallsActivity.this.callID1 = callID;
            CallsActivity.this.displayName = displayName;
            CallsActivity.this.remoteUri = remoteUri;
            CallsActivity.this.isVideo = isVideo;

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
             CallsActivity.this.callID1 = callID;
             CallsActivity.this.displayName = displayName;
             CallsActivity.this.remoteUri = remoteUri;
             CallsActivity.this.isVideo = isVideo;

            //answer.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.teal_200)));
    }

        @Override
        public void onOutgoingCall (String accountID,int callID, String number,boolean isVideo,
        boolean isVideoConference, boolean isTransfer){
        super.onOutgoingCall(accountID, callID, number, isVideo, isVideoConference, isTransfer);

            CallsActivity.this.accountID = accountID;
            CallsActivity.this.callID2 = callID;
            CallsActivity.this.displayName = displayName;
            CallsActivity.this.remoteUri = remoteUri;
            CallsActivity.this.isVideo = isVideo;

        }
    };



//    public void showLogsOnPhone(View view){
//        try {
//            Process process = Runtime.getRuntime().exec("logcat -d");
//            BufferedReader bufferedReader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//
//            StringBuilder log = new StringBuilder();
//            String line = "";
//            while ((line = bufferedReader.readLine()) != null) {
//                log.append(line);
//            }
//            addMessage(String.valueOf(log));
//        } catch (IOException e) {
//            // Handle Exception
//        }
//    }
//
//    private void addMessage(String msg) {
//        // append the new string
//        tv.append(msg + "\n");
//        // find the amount we need to scroll.  This works by
//        // asking the TextView's internal layout for the position
//        // of the final line and then subtracting the TextView's height
//        final int scrollAmount = tv.getLayout().getLineTop(tv.getLineCount()) - tv.getHeight();
//        // if there is no need to scroll, scrollAmount will be <=0
//        if (scrollAmount > 0)
//            tv.scrollTo(0, scrollAmount);
//        else
//            tv.scrollTo(0, 0);
//    }
}