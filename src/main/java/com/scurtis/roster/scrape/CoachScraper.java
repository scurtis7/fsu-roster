package com.scurtis.roster.scrape;

import com.scurtis.roster.model.coach.Coach;
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

    public List<Coach> scrapeCoaches() {
        Document doc = getCoachWebsite();
        if (doc != null) {
            log.info(doc.title());
            return processCoachWebsite(doc);
        }

        return new ArrayList<>();
    }

    private List<Coach> processCoachWebsite(Document doc) {
        List<Coach> coaches = new ArrayList<>();
        Element tbody = doc.select("tbody").first();
        List<Element> trElements = tbody.getElementsByTag("tr");
        int count;
        String coachName;
        String coachPosition;
        for (Element tr : trElements) {
            log.info("Row ====================================================");
            List<Element> tdElements = tr.getElementsByTag("td");
            count = 0;
            coachName = "";
            coachPosition = "";
            for (Element td : tdElements) {
                count++;
                if (count == 1) {
                    if (td.childNodeSize() > 0) {
                        Element anchor = td.firstElementSibling();
                        coachName = anchor.text();
                    }
                } else if (count == 2) {
                    coachPosition = td.text();
                }
                if (!StringUtils.isEmpty(coachName) && !StringUtils.isEmpty(coachPosition)) {
                    log.info("   Coach:{}    Position:{}", coachName, coachPosition);
                    coaches.add(new Coach(coachName, coachPosition, "football"));
                    break;
                }
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
