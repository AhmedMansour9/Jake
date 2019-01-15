package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.Categories_Response;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.Categories_View;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 30/12/2018.
 */

public class Categories_Presenter {

    Categories_View getCategories;

    public Categories_Presenter(Context context, Categories_View getCategories) {
        this.getCategories = getCategories;

    }

    public void GetCategories(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Categories_Response> call = apiInterface.categories(queryMap);
        call.enqueue(new Callback<Categories_Response>() {
            @Override
            public void onResponse(Call<Categories_Response> call, Response<Categories_Response> response) {

                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        getCategories.Categories(response.body().getData().getCategories());
                    } else {
                        getCategories.ErrorCategories();
                    }
                } else {
                    getCategories.ErrorCategories();
                }
            }

            @Override
            public void onFailure(Call<Categories_Response> call, Throwable t) {
                getCategories.ErrorCategories();

            }
        });
    }
}

