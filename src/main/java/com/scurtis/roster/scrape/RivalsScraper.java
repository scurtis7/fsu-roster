package com.scurtis.roster.scrape;

import com.scurtis.roster.model.player.Rivals;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Feb 05, 2020
 **/

@Slf4j
@Service
public class RivalsScraper {

    public List<Rivals> scrapeRivals(String season) {
        log.info("scrapeRecruits()");
        Document doc = getRivalsWebsite(season);
        if (doc != null) {
            log.info(doc.title());
            return processRivalsWebsite(doc);
        }

        return new ArrayList<>();
    }

    private List<Rivals> processRivalsWebsite(Document doc) {
        List<Rivals> rivals = new ArrayList<>();
        Element commitments = doc.select("rv-commitments").first();
        String prospects = commitments.toString().replaceAll("&quot;", "");
        log.info(prospects);

        // todo: add code to get recruits

        return rivals;
    }

    private Document getRivalsWebsite(String season) {
        String website = "https://floridastate.rivals.com/commitments/football/" + season + "/";
        try {
            return Jsoup.connect(website).get();
        } catch (IOException exception) {
            log.error("Unable to get rivals website: {}", exception.getMessage());
            return null;
        }
    }

}
