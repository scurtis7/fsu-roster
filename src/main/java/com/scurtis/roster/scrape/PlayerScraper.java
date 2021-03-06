package com.scurtis.roster.scrape;

import com.scurtis.roster.converter.PlayerConverter;
import com.scurtis.roster.dto.PlayerDto;
import com.scurtis.roster.exception.SoupConnectionException;
import com.scurtis.roster.model.player.Player;
import com.scurtis.roster.model.player.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Steve Curtis
 * Date: Feb 03, 2020
 **/

@Slf4j
@RequiredArgsConstructor
public class PlayerScraper {

    private static final String BASE_URL = "https://seminoles.com/sports/football/roster/season/";

    private final ScrapingService scrapingService;
    private final PlayerConverter playerConverter;
    private final PlayerRepository playerRepository;

    public List<String> scrape(String year) {
        log.info("Method: scrape()");
        List<String> playerList = new ArrayList<>();
        try {
            String lastTwo = Integer.toString(Integer.parseInt(year.substring(2)) + 1);
            String season = year + "-" + lastTwo;
            log.info("Player season: {}", season);
            int playerYear = Integer.parseInt(year);
            Document doc = scrapingService.connect(BASE_URL + season + "/");
            if (doc == null) {
                return playerList;
            }

            log.info(doc.title());
            List<PlayerDto> playerDtos = parse(doc, playerYear);
            List<Player> players = playerConverter.playerDtoToPlayer(playerDtos);
            playerList = savePlayers(players);
        } catch (SoupConnectionException exception) {
            playerList = Collections.singletonList(exception.getMessage());
        }
        return playerList;
    }

    private List<PlayerDto> parse(Document doc, int playerYear) {
        List<PlayerDto> players = new ArrayList<>();
        List<Element> anchors = doc.select("a").stream()
                .filter(aTag -> !aTag.attr("data-sort").isEmpty()).collect(Collectors.toList());;
        for (Element anchor : anchors) {
            List<Element> divs = anchor.select("div");
            PlayerDto player = new PlayerDto();
            String jersey = divs.get(1).text();
            if (jersey.contains("#")) {
                jersey = jersey.trim().replaceAll("#", "");
            }
            player.setJersey(jersey);
            player.setName(anchor.select("h3").text());
            player.setHeight(convertHeight(anchor.selectFirst("span") != null ? anchor.selectFirst("span").text() : ""));
            // 'sapn' is not a typo
            player.setWeight(anchor.selectFirst("sapn") != null ? anchor.selectFirst("sapn").text() : "");
            List<Element> trs = anchor.select("tr");
            List<Element> tds = trs.get(0).select("td");
            player.setPosition(tds.get(1) != null ? tds.get(1).text() : "");
            tds = trs.get(1).select("td");
            player.setClassYear(tds.get(1) != null ? tds.get(1).text() : "****");
            player.setYear(convertYear(player.getClassYear(), playerYear));
            tds = trs.get(2).select("td");
            player.setHomeTown(tds.get(1) != null ? tds.get(1).text() : "");
            if (trs.size() > 3) {
                tds = trs.get(3).select("td");
                player.setOtherCollege(tds.get(1) != null ? tds.get(1).text() : "");
            } else {
                player.setOtherCollege("");
            }
            players.add(player);
        }

        return players;
    }

    private String convertHeight(String height) {
        if (!StringUtils.isEmpty(height) && height.contains("'") && height.contains("\"")) {
            height = height.replaceAll("\"", "");
            return height.replaceAll("'", "-");
        }
        return "";
    }

    private String convertYear(String year, int playerYear) {
        log.info("Class Year to convert: {}", year);
        if (year.equalsIgnoreCase("Freshman")) {
            return String.valueOf(playerYear);
        } else if (year.equalsIgnoreCase("Redshirt Freshman") || year.equalsIgnoreCase("Sophomore")) {
            return String.valueOf(playerYear - 1);
        } else if (year.equalsIgnoreCase("Redshirt Sophomore") || year.equalsIgnoreCase("Junior")) {
            return String.valueOf(playerYear - 2);
        } else if (year.equalsIgnoreCase("Redshirt Junior") || year.equalsIgnoreCase("Senior")) {
            return String.valueOf(playerYear - 3);
        } else if (year.equalsIgnoreCase("Redshirt Senior")) {
            return String.valueOf(playerYear - 4);
        } else if (year.equalsIgnoreCase("Graduate")) {
            return "** " + playerYear;
        }
        log.info("Could not convert Class Year: {} - {}", playerYear, year);
        return "** " + playerYear;
    }

    private List<String> savePlayers(List<Player> players) {
        List<String> playerList = new ArrayList<>();
        playerList.add("   === Players Added ===   ");
        for (Player player : players) {
            if (playerRepository.findPlayerByNameUpperCase(player.getName().toUpperCase()) == null) {
                playerRepository.save(player);
                playerList.add(player.toString());
            }
        }
        if (playerList.size() == 1) {
            playerList = Collections.singletonList("   === No Players Added ===   ");
        }
        return playerList;
    }

//    private String convertPlayer(Player player) {
//        return player.getName() + ", " + player.getYear() + ", " + player.getJersey() + ", " + player.getPosition()
//                + ", " + player.getHeight() + ", " + player.getWeight() + ", " + player.getHomeTown()
//                + ", " + player.getOtherCollege();
//    }

}
