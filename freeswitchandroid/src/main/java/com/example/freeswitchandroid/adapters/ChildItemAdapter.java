package com.example.freeswitchandroid.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freeswitchandroid.Pojo.ChildItem;
import com.example.freeswitchandroid.R;

import java.util.List;

public class ChildItemAdapter
        extends RecyclerView
        .Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<ChildItem> ChildItemList;

    // Constructor
    ChildItemAdapter(List<ChildItem> childItemList)
    {
        this.ChildItemList = childItemList;
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
        childViewHolder.childItemTxt.setText(childItem.getChildItemTxt());

        if(childItem.getChildItemImg() == 0){
            childViewHolder.childItemImg.setImageResource(R.drawable.outgoing);
            childViewHolder.childItemDesc.setText(R.string.outgoing);
        }
        else if(childItem.getChildItemImg() == 1){
            childViewHolder.childItemImg.setImageResource(R.drawable.missed);
            childViewHolder.childItemDesc.setText(R.string.missed);
        }
        else if(childItem.getChildItemImg() == 2){
            childViewHolder.childItemImg.setImageResource(R.drawable.incoming);
            childViewHolder.childItemDesc.setText(R.string.incoming);
        }
        else if(childItem.getChildItemImg() == 3){
            childViewHolder.childItemImg.setImageResource(R.drawable.forwarded);
            childViewHolder.childItemDesc.setText(R.string.forwarded);
        }
        else if(childItem.getChildItemImg() == 4){
            childViewHolder.childItemImg.setImageResource(R.drawable.rejected);
            childViewHolder.childItemDesc.setText(R.string.rejected);
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

        ChildViewHolder(View itemView)
        {
            super(itemView);
            childItemTitle = itemView.findViewById(R.id.child_item_title);
            childItemImg = itemView.findViewById(R.id.child_item_img);
            childItemTxt = itemView.findViewById(R.id.child_item_txt);
            childItemDesc = itemView.findViewById(R.id.child_item_description);
        }
    }
}


