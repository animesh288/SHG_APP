package com.android.selfhelpgroup_androidapp.completedOrders.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.completedOrders.listener.CompletedOrderListener;

public class CompletedOrderHolder extends RecyclerView.ViewHolder {

    public TextView instituteName,departmentName,instituteLocation,updateDate,updateTime,instituteContact;


    public CompletedOrderHolder(@NonNull View itemView, CompletedOrderListener completedOrderListener) {
        super(itemView);
        instituteName=itemView.findViewById(R.id.instituteName);
        departmentName=itemView.findViewById(R.id.department);
        instituteLocation=itemView.findViewById(R.id.instituteLocation);
        updateDate=itemView.findViewById(R.id.updateDate);
        updateTime=itemView.findViewById(R.id.updateTime);
        instituteContact=itemView.findViewById(R.id.instituteContact);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(completedOrderListener!=null){
                    int pos=getAdapterPosition();

                    if(pos!=RecyclerView.NO_POSITION){
                        completedOrderListener.onClick(pos);
                    }
                }
            }
        });
    }
}
