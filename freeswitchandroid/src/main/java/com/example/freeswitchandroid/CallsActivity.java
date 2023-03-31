package com.example.freeswitchandroid;

import static com.example.freeswitchandroid.ServiceCommunicator.apiHasRetrievedNumbers;
import static com.example.freeswitchandroid.ServiceCommunicator.arraySpinner;
import static com.example.freeswitchandroid.ServiceCommunicator.businessNumbers;
import static com.example.freeswitchandroid.ServiceCommunicator.callID1;
import static com.example.freeswitchandroid.ServiceCommunicator.callID2;
import static com.example.freeswitchandroid.ServiceCommunicator.hostname;
import static com.example.freeswitchandroid.ServiceCommunicator.itemList;
import static com.example.freeswitchandroid.ServiceCommunicator.map;
import static com.example.freeswitchandroid.ServiceCommunicator.sipAccountData;
import static com.example.freeswitchandroid.ServiceCommunicator.transferList;
import static com.example.freeswitchandroid.ServiceCommunicator.uri;
import static com.example.freeswitchandroid.ServiceCommunicator.userDatum;
import static net.gotev.sipservice.SipServiceCommand.accountIsValid;
import static net.gotev.sipservice.SipTlsUtils.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.content.res.ColorStateList;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freeswitchandroid.Helpers.CryptoUtils;
import com.example.freeswitchandroid.Pojo.ChildItem;
import com.example.freeswitchandroid.Pojo.ParentItem;
import com.example.freeswitchandroid.Pojo.TransferData;
import com.example.freeswitchandroid.adapters.ParentItemAdapter;
import com.example.freeswitchandroid.adapters.TransferRecyclerViewAdapter;
import com.example.freeswitchandroid.rest.PressOneAPI;
import com.example.freeswitchandroid.rest.RetrofitData;
import com.example.freeswitchandroid.rest.model.BusinessNumber;
import com.example.freeswitchandroid.rest.model.CallDetail;
import com.example.freeswitchandroid.rest.model.UserDatum;
import com.google.android.material.snackbar.Snackbar;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.CallReconnectionState;
import net.gotev.sipservice.MediaState;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjsip_inv_state;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CallsActivity extends AppCompatActivity implements TransferRecyclerViewAdapter.ItemClickListener, AdapterView.OnItemSelectedListener,SensorEventListener {

    /////Combining Calls History Activity////

    PressOneAPI retrofitAPI;
    CoordinatorLayout layout;
    LinearLayout recycler_list;
    LinearLayout phone_calls_view;
    ActivityManager activityManager;
    ServiceCommunicator serviceCommunicator;
    RecyclerView ParentRecyclerViewItem;
    ParentItemAdapter parentItemAdapter;
    TransferRecyclerViewAdapter transferRecyclerViewAdapter;
    LinearLayoutManager layoutManager;
    LinearLayoutManager layoutManager2;
    Spinner myNumber;
    AudioManager audioManager;
    String numberToTransfer = "";
    String accountID;
    String displayName;
    String remoteUri;
    boolean isVideo;

    boolean isMute = false;

    ImageButton answer;

    ImageButton hangup;
    ImageButton hold;
    ImageButton transfer;
    static EditText number;
    Context context;
    boolean isHold = false;

    RecyclerView transferRecycler;
    ArrayAdapter<String> adapter;

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

    static LinearLayout callsActivity;

    static LinearLayout callsHistoryActivity;

    ImageButton deleteBtn;
    TextView callTime; //Visibility Toggle
    static TextView tvName; //Caller's name
    static TextView tvNumber; //Caller's number
    ImageButton muteBtn; //Highlight on press toggle
    ImageButton speakerBtn; //Highlight on press toggle
    ImageButton keypadBtn; //Highlight on press toggle
    static String no; // phone number

    PowerManager powerManager;
    PowerManager.WakeLock lock;
     Vibrator vibrator;
     ConnectivityManager conMgr;
     NetworkInfo activeNetwork;

     Boolean callIsActive = false;

     Boolean isOutgoingCall = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);

        ///From Calls History

        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Retrofit retrofit = RetrofitData.getRetrofit();

        retrofitAPI = retrofit.create(PressOneAPI.class);
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayOptions(ActionBar. DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_action_bar);
        actionBar.show();

        this.context = this;

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        serviceCommunicator = ServiceCommunicator.getInstance();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        lock = powerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK,"simplewakelock:wakelocktag");

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = conMgr.getActiveNetworkInfo();

        layout = findViewById(R.id.layout);
        myNumber = findViewById(R.id.my_number);
        recycler_list = findViewById(R.id.recycler_list);
        phone_calls_view = findViewById(R.id.phone_calls_view);
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
        callsActivity = findViewById(R.id.calls_activity);
        callsHistoryActivity = findViewById(R.id.calls_history_activity);

        initRecycler();

        Intent intent = getIntent();
        if(ServiceCommunicator.number != null && !ServiceCommunicator.number.isEmpty()) {
            no = ServiceCommunicator.number;
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, arraySpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            myNumber.setAdapter(adapter);
            myNumber.setSelection(Arrays.asList(arraySpinner).indexOf(no), false);
        }
        else{
            if(arraySpinner != null && arraySpinner.length > 0) {
                no = arraySpinner[0];
                adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, arraySpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myNumber.setAdapter(adapter);
                myNumber.setSelection(Arrays.asList(arraySpinner).indexOf(no), false);
            }
        }



        if(!apiHasRetrievedNumbers) {
            phone_calls_view.setVisibility(View.VISIBLE);
            recycler_list.setVisibility(View.GONE);
            getBusinessNumbers();
            ParentRecyclerViewItem.setLayoutManager(layoutManager);
            transferRecycler.setLayoutManager(layoutManager2);
            ParentRecyclerViewItem.setAdapter(parentItemAdapter);
            transferRecycler.setAdapter(transferRecyclerViewAdapter);
            try {
                if(apiHasRetrievedNumbers) {
                    initSipService(no, false);
                }
            } catch (Exception e) {
                Toast.makeText(this, "Service Crashed " + e, Toast.LENGTH_LONG).show();
                try {
                    handleErrors();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else{
            ParentItemList();
            try {
                if(active != null && !active){
                    no = Objects.requireNonNull(map.get(ServiceCommunicator.number)).getPhoneNumber();
                    initSipService(no, false);
                    if (intent != null && intent.getStringExtra("call").equals("incoming")) {
                        String caller = intent.getStringExtra("caller");
                        KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
                        if( myKM.inKeyguardRestrictedInputMode()) {
                            startRingTone();
                            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON| WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        }
                        tvNumber.setText(caller.substring(0, caller.indexOf('@')));
                        callsActivity.setVisibility(View.VISIBLE);
                        callsHistoryActivity.setVisibility(View.GONE);
                        callHorizontalLayout.setVisibility(View.VISIBLE);
                        hangup.setVisibility(View.VISIBLE);
                        answer.setVisibility(View.VISIBLE);
                        dialPad1Layout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.GONE);
                    }
                }
            } catch (Exception e) {
                Toast.makeText(this, "Service Crashed " + e, Toast.LENGTH_LONG).show();
                try {
                    handleErrors();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            ParentRecyclerViewItem.setLayoutManager(layoutManager);
            transferRecycler.setLayoutManager(layoutManager2);
            ParentRecyclerViewItem.setAdapter(parentItemAdapter);
            transferRecycler.setAdapter(transferRecyclerViewAdapter);
        }

        if(mReceiver.getReceiverContext() == null) {
            mReceiver.register(this);
        }

        myNumber.setOnItemSelectedListener(this);

        //Add default visibility at the start of activity.
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(apiHasRetrievedNumbers) {
            try {
                ParentItemList();
                initSipService(parent.getItemAtPosition(position).toString(), true);
            } catch (Exception e) {
                Toast.makeText(this, "Service Crashed " + e, Toast.LENGTH_LONG).show();
                try {
                    handleErrors();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }

    private void getBusinessNumbers(){

        SharedPreferences shared = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        String token = shared.getString("token", "");

        Call<UserDatum> call = retrofitAPI.getBusinessNumbers("Bearer " + token);

        call.enqueue(new Callback<UserDatum>() {
            @Override
            public void onResponse(Call<UserDatum> call, Response<UserDatum> response) {

                userDatum = response.body();
                if(userDatum != null) {
                    businessNumbers = userDatum.getBusinessNumbers();
                }
                if(businessNumbers.size() != 0 && businessNumbers.get(0) != null && (businessNumbers.get(0).getPhoneNumber() != null && !businessNumbers.get(0).getPhoneNumber().isEmpty())) {
                    arraySpinner = new String[businessNumbers.size()];
                    for(int i = 0; i < businessNumbers.size(); i++){
                        arraySpinner[i] = businessNumbers.get(i).getPhoneNumber();
                        map.put(businessNumbers.get(i).getPhoneNumber(), businessNumbers.get(i));
                    }
                    apiHasRetrievedNumbers = true;
                    recycler_list.setVisibility(View.VISIBLE);
                    phone_calls_view.setVisibility(View.GONE);
                    adapter = new ArrayAdapter<>(CallsActivity.this,
                            android.R.layout.simple_spinner_item, arraySpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    myNumber.setAdapter(adapter);
                    no = arraySpinner[0];
                    ServiceCommunicator.number = no;
                    myNumber.setSelection(Arrays.asList(arraySpinner).indexOf(no), false);
                }

                if(arraySpinner == null || arraySpinner.length == 0) {
                    arraySpinner = new String[]{"No Business Number Found"};
                    adapter = new ArrayAdapter<>(CallsActivity.this,
                            android.R.layout.simple_spinner_item, arraySpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    myNumber.setAdapter(adapter);
                    no = arraySpinner[0];
                    ServiceCommunicator.number = no;
                    myNumber.setSelection(Arrays.asList(arraySpinner).indexOf(no), false);
                    ParentItemList();
                }

            }

            @Override
            public void onFailure(Call<UserDatum> call, Throwable t) {
                Toast.makeText(CallsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void ParentItemList()
    {
        SharedPreferences shared = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        String token = shared.getString("token", "");

        if(apiHasRetrievedNumbers && arraySpinner != null && arraySpinner.length > 0) {
            Call<List<CallDetail>> call = retrofitAPI.getCallsData("Bearer " + token, map.get(myNumber.getSelectedItem().toString()).getId().toString());

            call.enqueue(new Callback<List<CallDetail>>() {
                @Override
                public void onResponse(Call<List<CallDetail>> call, Response<List<CallDetail>> response) {

                    List<CallDetail> callsEndDatumList = response.body();

                        if (callsEndDatumList != null && callsEndDatumList.size() > 0) {

                            final Map<String, TemporalAdjuster> ADJUSTERS = new HashMap<>();

                            ADJUSTERS.put("day", TemporalAdjusters.ofDateAdjuster(d -> d));


                            List<ChildItem> childList = new ArrayList<>();

                            if(transferList != null) {
                                transferList.clear();
                            }

                            for (CallDetail callsEndDatum : callsEndDatumList) {
                                childList.add(new ChildItem(callsEndDatum.getCallerId(), getCallerId(callsEndDatum), getCallType(callsEndDatum), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.parse(callsEndDatum.getDateCreated(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSxxx")))));
                                transferList.add(new TransferData(callsEndDatum.getCallerId(), getCallerId(callsEndDatum)));
                            }

                            Set<TransferData> uniqueContacts = new HashSet<>(transferList);
                            transferList.clear();
                            transferList.addAll(uniqueContacts);

                            transferRecyclerViewAdapter.notifyDataSetChanged();


                            Map<LocalDate, List<ChildItem>> result = childList.stream()
                                    .collect(Collectors.groupingBy(item -> LocalDate.parse(item.getChildItemTxt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
                                            .with(ADJUSTERS.get("day"))));

                            if(itemList != null) {
                                itemList.clear();
                            }

                            result.forEach((key, value) -> itemList.add(new ParentItem(DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(key), value)));

                            parentItemAdapter.notifyDataSetChanged();
                        }
                }

                @Override
                public void onFailure(Call<List<CallDetail>> call, Throwable t) {
                    Toast.makeText(CallsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
    private void initSipService(String number, boolean StopService) throws Exception {
        if(StopService) {
            SipServiceCommand.stop(this);
        }
        ServiceCommunicator.username = (map.get(number)).getReceivers().get(0).getLine().getUsername();
        String password = (map.get(number)).getReceivers().get(0).getLine().getPassword();
        String nonce = (map.get(number)).getReceivers().get(0).getLine().getNonce();
        hostname = (map.get(number)).getReceivers().get(0).getLine().getDomain();
        ServiceCommunicator.password = CryptoUtils.decyrpt(password, nonce);
        ServiceCommunicator.number = number;
        //TODO Remove logging
        SipServiceCommand.enableSipDebugLogging(true);
        serviceCommunicator.startService(activityManager, this);
    }

    private void initRecycler(){


        ParentRecyclerViewItem = findViewById(R.id.parent_recyclerview);
        transferRecycler = findViewById(R.id.transfer_recycler);

        // Initialise the Linear layout manager
        layoutManager = new LinearLayoutManager(CallsActivity.this);
        layoutManager2 = new LinearLayoutManager(CallsActivity.this);

        parentItemAdapter = new ParentItemAdapter(itemList, CallsActivity.this);
        transferRecyclerViewAdapter = new TransferRecyclerViewAdapter( CallsActivity.this, transferList);
        transferRecyclerViewAdapter.setClickListener(this);

    }

    @Override
    public void onItemClick(View view, int position) {
        RadioButton radioButton = view.findViewById(R.id.radio_btn);
        for(TransferData transferData: transferList){
            transferData.setChecked(false);
        }
        transferList.get(position).setChecked(true);
        transferRecyclerViewAdapter.notifyDataSetChanged();
        TextView textView = view.findViewById(R.id.transfer_number);
        numberToTransfer = textView.getText().toString();
    }



    private String getCallerId(CallDetail callsEndDatum) {
        String callerId = null;

       if(callsEndDatum.getCallerName() != null)callerId = callsEndDatum.getCallerName().toString();

       if(callerId == null || callerId.isEmpty()){
            callerId = callsEndDatum.getCallerId();
        }
        return callerId;
    }
    private int getCallType(CallDetail datum){

        int toReturn = 0;

        if(datum.getIsDialed()){
            toReturn = 0; // outgoing
        } else if (datum.getIsMissedCall()) {
            toReturn = 1; // missed
        } else if (!datum.getIsDialed()) {
            toReturn = 2; //incoming
        } else if (!datum.getIsForwardedCall()) {
            toReturn = 3; //forwarded
        } else if (!datum.getCallType().equals("REJECTED")) {
            toReturn = 4; //rejected
        }

        return toReturn;
    }

    public void keypadPress(View v){
        callsActivity.setVisibility(View.VISIBLE);
        callsHistoryActivity.setVisibility(View.GONE);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    @Override
    public void onBackPressed() {
        getSupportActionBar().show();
        if(callIsActive) {
            overlayTransferLayout.setVisibility(View.GONE);
        }
        else {
            callsActivity.setVisibility(View.GONE);
            callsHistoryActivity.setVisibility(View.VISIBLE);
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(mReceiver.getReceiverContext() == null){
            mReceiver.register(this);
        }
        mSensorManager.registerListener(CallsActivity.this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        if(!apiHasRetrievedNumbers){
            getBusinessNumbers();
        }
        else if(apiHasRetrievedNumbers){
            try {
                initSipService(no, false);
            } catch (Exception e) {
                handleErrors();
            }
        }
        ParentItemList();
    }

    public static void callLogItemPressed(ChildItem item, int position){
        number.setText(item.getNumber());
        if(!item.getChildItemTitle().matches("[0-9]+")) {
            tvName.setText(item.getChildItemTitle());
        }
        else{
            tvName.setText("");
        }
        tvNumber.setText(item.getNumber());
        callsHistoryActivity.setVisibility(View.GONE);
        callsActivity.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mReceiver.unregister(this); ?????
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
        SipServiceCommand.sendDTMF(this, uri, callID1, "1");
    }

    public void dtmf2Pressed(View v){
        SipServiceCommand.sendDTMF(this, uri, callID1, "2");
    }

    public void dtmf3Pressed(View v){
        SipServiceCommand.sendDTMF(this, uri, callID1, "3");
    }

    public void dtmf4Pressed(View v){
        SipServiceCommand.sendDTMF(this, uri, callID1, "4");
    }

    public void dtmf5Pressed(View v){
        SipServiceCommand.sendDTMF(this, uri, callID1, "5");
    }

    public void dtmf6Pressed(View v){
        SipServiceCommand.sendDTMF(this, uri, callID1, "6");
    }

    public void dtmf7Pressed(View v){
        SipServiceCommand.sendDTMF(this, uri, callID1, "7");
    }

    public void dtmf8Pressed(View v){
        SipServiceCommand.sendDTMF(this, uri, callID1, "8");
    }

    public void dtmf9Pressed(View v){
        SipServiceCommand.sendDTMF(this, uri, callID1, "9");
    }

    public void dtmf0Pressed(View v){
        SipServiceCommand.sendDTMF(this, uri, callID1, "0");
    }

    public void dtmfStarPressed(View v){
        SipServiceCommand.sendDTMF(this, uri, callID1, "*");
    }

    public void dtmfHashPressed(View v){
        SipServiceCommand.sendDTMF(this, uri, callID1, "#");
    }

    public void deleteBtnPressed(View v){
        if (number.getText().length() > 0) {
            number.getText().delete(number.getText().length() - 1, number.getText().length());
        }
    }

    //////////////////////////////////////// Calls related functions below(Call Options) //////////////////////////////////////////////////////////

    public void terminate(View v) {

            int callId = 0;
            if(isOutgoingCall){
                callId = callID1;
            }
            else{
                callId = callID2;
            }
            getSupportActionBar().show();
            callsHistoryActivity.setVisibility(View.VISIBLE);
            callsActivity.setVisibility(View.GONE);
            SipServiceCommand.hangUpCall(this, uri, callId);
            SipServiceCommand.hangUpActiveCalls(this, uri);

            dialPad1Layout.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.GONE);
            callHorizontalLayout.setVisibility(View.VISIBLE);
            answer.setVisibility(View.VISIBLE);
            hangup.setVisibility(View.GONE);

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

    public void mute(View v) {

            if (!isMute) {
                SipServiceCommand.setCallMute(this, uri, callID1, true);
                muteBtn.setImageResource(R.drawable.mute_active);
                isMute = true;
            } else {
                SipServiceCommand.setCallMute(this, uri, callID1, false);
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

    public void call() {

            String numberToCall = number.getText().toString();

            if (!numberToCall.isEmpty()) {
                if(accountIsValid()) {
                    dialPad1Layout.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    callHorizontalLayout.setVisibility(View.GONE);
                    callTime.setVisibility(View.GONE);
                    answer.setVisibility(View.GONE);

                SipServiceCommand.makeCall(this, uri, "sip:" + numberToCall + "@" + hostname, false, false, false);
                } else {
                    handleErrors();
                }
            }

    }


    public void answer(View view) {
            stopRingTone();
            if (hangup.getVisibility() == View.GONE) {
                call();
            }
            else {
                    dialPad1Layout.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    callOptionsLayout.setVisibility(View.VISIBLE);
                    callHorizontalLayout.setVisibility(View.GONE);
                    answer.setVisibility(View.GONE);

                    SipServiceCommand.acceptIncomingCall(this, uri, String.valueOf(callID1), isVideo);
                }
    }

    public void hangUp(View view) throws Exception {
        stopRingTone();
            SipServiceCommand.declineIncomingCall(this, uri, callID1);
            dialPad1Layout.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.GONE);
            callHorizontalLayout.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callTime.setVisibility(View.GONE);
            hangup.setVisibility(View.GONE);
            answer.setVisibility(View.VISIBLE);
    }

    public void hold(View view) {

        if(!isHold) {

            SipServiceCommand.holdActiveCalls(this, uri);

            hold.setImageResource(R.drawable.hold_active);

        }
          else if(isHold) {

                int callId = 0;
                if(isOutgoingCall){
                    callId = callID2;
                }
                else{
                    callId = callID1;
                }

                SipServiceCommand.setCallHold(this, uri, callId, false);


                hold.setImageResource(R.drawable.hold);

            }
          isHold = !isHold;

    }

    public void transfer(View view) {

            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);
            overlayTransferLayout.setVisibility(View.VISIBLE);

    }

    private void makeAttendedCallTransfer(){
        if (!isHold) {

            SipServiceCommand.toggleCallHold(this, uri, callID1);

            hold.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.cardview_dark_background)));

            Toast.makeText(this, "Holding before transfer !",
                    Toast.LENGTH_LONG).show();

            SipServiceCommand.makeCall(this, uri, numberToTransfer, true);
        } else if (isHold) {

            SipServiceCommand.attendedTransferCall(this, uri, callID1, callID2);

            Toast.makeText(this, "Making attended transfer !",
                    Toast.LENGTH_LONG).show();

            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);

            overlayTransferLayout.setVisibility(View.GONE);
        }

        isHold = !isHold;
    }

    private void makeBlindTransfer(){
            int callId = 0;
            if(isOutgoingCall) {
                 callId = callID2;
            }
            else{
                 callId = callID1;
            }

            SipServiceCommand.transferCall(this, uri, callId, numberToTransfer);

            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);

            overlayTransferLayout.setVisibility(View.GONE);
    }

    public void finalTransfer(View v) {
        if(accountIsValid()) {

            //makeAttendedCallTransfer();
            makeBlindTransfer();

        }
        else{
            handleErrors();
        }

    }

    BroadcastEventReceiver mReceiver = new BroadcastEventReceiver()

    {

    @Override
    public void onRegistration(String accountID, int registrationStateCode) {
        super.onRegistration(accountID, registrationStateCode);
        Log.i(TAG, "onRegistration: ");

        if(registrationStateCode == 200){
            Snackbar.make(layout, "Registered", Snackbar.LENGTH_SHORT).show();
        }
        else{
            Snackbar.make(layout, "Registration Error " + registrationStateCode, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCallState(String accountID, int callID, int callStateCode, int callStatusCode, long connectTimestamp) {
        super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp);

        if(callStateCode == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED){
            if(callID == callID2 || callID == callID1) {
                Objects.requireNonNull(getSupportActionBar()).hide();
                dialPad1Layout.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                callHorizontalLayout.setVisibility(View.VISIBLE);
                dtmfKeyPadLayout.setVisibility(View.GONE);
                callTime.setVisibility(View.GONE);
                hangup.setVisibility(View.GONE);
                answer.setVisibility(View.VISIBLE);
                overlayTransferLayout.setVisibility(View.GONE);
                audioManager.setMode(AudioManager.MODE_NORMAL);
                hold.setImageResource(R.drawable.hold);
                transfer.setImageResource(R.drawable.transfer);
                muteBtn.setImageResource(R.drawable.mute);
                speakerBtn.setImageResource(R.drawable.speaker);
                keypadBtn.setImageResource(R.drawable.keypad);
                callIsActive = false;
                isOutgoingCall = false;
                number.setText("");
                startTimer(true);
                resetTimer();
                stopRingTone();
            }
        }


        if(callStateCode == pjsip_inv_state.PJSIP_INV_STATE_CONNECTING){
            for (Map.Entry<String, BusinessNumber> e : map.entrySet()) {
                if (!e.getKey().endsWith(number.getText().toString())) {
                    tvName.setText("");
                }
            }
            if(isOutgoingCall){
                tvNumber.setText(number.getText());
            }
            callsActivity.setVisibility(View.VISIBLE);
            callsHistoryActivity.setVisibility(View.GONE);
            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            callTime.setVisibility(View.VISIBLE);
            answer.setVisibility(View.GONE);
            CallsActivity.this.accountID = accountID;
            if(isOutgoingCall) callID2 = callID; else callID1 = callID;
            CallsActivity.this.displayName = displayName;
            CallsActivity.this.remoteUri = remoteUri;
            CallsActivity.this.isVideo = isVideo;
            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
            stopRingTone();
        }

        if(callStateCode == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED){
            startTimer(false);
        }

    }


        @Override
        public void onCallMediaState(String accountID, int callID, MediaState stateType, boolean stateValue) {
            super.onCallMediaState(accountID, callID, stateType, stateValue);


        }

        @Override
        public void onIncomingCall (String accountID,int callID, String displayName, String
        remoteUri,boolean isVideo){
        super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo);
            KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            if( !myKM.inKeyguardRestrictedInputMode()) {
                startRingTone();
            }
            callsActivity.setVisibility(View.VISIBLE);
            callsHistoryActivity.setVisibility(View.GONE);
            callHorizontalLayout.setVisibility(View.VISIBLE);
            hangup.setVisibility(View.VISIBLE);
            answer.setVisibility(View.VISIBLE);

             CallsActivity.this.accountID = accountID;
             callID1 = callID;
             CallsActivity.this.displayName = displayName;
             CallsActivity.this.remoteUri = remoteUri;
             CallsActivity.this.isVideo = isVideo;

            tvName.setText(displayName);
            tvNumber.setText("");

            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);
            callIsActive = true;
            isOutgoingCall = false;
    }

        @Override
        public void onOutgoingCall (String accountID,int callID, String number,boolean isVideo,
        boolean isVideoConference, boolean isTransfer){
        super.onOutgoingCall(accountID, callID, number, isVideo, isVideoConference, isTransfer);

            CallsActivity.this.accountID = accountID;
            callID2 = callID;
            CallsActivity.this.displayName = displayName;
            CallsActivity.this.remoteUri = remoteUri;
            CallsActivity.this.isVideo = isVideo;
            callIsActive = true;
            isOutgoingCall = true;
        }

        @Override
        protected void onCallReconnectionState(CallReconnectionState state) {
            super.onCallReconnectionState(state);
            if(state.equals(CallReconnectionState.FAILED)){
                if(accountIsValid() && activeNetwork != null && activeNetwork.isConnected()){
                    SipServiceCommand.reconnectCall(CallsActivity.this);
                }
                else{
                    Toast.makeText(CallsActivity.this, "Lost Network", Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    public void startRingTone(){
        Intent startIntent = new Intent(getApplicationContext(), RingTonePlayingService.class);
        startService(startIntent);
    }

    public void stopRingTone(){
        Intent stopIntent = new Intent(getApplicationContext(), RingTonePlayingService.class);
        stopService(stopIntent);
    }

    public void startTimer(boolean stop){
        long tStart = 8*60*60000;
        SimpleDateFormat date = new SimpleDateFormat("H:mm:ss");
        date.setTimeZone(TimeZone.getTimeZone("UTC"));

        if(stop) {
        if(countDownTimer != null) countDownTimer.cancel();
        }
        else {
            countDownTimer = new CountDownTimer(8 * 60 * 60000, 1000) {

                public void onTick(long millisUntilFinished) {
                    callTime.setText(date.format(new Date(tStart - millisUntilFinished)));
                }

                public void onFinish() {
                }
            }.start();
        }
    }

    public void resetTimer(){
        callTime.setText(new SimpleDateFormat("H:mm:ss").format(new Date(0)));
    }

    static Boolean active = null;
    CountDownTimer countDownTimer;
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(CallsActivity.this);
        active = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }

    private SensorManager mSensorManager;
    private Sensor mProximity;
    private static final int SENSOR_SENSITIVITY = 4;


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                //near
                // Enable : Acquire the lock if it was not already acquired
                if(callIsActive){
                if(!lock.isHeld()) lock.acquire();}

            } else {
                //far
                // Disable : Release the lock if it was not already released
                if(lock.isHeld()) lock.release();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void handleErrors() {
        getBusinessNumbers();
        dialPad1Layout.setVisibility(View.VISIBLE);
        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        callHorizontalLayout.setVisibility(View.VISIBLE);
        dtmfKeyPadLayout.setVisibility(View.GONE);
        callTime.setVisibility(View.GONE);
        hangup.setVisibility(View.GONE);
        answer.setVisibility(View.VISIBLE);
        overlayTransferLayout.setVisibility(View.GONE);
        audioManager.setMode(AudioManager.MODE_NORMAL);
        hold.setImageResource(R.drawable.hold);
        transfer.setImageResource(R.drawable.transfer);
        muteBtn.setImageResource(R.drawable.mute);
        speakerBtn.setImageResource(R.drawable.speaker);
        keypadBtn.setImageResource(R.drawable.keypad);
        Snackbar snackbar = Snackbar.make(layout, "Connection Lost ! Reconnecting...", Snackbar.LENGTH_LONG).setAction("WAIT", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        try {
            initSipService(no, false);
        } catch (Exception e) {
            Toast.makeText(CallsActivity.this, "No Business Number Found. Registration failed", Toast.LENGTH_LONG).show();
        }
        snackbar.show();
        if (mReceiver.getReceiverContext() == null && serviceCommunicator.foregroundServiceRunning(activityManager)) mReceiver.register(this);
    }

}