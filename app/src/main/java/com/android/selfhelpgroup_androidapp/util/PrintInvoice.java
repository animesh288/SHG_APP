package com.android.selfhelpgroup_androidapp.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;

import com.android.selfhelpgroup_androidapp.data.model.ApprovedOrder;

public class PrintInvoice {
    Context context;
    ApprovedOrder approvedOrder;

    public PrintInvoice(Context context, ApprovedOrder approvedOrder) {
        this.context = context;
        this.approvedOrder = approvedOrder;
        buildPdf();
    }

    public void buildPdf(){
        PdfDocument pdfDocument=new PdfDocument();

        PdfDocument.PageInfo pageInfo= new PdfDocument.PageInfo.Builder(1120,792,1).create();

        PdfDocument.Page page=pdfDocument.startPage(pageInfo);

        Canvas canvas=page.getCanvas();
        Paint paint=new Paint();
    }

}
