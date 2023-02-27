package com.example.freeswitchandroid;

import static net.gotev.sipservice.ObfuscationHelper.getValue;
import static net.gotev.sipservice.SipTlsUtils.TAG;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.Logger;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipAccountTransport;
import net.gotev.sipservice.SipService;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjmedia_srtp_use;
import org.pjsip.pjsua2.pjsip_status_code;

public class ServiceCommunicator extends BroadcastEventReceiver {

    String uri;
    String username;
    String password;
    SipAccountData sipAccountData;
    String hostname = "david380.fs1.pressone.co";

    private boolean foregroundServiceRunning(ActivityManager activityManager){
        for(ActivityManager.RunningServiceInfo service: activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if(SipService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void startService(ActivityManager activityManager, Context context){
        if(!foregroundServiceRunning(activityManager)) {
            sipAccountData = new SipAccountData();
            //EditText editText = (EditText) findViewById(R.id.number);
            username = "5294241166";

            sipAccountData.setUsername(username);

            sipAccountData.setRealm(hostname);

           // EditText editText2 = (EditText) findViewById(R.id.password);

            password = "3Daet((t";

            sipAccountData.setPassword(password);
            sipAccountData.setHost(hostname);
            sipAccountData.setSrtpUse(pjmedia_srtp_use.PJMEDIA_SRTP_DISABLED);
            sipAccountData.setSrtpSecureSignalling(0);
            sipAccountData.setTransport(SipAccountTransport.TCP);

            uri = SipServiceCommand.setAccount(context, sipAccountData);
            SipServiceCommand.start(context);

            Toast.makeText(context, "Service started !",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRegistration(String accountID, int registrationStateCode) {
        super.onRegistration(accountID, registrationStateCode);
        Log.i(TAG, "onRegistration: ");



        if (registrationStateCode == pjsip_status_code.PJSIP_SC_OK) {
            Toast.makeText(getReceiverContext(), "Account ID：" + accountID, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getReceiverContext(), "Registration code：" + registrationStateCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCallState(String accountID, String callID, int callStateCode, int callStatusCode, long connectTimestamp) {
        super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp);
        if(callStateCode == 16){
            SipServiceCommand.hangUpCall(getReceiverContext(), accountID, callID);
        }

//        Logger.debug(LOG_TAG, "onCallState - accountID: " + getValue(getReceiverContext(), accountID) +
//                ", callID: " + callID +
//                ", callStateCode: " + callStateCode +
//                ", callStatusCode: " + callStatusCode +
//                ", connectTimestamp: " + connectTimestamp);
    }

    @Override
    public void onIncomingCall(String accountID, int callID, String displayName, String remoteUri, boolean isVideo) {
        super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo);
        //CallActivity.startActivityIn(getReceiverContext(), accountID, callID, displayName, remoteUri, isVideo);
    }

    @Override
    public void onOutgoingCall(String accountID, int callID, String number, boolean isVideo, boolean isVideoConference, boolean isTransfer) {
        super.onOutgoingCall(accountID, callID, number, isVideo, isVideoConference, isTransfer);
        //CallActivity.startActivityOut(getReceiverContext(), accountID, callID, number, isVideo, isVideoConference);
    }

}
