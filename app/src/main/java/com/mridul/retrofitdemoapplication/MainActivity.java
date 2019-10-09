package com.mridul.retrofitdemoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mridul.retrofitdemoapplication.Network.API;
import com.mridul.retrofitdemoapplication.Network.APIInterface;
import com.mridul.retrofitdemoapplication.Network.NetworkCall;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements NetworkCall.NetworkCallBack, View.OnClickListener{

    private NetworkCall networkCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        networkCall = new NetworkCall(this, this);
        Button getDataBTN = findViewById(R.id.getDataBTN);
        Button sendDataBTN = findViewById(R.id.sendDataBTN);
        getDataBTN.setOnClickListener(this);
        sendDataBTN.setOnClickListener(this);
    }

    @Override
    public Call<String> getAPI(String apitype, APIInterface service) {
        switch (apitype) {
            case API.GET_DATA_API:
                return service.getData(apitype);
            case API.POST_DATA_API:
                Map<String, String> map = new HashMap<>();
                map.put("title", "Mridul");
                map.put("body", "Singh");
                map.put("userId", "1");
                return service.postData(apitype, map);
        }
        return null;
    }

    @Override
    public void SuccessCallBack(JSONObject jsonstring, String apitype) {
        switch (apitype) {
            case API.GET_DATA_API:
                Toast.makeText(MainActivity.this, jsonstring.toString(), Toast.LENGTH_LONG).show();
                break;
            case API.POST_DATA_API:
                Toast.makeText(MainActivity.this, jsonstring.toString(), Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void ErrorCallBack(String jsonstring, String apitype) {
        switch (apitype) {
            default:
                Toast.makeText(this, jsonstring, Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getDataBTN:
                networkCall.NetworkAPICall(API.GET_DATA_API, true);
                break;
            case R.id.sendDataBTN:
                networkCall.NetworkAPICall(API.POST_DATA_API, true);
                break;
        }
    }
}
