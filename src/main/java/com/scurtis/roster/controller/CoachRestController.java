package com.scurtis.roster.controller;

import com.scurtis.roster.model.coach.Coach;
import com.scurtis.roster.model.coach.CoachRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Dec 22, 2019
 **/

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class CoachRestController {

    private final CoachRepository coachRepository;

    @GetMapping(value = "/coaches", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Coach> getAllCoaches() {
        log.info("Method: getAllCoaches");
        return coachRepository.findAll();
    }

}
