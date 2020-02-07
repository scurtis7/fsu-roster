package com.scurtis.roster.controller;

import com.scurtis.roster.converter.PlayerConverter;
import com.scurtis.roster.dto.RecruitDto;
import com.scurtis.roster.model.player.PlayerRepository;
import com.scurtis.roster.model.player.RecruitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<RecruitDto> getAllPlayers() {
        log.info("Method: getAllPlayers");
        return playerConverter.playerEntityToPlayerDto(playerRepository.findAll());
    }

    @GetMapping(value = "/recruits/{position}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecruitDto> getRecruits(@PathVariable(value = "position") String position) {
        log.info("Method: getRecruits('{}')", position);
        if (position.equalsIgnoreCase("ALL")) {
            return playerConverter.recruitEntityToPlayerDto(recruitRepository.findAllSorted());
        }
        return playerConverter.recruitEntityToPlayerDto(recruitRepository.findRecruitsByPositionSorted(position));
    }

    @GetMapping(value = "/recruits/jersey/{jersey}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecruitDto> getRecruitsByJersey(@PathVariable(value = "jersey") Integer jersey) {
        log.info("Method: getRecruitsByJersey('{}')", jersey);
        return playerConverter.recruitEntityToPlayerDto(recruitRepository.findRecruitsByJerseySorted(jersey));
    }

    @GetMapping(value = "/positions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllPositions() {
        log.info("Method: getAllPositions()");
        List<String> positions = new ArrayList<>();
        positions.add("ALL");
        positions.addAll(playerRepository.findAllPositions());
        return positions;
    }

    @GetMapping(value = "/jerseys", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllJerseys() {
        log.info("Method: getAllJerseys()");
        List<String> jerseys = new ArrayList<>();
        jerseys.add("ALL");
        jerseys.addAll(playerRepository.findAllJerseys());
        return jerseys;
    }

    @PostMapping(value = "/player", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPlayer(@RequestBody RecruitDto recruitDto) {
        log.info("Method: addPlayer()");
    }

}
