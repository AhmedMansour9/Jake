package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ikon.ikon.Model.CartResponse;
import ikon.ikon.Model.Cart_Details;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.ShowCart_View;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 31/12/2018.
 */

public class ShowCart_Presenter {

    ShowCart_View getService;

    public ShowCart_Presenter(Context context, ShowCart_View getService)
    {
        this.getService=getService;

    }

    public void ShowCart(String lang,String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<CartResponse> call = apiInterface.ShowCart(queryMap);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().getCart().size()!=0) {
                        getService.ShowCart(response.body().getData().getCart());
                        getService.ShowTotalprice(String.valueOf(response.body().getData().getTotalCarts()));

                }else {
                        getService.NoProduct();
                    }
                }
                else {
                    getService.Error();
                }
            }
            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                getService.Error();
            }
        });
    }

}
