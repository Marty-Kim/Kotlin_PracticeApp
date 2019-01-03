package marty.ration.com.flitto_kimeunchan.http;

import marty.ration.com.flitto_kimeunchan.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dev.Dec on 2018-11-04
 */
public class ServerRequest {

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    public static Retrofit getService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();


        return retrofit;
    }
}
