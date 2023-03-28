package com.example.freeswitchandroid;

import android.app.ActivityManager;
import android.content.Context;
import android.media.RingtoneManager;


import com.example.freeswitchandroid.Pojo.ParentItem;
import com.example.freeswitchandroid.Pojo.TransferData;
import com.example.freeswitchandroid.rest.model.BusinessNumber;
import com.example.freeswitchandroid.rest.model.UserDatum;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipService;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjmedia_srtp_use;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCommunicator extends BroadcastEventReceiver {

    public static String number;
    static String uri;
    static String username = "";
    static String password = "";
    static SipAccountData sipAccountData;
    static String hostname = "";

    static String[] arraySpinner;

    static List<ParentItem> itemList = new ArrayList<>();

    static  List<TransferData> transferList = new ArrayList<>();

    static UserDatum userDatum;

    static RingtoneManager ringtoneManager;

    static List<BusinessNumber> businessNumbers = new ArrayList<>();
    static Map<String, BusinessNumber> map = new HashMap<>();
    static boolean apiHasRetrievedNumbers = false;
    static boolean permissionsDone = false;
    static int callID1;
    static int callID2;

    //static long port = 5060;

    Context context;

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

            sipAccountData.setUsername(username);

            sipAccountData.setRealm(hostname);

            sipAccountData.setPassword(password);
            sipAccountData.setHost(hostname);
            sipAccountData.setSrtpUse(pjmedia_srtp_use.PJMEDIA_SRTP_DISABLED);
            sipAccountData.setSrtpSecureSignalling(0);
            this.context = context;

            uri = SipServiceCommand.setAccount(context, sipAccountData);
            SipServiceCommand.start(context);
        }
    }

    @Override
    public void onIncomingCall(String accountID, int callID, String displayName, String remoteUri, boolean isVideo) {
        super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo);
        callID1 = callID;
    }


}
