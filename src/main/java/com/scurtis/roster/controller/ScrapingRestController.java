package com.scurtis.roster.controller;

import com.scurtis.roster.converter.CoachConverter;
import com.scurtis.roster.converter.PlayerConverter;
import com.scurtis.roster.dto.CoachDto;
import com.scurtis.roster.dto.RivalsDto;
import com.scurtis.roster.dto.Two47Dto;
import com.scurtis.roster.exception.SoupConnectionException;
import com.scurtis.roster.model.coach.Coach;
import com.scurtis.roster.model.coach.CoachRepository;
import com.scurtis.roster.model.player.PlayerRepository;
import com.scurtis.roster.model.player.RivalsRepository;
import com.scurtis.roster.model.player.Two47Repository;
import com.scurtis.roster.scrape.CoachScraper;
import com.scurtis.roster.scrape.PlayerScraper;
import com.scurtis.roster.scrape.RivalsScraper;
import com.scurtis.roster.scrape.Two47Scraper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
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
public class ScrapingRestController {

    private final CoachScraper coachScraper;
    private final CoachRepository coachRepository;
    private final CoachConverter coachConverter;
    private final PlayerScraper playerScraper;
    private final PlayerRepository playerRepository;
    private final PlayerConverter playerConverter;
    private final RivalsScraper rivalsScraper;
    private final RivalsRepository rivalsRepository;
    private final Two47Scraper two47Scraper;
    private final Two47Repository two47Repository;

    @GetMapping(value = "/coach", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getCoaches() {
        log.info("Method: getCoaches");
        List<CoachDto> coachDtos = coachScraper.scrapeCoaches();
        List<Coach> coaches = coachConverter.coachDtoListToCoachEntity(coachDtos);
        coachRepository.deleteAll();
        coaches.forEach(coachRepository::save);
        return convertCoaches(coaches);
    }

    @GetMapping(value = "/player/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getPlayers(@PathVariable(value = "year") String year) {
        log.info("Method: getPlayers");
        return playerScraper.scrapePlayers(year);
    }

    @GetMapping(value = "/rivals/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getRivals(@PathVariable(value = "year") String year) {
        log.info("Method: getRivals");
        try {
            List<RivalsDto> commits = rivalsScraper.scrape(year);
//        List<Rivals> rivals = rivalsScraper.scrapeRivals("2020");
//        rivalsRepository.deleteAll();
//        rivalsRepository.saveAll(rivals);
            return convertRivalsDtoToString(commits);
        } catch (SoupConnectionException sce) {
            log.error("Exception: {}", sce.getMessage());
            return Collections.singletonList(sce.getMessage());
        }
    }

    @GetMapping(value = "/247/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getTwo47(@PathVariable(value = "year") String year) {
        log.info("Method: getTwo47");
        try {
            List<Two47Dto> commits = two47Scraper.scrape(year);
//        List<Rivals> rivals = rivalsScraper.scrapeRivals("2020");
//        rivalsRepository.deleteAll();
//        rivalsRepository.saveAll(rivals);
            return convertTwo47DtoToString(commits);
        } catch (SoupConnectionException sce) {
            log.error("Exception: {}", sce.getMessage());
            return Collections.singletonList(sce.getMessage());
        }
    }

    private List<String> convertCoaches(List<Coach> coaches) {
        return coaches.stream()
                .map(coach -> coach.getName() + ", " + coach.getPosition() + ", " + coach.getSport())
                .collect(Collectors.toList());
    }

//    private List<String> convertPlayers(List<Player> players) {
//        return players.stream()
//                .map(player -> player.getJersey() + ", " + player.getName() + ", " + player.getYear())
//                .collect(Collectors.toList());
//    }

    private List<String> convertRivalsDtoToString(List<RivalsDto> commits) {
        List<String> prospects = new ArrayList<>();
        commits.forEach(commit -> {
            prospects.add(commit.getRivalsId() + ", " + commit.getName() + ", " + commit.getCity() + ", " + commit.getState() + ", "
                    + commit.getPosition() + ", " + commit.getHeight() + ", " + commit.getWeight() + ", " + commit.getSign() + ", "
                    + commit.getStars() + ", " + commit.getRating() + ", " + commit.getCommitDate() + ", " + commit.getUrl() + ", "
                    + commit.getStatus() + ", " + commit.getSport() + ", " + commit.getYear()
            );
        });
        return prospects;
    }

    private List<String> convertTwo47DtoToString(List<Two47Dto> commits) {
        List<String> prospects = new ArrayList<>();
        commits.forEach(commit -> {
            prospects.add(commit.getTwo47Id() + ", " + commit.getName() + ", " + commit.getPosition() + ", " + commit.getHeight()
                    + ", " + commit.getWeight() + ", " + commit.getHomeTown() + ", " + commit.getHighSchool() + ", " + commit.getYear()
                    + ", " + commit.getCompositeRank() + ", " + commit.getRankNational() + ", " + commit.getRankPosition()
                    + ", " + commit.getRankState() + ", " + commit.getStars() + ", " + commit.getUrl());
        });
        return prospects;
    }

}
