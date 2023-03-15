package com.example.freeswitchandroid;

import static net.gotev.sipservice.SipTlsUtils.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
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

    String accountID;
    int callID1;
    int callID2;
    String displayName;
    String remoteUri;
    boolean isVideo;

    boolean isMute = false;

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

    LinearLayout overlayTransferLayout;

    ImageButton deleteBtn;
    TextView callTime; //Visibility Toggle
    TextView tvName; //Caller's name
    TextView tvNumber; //Caller's number
    ImageButton muteBtn; //Highlight on press toggle
    ImageButton speakerBtn; //Highlight on press toggle
    ImageButton keypadBtn; //Highlight on press toggle

    AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);

        // Removing action bar

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        this.context = this;

        String toCall = getIntent().getStringExtra("callNumber");

        mReceiver.register(this);

        number = findViewById(R.id.number);
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
        overlayTransferLayout = findViewById(R.id.overlay_transfer_layout);
        //Add default visibility at the start of activity.

         dialPad1Layout.setVisibility(View.VISIBLE);
         linearLayout1.setVisibility(View.GONE);
         linearLayout2.setVisibility(View.GONE);
         dtmfKeyPadLayout.setVisibility(View.GONE);
         callOptionsLayout.setVisibility(View.GONE);
         callHorizontalLayout.setVisibility(View.VISIBLE);
         overlayTransferLayout.setVisibility(View.GONE);
        answer.setVisibility(View.VISIBLE);

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        if(toCall != null && !toCall.isEmpty())number.setText(toCall);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReceiver.unregister(this);
    }


    /////////////////////////////////////// UI Functions /////////////////////////////////////////////////

    public void onePressed(View v){
        number.append("1");
    }

    public void twoPressed(View v){
        number.append("2");
    }

    public void threePressed(View v){
        number.append("3");
    }

    public void fourPressed(View v){
        number.append("4");
    }

    public void fivePressed(View v){
        number.append("5");
    }

    public void sixPressed(View v){
        number.append("6");
    }

    public void sevenPressed(View v){
        number.append("7");
    }

    public void eightPressed(View v){
        number.append("8");
    }

    public void ninePressed(View v){
        number.append("9");
    }

    public void zeroPressed(View v){
        number.append("0");
    }

    public void starPressed(View v){
        number.append("*");
    }

    public void hashPressed(View v){
        number.append("#");
    }

    public void dtmf1Pressed(View v){

    }

    public void dtmf2Pressed(View v){

    }

    public void dtmf3Pressed(View v){

    }

    public void dtmf4Pressed(View v){

    }

    public void dtmf5Pressed(View v){

    }

    public void dtmf6Pressed(View v){

    }

    public void dtmf7Pressed(View v){

    }

    public void dtmf8Pressed(View v){

    }

    public void dtmf9Pressed(View v){

    }

    public void dtmf0Pressed(View v){

    }

    public void dtmfStarPressed(View v){

    }

    public void dtmfHashPressed(View v){

    }

    public void deleteBtnPressed(View v){
        if (number.getText().length() > 0) {
            number.getText().delete(number.getText().length() - 1, number.getText().length());
        }
    }

    //////////////////////////////////////// Calls related functions below(Call Options) //////////////////////////////////////////////////////////

    public void terminate(View v){

        SipServiceCommand.hangUpActiveCalls(this, ServiceCommunicator.uri);

        Toast.makeText(this, "Hanging up !",
                Toast.LENGTH_LONG).show();

    }

    public void speaker(View v) {

        if (!audioManager.isSpeakerphoneOn()) {
            audioManager.setSpeakerphoneOn(true);
            speakerBtn.setImageResource(R.drawable.speaker_active);
        }
        else{
            audioManager.setSpeakerphoneOn(false);
            speakerBtn.setImageResource(R.drawable.speaker);
        }

    }

    public void mute(View v){

        if(!isMute) {
            SipServiceCommand.setCallMute(this, ServiceCommunicator.uri, callID1, true);
            muteBtn.setImageResource(R.drawable.mute_active);
            isMute = true;
        }
        else{
            SipServiceCommand.setCallMute(this, ServiceCommunicator.uri, callID1, false);
            muteBtn.setImageResource(R.drawable.mute);
            isMute = false;
        }


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
            SipServiceCommand.makeCall(this, ServiceCommunicator.uri, "sip:" + numberToCall + "@" + ServiceCommunicator.hostname, false, false, false);
            Toast.makeText(this, "Making a call !",
                    Toast.LENGTH_LONG).show();
        }

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

            call();
        }
        else if(callHorizontalLayout.getVisibility() == View.VISIBLE){

            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);

            SipServiceCommand.acceptIncomingCall(this, accountID, String.valueOf(callID1), isVideo);
            Toast.makeText(this, "Receiving a call !", Toast.LENGTH_LONG).show();

        }



    }

    public void hangUp(View view){



    }

    public void hold(View view){

        if(!isHold) {

            SipServiceCommand.holdActiveCalls(this, ServiceCommunicator.uri);

            hold.setImageResource(R.drawable.hold_active);

            Toast.makeText(this, "Putting call on hold !",
                    Toast.LENGTH_LONG).show();

        }
          else if(isHold) {

                if(ServiceCommunicator.sipAccountData.getCallId() == null || ServiceCommunicator.sipAccountData.getCallId().length() < 2){
                    ServiceCommunicator.sipAccountData.setCallId(String.valueOf(this.callID1));
                }

                SipServiceCommand.setCallHold(this, ServiceCommunicator.uri, Integer.parseInt(ServiceCommunicator.sipAccountData.getCallId()), false);

                hold.setImageResource(R.drawable.hold);


            Toast.makeText(this, "Unholding call !",
                        Toast.LENGTH_LONG).show();

            }
          isHold = !isHold;

    }

    public void transfer(View view){

        if(overlayTransferLayout.getVisibility() == View.GONE) {

            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);
            overlayTransferLayout.setVisibility(View.VISIBLE);

        }
        else{
            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.GONE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);

            overlayTransferLayout.setVisibility(View.GONE);

            if(!isHold) {

                SipServiceCommand.toggleCallHold(this, ServiceCommunicator.uri, callID1);

                hold.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.cardview_dark_background)));

                Toast.makeText(this, "Holding before transfer !",
                        Toast.LENGTH_LONG).show();

                SipServiceCommand.makeCall(this, ServiceCommunicator.uri, "9037217611", true);
            }
            else if(isHold){

                SipServiceCommand.attendedTransferCall(this, ServiceCommunicator.uri, callID1, callID2);

                Toast.makeText(this, "Making attended transfer !",
                        Toast.LENGTH_LONG).show();
            }

            isHold = !isHold;
        }


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
            dialPad1Layout.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.GONE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.GONE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.VISIBLE);
            audioManager.setMode(AudioManager.MODE_NORMAL);
        }

        if(callStateCode == pjsip_inv_state.PJSIP_INV_STATE_CONNECTING){
            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);

            CallsActivity.this.accountID = accountID;
            CallsActivity.this.callID1 = callID;
            CallsActivity.this.displayName = displayName;
            CallsActivity.this.remoteUri = remoteUri;
            CallsActivity.this.isVideo = isVideo;

            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);

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

            callHorizontalLayout.setVisibility(View.VISIBLE);
            hangup.setVisibility(View.VISIBLE);
            answer.setVisibility(View.VISIBLE);

             CallsActivity.this.accountID = accountID;
             CallsActivity.this.callID1 = callID;
             CallsActivity.this.displayName = displayName;
             CallsActivity.this.remoteUri = remoteUri;
             CallsActivity.this.isVideo = isVideo;

            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            callTime.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.GONE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.GONE);

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