package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.CartResponse;
import ikon.ikon.Model.Sales_Response;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.Sales_View;
import ikon.ikon.Viewes.ShowCart_View;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 06/01/2019.
 */

public class SalesMan_Presenter {

    Sales_View getService;

    public SalesMan_Presenter(Context context, Sales_View getService)
    {
        this.getService=getService;

    }

    public void ShowSales(String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", "en");
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Sales_Response> call = apiInterface.getSalesMans(queryMap);
        call.enqueue(new Callback<Sales_Response>() {
            @Override
            public void onResponse(Call<Sales_Response> call, Response<Sales_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().getSalesMan()!=null) {
                        getService.GetSalesMan(response.body().getData().getSalesMan());

                    }else {
                        getService.showError("");
                    }
                }
                else {
                    getService.showError("");
                }
            }
            @Override
            public void onFailure(Call<Sales_Response> call, Throwable t) {
                getService.showError("");
            }
        });
    }

}
