package com.android.selfhelpgroup_androidapp.network;

import com.android.selfhelpgroup_androidapp.data.model.ApprovedOrderResponse;
import com.android.selfhelpgroup_androidapp.data.model.BidRequest;
import com.android.selfhelpgroup_androidapp.data.model.CompletedOrderResponse;
import com.android.selfhelpgroup_androidapp.data.model.CompletedRequest;
import com.android.selfhelpgroup_androidapp.data.model.DeleteProductRequest;
import com.android.selfhelpgroup_androidapp.data.model.LoginRequest;
import com.android.selfhelpgroup_androidapp.data.model.LoginResponse;
import com.android.selfhelpgroup_androidapp.data.model.Message;
import com.android.selfhelpgroup_androidapp.data.model.OrderResponse;
import com.android.selfhelpgroup_androidapp.data.model.OtpRequest;
import com.android.selfhelpgroup_androidapp.data.model.OtpResponse;
import com.android.selfhelpgroup_androidapp.data.model.Product;
import com.android.selfhelpgroup_androidapp.data.model.ProductResponse;
import com.android.selfhelpgroup_androidapp.data.model.Profile;

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

    @POST(Constants.ORDER_DELIVERED)
    Call<Message> completed(@Header("Authorization") String token, @Body CompletedRequest completedRequest);


    @GET(Constants.COMPLETED_ORDER_URL)
    Call<CompletedOrderResponse> getCompletedOrders(@Header("Authorization") String token);

    @GET(Constants.PROFILE_URL)
    Call<Profile> getProfile(@Header("Authorization") String token);

}
