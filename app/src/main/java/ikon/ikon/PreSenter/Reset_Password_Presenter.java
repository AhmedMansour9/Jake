package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.ResetPassword_Response;
import ikon.ikon.Model.UserLoginResponse;
import ikon.ikon.Model.UserRegister;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.LoginView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 08/01/2019.
 */

public class Reset_Password_Presenter {

    LoginView loginvieew;

    public Reset_Password_Presenter(Context context, LoginView Loginview)
    {
        this.loginvieew=Loginview;

    }

    public void Reset(String email) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("email", email);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<ResetPassword_Response> call = apiInterface.ResetPass(queryMap);
        call.enqueue(new Callback<ResetPassword_Response>() {
            @Override
            public void onResponse(Call<ResetPassword_Response> call, Response<ResetPassword_Response> response) {

                if (response.code()==200) {
                    loginvieew.openMain("sucess");
                }
            else if(response.code()==400) {
                loginvieew.invalideemail("Invalide Email");

            }
            }


            @Override
            public void onFailure(Call<ResetPassword_Response> call, Throwable t) {
                loginvieew.showError("");

            }
        });
    }
}

