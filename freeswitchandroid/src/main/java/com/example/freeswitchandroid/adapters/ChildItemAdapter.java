package com.example.freeswitchandroid.adapters;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freeswitchandroid.CallsHistory;
import com.example.freeswitchandroid.Pojo.ChildItem;
import com.example.freeswitchandroid.R;

import java.util.List;

public class ChildItemAdapter
        extends RecyclerView
        .Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<ChildItem> ChildItemList;
    private Context context;

    // Constructor
    ChildItemAdapter(List<ChildItem> childItemList, Context context)
    {
        this.ChildItemList = childItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i)
    {

        // Here we inflate the corresponding
        // layout of the child item
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.child_item,
                        viewGroup, false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ChildViewHolder childViewHolder,
            int position)
    {

        // Create an instance of the ChildItem
        // class for the given position
        ChildItem childItem = ChildItemList.get(position);

        // For the created instance, set title.
        // No need to set the image for
        // the ImageViews because we have
        // provided the source for the images
        // in the layout file itself
        childViewHolder.childItemTitle.setText(childItem.getChildItemTitle());
        childViewHolder.childItemTxt.setText(childItem.getChildItemTxt().substring(11, 16));

        if(childItem.getChildItemImg() == 0){
            childViewHolder.childItemImg.setImageResource(R.drawable.outgoing);
            childViewHolder.childItemDesc.setText(R.string.outgoing);
            if(childItem.getChildItemTitle().matches("[0-9]+") && childItem.getChildItemTitle().length() > 2){
                childViewHolder.iconImg.setBackground(AppCompatResources.getDrawable(context, R.drawable.plain_bg_caller_blue));
            }
            else {
                childViewHolder.iconImg.setBackground(AppCompatResources.getDrawable(context, R.drawable.plain_bg_caller_no_fill));
                childViewHolder.iconImg.setImageResource(R.drawable.empty_frame);
                childViewHolder.iconTxt.setVisibility(View.VISIBLE);
                childViewHolder.iconTxt.setText(childItem.getChildItemTitle().substring(0,1));
            }
        }
        else if(childItem.getChildItemImg() == 1){
            childViewHolder.childItemImg.setImageResource(R.drawable.missed);
            childViewHolder.childItemDesc.setText(R.string.missed);
            if(childItem.getChildItemTitle().matches("[0-9]+") && childItem.getChildItemTitle().length() > 2){
                childViewHolder.iconImg.setBackground(AppCompatResources.getDrawable(context, R.drawable.plain_bg_caller_green));
            }
            else {
                childViewHolder.iconImg.setBackground(AppCompatResources.getDrawable(context, R.drawable.plain_bg_caller_no_fill));
                childViewHolder.iconImg.setImageResource(R.drawable.empty_frame);
                childViewHolder.iconTxt.setVisibility(View.VISIBLE);
                childViewHolder.iconTxt.setText(childItem.getChildItemTitle().substring(0,1));
            }
        }
        else if(childItem.getChildItemImg() == 2){
            childViewHolder.childItemImg.setImageResource(R.drawable.incoming);
            childViewHolder.childItemDesc.setText(R.string.incoming);
            if(childItem.getChildItemTitle().matches("[0-9]+") && childItem.getChildItemTitle().length() > 2){
                childViewHolder.iconImg.setBackground(AppCompatResources.getDrawable(context, R.drawable.plain_bg_caller_blue));
            }
            else {
                childViewHolder.iconImg.setBackground(AppCompatResources.getDrawable(context, R.drawable.plain_bg_caller_no_fill));
                childViewHolder.iconImg.setImageResource(R.drawable.empty_frame);
                childViewHolder.iconTxt.setVisibility(View.VISIBLE);
                childViewHolder.iconTxt.setText(childItem.getChildItemTitle().substring(0,1));
            }
        }
        else if(childItem.getChildItemImg() == 3){
            childViewHolder.childItemImg.setImageResource(R.drawable.forwarded);
            childViewHolder.childItemDesc.setText(R.string.forwarded);
            if(childItem.getChildItemTitle().matches("[0-9]+") && childItem.getChildItemTitle().length() > 2){
                childViewHolder.iconImg.setBackground(AppCompatResources.getDrawable(context, R.drawable.plain_bg_caller_green));
            }
            else {
                childViewHolder.iconImg.setBackground(AppCompatResources.getDrawable(context, R.drawable.plain_bg_caller_no_fill));
                childViewHolder.iconImg.setImageResource(R.drawable.empty_frame);
                childViewHolder.iconTxt.setVisibility(View.VISIBLE);
                childViewHolder.iconTxt.setText(childItem.getChildItemTitle().substring(0,1));
            }
        }
        else if(childItem.getChildItemImg() == 4){
            childViewHolder.childItemImg.setImageResource(R.drawable.rejected);
            childViewHolder.childItemDesc.setText(R.string.rejected);
            if(childItem.getChildItemTitle().matches("[0-9]+") && childItem.getChildItemTitle().length() > 2){
                childViewHolder.iconImg.setBackground(AppCompatResources.getDrawable(context, R.drawable.plain_bg_caller_green));
            }
            else {
                childViewHolder.iconImg.setBackground(AppCompatResources.getDrawable(context, R.drawable.plain_bg_caller_no_fill));
                childViewHolder.iconImg.setImageResource(R.drawable.empty_frame);
                childViewHolder.iconTxt.setVisibility(View.VISIBLE);
                childViewHolder.iconTxt.setText(childItem.getChildItemTitle().substring(0,1));
            }
        }
    }

    @Override
    public int getItemCount()
    {

        // This method returns the number
        // of items we have added
        // in the ChildItemList
        // i.e. the number of instances
        // of the ChildItemList
        // that have been created
        return ChildItemList.size();
    }

    // This class is to initialize
    // the Views present
    // in the child RecyclerView
    class ChildViewHolder extends RecyclerView.ViewHolder {

        TextView childItemTitle;
        ImageView childItemImg;
        TextView childItemTxt;
        TextView childItemDesc;
        TextView iconTxt;
        ImageView iconImg;

        ChildViewHolder(View itemView)
        {
            super(itemView);
            childItemTitle = itemView.findViewById(R.id.child_item_title);
            childItemImg = itemView.findViewById(R.id.child_item_img);
            childItemTxt = itemView.findViewById(R.id.child_item_txt);
            childItemDesc = itemView.findViewById(R.id.child_item_description);
            iconImg = itemView.findViewById(R.id.icon_img);
            iconTxt = itemView.findViewById(R.id.icon_text);
        }
    }
}


