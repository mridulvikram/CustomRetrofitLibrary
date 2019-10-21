package com.mridul.customretrofitlibrary.Network;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;

public interface NetworkCallBack {

    Call<String> getAPI(String apitype, APIInterface service);

    void SuccessCallBack(JSONObject jsonstring, String apitype) throws JSONException;

    void ErrorCallBack(String jsonstring, String apitype);
}