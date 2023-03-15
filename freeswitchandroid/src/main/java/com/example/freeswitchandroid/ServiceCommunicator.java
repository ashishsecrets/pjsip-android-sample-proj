package com.example.freeswitchandroid;

import static net.gotev.sipservice.ObfuscationHelper.getValue;
import static net.gotev.sipservice.SipTlsUtils.TAG;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import org.pjsip.pjsua2.pjsip_inv_state;
import org.pjsip.pjsua2.pjsip_status_code;

public class ServiceCommunicator {

    static String uri;
    static String username = "";
    static String password = "";
    static SipAccountData sipAccountData;
    static String hostname = "david380.fs1.pressone.co";

    Context context;

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
            sipAccountData.setRealm("sunnex58.fs1.pressone.co");
            sipAccountData.setUsername("017004607");
            sipAccountData.setPassword("odUX2681Gvl5X0iTrUYL");
            sipAccountData.setHost("sunnex58.fs1.pressone.co");
            sipAccountData.setSrtpUse(pjmedia_srtp_use.PJMEDIA_SRTP_DISABLED);
            sipAccountData.setSrtpSecureSignalling(0);
            sipAccountData.setTransport(SipAccountTransport.TCP);

            this.context = context;

            uri = SipServiceCommand.setAccount(context, sipAccountData);
            SipServiceCommand.start(context);

            Toast.makeText(context, "Service started !",
                    Toast.LENGTH_LONG).show();
        }
    }




}
