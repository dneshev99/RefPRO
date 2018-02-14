package com.elsys.refpro.refpromobile.services;

import com.elsys.refpro.refpromobile.dto.TeamDTO;
import com.elsys.refpro.refpromobile.dto.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 14.2.2018 г..
 */

public interface TeamService {
    @GET("/team/getAll")
    Call<List<TeamDTO>> getAllTeams ();

}
