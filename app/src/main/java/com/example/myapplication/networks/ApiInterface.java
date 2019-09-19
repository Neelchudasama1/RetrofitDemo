package com.example.myapplication.networks;

import com.example.myapplication.models.UserResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("getData.php")
    Call<UserResponseModel>getUser();

}
