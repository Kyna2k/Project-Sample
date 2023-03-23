package com.example.assigment.model;

import java.util.ArrayList;
import java.util.Queue;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceAPI_User {
    String Base_Service = "https://kynalab.com/api/";
    @GET("all-account")
    Observable<ArrayList<user2>> getAllUser();

    @GET("danhnhap")
    Observable<User> getUser(@Query("username") String username, @Query("password") String password);

    @POST("CapNhatUser")
    Observable<Integer> capnhatuser(@Body user2 user1);
}
