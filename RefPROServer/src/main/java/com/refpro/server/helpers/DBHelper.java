package com.refpro.server.helpers;

import com.refpro.server.DTOs.MatchEventDTO;
import com.refpro.server.models.MatchEvent;
import com.refpro.server.repositories.MatchEventRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DBHelper {
    @Autowired
    private MatchEventRepository matchEventRepository;

    public void saveMatchEvent(MatchEventDTO matchEventDTO) {
        MatchEvent matchEvent = new MatchEvent(matchEventDTO.getTeam(),
                                               matchEventDTO.getMessage(),
                                               matchEventDTO.getTime());
        matchEventRepository.save(matchEvent);
    }
}
