package com.android.rahmadarifanhr.instadakwah.API;


import com.android.rahmadarifanhr.instadakwah.Bean.Login;
import com.android.rahmadarifanhr.instadakwah.Bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Rahmad Arifan Hr on 11/25/2016.
 */

public interface InstaDakwahService {
    @POST("/login")
    Call<Login> login(@Query("username") String username, @Query("password") String password);

    @GET("/api/users")
    Call<List<User>> getUser(@Header("Authorization") String token);
}
