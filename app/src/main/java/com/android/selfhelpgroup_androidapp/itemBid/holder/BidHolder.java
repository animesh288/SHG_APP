package com.android.selfhelpgroup_androidapp.itemBid.holder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.itemBid.listener.ItemBidListener;

public class BidHolder extends RecyclerView.ViewHolder {

    public TextView itemName,itemQuantity;;
    public EditText bidQuantity;

    public BidHolder(@NonNull View itemView) {
        super(itemView);

        itemName=itemView.findViewById(R.id.itemName);
        itemQuantity=itemView.findViewById(R.id.itemQuantity);
        bidQuantity=itemView.findViewById(R.id.bidQuantity);



//        remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(itemBidListener!=null){
//                    int pos=getAdapterPosition();
//
//                    if(pos!=RecyclerView.NO_POSITION){
//                        itemBidListener.onRemoveClick(pos);
//                    }
//                }
//            }
//        });


    }
}
