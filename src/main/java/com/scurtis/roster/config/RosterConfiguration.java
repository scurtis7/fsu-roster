package com.scurtis.roster.config;

import com.scurtis.roster.converter.CoachConverter;
import com.scurtis.roster.converter.PlayerConverter;
import com.scurtis.roster.model.coach.CoachRepository;
import com.scurtis.roster.model.player.PlayerRepository;
import com.scurtis.roster.model.player.RivalsRepository;
import com.scurtis.roster.model.player.Two47Repository;
import com.scurtis.roster.scrape.CoachScraper;
import com.scurtis.roster.scrape.PlayerScraper;
import com.scurtis.roster.scrape.RivalsScraper;
import com.scurtis.roster.scrape.ScrapingService;
import com.scurtis.roster.scrape.Two47Scraper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Steve Curtis
 * Date: Feb 09, 2020
 **/

@Configuration
public class RosterConfiguration {

    @Bean
    public CoachConverter coachConverter() {
        return new CoachConverter();
    }

    @Bean
    public PlayerConverter playerConverter() {
        return new PlayerConverter();
    }

    @Bean
    public ScrapingService scrapingService() {
        return new ScrapingService();
    }

    @Bean
    public CoachScraper coachScraper(ScrapingService scrapingService, CoachRepository coachRepository, CoachConverter coachConverter) {
        return new CoachScraper(scrapingService, coachRepository, coachConverter);
    }

    @Bean
    public PlayerScraper playerScraper(ScrapingService scrapingService, PlayerConverter playerConverter, PlayerRepository playerRepository) {
        return new PlayerScraper(scrapingService, playerConverter, playerRepository);
    }

    @Bean
    public RivalsScraper rivalsScraper(ScrapingService scrapingService, RivalsRepository rivalsRepository, PlayerRepository playerRepository) {
        return new RivalsScraper(scrapingService, rivalsRepository, playerRepository);
    }

    @Bean
    public Two47Scraper two47Scraper(ScrapingService scrapingService, Two47Repository two47Repository, PlayerRepository playerRepository) {
        return new Two47Scraper(scrapingService, two47Repository, playerRepository);
    }

}
