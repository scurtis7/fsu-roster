package com.scurtis.roster.controller;

import com.scurtis.roster.converter.PlayerConverter;
import com.scurtis.roster.dto.PlayerDto;
import com.scurtis.roster.model.player.PlayerRepository;
import com.scurtis.roster.model.player.RecruitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Dec 22, 2019
 **/

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class PlayerRestController {

    private final PlayerRepository playerRepository;
    private final RecruitRepository recruitRepository;
    private final PlayerConverter playerConverter;

    @GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlayerDto> getAllPlayers() {
        log.info("Method: getAllPlayers");
        return playerConverter.playerEntityToPlayerDto(playerRepository.findAll());
    }

    @GetMapping(value = "/recruits/{position}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlayerDto> getRecruits(@PathVariable(value = "position") String position) {
        log.info("Method: getRecruits('{}')", position);
        if (position.equalsIgnoreCase("ALL")) {
            return playerConverter.recruitEntityToPlayerDto(recruitRepository.findAllActiveSorted());
        }
        return playerConverter.recruitEntityToPlayerDto(recruitRepository.findRecruitsByPositionSorted(position));
    }

    @GetMapping(value = "/positions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllPositions() {
        List<String> positions = new ArrayList<>();
        positions.add("ALL");
        positions.addAll(playerRepository.findAllPositions());
        return positions;
    }

}
