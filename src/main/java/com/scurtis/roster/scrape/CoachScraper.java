package com.scurtis.roster.scrape;

import com.scurtis.roster.dto.CoachDto;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Jan 31, 2020
 **/

@Slf4j
public class CoachScraper {

    public List<CoachDto> scrapeCoaches() {
        Document doc = getCoachWebsite();
        if (doc != null) {
            log.info(doc.title());
            return processCoachWebsite(doc);
        }

        return new ArrayList<>();
    }

    private List<CoachDto> processCoachWebsite(Document doc) {
        List<CoachDto> coaches = new ArrayList<>();
        Element tbody = doc.select("tbody").first();
        List<Element> trElements = tbody.getElementsByTag("tr");
        for (Element tr : trElements) {
            List<Element> tdElements = tr.getElementsByTag("td");
            CoachDto coachDto = new CoachDto();
            coachDto.setName(tr.getElementsByTag("td").first().text());
            coachDto.setPosition(tr.getElementsByTag("td").get(1).text());
            coachDto.setSport("Football");
            if (!StringUtils.isEmpty(coachDto.getName()) && !StringUtils.isEmpty(coachDto.getPosition())) {
                coaches.add(coachDto);
            }
        }
        return coaches;
    }

    private Document getCoachWebsite() {
        try {
            return Jsoup.connect("https://seminoles.com/staff-directory/department/football/").get();
        } catch (IOException exception) {
            log.error("Unable to get coaches website: {}", exception.getMessage());
            return null;
        }
    }

}
