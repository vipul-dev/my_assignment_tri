package com.example.myassignment.api;



public interface ApiResponseListener<T> {
    void onApiSuccess(T response, String apiName);
    void onApiError(String response, String apiName);
    void onApiFailure(String failureMessage, String apiName);
}
