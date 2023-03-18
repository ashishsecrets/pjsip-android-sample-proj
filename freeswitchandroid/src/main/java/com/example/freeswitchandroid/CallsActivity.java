package com.example.freeswitchandroid;

import static com.example.freeswitchandroid.ServiceCommunicator.apiHasRetrievedNumbers;
import static com.example.freeswitchandroid.ServiceCommunicator.arraySpinner;
import static com.example.freeswitchandroid.ServiceCommunicator.businessNumbers;
import static com.example.freeswitchandroid.ServiceCommunicator.callID1;
import static com.example.freeswitchandroid.ServiceCommunicator.callID2;
import static com.example.freeswitchandroid.ServiceCommunicator.map;
import static com.example.freeswitchandroid.ServiceCommunicator.transferList;
import static com.example.freeswitchandroid.ServiceCommunicator.userDatum;
import static net.gotev.sipservice.SipTlsUtils.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.example.freeswitchandroid.rest.model.Receiver;
import com.example.freeswitchandroid.rest.model.UserDatum;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjsip_inv_state;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CallsActivity extends AppCompatActivity implements TransferRecyclerViewAdapter.ItemClickListener, AdapterView.OnItemSelectedListener{

    /////Combining Calls History Activity////

    PressOneAPI retrofitAPI;

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
    TextView tvName; //Caller's name
    TextView tvNumber; //Caller's number
    ImageButton muteBtn; //Highlight on press toggle
    ImageButton speakerBtn; //Highlight on press toggle
    ImageButton keypadBtn; //Highlight on press toggle

    AudioManager audioManager;

    String no; // phone number


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

        myNumber = (Spinner) findViewById(R.id.my_number);
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

        if(arraySpinner != null && arraySpinner.length > 0 && !arraySpinner[0].equals("No Business Number Found")) {
            no = map.values().iterator().next().getPhoneNumber();
        }
        else{
            ServiceCommunicator.arraySpinner = new String[]{"No Business Number Found"};
            no = arraySpinner[0];
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CallsActivity.this, android.R.layout.simple_spinner_item, ServiceCommunicator.arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myNumber.setAdapter(adapter);

        phone_calls_view.setVisibility(View.VISIBLE);
        recycler_list.setVisibility(View.GONE);

        initRecycler();


        if(arraySpinner.length <= 1 && no.equals("No Business Number Found")) {
            getBusinessNumbers();
            try {
                if(!arraySpinner[0].equals("No Business Number Found")) {
                    initSipService(no, false);
                    mReceiver.register(this);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            ParentItemList();
            try {
                if(active != null && !active){
                    no = Objects.requireNonNull(map.get(ServiceCommunicator.number)).getPhoneNumber();
                    initSipService(no, false);
                    Intent intent = getIntent();
                    if (intent != null && intent.getStringExtra("call").equals("incoming")) {
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
                else if(active == null){
                    initSipService(no, false);
                    mReceiver.register(this);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ParentRecyclerViewItem.setLayoutManager(layoutManager);
            transferRecycler.setLayoutManager(layoutManager2);
            ParentRecyclerViewItem.setAdapter(parentItemAdapter);
            transferRecycler.setAdapter(transferRecyclerViewAdapter);
        }

        myNumber.setSelection(Arrays.asList(arraySpinner).indexOf(no), false);
        myNumber.setOnItemSelectedListener(CallsActivity.this);


        // Removing action bar

//        actionBar = getSupportActionBar();
//        assert actionBar != null;
//        actionBar.hide();

        this.context = this;

        //Add default visibility at the start of activity.

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(apiHasRetrievedNumbers) {
            try {
                ParentItemList();
                initSipService(parent.getItemAtPosition(position).toString(), true);
            } catch (Exception e) {
                throw new RuntimeException(e);
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
                if(businessNumbers != null || !(businessNumbers.size() == 0)) {
                    ServiceCommunicator.arraySpinner = new String[businessNumbers.size()];
                    for(int i = 0; i < businessNumbers.size(); i++){
                        ServiceCommunicator.arraySpinner[i] = businessNumbers.get(i).getPhoneNumber();
                        ServiceCommunicator.map.put(businessNumbers.get(i).getPhoneNumber(), businessNumbers.get(i));
                    }
                    apiHasRetrievedNumbers = true;
                }
                else{
                    ServiceCommunicator.arraySpinner = new String[]{"No Business Number Found"};
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CallsActivity.this,
                        android.R.layout.simple_spinner_item, ServiceCommunicator.arraySpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myNumber.setAdapter(adapter);

                ParentItemList();

            }

            @Override
            public void onFailure(Call<UserDatum> call, Throwable t) {
                Toast.makeText(CallsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void ParentItemList()
    {
        ServiceCommunicator.itemList.clear();
        ServiceCommunicator.transferList.clear();
        SharedPreferences shared = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        String token = shared.getString("token", "");

        if(arraySpinner != null && arraySpinner.length > 0 && !arraySpinner[0].equals("No Business Number Found")) {
            Call<List<CallDetail>> call = retrofitAPI.getCallsData("Bearer " + token, ServiceCommunicator.map.get(myNumber.getSelectedItem().toString()).getId().toString());

            call.enqueue(new Callback<List<CallDetail>>() {
                @Override
                public void onResponse(Call<List<CallDetail>> call, Response<List<CallDetail>> response) {

                    List<CallDetail> callsEndDatumList = response.body();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        if (callsEndDatumList != null && callsEndDatumList.size() > 0) {
                            phone_calls_view.setVisibility(View.GONE);
                            recycler_list.setVisibility(View.VISIBLE);

                            final Map<String, TemporalAdjuster> ADJUSTERS = new HashMap<>();

                            ADJUSTERS.put("day", TemporalAdjusters.ofDateAdjuster(d -> d));


                            List<ChildItem> childList = new ArrayList<>();

                            for (CallDetail callsEndDatum : callsEndDatumList) {
                                childList.add(new ChildItem(callsEndDatum.getCallerId(), getCallerId(callsEndDatum), getCallType(callsEndDatum), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.parse(callsEndDatum.getDateCreated(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSxxx")))));
                                ServiceCommunicator.transferList.add(new TransferData(callsEndDatum.getCallerId(), getCallerId(callsEndDatum), getCallerId(callsEndDatum).substring(0, 1)));
                            }

                            Set<TransferData> uniqueContacts = new HashSet<TransferData>(ServiceCommunicator.transferList);
                            ServiceCommunicator.transferList.clear();
                            ServiceCommunicator.transferList.addAll(uniqueContacts);

                            transferRecyclerViewAdapter.notifyDataSetChanged();


                            Map<LocalDate, List<ChildItem>> result = childList.stream()
                                    .collect(Collectors.groupingBy(item -> LocalDate.parse(item.getChildItemTxt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
                                            .with(ADJUSTERS.get("day"))));

                            result.entrySet().forEach(x -> ServiceCommunicator.itemList.add(new ParentItem(DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(x.getKey()), x.getValue())));

                            parentItemAdapter.notifyDataSetChanged();
                        }

                    } else {
                        Toast.makeText(CallsActivity.this, "Please update your phone's software", Toast.LENGTH_SHORT).show();
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
        //Remove logging // TODO
        if(StopService) {
            SipServiceCommand.stop(this);
        }
        ServiceCommunicator.username = Objects.requireNonNull(ServiceCommunicator.map.get(number)).getReceivers().get(0).getLine().getUsername();
        String password = Objects.requireNonNull(ServiceCommunicator.map.get(number)).getReceivers().get(0).getLine().getPassword();
        String nonce = Objects.requireNonNull(ServiceCommunicator.map.get(number)).getReceivers().get(0).getLine().getNonce();
        ServiceCommunicator.hostname = Objects.requireNonNull(ServiceCommunicator.map.get(number)).getReceivers().get(0).getLine().getDomain();
        //ServiceCommunicator.port = Long.parseLong(map.get(number).getReceivers().get(0).getLine().getPort());
        ServiceCommunicator.password = CryptoUtils.decyrptNew(password, nonce);
        ServiceCommunicator.number = number;

//        ServiceCommunicator.username = "5294241166";
//        ServiceCommunicator.password = "3Daet((t";
//        ServiceCommunicator.hostname = "david380.fs1.pressone.co";


        SipServiceCommand.enableSipDebugLogging(true);
        serviceCommunicator = new ServiceCommunicator();
        serviceCommunicator.startService(activityManager, this);
    }

    private void initRecycler(){


        ParentRecyclerViewItem = findViewById(R.id.parent_recyclerview);
        transferRecycler = findViewById(R.id.transfer_recycler);

        // Initialise the Linear layout manager
        layoutManager = new LinearLayoutManager(CallsActivity.this);
        layoutManager2 = new LinearLayoutManager(CallsActivity.this);

        parentItemAdapter = new ParentItemAdapter(ServiceCommunicator.itemList, CallsActivity.this);
        transferRecyclerViewAdapter = new TransferRecyclerViewAdapter( CallsActivity.this, ServiceCommunicator.transferList);
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

        callerId = callsEndDatum.getUser();

        if(callerId.isEmpty()){
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
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        callsActivity.setVisibility(View.GONE);
        callsHistoryActivity.setVisibility(View.VISIBLE);
    }



    @Override
    protected void onResume() {
        super.onResume();
        //active = true;
        if(!apiHasRetrievedNumbers){
            getBusinessNumbers();
        }
        ParentItemList();
    }

    public static void callLogItemPressed(ChildItem item, int position){
        number.setText(item.getNumber());
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
    SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "1");
    }

    public void dtmf2Pressed(View v){
        SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "2");
    }

    public void dtmf3Pressed(View v){
        SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "3");
    }

    public void dtmf4Pressed(View v){
        SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "4");
    }

    public void dtmf5Pressed(View v){
        SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "5");
    }

    public void dtmf6Pressed(View v){
        SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "6");
    }

    public void dtmf7Pressed(View v){
        SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "7");
    }

    public void dtmf8Pressed(View v){
        SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "8");
    }

    public void dtmf9Pressed(View v){
        SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "9");
    }

    public void dtmf0Pressed(View v){
        SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "0");
    }

    public void dtmfStarPressed(View v){
        SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "*");
    }

    public void dtmfHashPressed(View v){
        SipServiceCommand.sendDTMF(this, ServiceCommunicator.uri, callID1, "#");
    }

    public void deleteBtnPressed(View v){
        if (number.getText().length() > 0) {
            number.getText().delete(number.getText().length() - 1, number.getText().length());
        }
    }

    //////////////////////////////////////// Calls related functions below(Call Options) //////////////////////////////////////////////////////////

    public void terminate(View v){

        callsHistoryActivity.setVisibility(View.VISIBLE);
        callsActivity.setVisibility(View.GONE);
        SipServiceCommand.hangUpActiveCalls(this, ServiceCommunicator.uri);


        Toast.makeText(this, "Hanging up !",
                Toast.LENGTH_LONG).show();

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

            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            callTime.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);


            SipServiceCommand.makeCall(this, ServiceCommunicator.uri, "sip:" + numberToCall + "@" + ServiceCommunicator.hostname, false, false, false);
            Toast.makeText(this, "Calling... !",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void answer(View view){

         if(hangup.getVisibility() == View.GONE) {
                call();
         }
         else {
            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            callOptionsLayout.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);

            SipServiceCommand.acceptIncomingCall(this, ServiceCommunicator.uri, String.valueOf(callID1), isVideo);
            Toast.makeText(this, "Answering Call !", Toast.LENGTH_LONG).show();

        }



    }

    public void hangUp(View view){

    SipServiceCommand.declineIncomingCall(this, ServiceCommunicator.uri, callID1);

        dialPad1Layout.setVisibility(View.VISIBLE);
        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        callHorizontalLayout.setVisibility(View.VISIBLE);
        dtmfKeyPadLayout.setVisibility(View.GONE);
        callTime.setVisibility(View.GONE);
        hangup.setVisibility(View.GONE);
        answer.setVisibility(View.VISIBLE);

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
                    ServiceCommunicator.sipAccountData.setCallId(String.valueOf(callID1));
                }

                SipServiceCommand.setCallHold(this, ServiceCommunicator.uri, Integer.parseInt(ServiceCommunicator.sipAccountData.getCallId()), false);

                hold.setImageResource(R.drawable.hold);


            Toast.makeText(this, "Unholding call !",
                        Toast.LENGTH_LONG).show();

            }
          isHold = !isHold;

    }

    public void transfer(View view){
            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            dtmfKeyPadLayout.setVisibility(View.GONE);
            callOptionsLayout.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            answer.setVisibility(View.GONE);
            overlayTransferLayout.setVisibility(View.VISIBLE);
    }

    public void finalTransfer(View v){


            if(!isHold) {

                SipServiceCommand.toggleCallHold(this, ServiceCommunicator.uri, callID1);

                hold.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.cardview_dark_background)));

                Toast.makeText(this, "Holding before transfer !",
                        Toast.LENGTH_LONG).show();

                SipServiceCommand.makeCall(this, ServiceCommunicator.uri, numberToTransfer, true);
            }
            else if(isHold){

                SipServiceCommand.attendedTransferCall(this, ServiceCommunicator.uri, callID1, callID2);

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

    BroadcastEventReceiver mReceiver = new BroadcastEventReceiver()

    {

    @Override
    public void onRegistration(String accountID, int registrationStateCode) {
        super.onRegistration(accountID, registrationStateCode);
        Log.i(TAG, "onRegistration: ");

        if(registrationStateCode == 200){
            Toast.makeText(context, "Registration OK", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCallState(String accountID, int callID, int callStateCode, int callStatusCode, long connectTimestamp) {
        super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp);

        if(callStateCode == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED){

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
            //TODO
            //Call Time Re-set Counter
        }

        if(callStateCode == pjsip_inv_state.PJSIP_INV_STATE_CONNECTING){
            callsActivity.setVisibility(View.VISIBLE);
            callsHistoryActivity.setVisibility(View.GONE);
            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.VISIBLE);
            callHorizontalLayout.setVisibility(View.GONE);
            callTime.setVisibility(View.VISIBLE);
            answer.setVisibility(View.GONE);
            //TODO
            //Call Time set Counter
            CallsActivity.this.accountID = accountID;
            callID1 = callID;
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

            dialPad1Layout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);

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

        }
    };

    static Boolean active = null;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        active = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }
}