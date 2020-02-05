package com.scurtis.roster.scrape;

import com.scurtis.roster.model.player.Player;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Steve Curtis
 * Date: Feb 03, 2020
 **/

@Slf4j
@Service
public class PlayerScraper {

    public List<Player> scrapePlayers() {
        Document doc = getPlayerWebsite("2020-21");
        if (doc != null) {
            log.info(doc.title());
            return processPlayerWebsite(doc);
        }

        return new ArrayList<>();
    }

    private List<Player> processPlayerWebsite(Document doc) {
        List<Player> players = new ArrayList<>();
        List<Element> anchors = doc.select("a").stream()
                .filter(aTag -> !aTag.attr("data-sort").isEmpty()).collect(Collectors.toList());;
        log.info("===========================================================");
        log.info("            Number of anchors: {}", anchors.size());
        String jersey;
        String playerName;
        String height;
        String weight;
        String position;
        String year;
        String hometown;
        String otherCollege;
        for (Element anchor : anchors) {
            log.info("=========");
            List<Element> divs = anchor.select("div");
            jersey = divs.get(1).text();
            playerName = anchor.select("h3").text();
            height = anchor.selectFirst("span") != null ? anchor.selectFirst("span").text() : "";
            weight = anchor.selectFirst("sapn") != null ? anchor.selectFirst("sapn").text() : "";
            List<Element> trs = anchor.select("tr");
            List<Element> tds = trs.get(0).select("td");
            position = tds.get(1) != null ? tds.get(1).text() : "";
            tds = trs.get(1).select("td");
            year = tds.get(1) != null ? tds.get(1).text() : "";
            tds = trs.get(2).select("td");
            hometown = tds.get(1) != null ? tds.get(1).text() : "";
            if (trs.size() > 3) {
                tds = trs.get(3).select("td");
                otherCollege = tds.get(1) != null ? tds.get(1).text() : "";
            } else {
                otherCollege = "";
            }
            Player player = new Player();
            player.setSport("Football");
            player.setActive(true);
            player.setJersey(convertJersey(jersey));
            player.setName(playerName);
            player.setHeight(height);
            player.setWeight(convertWeight(weight));
            player.setPosition(position);
            player.setYear(convertYear(year));
            player.setRedshirt(year.contains("Redshirt"));
            player.setHomeTown(hometown);
            player.setOtherCollege(otherCollege);
            players.add(player);
            log.info("Jersey: {},   Name: {},   height: {},   weight: {},   position: {},   year: {},   hometown: {},   otherCollege: {}", jersey, playerName, height, weight, position, year, hometown, otherCollege);
            jersey = "";
            playerName = "";
            height = "";
            weight = "";
            position = "";
            year = "";
            hometown = "";
            otherCollege = "";
        }

        return players;
    }

    private int convertJersey(String jersey) {
        if (StringUtils.isEmpty(jersey)) {
            return 0;
        }
        return Integer.parseInt(jersey.substring(1));
    }

    private int convertWeight(String weight) {
        if (StringUtils.isEmpty(weight)) {
            return 0;
        } else if (weight.contains("lbs")) {
            return Integer.parseInt(weight.replace(" lbs", ""));
        } else {
            return 0;
        }
    }

    private int convertYear(String year) {
        if (year.equals("Freshman")) {
            return 2020;
        } else if (year.equals("Redshirt Freshman")) {
            return 2019;
        } else if (year.equals("Sophomore")) {
            return 2019;
        } else if (year.equals("Redshirt Sophomore")) {
            return 2018;
        } else if (year.equals("Junior")) {
            return 2018;
        } else if (year.equals("Redshirt Junior")) {
            return 2017;
        } else if (year.equals("Senior")) {
            return 2017;
        } else if (year.equals("Redshirt Senior")) {
            return 2016;
        }
        return 2015;
    }

    private Document getPlayerWebsite(String season) {
        String website = "https://seminoles.com/sports/football/roster/season/" + season + "/";
        try {
            return Jsoup.connect(website).get();
        } catch (IOException exception) {
            log.error("Unable to get players website: {}", exception.getMessage());
            return null;
        }
    }

}
