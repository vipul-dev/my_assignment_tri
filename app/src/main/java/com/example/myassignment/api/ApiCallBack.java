package com.example.myassignment.api;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;


import com.example.myassignment.R;
import com.example.myassignment.util.LogUtils;
import com.example.myassignment.util.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiCallBack<T> implements Callback<T> {
    private ApiResponseListener<T> apiListener;
    private String apiName;
    private Context mContext;

    public ApiCallBack(ApiResponseListener<T> apiListener, String apiName, Context mContext) {
        this.apiListener = apiListener;
        this.apiName = apiName;
        this.mContext = mContext;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {

        LogUtils.LOGD("Response:", new Gson().toJson(response));
        Log.d("assert_log", "ApiCallBack - onResponse : " + new Gson().toJson(response));

        if (response.code() == 200) {
            apiListener.onApiSuccess(response.body(), apiName);
        } else {
            String apiResponseError = "";
            try {
                if (response.code() == 401) {
                    ToastUtils.showToastShort(mContext,response.message());

                } else if (response.code() == 404) {

                    ToastUtils.showToastShort(mContext,response.message());
                } else {
                    if (response.body() != null) {
                        apiResponseError = new Gson().toJson(response.errorBody().string());
                        LogUtils.LOGE("Error:", new Gson().toJson(response.errorBody().string()));
                    } else {
                        LogUtils.LOGE("Token expired===>", "something went wrong!");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            apiListener.onApiError(apiResponseError, apiName);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        LogUtils.LOGE("FAILURE", t.getMessage());
        Log.d("assert_log", "ApiCallBack - onFailure : " + t.getMessage());
        apiListener.onApiFailure(t.toString(), apiName);
    }
}