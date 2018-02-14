package com.elsys.refpro.refpromobile.services;

import com.elsys.refpro.refpromobile.dto.NewMatchInfoDTO;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CreateMatchService {

    @POST("/MatchInfo/CreateActivity")
    Call<ResponseBody> create(@Body NewMatchInfoDTO body);
}
