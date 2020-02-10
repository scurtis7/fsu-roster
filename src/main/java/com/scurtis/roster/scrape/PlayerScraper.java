package com.scurtis.roster.scrape;

import com.scurtis.roster.converter.PlayerConverter;
import com.scurtis.roster.dto.PlayerDto;
import com.scurtis.roster.model.player.Player;
import com.scurtis.roster.model.player.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
@RequiredArgsConstructor
public class PlayerScraper {

    private final PlayerConverter playerConverter;
    private final PlayerRepository playerRepository;

    public List<String> scrapePlayers(String year) {
        String lastTwo = Integer.toString(Integer.parseInt(year.substring(2)) + 1);
        String season = year + "-" + lastTwo;
        log.info("Player season: {}", season);
        int playerYear = Integer.parseInt(year);
        Document doc = getPlayerWebsite(season);
        if (doc == null) {
            return new ArrayList<>();
        }

        log.info(doc.title());
        List<PlayerDto> playerDtos = processPlayerWebsite(doc, playerYear);
        List<Player> players = playerConverter.playerDtoToPlayer(playerDtos);
        savePlayers(players);
        return convertPlayers(players);
    }

    private List<PlayerDto> processPlayerWebsite(Document doc, int playerYear) {
        List<PlayerDto> players = new ArrayList<>();
        List<Element> anchors = doc.select("a").stream()
                .filter(aTag -> !aTag.attr("data-sort").isEmpty()).collect(Collectors.toList());;
        for (Element anchor : anchors) {
            List<Element> divs = anchor.select("div");
            PlayerDto player = new PlayerDto();
            player.setJersey(divs.get(1).text());
            player.setName(anchor.select("h3").text());
            player.setHeight(convertHeight(anchor.selectFirst("span") != null ? anchor.selectFirst("span").text() : ""));
            player.setWeight(anchor.selectFirst("sapn") != null ? anchor.selectFirst("sapn").text() : "");
            List<Element> trs = anchor.select("tr");
            List<Element> tds = trs.get(0).select("td");
            player.setPosition(tds.get(1) != null ? tds.get(1).text() : "");
            tds = trs.get(1).select("td");
            player.setClassYear(tds.get(1) != null ? tds.get(1).text() : "");
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
        if (year.equals("Freshman")) {
            return String.valueOf(playerYear);
        } else if (year.equals("Redshirt Freshman") || year.equals("Sophomore")) {
            return String.valueOf(playerYear - 1);
        } else if (year.equals("Redshirt Sophomore") || year.equals("Junior")) {
            return String.valueOf(playerYear - 2);
        } else if (year.equals("Redshirt Junior") || year.equals("Senior")) {
            return String.valueOf(playerYear - 3);
        } else if (year.equals("Redshirt Senior")) {
            return String.valueOf(playerYear - 4);
        }
        return String.valueOf(playerYear - 5);
    }

    private void savePlayers(List<Player> players) {
        players.forEach(player -> {
            if (playerRepository.findPlayer(player.getName(), player.getYear()) == null) {
                playerRepository.save(player);
            }
        });
    }

    private List<String> convertPlayers(List<Player> players) {
        return players.stream()
                .map(player -> player.getJersey() + ", " + player.getName() + ", " + player.getYear())
                .collect(Collectors.toList());
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
