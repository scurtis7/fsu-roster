package com.scurtis.roster.controller;

import com.scurtis.roster.model.coach.Coach;
import com.scurtis.roster.model.coach.CoachRepository;
import com.scurtis.roster.model.player.Player;
import com.scurtis.roster.model.player.PlayerRepository;
import com.scurtis.roster.scrape.CoachScraper;
import com.scurtis.roster.scrape.PlayerScraper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Steve Curtis
 * Date: Jan 31, 2020
 **/

@RestController
@RequestMapping("/api/scrape/")
@RequiredArgsConstructor
@Slf4j
public class ScrapingController {

    private final CoachScraper coachScraper;
    private final CoachRepository coachRepository;
    private final PlayerScraper playerScraper;
    private final PlayerRepository playerRepository;

    @GetMapping(value = "/coach", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getCoaches() {
        log.info("Method: getCoaches");
        List<Coach> coaches = coachScraper.scrapeCoaches();
        coachRepository.deleteAll();
        coaches.forEach(coachRepository::save);
        return convertCoaches(coaches);
    }

    @GetMapping(value = "/player", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getPlayers() {
        log.info("Method: getPlayers");
        List<Player> players = playerScraper.scrapePlayers();
        playerRepository.deleteAll();
        players.forEach(playerRepository::save);
        return convertPlayers(players);
    }

    private List<String> convertCoaches(List<Coach> coaches) {
        return coaches.stream()
                .map(coach -> coach.getName() + ", " + coach.getPosition() + ", " + coach.getSport())
                .collect(Collectors.toList());
    }

    private List<String> convertPlayers(List<Player> players) {
        return players.stream()
                .map(player -> player.getJersey() + ", " + player.getName() + ", " + player.getYear())
                .collect(Collectors.toList());
    }

}
