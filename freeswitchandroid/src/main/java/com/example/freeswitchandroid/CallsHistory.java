package com.example.freeswitchandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.freeswitchandroid.Pojo.ChildItem;
import com.example.freeswitchandroid.Pojo.ParentItem;
import com.example.freeswitchandroid.adapters.ParentItemAdapter;

import org.pjsip.pjsua2.Call;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CallsHistory extends AppCompatActivity {


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

        List<ChildItem> childItemList1 = new ArrayList<>();
        childItemList1.add(new ChildItem("+234 888 434 0404", 0, "10:38 am"));
        childItemList1.add(new ChildItem("+234 888 434 0404", 1, "10:33 am"));
        ParentItem item = new ParentItem("15 February, 2023", childItemList1);
        itemList.add(item);


        List<ChildItem> childItemList2 = new ArrayList<>();

        childItemList2.add(new ChildItem("+91 888 434 0404", 2, "11:43 am"));
        childItemList2.add(new ChildItem("+91 941 622 7909", 3, "9:03 pm"));
        childItemList2.add(new ChildItem("+91 941 629 0699", 4, "4:14 pm"));
        ParentItem item1 = new ParentItem("14 February, 2023", childItemList2);
        itemList.add(item1);

        return itemList;
    }

    public void keypadPress(View v){
        Intent intent = new Intent(CallsHistory.this, CallsActivity.class);
        startActivity(intent);
    }

}