package com.scurtis.roster.scrape;

import com.scurtis.roster.dto.RivalsDto;
import com.scurtis.roster.exception.SoupConnectionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Feb 05, 2020
 **/

@Slf4j
@RequiredArgsConstructor
public class RivalsScraper {

    private static final String BASE_URL = "https://floridastate.rivals.com/commitments/football/";

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

    private final ScrapingService scrapingService;

    public List<String> scrape(String season) {
        log.info("scrape()");
        List<String> rivalsList = new ArrayList<>();
        try {
            Document doc = scrapingService.connect(BASE_URL + season + "/");
            if (doc == null) {
                return rivalsList;
            }
            List<RivalsDto> commits =  parse(doc);
            rivalsList = convertRivalsDtoToString(commits);
        } catch (SoupConnectionException exception) {
            rivalsList = Collections.singletonList(exception.getMessage());
        }
        return rivalsList;
    }

    private List<RivalsDto> parse(Document doc) {
        log.info("Document Title: {}", doc.title());
        Element commitments = doc.select("rv-commitments").first();
        String temp = commitments.toString().replaceAll("&quot;", "");
        String temp1 = temp.substring(temp.indexOf('[') + 1, temp.indexOf(']'));
        String[] prospects = temp1.split("},");
        List<RivalsDto> commits = getRivalsProspects(prospects);
        return setPlayerRankings(commits);
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

    private List<RivalsDto> setPlayerRankings(List<RivalsDto> commits) {
        for (RivalsDto commit : commits) {
            getRanking(commit);
        }
        return commits;
    }

    private void getRanking(RivalsDto commit) {
        try {
            String rawRankings = scrapingService.connect(commit.getUrl()).toString();
            log.info("Size of raw rankings page: {}", rawRankings.length());
        } catch (SoupConnectionException exception) {
            // Just ignore for now
            log.error("Exception getting raw rankings: {}", exception.getMessage());
        }
    }

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

}
