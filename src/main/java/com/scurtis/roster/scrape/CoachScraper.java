package com.scurtis.roster.scrape;

import com.scurtis.roster.converter.CoachConverter;
import com.scurtis.roster.dto.CoachDto;
import com.scurtis.roster.exception.SoupConnectionException;
import com.scurtis.roster.model.coach.Coach;
import com.scurtis.roster.model.coach.CoachRepository;
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
 * Date: Jan 31, 2020
 **/

@Slf4j
@RequiredArgsConstructor
public class CoachScraper {

    private static final String WEBSITE = "https://seminoles.com/staff-directory/department/football/";

    private final ScrapingService scrapingService;
    private final CoachRepository coachRepository;
    private final CoachConverter coachConverter;

    public List<String> scrapeCoaches() {

        List<String> coachList = new ArrayList<>();
        try {
            Document doc = scrapingService.connect(WEBSITE);
            if (doc == null) {
                return coachList;
            }

            log.info(doc.title());
            List<CoachDto> coachDtos = processCoachWebsite(doc);
            List<Coach> coaches = coachConverter.coachDtoListToCoachEntity(coachDtos);
            coachRepository.deleteAll();
            coaches.forEach(coachRepository::save);
            coachList = convertCoaches(coaches);
        } catch (SoupConnectionException exception) {
            coachList = Collections.singletonList(exception.getMessage());
        }
        return coachList;
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

    private List<String> convertCoaches(List<Coach> coaches) {
        return coaches.stream()
                .map(coach -> coach.getName() + ", " + coach.getPosition() + ", " + coach.getSport())
                .collect(Collectors.toList());
    }

}
