package com.scurtis.roster.controller;

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

import java.util.List;

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
    private final PlayerScraper playerScraper;
    private final RivalsScraper rivalsScraper;
    private final Two47Scraper two47Scraper;

    @GetMapping(value = "/coach", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getCoaches() {
        log.info("Method: getCoaches");
        return coachScraper.scrapeCoaches();
    }

    @GetMapping(value = "/player/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getPlayers(@PathVariable(value = "year") String year) {
        log.info("Method: getPlayers");
        return playerScraper.scrapePlayers(year);
    }

    @GetMapping(value = "/rivals/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getRivals(@PathVariable(value = "year") String year) {
        log.info("Method: getRivals");
        return rivalsScraper.scrape(year);
    }

    @GetMapping(value = "/247/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getTwo47(@PathVariable(value = "year") String year) {
        log.info("Method: getTwo47");
        return two47Scraper.scrape(year);
    }

}
