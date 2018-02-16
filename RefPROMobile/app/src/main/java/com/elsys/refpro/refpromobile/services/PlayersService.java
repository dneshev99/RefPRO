package com.elsys.refpro.refpromobile.services;


import com.elsys.refpro.refpromobile.dto.PlayerDTO;
import com.elsys.refpro.refpromobile.dto.TeamDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlayersService {

    @GET("player/getPlayersByTeam/{teamName}")
    Call<List<PlayerDTO>> getPlayersByTeam (@Path("teamName") String teamName);
}
