package com.refpro.server.DBhandlers;

import com.refpro.server.DTOs.MatchUpdateDTO;
import com.refpro.server.DTOs.NewMatchInfoDTO;
import com.refpro.server.models.MatchInfo;
import com.refpro.server.repositories.MatchInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchInfoHandler implements MatchInfoService {
    @Autowired
    private MatchInfoRepository matchInfoRepository;

    @Override
    public String addMatchInfo(NewMatchInfoDTO newMatchInfoDTO) {
        MatchInfo newEntry = new MatchInfo();

        newEntry.setActive(newMatchInfoDTO.isActive());
        newEntry.setCompetition(newMatchInfoDTO.getCompetition());
        newEntry.setVenue(newMatchInfoDTO.getVenue());
        newEntry.setDate(newMatchInfoDTO.getDate());
        newEntry.setTime(newMatchInfoDTO.getTime());
        newEntry.setHome(newMatchInfoDTO.getHome());
        newEntry.setAway(newMatchInfoDTO.getAway());
        newEntry.setHomeAbbr(newMatchInfoDTO.getHomeabbr());
        newEntry.setAwayAbbr(newMatchInfoDTO.getAwayabbr());
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

    @Override
    public void updateMatchInfo(MatchUpdateDTO matchUpdateDTO) {
            System.out.println(matchUpdateDTO.getMatchId());
            MatchInfo entryToUpdate = matchInfoRepository.findOne(matchUpdateDTO.getMatchId());
            entryToUpdate.setPlayersHome(matchUpdateDTO.getPlayersHome());
            entryToUpdate.setPlayersAway(matchUpdateDTO.getPlayersAway());
            entryToUpdate.setSubsAway(matchUpdateDTO.getSubsAway());
            entryToUpdate.setSubsHome(matchUpdateDTO.getSubsHome());

            matchInfoRepository.save(entryToUpdate);
    }

    @Override
    public List<MatchInfo> getAllMatchInfo() {
        return matchInfoRepository.findAll();
    }

    @Override
    public void delete() {
        matchInfoRepository.deleteAll();
    }

    @Override
    public void deleteMatchInfo(String id) {
        matchInfoRepository.delete(id);
    }
}
