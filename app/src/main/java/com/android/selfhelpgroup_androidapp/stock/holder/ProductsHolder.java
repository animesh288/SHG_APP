package com.android.selfhelpgroup_androidapp.stock.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.stock.listener.ProductClickListener;

public class ProductsHolder extends RecyclerView.ViewHolder {

    public TextView name,quantity,type,mfg,exp;
    ImageView delete;

    public ProductsHolder(@NonNull View itemView, ProductClickListener productClickListener) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        quantity=itemView.findViewById(R.id.quantity);
        type=itemView.findViewById(R.id.type);
        mfg=itemView.findViewById(R.id.mfg);
        exp=itemView.findViewById(R.id.exp);
        delete=itemView.findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(productClickListener!=null){
                    int pos=getAdapterPosition();

                    if(pos!=RecyclerView.NO_POSITION){
                        productClickListener.onDeleteClick(pos);
                    }
                }
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productClickListener!=null){
                    int pos=getAdapterPosition();

                    if(pos!=RecyclerView.NO_POSITION){
                        productClickListener.onItemClick(pos);
                    }
                }
            }
        });
    }
}
