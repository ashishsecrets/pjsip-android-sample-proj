package com.example.freeswitchandroid;

import static com.example.freeswitchandroid.ServiceCommunicator.arraySpinner;
import static com.example.freeswitchandroid.ServiceCommunicator.businessNumbers;
import static com.example.freeswitchandroid.ServiceCommunicator.map;
import static com.example.freeswitchandroid.ServiceCommunicator.userDatum;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.freeswitchandroid.Pojo.ChildItem;
import com.example.freeswitchandroid.Pojo.ParentItem;
import com.example.freeswitchandroid.Pojo.TransferData;
import com.example.freeswitchandroid.rest.PressOneAPI;
import com.example.freeswitchandroid.rest.RetrofitData;
import com.example.freeswitchandroid.rest.model.CallDetail;
import com.example.freeswitchandroid.rest.model.UserDatum;

import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
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

public class MainActivity extends AppCompatActivity {

    String authToken;
    PressOneAPI retrofitAPI;

    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        imageButton = findViewById(R.id.imageButton);

        ServiceCommunicator.itemList = new ArrayList<>();
        ServiceCommunicator.transferList = new ArrayList<>();
        businessNumbers = new ArrayList<>();
        ServiceCommunicator.map = new HashMap<>();

        SharedPreferences shared = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        authToken = shared.getString("token", "");

        if(!authToken.isEmpty()){
            imageButton.setVisibility(View.GONE);
            Retrofit retrofit = RetrofitData.getRetrofit();
            retrofitAPI = retrofit.create(PressOneAPI.class);
            getBusinessNumbers();
        }

    }

    public void initialLoginClick(View view) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
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
                if(businessNumbers != null && !(businessNumbers.size() == 0)) {
                    ServiceCommunicator.arraySpinner = new String[businessNumbers.size()];
                    for(int i = 0; i < businessNumbers.size(); i++){
                        ServiceCommunicator.arraySpinner[i] = businessNumbers.get(i).getPhoneNumber();
                        ServiceCommunicator.map.put(businessNumbers.get(i).getPhoneNumber(), businessNumbers.get(i));
                    }
                    ServiceCommunicator.apiHasRetrievedNumbers = true;
                    ParentItemList();
                }
                else{
                    ServiceCommunicator.arraySpinner = new String[]{"No Business Number Found"};
                    ParentItemList();
                }

            }

            @Override
            public void onFailure(Call<UserDatum> call, Throwable t) {
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
            Call<List<CallDetail>> call = retrofitAPI.getCallsData("Bearer " + token, ServiceCommunicator.map.values().iterator().next().getId().toString());

            call.enqueue(new Callback<List<CallDetail>>() {
                @Override
                public void onResponse(Call<List<CallDetail>> call, Response<List<CallDetail>> response) {

                    List<CallDetail> callsEndDatumList = response.body();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        if (callsEndDatumList != null && callsEndDatumList.size() > 0) {

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

                            Map<LocalDate, List<ChildItem>> result = childList.stream()
                                    .collect(Collectors.groupingBy(item -> LocalDate.parse(item.getChildItemTxt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
                                            .with(ADJUSTERS.get("day"))));

                            result.entrySet().forEach(x -> ServiceCommunicator.itemList.add(new ParentItem(DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(x.getKey()), x.getValue())));

                            Intent intent = new Intent(MainActivity.this, CallsActivity.class);
                            startActivity(intent);
                        }

                    }

                }

                @Override
                public void onFailure(Call<List<CallDetail>> call, Throwable t) {

                }
            });

        }

        Intent intent = new Intent(MainActivity.this, CallsActivity.class);
        startActivity(intent);

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
}