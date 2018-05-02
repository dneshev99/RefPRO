package com.refpro.server.DBhandlers;

import com.refpro.server.DTOs.PlayerDTO;
import com.refpro.server.exception.AbstractRestException;
import com.refpro.server.exception.PlayerNotFoundExeption;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by i.ivanov on 30-Apr-18.
 */
public interface PlayerService {
    List<String> createPlayer(List<PlayerDTO> players) throws AbstractRestException;

    List<PlayerDTO> getPlayersByTeam(String name) throws Exception;

    void addPlayerIcon(String playerId, MultipartFile file) throws IOException, PlayerNotFoundExeption, AbstractRestException;

    ByteArrayResource getPlayerIcon(String playerId) throws AbstractRestException,IOException;
}
