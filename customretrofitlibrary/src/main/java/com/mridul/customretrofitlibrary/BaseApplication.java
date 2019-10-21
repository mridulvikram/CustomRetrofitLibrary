package com.mridul.customretrofitlibrary;

import android.support.multidex.MultiDexApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by mridul on 09/13/18.
 */
public class BaseApplication extends MultiDexApplication {

    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Retrofit getRetrofitInstance(String baseUrl) {

        if (retrofit != null)
            return retrofit;

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .build();
                return chain.proceed(request);
            }
        }).connectTimeout(13, TimeUnit.SECONDS)
                .readTimeout(13, TimeUnit.SECONDS)
                .writeTimeout(13, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(baseUrl)
                .client(httpClient.build()).build();
        return retrofit;
    }
}
