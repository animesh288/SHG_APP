package com.android.selfhelpgroup_androidapp.approvedOrders.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;

import org.w3c.dom.Text;

public class ApprovedProductHolder extends RecyclerView.ViewHolder {

    public TextView itemName,itemQuantity,price;

    public ApprovedProductHolder(@NonNull View itemView) {
        super(itemView);

        itemName=itemView.findViewById(R.id.itemName);
        itemQuantity=itemView.findViewById(R.id.itemQuantity);
        price=itemView.findViewById(R.id.price);
    }
}
