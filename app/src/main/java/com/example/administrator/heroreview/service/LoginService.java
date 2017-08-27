package com.example.administrator.heroreview.service;

import com.example.administrator.heroreview.model.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 21/8/2560.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("login_api.php")
    Call<Login> getLoginData(@Field("username") String usernameString,
                             @Field("password") String passwordString);
}
