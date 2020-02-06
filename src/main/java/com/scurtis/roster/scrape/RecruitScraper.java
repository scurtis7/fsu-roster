package com.scurtis.roster.scrape;

import com.scurtis.roster.model.player.Player;
import com.scurtis.roster.model.player.Recruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Feb 05, 2020
 **/

@Slf4j
@Service
public class RecruitScraper {

    public List<Recruit> scrapeRecruits(List<Player> players) {
        log.info("scrapeRecruits()");
//        Document doc = getPlayerWebsite();
//        if (doc != null) {
//            log.info(doc.title());
//            return processPlayerWebsite(doc);
//        }

        return new ArrayList<>();
    }

}
