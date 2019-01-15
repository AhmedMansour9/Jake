package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.Counter_Response;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.Counter_View;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 03/01/2019.
 */

public class Get_Counter_Presenter {

    Counter_View review;

    public Get_Counter_Presenter(Context context, Counter_View Loginview)
    {
        this.review=Loginview;

    }

    public void GetCounter(String user,String lan) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token","100");
        queryMap.put("lang",lan);
        queryMap.put("user_token", user);


        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<Counter_Response> call = apiInterface.Counter(queryMap);
        call.enqueue(new Callback<Counter_Response>() {
            @Override
            public void onResponse(Call<Counter_Response> call, Response<Counter_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData()!=null) {
                        review.Counter(String.valueOf(response.body().getData().getCount()));
                    }else {
                        review.Error();
                    }
                } else {
                    review.Error();
                }
            }


            @Override
            public void onFailure(Call<Counter_Response> call, Throwable t) {
                review.Error();

            }
        });
    }
}
