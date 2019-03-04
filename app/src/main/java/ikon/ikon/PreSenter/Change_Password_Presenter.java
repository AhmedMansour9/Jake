package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.EditProfile_Response;
import ikon.ikon.Model.UserRegister;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.Edit_Profile_View;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Change_Password_Presenter {

    Edit_Profile_View loginvieew;

    public Change_Password_Presenter(Context context, Edit_Profile_View Loginview)
    {
        this.loginvieew=Loginview;

    }

    public void changeuser(UserRegister user){

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("phone", user.getPhone());
        queryMap.put("firstname", user.getFirstName());
        queryMap.put("email", user.getEmail());
        queryMap.put("user_token", user.getUsertoken());
        queryMap.put("api_token","100");
        queryMap.put("lang","en");
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<EditProfile_Response> call = apiInterface.EditProfile(queryMap);
        call.enqueue(new Callback<EditProfile_Response>() {
            @Override
            public void onResponse(Call<EditProfile_Response> call, Response<EditProfile_Response> response) {


                    if(response.code()==200) {

                        loginvieew.ProfileUpdted();

                    }else if(response.code()==400){
                        loginvieew.showError();
                    }



            }

            @Override
            public void onFailure(Call<EditProfile_Response> call, Throwable t) {
                loginvieew.showError();

            }
        });
    }
}
