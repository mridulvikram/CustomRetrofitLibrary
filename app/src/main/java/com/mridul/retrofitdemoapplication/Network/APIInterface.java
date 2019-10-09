package com.mridul.retrofitdemoapplication.Network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface APIInterface {

    @FormUrlEncoded
    @POST
    Call<String> postData(@Url String url, @FieldMap Map<String, String> fieldMap);

    @GET
    Call<String> getData(@Url String url);
}