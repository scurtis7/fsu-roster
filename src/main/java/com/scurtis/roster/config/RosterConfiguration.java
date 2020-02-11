package com.scurtis.roster.config;

import com.scurtis.roster.converter.CoachConverter;
import com.scurtis.roster.converter.PlayerConverter;
import com.scurtis.roster.model.coach.CoachRepository;
import com.scurtis.roster.model.player.PlayerRepository;
import com.scurtis.roster.scrape.CoachScraper;
import com.scurtis.roster.scrape.PlayerScraper;
import com.scurtis.roster.scrape.RivalsScraper;
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
    public CoachScraper coachScraper(CoachRepository coachRepository, CoachConverter coachConverter) {
        return new CoachScraper(coachRepository, coachConverter);
    }

    @Bean
    public PlayerScraper playerScraper(PlayerConverter playerConverter, PlayerRepository playerRepository) {
        return new PlayerScraper(playerConverter, playerRepository);
    }

    @Bean
    public RivalsScraper rivalsScraper() {
        return new RivalsScraper();
    }

    @Bean
    public Two47Scraper two47Scraper() {
        return new Two47Scraper();
    }

}
