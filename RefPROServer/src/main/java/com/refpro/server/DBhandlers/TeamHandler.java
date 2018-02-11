package com.refpro.server.DBhandlers;

import com.refpro.server.DTOs.TeamDto;
import com.refpro.server.models.Team;
import com.refpro.server.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamHandler {

    @Autowired
    private TeamRepository teamRepository;

    public String createNewTeam(TeamDto teamDto){

        Team team = new Team();
        team.setAbbreaviature(teamDto.getAbbreaviature());
        team.setCountry(teamDto.getCountry());
        team.setName(teamDto.getName());
        teamRepository.save(team);
        return team.getId();
    }

    public List<TeamDto> getAllTeams(){
        List<TeamDto> result = new ArrayList<>();
        List<Team> allTeams = teamRepository.findAll();
        if(allTeams!=null){
            for(Team team:allTeams){
                result.add(new TeamDto(team));
            }
        }
        return result;
    }
}
