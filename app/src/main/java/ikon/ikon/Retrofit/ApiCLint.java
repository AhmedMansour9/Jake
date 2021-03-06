package ikon.ikon.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.FacebookSdk.getCacheDir;

/**
 * Created by HP on 04/09/2018.
 */

public class ApiCLint {

//    public static final String BASE_URL="http://192.168.1.22:8080/ikon/api/";
public static final String BASE_URL="https://jak-go.com/api/";
//    public static final String BASE_URL="http://localhost:83/api_Marrage/";


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(getCacheDir(), cacheSize);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }
}
