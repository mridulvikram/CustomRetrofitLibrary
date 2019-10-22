package com.mridul.customretrofitlibrary.Network;

import android.content.Context;
import android.util.Log;

import com.mridul.customretrofitlibrary.BaseApplication;
import com.mridul.customretrofitlibrary.R;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mridul on 05/26/18.
 */

public class NetworkCall {

    private final String TAG = NetworkCall.class.getSimpleName();
    private Progress mprogress;
    private NetworkCallBack networkCallBack;
    private Context context;
    private Call<String> call;

    public NetworkCall(NetworkCallBack callBackInterface, Context context) {
        mprogress = new Progress(context);
        mprogress.setCancelable(false);
        networkCallBack = callBackInterface;
        this.context = context;
    }

    public void NetworkAPICall(final String baseUrl, final String apiType, final boolean showprogress) {
        Log.e(TAG, "================" + apiType);
        APIInterface service = BaseApplication.getRetrofitInstance(baseUrl).create(APIInterface.class);
        if (CheckConnection.isConnected(context)) {
            if (showprogress) mprogress.show();
            call = networkCallBack.getAPI(apiType, service);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (showprogress) mprogress.dismiss();
                    if (response.body() != null && response.isSuccessful()) {
                        String jsonString = response.body();
                        try {
                            if (!jsonString.isEmpty()) {
                                JSONObject jsonObject = new JSONObject(jsonString);
                                    networkCallBack.SuccessCallBack(jsonObject, apiType);
                            } else {
                                networkCallBack.ErrorCallBack(context.getString(R.string.jsonparsing_error_message), apiType);
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        networkCallBack.ErrorCallBack(context.getString(R.string.exception_api_error_message), apiType);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (showprogress) mprogress.dismiss();
                    networkCallBack.ErrorCallBack(context.getResources().getString(R.string.exception_api_error_message), apiType);
                }
            });
        } else {
            networkCallBack.ErrorCallBack(context.getString(R.string.internet_error_message), apiType);
        }
    }

    public void cancelRequest() {
        if (call != null)
            call.cancel();
    }
}
