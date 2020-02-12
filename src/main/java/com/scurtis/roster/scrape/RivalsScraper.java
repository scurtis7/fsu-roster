package com.scurtis.roster.scrape;

import com.scurtis.roster.dto.RivalsDto;
import com.scurtis.roster.exception.SoupConnectionException;
import com.scurtis.roster.model.player.Player;
import com.scurtis.roster.model.player.PlayerRepository;
import com.scurtis.roster.model.player.Rivals;
import com.scurtis.roster.model.player.RivalsRepository;
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
    private final RivalsRepository rivalsRepository;
    private final PlayerRepository playerRepository;

    public List<String> scrape(String season) {
        log.info("Method: scrape()");
        List<String> rivalsList = new ArrayList<>();
        try {
            Document doc = scrapingService.connect(BASE_URL + season + "/");
            if (doc == null) {
                return rivalsList;
            }
            List<RivalsDto> commits =  parse(doc);
            rivalsList = saveCommits(commits);
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
                    dto.setSiteId(player.substring(KEY_ID.length()));
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
                    dto.setLink(player.substring(KEY_URL.length()));
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
            String rawRankings = scrapingService.connect(commit.getLink()).toString();
            log.info("Size of raw rankings page: {}", rawRankings.length());
        } catch (SoupConnectionException exception) {
            // Just ignore for now
            log.error("Exception getting raw rankings: {}", exception.getMessage());
        }
    }

    private List<String> saveCommits(List<RivalsDto> commits) {
        List<String> commitList = new ArrayList<>();
        commitList.add("   === Rivals Commits Added ===   ");
        for (RivalsDto commit : commits) {
            if (rivalsRepository.findRivalsPlayer(commit.getSiteId()) == null) {
                Player player = playerRepository.findPlayer(commit.getName(), commit.getYear());
                if (player != null) {
                    Rivals rivalsCommit = new Rivals();
                    rivalsCommit.setPlayer(player);
                    rivalsCommit.setSiteId(commit.getSiteId());
                    rivalsCommit.setName(commit.getName());
                    rivalsCommit.setCity(commit.getCity());
                    rivalsCommit.setState(commit.getState());
                    rivalsCommit.setPosition(commit.getPosition());
                    rivalsCommit.setHeight(commit.getHeight());
                    rivalsCommit.setWeight(commit.getWeight());
                    rivalsCommit.setSign(commit.getSign());
                    rivalsCommit.setStars(commit.getStars());
                    rivalsCommit.setRating(commit.getRating());
                    rivalsCommit.setCommitDate(commit.getCommitDate());
                    rivalsCommit.setLink(commit.getLink());
                    rivalsCommit.setStatus(commit.getStatus());
                    rivalsCommit.setSport(commit.getSport());
                    rivalsCommit.setYear(commit.getYear());
                    rivalsCommit.setRankNational(commit.getRankNational());
                    rivalsCommit.setRankPosition(commit.getRankPosition());
                    rivalsCommit.setRankState(commit.getRankState());
                    rivalsRepository.save(rivalsCommit);
                    commitList.add(convertRivalsDtoToString(commit));
                }
            }
        }
        if (commitList.size() == 1) {
            commitList = Collections.singletonList("   === No Rivals Commits Added ===   ");
        }
        return commitList;
    }

    private String convertRivalsDtoToString(RivalsDto commit) {
        return commit.getSiteId() + ", " + commit.getName() + ", " + commit.getCity() + ", " + commit.getState() + ", "
                + commit.getPosition() + ", " + commit.getHeight() + ", " + commit.getWeight() + ", " + commit.getSign() + ", "
                + commit.getStars() + ", " + commit.getRating() + ", " + commit.getCommitDate() + ", " + commit.getLink() + ", "
                + commit.getStatus() + ", " + commit.getSport() + ", " + commit.getYear();
    }

}
