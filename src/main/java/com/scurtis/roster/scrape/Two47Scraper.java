package com.scurtis.roster.scrape;

import com.scurtis.roster.dto.Two47Dto;
import com.scurtis.roster.exception.SoupConnectionException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Feb 08, 2020
 **/
@Slf4j
public class Two47Scraper {

    private static final String HTTPS = "https:";

    public List<Two47Dto> scrape(String season) throws SoupConnectionException {
        log.info("scrape()");
        String website = "https://247sports.com/college/florida-state/Season/" + season + "-Football/Commits/";
        Document doc = connect(website);
        return parse(doc, season);
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
                Document doc = connect(website);
                commits.add(getPlayer(doc, id, season, website));
            } catch (SoupConnectionException sce) {
                // Just ignore for now
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

    private Document connect(String website) throws SoupConnectionException {
        log.info("Connect to Website: {}", website);
        try {
            return Jsoup.connect(website).get();
        } catch (IOException exception) {
            log.error("Unable to get rivals website: {}", exception.getMessage());
            throw new SoupConnectionException("Unable to get rivals website: " + exception.getMessage(), exception);
        }
    }


}
