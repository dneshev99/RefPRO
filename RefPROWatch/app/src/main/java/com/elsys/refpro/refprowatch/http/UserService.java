package com.elsys.refpro.refprowatch.http;

import com.elsys.refpro.refprowatch.models.Match;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("/user/updateUserToken")
    Call<Void> addFcmTokenForUser(@Body String token);

    @GET("/MatchInfo/{id}")
    Call<ResponseBody> getMatchInformation(@Path("id") String id);
}
