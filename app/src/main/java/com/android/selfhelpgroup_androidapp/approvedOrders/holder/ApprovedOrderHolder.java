package com.android.selfhelpgroup_androidapp.approvedOrders.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.listener.ApprovedOrderListener;

public class ApprovedOrderHolder extends RecyclerView.ViewHolder {

    public TextView instituteName,departmentName,instituteLocation,updateDate,updateTime,instituteContact;

    public ApprovedOrderHolder(@NonNull View itemView, ApprovedOrderListener approvedOrderListener) {
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
                if(approvedOrderListener!=null){
                    int pos=getAdapterPosition();

                    if(pos!=RecyclerView.NO_POSITION){
                        approvedOrderListener.onClick(pos);
                    }
                }
            }
        });
    }
}
