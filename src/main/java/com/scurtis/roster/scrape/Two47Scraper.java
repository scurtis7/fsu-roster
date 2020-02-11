package com.scurtis.roster.scrape;

import com.scurtis.roster.dto.Two47Dto;
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
 * Date: Feb 08, 2020
 **/

@Slf4j
@RequiredArgsConstructor
public class Two47Scraper {

    private static final String HTTPS = "https:";
    private static final String BASE_URL = "https://247sports.com/college/florida-state/Season/";
    private static final String FOOTBALL_COMMITS = "-Football/Commits/";

    private final ScrapingService scrapingService;

    public List<String> scrape(String season) {
        log.info("Method: scrape()");
        List<String> two47List = new ArrayList<>();
        try {
            Document doc = scrapingService.connect(BASE_URL + season + FOOTBALL_COMMITS);
            if (doc == null) {
                return two47List;
            }

            List<Two47Dto> commits =  parse(doc, season);
            two47List = convertTwo47DtoToString(commits);
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
        player.setTwo47Id(id);
        player.setYear(season);
        player.setUrl(url);
        List<Element> headers = doc.getElementsByClass("profile-header");
        log.info("Number of headers: {}", headers.size());
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

        String rank = doc.getElementsByClass("composite-rank").first().text();
        if (rank != null && rank.contains("i")) {
            player.setCompositeRank(rank.replaceAll("i", "").trim());
        }
        Element starsBlock = doc.getElementsByClass("stars-block").first();
        List<Element> starElements = starsBlock.select("span");
        long stars = starElements.stream().filter(span -> span.className().contains("yellow")).count();
        player.setStars(Long.toString(stars));

        Element rankList = doc.getElementsByClass("ranks-list").first();
        rankList.select("li").forEach(rankItem -> {
            if (rankItem.selectFirst("h5").text().contains("Natl")) {
                player.setRankNational(rankItem.selectFirst("strong").text());
            } else if (rankItem.selectFirst("a").attr("href").contains("Position=")) {
                player.setRankPosition(rankItem.selectFirst("strong").text());
            } else if (rankItem.selectFirst("a").attr("href").contains("State=")) {
                player.setRankState(rankItem.selectFirst("strong").text());
            }
        });

        return player;
    }

    private List<String> convertTwo47DtoToString(List<Two47Dto> commits) {
        List<String> prospects = new ArrayList<>();
        commits.forEach(commit -> {
            prospects.add(commit.getTwo47Id() + ", " + commit.getName() + ", " + commit.getPosition() + ", " + commit.getHeight()
                    + ", " + commit.getWeight() + ", " + commit.getHomeTown() + ", " + commit.getHighSchool() + ", " + commit.getYear()
                    + ", " + commit.getCompositeRank() + ", " + commit.getRankNational() + ", " + commit.getRankPosition()
                    + ", " + commit.getRankState() + ", " + commit.getStars() + ", " + commit.getUrl());
        });
        return prospects;
    }

}
