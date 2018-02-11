package com.refpro.server.DBhandlers;

import com.refpro.server.DTOs.MatchUpdateDTO;
import com.refpro.server.DTOs.NewMatchInfoDTO;
import com.refpro.server.enums.MatchEventTypes;
import com.refpro.server.models.MatchEvent;
import com.refpro.server.models.MatchInfo;
import com.refpro.server.models.PlayerEvent;
import com.refpro.server.models.Team;
import com.refpro.server.repositories.MatchInfoRepository;
import com.refpro.server.repositories.PlayerRepository;
import com.refpro.server.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchInfoHandler implements MatchInfoService {
    @Autowired
    private MatchInfoRepository matchInfoRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public String addMatchInfo(NewMatchInfoDTO newMatchInfoDTO) {
        MatchInfo newEntry = new MatchInfo();

        newEntry.setActive(newMatchInfoDTO.isActive());
        newEntry.setCompetition(newMatchInfoDTO.getCompetition());
        newEntry.setVenue(newMatchInfoDTO.getVenue());
        newEntry.setDate(newMatchInfoDTO.getDate());
        newEntry.setTime(newMatchInfoDTO.getTime());

        Team homeTeam = teamRepository.findByName(newMatchInfoDTO.getHome());
        newEntry.setHome(homeTeam);
        Team awayTeam = teamRepository.findByName(newMatchInfoDTO.getAway());
        newEntry.setAway(awayTeam);


        newEntry.setHomeAbbr(homeTeam.getAbbreaviature());
        newEntry.setAwayAbbr(awayTeam.getAbbreaviature());

//        newEntry.setPlayers(newMatchInfoDTO.getPlayersNumber());
//        newEntry.setSubs(newMatchInfoDTO.getSubstitutesNumber());
        newEntry.setLength(newMatchInfoDTO.getHalfLength());
//        newEntry.setPlayersHome(null);
//        newEntry.setSubsHome(null);
//        newEntry.setPlayersAway(null);
//        newEntry.setSubsAway(null);
//        newEntry.setLog(null);

        MatchInfo matchInfo = matchInfoRepository.save(newEntry);


        return matchInfo.getID();

    }

    @Override
    public void updateMatchInfo(MatchUpdateDTO matchUpdateDTO) {
            System.out.println(matchUpdateDTO.getMatchId());
            MatchInfo entryToUpdate = matchInfoRepository.findOne(matchUpdateDTO.getMatchId());
//            entryToUpdate.setPlayersHome(matchUpdateDTO.getPlayersHome());
//            entryToUpdate.setPlayersAway(matchUpdateDTO.getPlayersAway());
//            entryToUpdate.setSubsAway(matchUpdateDTO.getSubsAway());
//            entryToUpdate.setSubsHome(matchUpdateDTO.getSubsHome());



            matchInfoRepository.save(entryToUpdate);
    }

    @Override
    public void addEventToMatch(){
        PlayerEvent newPlayerEvent = new PlayerEvent();
        MatchInfo matchInfo = matchInfoRepository.findOne("5a806e178abaee2d94e434f8");
        newPlayerEvent.setEventType(MatchEventTypes.GOAL);
        newPlayerEvent.setPlayer(playerRepository.findOne("5a8072b68abaee2028dd1c6d"));
        matchInfo.getEventList().add(newPlayerEvent);
        matchInfoRepository.save(matchInfo);
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
