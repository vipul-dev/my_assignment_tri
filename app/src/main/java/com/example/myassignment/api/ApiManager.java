package com.example.myassignment.api;

import android.content.Context;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;


public class ApiManager extends ApiClient {

    private static ApiManager apiManager;
    private Context mContext;
    private String accessToken;
    /*
     * get single instance
     *
     * @param context current application context
     * @return instance of PrefManager */

    public ApiManager(Context context) {
        super(context);
        this.mContext = context;

    }

    public static ApiManager getInstance(Context context) {
        if (apiManager == null) {
            apiManager = new ApiManager(context);
        }
        return apiManager;
    }


    public void getIconSet(ApiCallBack<ApiResponse> callBack,String iconSetIdentifier,String clientId,String clientSecret,int count){
        ApiClient.current(mContext).getIconSet(iconSetIdentifier,clientId,clientSecret,count).enqueue(callBack);
    }


}
