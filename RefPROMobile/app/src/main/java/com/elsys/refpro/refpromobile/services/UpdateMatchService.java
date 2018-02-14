package com.elsys.refpro.refpromobile.services;

import com.elsys.refpro.refpromobile.dto.MatchUpdateDTO;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UpdateMatchService {

    @POST("/MatchInfo/Update")
    Call<ResponseBody> update(@Body MatchUpdateDTO body);
}
