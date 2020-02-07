package com.scurtis.roster.scrape;

import com.scurtis.roster.dto.RivalsDto;
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

    private static final String KEY_ID = "{id:";
    private static final String KEY_NAME = "name:";
    private static final String KEY_CITY = "city:";
    private static final String KEY_STATE = "state_abbreviation:";
    private static final String KEY_POSITION = "position_group_abbreviation:";
    private static final String KEY_HEIGHT = "height:";
    private static final String KEY_WEIGHT = "weight:";
    private static final String KEY_SIGN = "sign:";
    private static final String KEY_STARS = "stars:";
    private static final String KEY_RATING = "rivals_rating:";
    private static final String KEY_COMMIT_DATE = "commit_date:";
    private static final String KEY_URL = "url:";
    private static final String KEY_STATUS = "status:";
    private static final String KEY_SPORT = "sport:";
    private static final String KEY_YEAR = "year:";

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
        String temp = commitments.toString().replaceAll("&quot;", "");
        log.info(temp);
        String temp1 = temp.substring(temp.indexOf('[') + 1, temp.indexOf(']'));
        log.info(temp1);
        String[] prospects = temp1.split("},");
        log.info("Number of prospects: {}", prospects.length);
        List<RivalsDto> commits = getRivalsProspects(prospects);
        return rivals;
    }

    private List<RivalsDto> getRivalsProspects(String[] prospects) {
        log.debug("getRivalsProspects()");
        List<RivalsDto> commits = new ArrayList<>();
        for (String entry : prospects) {
            RivalsDto dto = new RivalsDto();
            String[] players = entry.split(",");
            log.debug("Players size: {}", players.length);
            for (String player : players) {
                if (player.contains(KEY_ID)) {
                    dto.setRivalsId(player.substring(KEY_ID.length()));
                } else if (player.contains(KEY_NAME)) {
                    dto.setName(player.substring(KEY_NAME.length()));
                } else if (player.contains(KEY_CITY)) {
                    dto.setCity(player.substring(KEY_CITY.length()));
                } else if (player.contains(KEY_STATE)) {
                    dto.setState(player.substring(KEY_STATE.length()));
                } else if (player.contains(KEY_POSITION)) {
                    dto.setPosition(player.substring(KEY_POSITION.length()));
                } else if (player.contains(KEY_HEIGHT)) {
                    dto.setHeight(player.substring(KEY_HEIGHT.length()));
                } else if (player.contains(KEY_WEIGHT)) {
                    dto.setWeight(player.substring(KEY_WEIGHT.length()));
                } else if (player.contains(KEY_SIGN)) {
                    dto.setSign(player.substring(KEY_SIGN.length()));
                } else if (player.contains(KEY_STARS)) {
                    dto.setStars(player.substring(KEY_STARS.length()));
                } else if (player.contains(KEY_RATING)) {
                    dto.setRating(player.substring(KEY_RATING.length()));
                } else if (player.contains(KEY_COMMIT_DATE)) {
                    dto.setCommitDate(player.substring(KEY_COMMIT_DATE.length()));
                } else if (player.contains(KEY_URL)) {
                    dto.setUrl(player.substring(KEY_URL.length()));
                } else if (player.contains(KEY_STATUS)) {
                    dto.setStatus(player.substring(KEY_STATUS.length()));
                } else if (player.contains(KEY_SPORT)) {
                    dto.setSport(player.substring(KEY_SPORT.length()));
                } else if (player.contains(KEY_YEAR)) {
                    dto.setYear(player.substring(KEY_YEAR.length()));
                }
            }
            commits.add(dto);
        }
        return commits;
    }

    private Document getRivalsWebsite(String season) {
        String website = "https://floridastate.rivals.com/commitments/football/" + season + "/";
        log.info("Website: {}", website);
        try {
            return Jsoup.connect(website).get();
        } catch (IOException exception) {
            log.error("Unable to get rivals website: {}", exception.getMessage());
            return null;
        }
    }

}
