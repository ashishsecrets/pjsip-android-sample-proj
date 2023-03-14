package com.example.freeswitchandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freeswitchandroid.Pojo.ChildItem;
import com.example.freeswitchandroid.Pojo.ParentItem;
import com.example.freeswitchandroid.adapters.ParentItemAdapter;
import com.example.freeswitchandroid.rest.PressOneAPI;
import com.example.freeswitchandroid.rest.RetrofitData;
import com.example.freeswitchandroid.rest.model.BusinessNumber;
import com.example.freeswitchandroid.rest.model.CallsEndDatum;
import com.example.freeswitchandroid.rest.model.UserDatum;

import net.gotev.sipservice.SipServiceCommand;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CallsHistory extends AppCompatActivity {

    PressOneAPI retrofitAPI;

    LinearLayout recycler_list;
    LinearLayout phone_calls_view;
    ActivityManager activityManager;
    ServiceCommunicator serviceCommunicator;
    RecyclerView ParentRecyclerViewItem;
    ParentItemAdapter parentItemAdapter;
    LinearLayoutManager layoutManager;
    List<ParentItem> itemList;

    Spinner myNumber;
    String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls_history);

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

        arraySpinner = new String[]{"No Number Found"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CallsHistory.this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myNumber.setAdapter(adapter);

        getBusinessNumbers();


        phone_calls_view.setVisibility(View.VISIBLE);
        recycler_list.setVisibility(View.GONE);

        initRecycler();
        initSipService();
    }

    private void getBusinessNumbers(){

        SharedPreferences shared = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        String token = shared.getString("token", "");

        Call<UserDatum> call = retrofitAPI.getBusinessNumbers("Bearer " + token);

        call.enqueue(new Callback<UserDatum>() {
            @Override
            public void onResponse(Call<UserDatum> call, Response<UserDatum> response) {
                Toast.makeText(CallsHistory.this, response.message(), Toast.LENGTH_SHORT).show();

                UserDatum userDatum = response.body();
                List<BusinessNumber> list = userDatum.getBusinessNumbers();
                arraySpinner = new String[0];
                if(list != null || !(list.size() == 0)) {
                    arraySpinner = new String[list.size()];
                    for(int i = 0; i < list.size(); i++){
                        arraySpinner[i] = list.get(i).getPhoneNumber();
                    }
                }
                else{
                    arraySpinner = new String[]{"No Business Number Found"};
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CallsHistory.this,
                        android.R.layout.simple_spinner_item, arraySpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myNumber.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<UserDatum> call, Throwable t) {
                Toast.makeText(CallsHistory.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initSipService(){
        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        SipServiceCommand.enableSipDebugLogging(true);

        SharedPreferences shared = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        String username = shared.getString("username", "");

        serviceCommunicator = new ServiceCommunicator();
        serviceCommunicator.username = username;
        //serviceCommunicator.startService(activityManager, this);
    }

    private void initRecycler(){

        ParentRecyclerViewItem = findViewById(R.id.parent_recyclerview);

        // Initialise the Linear layout manager
        layoutManager = new LinearLayoutManager(CallsHistory.this);

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        parentItemAdapter = new ParentItemAdapter(ParentItemList());

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem.setAdapter(parentItemAdapter);
        ParentRecyclerViewItem.setLayoutManager(layoutManager);
    }

    private List<ParentItem> ParentItemList()
    {

        itemList = new ArrayList<>();

        SharedPreferences shared = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        String token = shared.getString("token", "");

        Call<List<CallsEndDatum>> call = retrofitAPI.getCallsData("Bearer " + token);
        
        call.enqueue(new Callback<List<CallsEndDatum>>() {
            @Override
            public void onResponse(Call<List<CallsEndDatum>> call, Response<List<CallsEndDatum>> response) {

                List<CallsEndDatum> callsEndDatumList = response.body();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if(callsEndDatumList != null && callsEndDatumList.size() > 0) {
                        phone_calls_view.setVisibility(View.GONE);
                        recycler_list.setVisibility(View.VISIBLE);

                        final Map<String, TemporalAdjuster> ADJUSTERS = new HashMap<>();

                        ADJUSTERS.put("day", TemporalAdjusters.ofDateAdjuster(d -> d));


                        List<ChildItem> childList = new ArrayList<>();

                        for (CallsEndDatum callsEndDatum : callsEndDatumList) {
                                childList.add(new ChildItem(callsEndDatum.getCallerId(), getCallType(callsEndDatum), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.parse(callsEndDatum.getDateCreated(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSxxx")))));
                            }

                        Map<LocalDate, List<ChildItem>> result = childList.stream()
                                .collect(Collectors.groupingBy(item -> LocalDate.parse(item.getChildItemTxt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
                                        .with(ADJUSTERS.get("day"))));

                        result.entrySet().forEach(x -> itemList.add(new ParentItem(DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(x.getKey()), x.getValue())));

                    }

                }
                else{
                    Toast.makeText(CallsHistory.this, "Please update your phone's software", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CallsEndDatum>> call, Throwable t) {
                Toast.makeText(CallsHistory.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        

        System.out.print("List : " + itemList);

        return itemList;
    }

    public void keypadPress(View v){
        Intent intent = new Intent(CallsHistory.this, CallsActivity.class);
        startActivity(intent);
    }
    
    private int getCallType(CallsEndDatum datum){
        
        int toReturn = 0;
        
        if(datum.getIsDialed()){
            toReturn = 0; // outgoing
        } else if (datum.getIsMissedCall()) {
            toReturn = 1; // missed
        } else if (!datum.getIsDialed()) {
            toReturn = 2; //incoming
        } else if (!datum.getIsForwardedCall()) {
            toReturn = 3; //forwarded
        } else if (!datum.getIsRejectedCall()) {
            toReturn = 4; //rejected
        }

        return toReturn;
    }

    @Override
    protected void onResume() {
        super.onResume();
        parentItemAdapter = new ParentItemAdapter(itemList);
        ParentRecyclerViewItem.setAdapter(parentItemAdapter);
        ParentRecyclerViewItem.setLayoutManager(layoutManager);
    }
}