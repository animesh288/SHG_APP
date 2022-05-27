package com.android.selfhelpgroup_androidapp.completedOrders.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;

public class CompletedProductHolder extends RecyclerView.ViewHolder {
    public TextView itemName,itemQuantity,price;

    public CompletedProductHolder(@NonNull View itemView) {
        super(itemView);

        itemName=itemView.findViewById(R.id.itemName);
        itemQuantity=itemView.findViewById(R.id.itemQuantity);
        price=itemView.findViewById(R.id.price);
    }
}
