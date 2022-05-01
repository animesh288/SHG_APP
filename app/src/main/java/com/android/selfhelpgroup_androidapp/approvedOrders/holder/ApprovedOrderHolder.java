package com.android.selfhelpgroup_androidapp.approvedOrders.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;

public class ApprovedOrderHolder extends RecyclerView.ViewHolder {

    public TextView name,type,department,institute,quantity;

    public ApprovedOrderHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        type=itemView.findViewById(R.id.type);
        department=itemView.findViewById(R.id.department);
        institute=itemView.findViewById(R.id.institute);
        quantity=itemView.findViewById(R.id.itemquantity);
    }
}
