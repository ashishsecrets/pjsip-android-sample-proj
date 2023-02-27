package com.example.freeswitchandroid;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipAccountTransport;
import net.gotev.sipservice.SipService;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjmedia_srtp_use;

public class ServiceCommunicator {

    static String uri;
    static String username;
    static String password;
    static SipAccountData sipAccountData;
    static Context context;

    static String hostname = "david380.fs1.pressone.co";

    public boolean foregroundServiceRunning(ActivityManager activityManager){
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

}
