package com.example.myassignment.api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* * Interface - It will have method name with request type and response type for each API
 * In this I have one only for login , we can define more too*/
public interface ApiInterface {

    @GET("/v3/iconsets/{icon_set_identifier}/icons")
    Call<ApiResponse> getIconSet(@Path("icon_set_identifier") String iconSetIdentifier, @Query("client_id") String clientId, @Query("client_secret") String clientSecret, @Query("count") int count);


}


