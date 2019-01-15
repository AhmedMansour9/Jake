package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.Order_Response;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.OrderService_View;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 03/01/2019.
 */

public class Order_Presenter {

    OrderService_View orderService_view;

    public Order_Presenter(Context context, OrderService_View registerView)
    {
        this.orderService_view=registerView;

    }


    public void Order_Service(String Usertoken,String lang,String phone,String city,String Address,
                              String Name,String Note,String mandobid,String lat,String lon) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("user_token", Usertoken);
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("phone", phone);
        queryMap.put("city", city);
        queryMap.put("address", Address);
        queryMap.put("firstname", Name);
        queryMap.put("comments", Note);
        if(mandobid!=null) {
            queryMap.put("salesman_id", mandobid);
        }
        queryMap.put("lat", lat);
        queryMap.put("lon", lon);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Order_Response> call = apiInterface.Order(queryMap);
        call.enqueue(new Callback<Order_Response>() {
            @Override
            public void onResponse(Call<Order_Response> call, Response<Order_Response> response) {

                if (response.isSuccessful()) {
                    orderService_view.Orders(response.body().getData().getOrderId());
                } else {
                    orderService_view.Error();
                }
            }
            @Override
            public void onFailure(Call<Order_Response> call, Throwable t) {
                orderService_view.Error();

            }
        });
    }

}
