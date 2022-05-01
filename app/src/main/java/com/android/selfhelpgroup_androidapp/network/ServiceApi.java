package com.android.selfhelpgroup_androidapp.network;

import com.android.selfhelpgroup_androidapp.data.modal.ApprovedOrderResponse;
import com.android.selfhelpgroup_androidapp.data.modal.BidRequest;
import com.android.selfhelpgroup_androidapp.data.modal.DeleteProductRequest;
import com.android.selfhelpgroup_androidapp.data.modal.LoginRequest;
import com.android.selfhelpgroup_androidapp.data.modal.LoginResponse;
import com.android.selfhelpgroup_androidapp.data.modal.Message;
import com.android.selfhelpgroup_androidapp.data.modal.OrderResponse;
import com.android.selfhelpgroup_androidapp.data.modal.OtpRequest;
import com.android.selfhelpgroup_androidapp.data.modal.OtpResponse;
import com.android.selfhelpgroup_androidapp.data.modal.Product;
import com.android.selfhelpgroup_androidapp.data.modal.ProductResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceApi {

    @POST(Constants.LOGIN_URL)
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @POST(Constants.OTP_URL)
    Call<OtpResponse> getOtpResponse(@Body OtpRequest otpRequest);

    @GET(Constants.ORDERS_URL)
    Call<OrderResponse> getOrders(@Header("Authorization") String token);

    @GET(Constants.APPROVED_ORDER_URL)
    Call<ApprovedOrderResponse> getApprovedOrders(@Header("Authorization") String token);

    @GET(Constants.GET_PRODUCT_URL)
    Call<ProductResponse> getProducts(@Header("Authorization") String token);

    @POST(Constants.ADD_PRODUCT_URL)
    Call<Message> addProduct(@Header("Authorization") String token, @Body Product product);

    @HTTP(method = "DELETE",path = Constants.DELETE_PRODUCT_URL, hasBody = true)
    Call<Message> deleteProduct(@Header("Authorization") String token,@Body DeleteProductRequest deleteProductRequest);

    @POST(Constants.BID_URL)
    @Headers({"Content-type: application/json"})
    Call<Message> bidProduct(@Header("Authorization") String token, @Body BidRequest bidRequest);

}
