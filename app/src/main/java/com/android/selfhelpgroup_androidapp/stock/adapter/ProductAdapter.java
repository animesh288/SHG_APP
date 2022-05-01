package com.android.selfhelpgroup_androidapp.stock.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.data.modal.DeleteProductRequest;
import com.android.selfhelpgroup_androidapp.data.modal.Message;
import com.android.selfhelpgroup_androidapp.data.modal.Product;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;
import com.android.selfhelpgroup_androidapp.stock.holder.ProductsHolder;
import com.android.selfhelpgroup_androidapp.stock.listener.ProductClickListener;
import com.android.selfhelpgroup_androidapp.util.BaseApplication;
import com.android.selfhelpgroup_androidapp.util.NetworkUtil;
import com.android.selfhelpgroup_androidapp.util.SessionManager;
import com.android.selfhelpgroup_androidapp.util.Token;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductAdapter extends RecyclerView.Adapter<ProductsHolder> {

    private List<Product> productList;
    Context context;
    SimpleDateFormat simpleDateFormat;
    ProductClickListener listener;
    private AlertDialog mDialog;
    private int mListRowPosition;
    private ProgressDialog pd;
    ServiceApi serviceApi;
    Intent intent;
    AlertDialog.Builder builder;

    @Inject
    Retrofit retrofit;

    public ProductAdapter(ProductClickListener listener,Context context) {
        this.listener = listener;
        this.context=context;
        pd=new ProgressDialog(context);

        ((BaseApplication)context.getApplicationContext()).getNetworkComponent().inject(this);
        serviceApi=retrofit.create(ServiceApi.class);

        initDialog();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new ProductsHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsHolder holder, int position) {

        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

        Date date=productList.get(position).getExpiryDate();
        String exp="";
        if(date!=null) exp=simpleDateFormat.format(date);

        date=productList.get(position).getManufacturingDate();
        String mfg="";
        if(date!=null) mfg=simpleDateFormat.format(date);

        holder.name.setText(productList.get(position).getName());
        holder.type.setText(productList.get(position).getType());
        if(exp.length()>0) holder.exp.setText("समाप्ति तिथि"+" : "+exp);
        if(mfg.length()>0) holder.mfg.setText("िर्माण तिथि"+" : "+mfg);
//        holder.quantity.setText(productList.get(position).getQuantity()+"kg");
    }

    @Override
    public int getItemCount() {
        return productList==null?0:productList.size();
    }

    public void showDialog(int position){
        mListRowPosition=position;
        if(mDialog!=null) mDialog.show();
    }
    private void createCall() {
        if(!NetworkUtil.isNetworkConnected(context)) {
            Toast.makeText(context, "Internet not connected", Toast.LENGTH_SHORT).show();
            return;
        }
        Call<Message> call=serviceApi.deleteProduct("Bearer "+ new SessionManager(context).getToken(),new DeleteProductRequest(productList.get(mListRowPosition).getId()));
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Log.i("animesh", String.valueOf(response.code()));
                if(response.code()==400) Toast.makeText(context, R.string.delete_product_approved_order, Toast.LENGTH_LONG).show();
                else if(response.isSuccessful()) Toast.makeText(context, "product deleted", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
        pd.dismiss();
    }
    private void initDialog() {
        builder=new AlertDialog.Builder(context);
        builder.setMessage("delete product?")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pd.show();
                        createCall();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        mDialog=builder.create();
    }
}
