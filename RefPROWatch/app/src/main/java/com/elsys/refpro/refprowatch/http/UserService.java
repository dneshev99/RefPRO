package com.elsys.refpro.refprowatch.http;

import com.elsys.refpro.refprowatch.http.dto.MatchInfoDTO;
import com.elsys.refpro.refprowatch.models.Match;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @POST("/user/updateUserToken")
    Call<Void> addFcmTokenForUser(@Body String token);

    @GET("/matchInfo/getMatchById")
    Call<MatchInfoDTO> getMatchInformation(@Query("id") String id);
}
