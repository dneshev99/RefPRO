package com.elsys.refpro.refpromobile.services;

import com.elsys.refpro.refpromobile.dto.UserDTO;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("/login")
    Call<ResponseBody> login(@Body UserDTO body);

    @POST("/user/updateUserToken")
    Call<Void> addFcmTokenForUser(@Body String token);

    @GET("/user/getTokensForUser")
    Call<UserDTO> getFcmTokenForUser();
}
