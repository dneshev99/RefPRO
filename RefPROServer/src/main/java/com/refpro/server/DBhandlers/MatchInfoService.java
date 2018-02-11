package com.refpro.server.DBhandlers;

import com.refpro.server.DTOs.MatchUpdateDTO;
import com.refpro.server.DTOs.NewMatchInfoDTO;
import com.refpro.server.models.MatchInfo;

import java.util.List;

public interface MatchInfoService {
    String addMatchInfo(NewMatchInfoDTO newMatchInfoDTO);

    void updateMatchInfo(MatchUpdateDTO matchUpdateDTO);

    void addEventToMatch();

    List<MatchInfo> getAllMatchInfo();

    void delete();

    void deleteMatchInfo(String id);
}
