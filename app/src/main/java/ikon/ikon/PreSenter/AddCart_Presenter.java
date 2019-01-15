package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.CartResponse;
import ikon.ikon.Model.CartUpdate_Response;
import ikon.ikon.Model.Cart_Response;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.Cart_View;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 02/01/2019.
 */

public class AddCart_Presenter {

    Cart_View cart_view;

    public AddCart_Presenter(Context context, Cart_View cart_view)
    {
        this.cart_view=cart_view;

    }

    public void Add_toCart(String lang,String user,String product_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("products_id", product_id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Cart_Response> call = apiInterface.AddCart(queryMap);
        call.enqueue(new Callback<Cart_Response>() {
            @Override
            public void onResponse(Call<Cart_Response> call, Response<Cart_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().getMessage().equals("Product already exists")){
                        cart_view.Youhavethisproduct();
                    }else {
                        cart_view.Success();
                    }
                } else {
                    cart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<Cart_Response> call, Throwable t) {

                cart_view.Failed();

            }
        });
    }
    public void Delete_toCart(String lang,String user,String product_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("customers_basket_id", product_id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<CartResponse> call = apiInterface.DeleteCart(queryMap);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {

                if (response.isSuccessful()) {
                    cart_view.DeleteCart(response.body().getData().getCounter());
                } else {
                    cart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                cart_view.Failed();

            }
        });
    }

    public void UpdateCart(String lang,String user,String product_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("customers_basket_id", product_id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<CartUpdate_Response> call = apiInterface.UpdateCart(queryMap);
        call.enqueue(new Callback<CartUpdate_Response>() {
            @Override
            public void onResponse(Call<CartUpdate_Response> call, Response<CartUpdate_Response> response) {

                if (response.isSuccessful()) {
                    cart_view.Success();
                } else {
                    cart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<CartUpdate_Response> call, Throwable t) {
                cart_view.Failed();

            }
        });
    }

    public void MinusCart(String lang,String user,String product_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("customers_basket_id", product_id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<CartUpdate_Response> call = apiInterface.MinusCart(queryMap);
        call.enqueue(new Callback<CartUpdate_Response>() {
            @Override
            public void onResponse(Call<CartUpdate_Response> call, Response<CartUpdate_Response> response) {

                if (response.isSuccessful()) {
                    cart_view.Success();
                } else {
                    cart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<CartUpdate_Response> call, Throwable t) {
                cart_view.Failed();

            }
        });
    }

}

