package com.elsys.refpro.refpromobile.services;

import com.elsys.refpro.refpromobile.dto.MatchInfoDTO;
import com.elsys.refpro.refpromobile.dto.MatchUpdateDTO;
import com.elsys.refpro.refpromobile.dto.NewMatchInfoDTO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MatchService {

    String MATCH_INFO_ENDPOINT = "matchInfo";

    @POST("/"+MATCH_INFO_ENDPOINT+"/create")
    Call<ResponseBody> create(@Body NewMatchInfoDTO body);

    @GET("/"+MATCH_INFO_ENDPOINT+"/getMatchById")
    Call<MatchInfoDTO> getMatchById(@Query("id") String id);

    @POST("/"+MATCH_INFO_ENDPOINT+"/update")
    Call<ResponseBody> updateMatchInfo(@Body MatchUpdateDTO dto);
}
