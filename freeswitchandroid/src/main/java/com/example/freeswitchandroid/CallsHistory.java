package com.example.freeswitchandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freeswitchandroid.Pojo.ChildItem;
import com.example.freeswitchandroid.Pojo.ParentItem;
import com.example.freeswitchandroid.adapters.ParentItemAdapter;
import com.example.freeswitchandroid.rest.PressOneAPI;
import com.example.freeswitchandroid.rest.RetrofitData;
import com.example.freeswitchandroid.rest.model.CallsEndDatum;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CallsHistory extends AppCompatActivity {

    PressOneAPI retrofitAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls_history);

        // Setting Screen Title

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayOptions(ActionBar. DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_action_bar);
        actionBar.show();

        SharedPreferences shared = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        String mobileNumber = shared.getString("username", "");

        TextView myNumber = findViewById(R.id.my_number);

        myNumber.setText(MessageFormat.format("0{0}", mobileNumber));

        initRecycler();
    }

    private void initRecycler(){

        RecyclerView ParentRecyclerViewItem = findViewById(R.id.parent_recyclerview);

        // Initialise the Linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(CallsHistory.this);

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(ParentItemList());

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem.setAdapter(parentItemAdapter);
        ParentRecyclerViewItem.setLayoutManager(layoutManager);
    }

    private List<ParentItem> ParentItemList()
    {

        List<ParentItem> itemList = new ArrayList<>();
        
        Retrofit retrofit = RetrofitData.getRetrofit();

        retrofitAPI = retrofit.create(PressOneAPI.class);

        SharedPreferences shared = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        String token = shared.getString("token", "");

        Call<List<CallsEndDatum>> call = retrofitAPI.getCallsData(token);
        
        call.enqueue(new Callback<List<CallsEndDatum>>() {
            @Override
            public void onResponse(Call<List<CallsEndDatum>> call, Response<List<CallsEndDatum>> response) {
                Toast.makeText(CallsHistory.this, response.toString(), Toast.LENGTH_SHORT).show();

                List<CallsEndDatum> callsEndDatumList = response.body();


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if(callsEndDatumList != null && callsEndDatumList.size() > 0) {

                        final Map<String, TemporalAdjuster> ADJUSTERS = new HashMap<>();

                        ADJUSTERS.put("day", TemporalAdjusters.ofDateAdjuster(d -> d));

                        List<ChildItem> dynamicList = new ArrayList<>();

                        for (CallsEndDatum callsEndDatum : callsEndDatumList) {
                            dynamicList.add(new ChildItem(callsEndDatum.getBusinessNumber().toString(), 0, DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss").format(LocalDateTime.parse(callsEndDatum.getDateCreated(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))));
                        }

                        Map<LocalDate, List<ChildItem>> result = dynamicList.stream()
                                .collect(Collectors.groupingBy(item -> LocalDate.parse(item.getChildItemTxt(), DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss"))
                                        .with(ADJUSTERS.get(ChronoField.DAY_OF_YEAR))));


                        dynamicList.stream().map(x -> itemList.add(new ParentItem(DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(LocalDateTime.parse(x.getChildItemTxt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))), result.get(LocalDate.parse(x.getChildItemTxt(), DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss"))))));

                    }

                }
                else{
                    Toast.makeText(CallsHistory.this, "Please update your phone's software", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CallsEndDatum>> call, Throwable t) {

            }
        });


//
//
//        List<ChildItem> childItemList2 = new ArrayList<>();
//
//        childItemList2.add(new ChildItem("+91 888 434 0404", 2, "11:43 am"));
//        childItemList2.add(new ChildItem("+91 941 622 7909", 3, "9:03 pm"));
//        childItemList2.add(new ChildItem("+91 941 629 0699", 4, "4:14 pm"));
//        ParentItem item1 = new ParentItem("14 February, 2023", childItemList2);
//        itemList.add(item1);

        return itemList;
    }

    public void keypadPress(View v){
        Intent intent = new Intent(CallsHistory.this, CallsActivity.class);
        startActivity(intent);
    }
    
    private int getCallType(String string){
        
        int toReturn = 0;
        
        if(string.equals("isOutgoing")){
            toReturn = 0;
        } else if (string.equals("isMissed")) {
            toReturn = 1;
        } else if (string.equals("isIncoming")) {
            toReturn = 2;
        } else if (string.equals("isForwarded")) {
            toReturn = 3;
        } else if (string.equals("isRejected")) {
            toReturn = 4;
        }

        return toReturn;
    }

}