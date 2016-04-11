package com.sym.demoretrofit.service;

import com.sym.demoretrofit.bean.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by xiaoming on 2016/4/7.
 * this is just simple example
 */
public interface UserService {

    @GET("UserService/login")
    Call<ResponseBody> login(@Query("username") String username, @Query("password") String password);

    @FormUrlEncoded
    @POST("UserService/register")
    Call<ResponseBody> register(@Field("username") String username, @Field("password") String password);

    @GET("UserService/getUser")
    Call<User> getUser();

}
