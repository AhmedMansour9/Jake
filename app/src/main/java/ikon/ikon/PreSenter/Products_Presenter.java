package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.Products_Response;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.Products_View;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 30/12/2018.
 */

public class Products_Presenter {
    Products_View getProducts;

    public Products_Presenter(Context context, Products_View getCategories) {
        this.getProducts = getCategories;

    }

    public void GetFeatureProduct(String lang,String id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Products_Response> call = apiInterface.ProductsFeature(queryMap);
        call.enqueue(new Callback<Products_Response>() {
            @Override
            public void onResponse(Call<Products_Response> call, Response<Products_Response> response) {

                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        getProducts.Products(response.body().getData().getProducts());
                    } else {
                        getProducts.ErrorProducts();
                    }
                } else {
                    getProducts.ErrorProducts();
                }
            }

            @Override
            public void onFailure(Call<Products_Response> call, Throwable t) {
                getProducts.ErrorProducts();
            }
        });
    }

    public void GetSlashProduct(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Products_Response> call = apiInterface.Slash(queryMap);
        call.enqueue(new Callback<Products_Response>() {
            @Override
            public void onResponse(Call<Products_Response> call, Response<Products_Response> response) {

                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        if(response.body().getData().getMessage().equals("no product found")){
                            getProducts.EmptyProductsFlash();
                        }else {
                            getProducts.Products(response.body().getData().getProducts());
                        }

                    } else {
                        getProducts.ErrorProducts();
                    }
                } else {
                    getProducts.ErrorProducts();
                }
            }

            @Override
            public void onFailure(Call<Products_Response> call, Throwable t) {
                getProducts.ErrorProducts();
            }
        });
    }
    public void GetFeatureProductsss(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Products_Response> call = apiInterface.ProductsFeature(queryMap);
        call.enqueue(new Callback<Products_Response>() {
            @Override
            public void onResponse(Call<Products_Response> call, Response<Products_Response> response) {

                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        getProducts.Products(response.body().getData().getProducts());
                    } else {
                        getProducts.ErrorProducts();
                    }
                } else {
                    getProducts.ErrorProducts();
                }
            }

            @Override
            public void onFailure(Call<Products_Response> call, Throwable t) {
                getProducts.ErrorProducts();
            }
        });
    }
    public void GetFeatureProductFlash(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Products_Response> call = apiInterface.ProductsFlash(queryMap);
        call.enqueue(new Callback<Products_Response>() {
            @Override
            public void onResponse(Call<Products_Response> call, Response<Products_Response> response) {

                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        if(response.body().getData().getMessage().equals("success")){
                            getProducts.ProductsFlash(response.body().getData().getProducts());

                        }else if(response.body().getData().getMessage().equals("no product found")){
                            getProducts.EmptyProductsFlash();
                        }
                    } else {
                        getProducts.ErrorProductsFlash();
                    }
                } else {
                    getProducts.ErrorProductsFlash();
                }
            }

            @Override
            public void onFailure(Call<Products_Response> call, Throwable t) {
                getProducts.ErrorProductsFlash();
            }
        });
    }


    public void GetFeaturessProduct(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Products_Response> call = apiInterface.ProductssssFeature(queryMap);
        call.enqueue(new Callback<Products_Response>() {
            @Override
            public void onResponse(Call<Products_Response> call, Response<Products_Response> response) {

                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        getProducts.Products(response.body().getData().getProducts());
                    } else {
                        getProducts.ErrorProducts();
                    }
                } else {
                    getProducts.ErrorProducts();
                }
            }

            @Override
            public void onFailure(Call<Products_Response> call, Throwable t) {
                getProducts.ErrorProducts();
            }
        });
    }
}
