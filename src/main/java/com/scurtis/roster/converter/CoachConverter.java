package com.scurtis.roster.converter;

import com.scurtis.roster.dto.CoachDto;
import com.scurtis.roster.model.coach.Coach;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Jan 12, 2020
 **/

@Service
public class CoachConverter {

    public List<CoachDto> coachListToCoachDto(List<Coach> coaches) {
        List<CoachDto> coachDtos = new ArrayList<>();
        coaches.forEach(coach -> {
            CoachDto coachDto = CoachDto.builder()
                    .coachId(Long.toString(coach.getCoachId()))
                    .name(coach.getName())
                    .position(coach.getPosition())
                    .build();
            coachDtos.add(coachDto);
        });
        return coachDtos;
    }

    public List<Coach> CoachDtoListToCoachEntity(List<CoachDto> coachDtos) {
        List<Coach> coaches = new ArrayList<>();
        coachDtos.forEach(coach -> {
            Coach coachEntity = new Coach();
            coachEntity.setName(coach.getName());
            coachEntity.setPosition(coach.getPosition());
            coachEntity.setSport(coach.getSport());
            coaches.add(coachEntity);
        });
        return coaches;
    }

    public Coach CoachDtoToCoach(CoachDto coachDto) {
        Coach coach = new Coach();
        coach.setCoachId(Long.getLong(coachDto.getCoachId()));
        coach.setName(coachDto.getName());
        coach.setPosition(coachDto.getPosition());
        coach.setSport(coachDto.getSport());
        return coach;
    }

}
