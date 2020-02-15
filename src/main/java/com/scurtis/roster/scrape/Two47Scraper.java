package com.scurtis.roster.scrape;

import com.scurtis.roster.dto.Two47Dto;
import com.scurtis.roster.exception.SoupConnectionException;
import com.scurtis.roster.model.player.Player;
import com.scurtis.roster.model.player.PlayerRepository;
import com.scurtis.roster.model.player.Two47;
import com.scurtis.roster.model.player.Two47Repository;
import com.scurtis.roster.model.player.Two47Unmatched;
import com.scurtis.roster.model.player.Two47UnmatchedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Feb 08, 2020
 **/

@Slf4j
@RequiredArgsConstructor
public class Two47Scraper {

    private static final String HTTPS = "https:";
    private static final String BASE_URL = "https://247sports.com/college/florida-state/Season/";
    private static final String FOOTBALL_COMMITS = "-Football/Commits/";

    private final ScrapingService scrapingService;
    private final Two47Repository two47Repository;
    private final Two47UnmatchedRepository two47UnmatchedRepository;
    private final PlayerRepository playerRepository;

    public List<String> scrape(String season) {
        log.info("Method: scrape()");
        List<String> two47List = new ArrayList<>();
        try {
            Document doc = scrapingService.connect(BASE_URL + season + FOOTBALL_COMMITS);
            if (doc == null) {
                two47List.add("   === Unable to get website data ===   ");
                return two47List;
            }

            List<Two47Dto> commits =  parse(doc, season);
            two47List = saveCommits(commits);
        } catch (SoupConnectionException exception) {
            two47List = Collections.singletonList(exception.getMessage());
        }
        return two47List;
    }

    private List<Two47Dto> parse(Document doc, String season) {
        log.info("Document Title: {}", doc.title());
        List<Element> anchors = doc.getElementsByClass("ri-page__name-link");
        return getCommits(anchors, season);
    }

    private List<Two47Dto> getCommits(List<Element> anchors, String season) {
        List<Two47Dto> commits = new ArrayList<>();
        anchors.forEach(anchor -> {
            String href = anchor.attr("href");
            String id = href.substring(href.lastIndexOf('-') + 1);
            String website = HTTPS + href;
            try {
                Document doc = scrapingService.connect(website);
                commits.add(getPlayer(doc, id, season, website));
            } catch (SoupConnectionException sce) {
                // Just ignore for now
                log.error(sce.getMessage());
            }
        });
        return commits;
    }

    private Two47Dto getPlayer(Document doc, String id, String season, String url) {
        Two47Dto player = new Two47Dto();
        player.setSiteId(id);
        player.setYear(season);
        player.setLink(url);
        player.setName(doc.getElementsByClass("name").first().text());
        Element metrics = doc.getElementsByClass("metrics-list").first();
        List<Element> listItems = metrics.select("li");
        listItems.forEach(listItem -> {
            if (listItem.selectFirst("span").text().contains("Pos")) {
                player.setPosition(listItem.select("span").get(1).text());
            } else if (listItem.selectFirst("span").text().contains("Height")) {
                player.setHeight(listItem.select("span").get(1).text());
            } else if (listItem.selectFirst("span").text().contains("Weight")) {
                player.setWeight(listItem.select("span").get(1).text());
            }
        });

        Element details = doc.getElementsByClass("details").first();
        listItems = details.select("li");
        listItems.forEach(listItem -> {
            if (listItem.selectFirst("span").text().contains("High School")) {
                player.setHighSchool(listItem.select("span").get(1).select("a").text());
            } else if (listItem.selectFirst("span").text().contains("Home Town")) {
                player.setHomeTown(listItem.select("span").get(1).text());
            }
        });

        if (doc.getElementsByClass("composite-rank") != null &&
                doc.getElementsByClass("composite-rank").first() != null &&
                doc.getElementsByClass("composite-rank").first().text() != null) {
            String rank = doc.getElementsByClass("composite-rank").first().text();
            player.setCompositeRank(rank.replaceAll("i", "").trim());
        } else {
            player.setCompositeRank("");
        }
        Element starsBlock = doc.getElementsByClass("stars-block").first();
        if (starsBlock != null) {
            List<Element> starElements = starsBlock.select("span");
            long stars = starElements.stream().filter(span -> span.className().contains("yellow")).count();
            player.setStars(Long.toString(stars));
        } else {
            player.setStars("0");
        }

        Element rankList = doc.getElementsByClass("ranks-list").first();
        if (rankList != null) {
            rankList.select("li").forEach(rankItem -> {
                if (rankItem.selectFirst("h5").text().contains("Natl")) {
                    player.setRankNational(rankItem.selectFirst("strong").text());
                } else if (rankItem.selectFirst("a").attr("href").contains("Position=")) {
                    player.setRankPosition(rankItem.selectFirst("strong").text());
                } else if (rankItem.selectFirst("a").attr("href").contains("State=")) {
                    player.setRankState(rankItem.selectFirst("strong").text());
                }
            });
        } else {
            player.setRankNational("");
            player.setRankPosition("");
            player.setRankState("");
        }

        return player;
    }

    private List<String> saveCommits(List<Two47Dto> commits) {
        log.info("Method: saveCommits(), number of commits: {}", commits.size());
        List<String> commitList = new ArrayList<>();
        commitList.add("   === Noles 247 Commits Added ===   ");
        for (Two47Dto commit : commits) {
            log.info("Commit Name: {}", commit.getName());
            if (two47Repository.find247Player(commit.getSiteId()) == null) {
                log.info("  Commit not in database, save it now");
                Player player = findPlayer(commit);
                if (player != null) {
                    log.info("  Player found in database, saving 247 Recruit");
                    Two47 two47 = new Two47();
                    two47.setPlayerId(player.getPlayerId());
                    two47.setSiteId(commit.getSiteId());
                    two47.setName(commit.getName());
                    two47.setPosition(commit.getPosition());
                    two47.setHeight(commit.getHeight());
                    two47.setWeight(commit.getWeight());
                    two47.setHomeTown(commit.getHomeTown());
                    two47.setHighSchool(commit.getHighSchool());
                    two47.setYear(commit.getYear());
                    two47.setCompositeRank(commit.getCompositeRank());
                    two47.setRankNational(commit.getRankNational());
                    two47.setRankPosition(commit.getRankPosition());
                    two47.setRankState(commit.getRankState());
                    two47.setStars(commit.getStars());
                    two47.setLink(commit.getLink());
                    two47Repository.save(two47);
                    log.info("    Noles 247 Recruit added -> {}", two47.getName());
                    commitList.add(convertTwo47DtoToString(commit));
                } else {
                    log.info("  Player not found in database, will not save 247 Recruit");
                    saveUnmatched(commit);
                }
            }
        }
        if (commitList.size() == 1) {
            log.info("=== No Noles 247 Commits Added ===");
            commitList = Collections.singletonList("   === No Noles 247 Commits Added ===   ");
        }
        log.info("Number of commits added: {}", commitList.size());
        return commitList;
    }

    private void saveUnmatched(Two47Dto commit) {
        Two47Unmatched unmatched = new Two47Unmatched();
        unmatched.setSiteId(commit.getSiteId());
        unmatched.setName(commit.getName());
        unmatched.setPosition(commit.getPosition());
        unmatched.setHeight(commit.getHeight());
        unmatched.setWeight(commit.getWeight());
        unmatched.setHomeTown(commit.getHomeTown());
        unmatched.setHighSchool(commit.getHighSchool());
        unmatched.setYear(commit.getYear());
        unmatched.setCompositeRank(commit.getCompositeRank());
        unmatched.setRankNational(commit.getRankNational());
        unmatched.setRankPosition(commit.getRankPosition());
        unmatched.setRankState(commit.getRankState());
        unmatched.setStars(commit.getStars());
        unmatched.setLink(commit.getLink());
        two47UnmatchedRepository.save(unmatched);
    }

    private Player findPlayer(Two47Dto commit) {
        Player player = playerRepository.findPlayerByNameUpperCase(commit.getName());
        if (player != null) {
            log.info("Player found");
            return player;
        }
        log.info("Player NOT found, comparing last name");
        String[] names =commit.getName().split(" ");
        String lastName = names[1];
        List<Player> players = playerRepository.findAll();
        for (Player person : players) {
            if (person.getName().contains(lastName) && person.getYear().equals(commit.getYear())) {
                log.info("Player found by looking for last name");
                player = person;
                break;
            }
        }
        if (player == null) {
            log.info("Player not found by looking for last name");
        }
        return player;
    }

    private String convertTwo47DtoToString(Two47Dto commit) {
        return commit.getSiteId() + ", " + commit.getName() + ", " + commit.getPosition() + ", " + commit.getHeight()
                + ", " + commit.getWeight() + ", " + commit.getHomeTown() + ", " + commit.getHighSchool() + ", " + commit.getYear()
                + ", " + commit.getCompositeRank() + ", " + commit.getRankNational() + ", " + commit.getRankPosition()
                + ", " + commit.getRankState() + ", " + commit.getStars() + ", " + commit.getLink();
    }

}
