package com.refpro.server.DBhandlers;

import com.refpro.server.DTOs.MatchUpdateDTO;
import com.refpro.server.DTOs.NewMatchInfoDTO;
import com.refpro.server.models.MatchInfo;
import com.refpro.server.repositories.MatchInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchInfoHandler {
    @Autowired
    private MatchInfoRepository matchInfoRepository;

    public String addMatchInfo(NewMatchInfoDTO newMatchInfoDTO) {
        MatchInfo newEntry = new MatchInfo();

        newEntry.setActive(newMatchInfoDTO.isActive());
        newEntry.setCompetition(newMatchInfoDTO.getCompetition());
        newEntry.setVenue(newMatchInfoDTO.getVenue());
        newEntry.setDate(newMatchInfoDTO.getDate());
        newEntry.setTime(newMatchInfoDTO.getTime());
        newEntry.setHome(newMatchInfoDTO.getHome());
        newEntry.setAway(newMatchInfoDTO.getAway());
        newEntry.setHomeabbr(newMatchInfoDTO.getHomeabbr());
        newEntry.setAwayabbr(newMatchInfoDTO.getAwayabbr());
        newEntry.setPlayers(newMatchInfoDTO.getPlayers());
        newEntry.setSubs(newMatchInfoDTO.getSubs());
        newEntry.setLength(newMatchInfoDTO.getLength());
        newEntry.setPlayersHome(null);
        newEntry.setSubsHome(null);
        newEntry.setPlayersAway(null);
        newEntry.setSubsAway(null);
        newEntry.setLog(null);

        MatchInfo matchInfo = matchInfoRepository.save(newEntry);


        return matchInfo.getID();

    }

    public void updateMatchInfo(MatchUpdateDTO matchUpdateDTO) {
            System.out.println(matchUpdateDTO.getMatchID());
            MatchInfo entryToUpdate = matchInfoRepository.findOne(matchUpdateDTO.getMatchID());
            entryToUpdate.setPlayersHome(matchUpdateDTO.getPlayersHome());
            entryToUpdate.setPlayersAway(matchUpdateDTO.getPlayersAway());
            entryToUpdate.setSubsAway(matchUpdateDTO.getSubsAway());
            entryToUpdate.setSubsHome(matchUpdateDTO.getSubsHome());

            matchInfoRepository.save(entryToUpdate);
    }

    public List<MatchInfo> getAllMatchInfo() {
        return matchInfoRepository.findAll();
    }

    public void delete() {
        matchInfoRepository.deleteAll();
    }
}
