package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.ProfileResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.ProfileView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 27/12/2018.
 */

public class ProfilePresenter {
    Context context;
    ProfileView profileView;

    public ProfilePresenter(Context context, ProfileView profileView) {
        this.context = context;
        this.profileView = profileView;
    }
    public  void getProfileData(String user_token) {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",user_token );

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<ProfileResponse> call=apiInterface.profileData( map );
        call.enqueue( new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful()) {
                    profileView.showProfileData(response.body().getData());
                }else {
                    profileView.Errorprofile();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                profileView.Errorprofile();

            }
        } );

    }

}
