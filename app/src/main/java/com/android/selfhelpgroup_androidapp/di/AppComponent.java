package com.android.selfhelpgroup_androidapp.di;

import com.android.selfhelpgroup_androidapp.AddProductActivity;
import com.android.selfhelpgroup_androidapp.approvedOrders.ui.ApprovedOrderActivity;
import com.android.selfhelpgroup_androidapp.approvedOrders.ui.ApprovedOrderDetails;
import com.android.selfhelpgroup_androidapp.approvedOrders.viewmodel.ApprovedOrderViewModel;
import com.android.selfhelpgroup_androidapp.completedOrders.viewmodel.CompletedOrderViewModel;
import com.android.selfhelpgroup_androidapp.itemBid.ui.BidActivity;
import com.android.selfhelpgroup_androidapp.login.LoginActivity;
import com.android.selfhelpgroup_androidapp.login.OtpActivity;
import com.android.selfhelpgroup_androidapp.orders.adapter.OrderAdapter;
import com.android.selfhelpgroup_androidapp.orders.viewmodel.OrderActivityViewModel;
import com.android.selfhelpgroup_androidapp.stock.adapter.ProductAdapter;
import com.android.selfhelpgroup_androidapp.stock.viewmodel.StockActivityViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,NetworkModule.class})
public interface AppComponent {
    void inject(LoginActivity loginActivity);
    void inject(OtpActivity otpActivity);
    void inject(AddProductActivity addProductActivity);
    void inject(StockActivityViewModel stockActivityViewModel);
    void inject(OrderActivityViewModel orderActivityViewModel);
    void inject(ProductAdapter productAdapter);
    void inject(ApprovedOrderViewModel approvedOrderViewModel);
    void inject(ApprovedOrderDetails approvedOrderDetails);
    void inject(OrderAdapter orderAdapter);
    void inject(BidActivity bidActivity);
    void inject(CompletedOrderViewModel completedOrderViewModel);
}
