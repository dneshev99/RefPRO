package com.elsys.refpro.refpromobile.http;

import com.elsys.refpro.refpromobile.http.dto.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by user on 3.12.2017 г..
 */

public interface LoginService {

    @FormUrlEncoded
    @POST("/login")
    Call<UserDto> login(@Field("username") String username, @Field("password") String password);

    @GET("/Login/Get")
    Call<List<UserDto>> listRepos();

    @GET("/")
    Call<String> g();
}
