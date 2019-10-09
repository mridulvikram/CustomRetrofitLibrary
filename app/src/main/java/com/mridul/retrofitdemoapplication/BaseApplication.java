package com.mridul.retrofitdemoapplication;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.mridul.retrofitdemoapplication.Network.API;

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

    private static Context appContext;
    private static Retrofit retrofit;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Retrofit getRetrofitInstance() {

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
                .baseUrl(API.BASE_URL)
                .client(httpClient.build()).build();
        return retrofit;
    }
}
