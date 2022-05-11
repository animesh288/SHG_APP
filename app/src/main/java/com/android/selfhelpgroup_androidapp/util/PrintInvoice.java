package com.android.selfhelpgroup_androidapp.util;

import android.content.Context;

import com.android.selfhelpgroup_androidapp.data.model.ApprovedOrder;

public class PrintInvoice {
    Context context;
    ApprovedOrder approvedOrder;

    public PrintInvoice(Context context, ApprovedOrder approvedOrder) {
        this.context = context;
        this.approvedOrder = approvedOrder;
    }


}
