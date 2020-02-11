package com.scurtis.roster.scrape;

import com.scurtis.roster.exception.SoupConnectionException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Author: Steve Curtis
 * Date: Feb 10, 2020
 **/

@Slf4j
public class ScrapingService {

    public Document connect(String website) throws SoupConnectionException {
        log.info("Connect to Website: {}", website);
        try {
            return Jsoup.connect(website).get();
        } catch (IOException exception) {
            log.error("Unable to get rivals website: {}", exception.getMessage());
            throw new SoupConnectionException("Unable to get rivals website: " + exception.getMessage(), exception);
        }
    }


}
