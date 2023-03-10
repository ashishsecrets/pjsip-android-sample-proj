package com.example.freeswitchandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.freeswitchandroid.Pojo.ChildItem;
import com.example.freeswitchandroid.Pojo.ParentItem;
import com.example.freeswitchandroid.adapters.ParentItemAdapter;

import org.pjsip.pjsua2.Call;

import java.util.ArrayList;
import java.util.List;

public class CallsHistory extends AppCompatActivity {

    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls_history);

        // Setting Screen Title

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle("Calls");
        actionBar.show();

        Intent i = getIntent();
        text = i.getStringExtra( "username" );

        RecyclerView
                ParentRecyclerViewItem
                = findViewById(
                R.id.parent_recyclerview);

        // Initialise the Linear layout manager
        LinearLayoutManager
                layoutManager
                = new LinearLayoutManager(
                CallsHistory.this);

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        ParentItemAdapter
                parentItemAdapter
                = new ParentItemAdapter(
                ParentItemList());

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem
                .setAdapter(parentItemAdapter);
        ParentRecyclerViewItem
                .setLayoutManager(layoutManager);

    }

    private List<ParentItem> ParentItemList()
    {
        List<ParentItem> itemList
                = new ArrayList<>();

        ParentItem item
                = new ParentItem(
                "15 February, 2023",
                ChildItemList1());
        itemList.add(item);
        ParentItem item1
                = new ParentItem(
                "14 February, 2023",
                ChildItemList2());
        itemList.add(item1);

        return itemList;
    }

    // Method to pass the arguments
    // for the elements
    // of child RecyclerView
    private List<ChildItem> ChildItemList1()
    {
        List<ChildItem> ChildItemList1
                = new ArrayList<>();

        ChildItemList1.add(new ChildItem("+234 888 434 0404"));

        return ChildItemList1;
    }

    private List<ChildItem> ChildItemList2()
    {
        List<ChildItem> ChildItemList2
                = new ArrayList<>();

        ChildItemList2.add(new ChildItem("+91 888 434 0404"));
        ChildItemList2.add(new ChildItem("+91 941 622 7909"));
        ChildItemList2.add(new ChildItem("+91 941 629 0699"));

        return ChildItemList2;
    }

    public void keypadPress(View v){
        Intent intent = new Intent(CallsHistory.this, CallsActivity.class);
        intent.putExtra ( "username", text);
        startActivity(intent);
    }

}