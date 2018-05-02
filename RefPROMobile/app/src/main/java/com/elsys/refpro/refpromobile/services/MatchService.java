package com.elsys.refpro.refpromobile.services;

import com.elsys.refpro.refpromobile.dto.MatchInfoDTO;
import com.elsys.refpro.refpromobile.dto.NewMatchInfoDTO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MatchService {

    @POST("/matchInfo/create")
    Call<ResponseBody> create(@Body NewMatchInfoDTO body);

    @GET("/matchInfo/getMatchById")
    Call<MatchInfoDTO> getMatchById(@Query("id") String id);
}
