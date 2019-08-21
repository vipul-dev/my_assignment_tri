package com.example.myassignment.api;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static ApiClient apiClient = null;
    private ApiInterface apiInterface;

    private Request.Builder requestBuilder;

    ApiClient(Context context) {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        httpClient.connectTimeout(100, TimeUnit.SECONDS);
        httpClient.readTimeout(100, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                requestBuilder = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });


        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppUrlConstant.BASE_URL.trim())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }


    public static ApiInterface current(Context context) {
        return getInstance(context).getApiInterface();
    }

    public static ApiClient getInstance(Context context) {
        if (apiClient == null) {
            apiClient = new ApiClient(context);
        }
        return apiClient;
    }

    private ApiInterface getApiInterface() {
        return apiInterface;
    }

}
