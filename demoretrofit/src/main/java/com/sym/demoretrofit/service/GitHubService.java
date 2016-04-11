package com.sym.demoretrofit.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xiaoming on 2016/2/29.
 */
public interface GitHubService {

    @GET("users/{user}/repos")
    Call<ResponseBody> listRepos(@Path("user") String user);
}
