package com.example.myassignment.api;


import com.example.myassignment.BuildConfig;

public class AppUrlConstant {

    public static final String BASE_URL;

    static {
        if (BuildConfig.DEBUG) {

            BASE_URL = "http://api.iconfinder.com/v3/";
        } else {

            BASE_URL = "http://api.iconfinder.com/v3/";
        }
    }
}