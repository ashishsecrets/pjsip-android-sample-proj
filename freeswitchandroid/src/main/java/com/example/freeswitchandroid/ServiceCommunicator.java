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

import com.example.freeswitchandroid.Pojo.ParentItem;
import com.example.freeswitchandroid.Pojo.TransferData;
import com.example.freeswitchandroid.rest.model.BusinessNumber;
import com.example.freeswitchandroid.rest.model.UserDatum;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.Logger;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipAccountTransport;
import net.gotev.sipservice.SipService;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjmedia_srtp_use;
import org.pjsip.pjsua2.pjsip_inv_state;
import org.pjsip.pjsua2.pjsip_status_code;

import java.util.List;
import java.util.Map;

public class ServiceCommunicator {

    static String uri;
    static String username = "";
    static String password = "";
    static SipAccountData sipAccountData;
    static String hostname = "david380.fs1.pressone.co";

    static String[] arraySpinner;

    static List<ParentItem> itemList;

    static  List<TransferData> transferList;

    static UserDatum userDatum;

    static List<BusinessNumber> businessNumbers;
    static Map<String, BusinessNumber> map;
    static boolean apiHasRetrievedNumbers = false;

    //static long port = 5060;

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

            sipAccountData.setUsername(username);

            sipAccountData.setRealm(hostname);

            //sipAccountData.setPort(port);
            sipAccountData.setPassword(password);
            sipAccountData.setHost(hostname);
            sipAccountData.setSrtpUse(pjmedia_srtp_use.PJMEDIA_SRTP_DISABLED);
            sipAccountData.setSrtpSecureSignalling(0);
            sipAccountData.setTransport(SipAccountTransport.TCP);

            this.context = context;

            uri = SipServiceCommand.setAccount(context, sipAccountData);
            SipServiceCommand.start(context);
        }
    }


}
