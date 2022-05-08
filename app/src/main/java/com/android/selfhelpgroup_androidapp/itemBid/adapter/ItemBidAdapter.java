package com.android.selfhelpgroup_androidapp.itemBid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.data.modal.BidSubRequest;
import com.android.selfhelpgroup_androidapp.data.modal.Item;
import com.android.selfhelpgroup_androidapp.itemBid.holder.BidHolder;
import com.android.selfhelpgroup_androidapp.itemBid.listener.ItemBidListener;

import java.util.ArrayList;
import java.util.List;

public class ItemBidAdapter extends RecyclerView.Adapter<BidHolder> {

    List<Item> itemList;
    Context context;
    List<BidSubRequest> bidSubRequests;

    public List<BidSubRequest> getBidSubRequests() {
        return bidSubRequests;
    }

    public void setBidSubRequests(List<BidSubRequest> bidSubRequests) {
        this.bidSubRequests = bidSubRequests;
    }

    public ItemBidAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
        bidSubRequests=new ArrayList<>();

        for(int i=0;i<itemList.size();i++){
            bidSubRequests.add(new BidSubRequest(itemList.get(i).getItemId(),0,0));
        }
    }

    @NonNull
    @Override
    public BidHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_item,parent,false);
        return new BidHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BidHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemName.setText(itemList.get(position).getItemName());
        holder.itemQuantity.setText(itemList.get(position).getItemQuantity()+" "+itemList.get(position).getItemUnit());

//        holder.bidQuantity.setText("0");

        holder.bidQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null && s.length()>0) bidSubRequests.get(position).setQuantity(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.bidPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null && s.length()>0) bidSubRequests.get(position).setUnitPrice(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList==null?0:itemList.size();
    }
}
