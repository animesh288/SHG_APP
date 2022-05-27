package com.android.selfhelpgroup_androidapp.orders.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.orders.listener.OrderClickListener;

public class OrderHolder extends RecyclerView.ViewHolder {

    public TextView instituteName,departmentName,instituteLocation,updateDate,updateTime;

    public OrderHolder(@NonNull View itemView, OrderClickListener orderClickListener) {
        super(itemView);

        instituteName=itemView.findViewById(R.id.instituteName);
        departmentName=itemView.findViewById(R.id.departmentName);
        instituteLocation=itemView.findViewById(R.id.instituteLocation);
        updateDate=itemView.findViewById(R.id.updateDate);
        updateTime=itemView.findViewById(R.id.updateTime);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderClickListener!=null){
                    int pos=getAdapterPosition();

                    if(pos!=RecyclerView.NO_POSITION){
                        orderClickListener.onClick(pos);
                    }
                }
            }
        });
    }
}
