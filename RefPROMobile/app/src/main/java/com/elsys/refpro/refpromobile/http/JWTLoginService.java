package com.elsys.refpro.refpromobile.http;

import com.elsys.refpro.refpromobile.http.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by i.ivanov on 15-Dec-17.
 */

interface JWTLoginService {
    @POST("/login")
    Call<Void> login(@Body UserDto creds);
}
