package com.example.freeswitchandroid;


import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;



import com.example.freeswitchandroid.Pojo.ParentItem;
import com.example.freeswitchandroid.Pojo.TransferData;
import com.example.freeswitchandroid.rest.model.BusinessNumber;
import com.example.freeswitchandroid.rest.model.UserDatum;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipService;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjmedia_srtp_use;
import org.pjsip.pjsua2.pjsip_inv_state;


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
    static List<BusinessNumber> businessNumbers = new ArrayList<>();
    static Map<String, BusinessNumber> map = new HashMap<>();
    static boolean apiHasRetrievedNumbers = false;
    static boolean permissionsDone = false;
    static int callID1;
    static int callID2;

    static String remoteUri;

    Context context;

    static ServiceCommunicator serviceCommunicator;

    public static ServiceCommunicator getInstance()
    {
        if (serviceCommunicator == null)
            serviceCommunicator = new ServiceCommunicator();

        return serviceCommunicator;
    }

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
        ServiceCommunicator.remoteUri = remoteUri;
    }

    @Override
    public void onCallState(String accountID, int callID, int callStateCode, int callStatusCode, long connectTimestamp) {
        super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp);

        if(callStateCode == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED){
            Intent stopIntent = new Intent(context, RingTonePlayingService.class);
            context.stopService(stopIntent);
        }

    }
}
