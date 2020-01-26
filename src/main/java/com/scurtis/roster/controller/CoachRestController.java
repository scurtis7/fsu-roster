package com.scurtis.roster.controller;

import com.scurtis.roster.converter.CoachConverter;
import com.scurtis.roster.dto.CoachDto;
import com.scurtis.roster.model.coach.Coach;
import com.scurtis.roster.model.coach.CoachRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final CoachConverter coachConverter;

    @GetMapping(value = "/coaches", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Coach> getAllCoaches() {
        log.info("Method: getAllCoaches");
        return coachRepository.findAll();
    }

    @PostMapping(value = "/coach", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCoach(@RequestBody CoachDto coachDto) {
        log.info("Method: addCoach()");
        Coach coach = coachConverter.CoachDtoToCoach(coachDto);
        coachRepository.save(coach);
    }

    @DeleteMapping(value = "/coach/{id}")
    public List<Coach> deleteCoach(@PathVariable(value = "id") Long id) {
        log.info("Method: deleteCoach('{}')", id);
        Coach coach = coachRepository.getOne(id);
        coachRepository.delete(coach);
        return coachRepository.findAll();
    }

}
