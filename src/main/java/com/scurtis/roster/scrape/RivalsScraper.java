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
import java.util.stream.Collectors;

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
        List<Element> anchors = doc.select("a").stream()
                .filter(aTag -> !aTag.attr("data-sort").isEmpty()).collect(Collectors.toList());;
        return rivals;
    }

    private Document getRivalsWebsite(String season) {
        String website = "https://floridastate.rivals.com/commitments/football/" + season + "/";
        try {
            return Jsoup.connect(website).get();
        } catch (IOException exception) {
            log.error("Unable to get players website: {}", exception.getMessage());
            return null;
        }
    }

}
