package com.scurtis.roster.converter;

import com.scurtis.roster.dto.PlayerDto;
import com.scurtis.roster.model.player.Player;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Dec 23, 2019
 **/
@Service
public class PlayerConverter {

    private static final String CLASS_FRESHMAN = "Freshman";
    private static final String CLASS_REDSHIRT_FRESHMAN = "Redshirt Freshman";
    private static final String CLASS_SOPHOMORE = "Sophomore";
    private static final String CLASS_REDSHIRT_SOPHOMORE = "Redshirt Sophomore";
    private static final String CLASS_JUNIOR = "Junior";
    private static final String CLASS_REDSHIRT_JUNIOR = "Redshirt Junior";
    private static final String CLASS_SENIOR = "Senior";
    private static final String CLASS_REDSHIRT_SENIOR = "Redshirt Senior";
    
    public List<PlayerDto> playerEntityToPlayerDto(List<Player> players) {
        List<PlayerDto> playerDtos = new ArrayList<>();
        players.forEach(player -> {
            PlayerDto playerDto = PlayerDto.builder()
                    .id(player.getId())
                    .name(player.getName())
                    .position(player.getPosition())
                    .year(player.getYear())
                    .redshirt(player.getRedshirt())
                    .classStanding(setClassStanding(player.getYear(), player.getRedshirt()))
                    .jersey(player.getJersey())
                    .sport(player.getSport())
                    .status(player.getStatus())
                    .height(player.getHeight())
                    .weight(player.getWeight())
                    .homeTown(player.getHomeTown())
                    .highSchool(player.getHighSchool())
                    .otherCollege(player.getOtherCollege())
                    .draftPick(player.getDraftPick())
                    .nflTeam(player.getNflTeam())
                    .notes(player.getNotes())
                    .build();
            playerDtos.add(playerDto);
        });
        return playerDtos;
    }

    private String setClassStanding(int year, boolean redshirt) {
        String classStanding = "";
        int currentYear = LocalDate.now().getYear();
        if (year == currentYear) {
            classStanding = CLASS_FRESHMAN;
        } else if (year == (currentYear - 1)) {
            if (redshirt) {
                classStanding = CLASS_REDSHIRT_FRESHMAN;
            } else {
                classStanding = CLASS_SOPHOMORE;
            }
        } else if (year == (currentYear - 2)) {
            if (redshirt) {
                classStanding = CLASS_REDSHIRT_SOPHOMORE;
            } else {
                classStanding = CLASS_JUNIOR;
            }
        } else if (year == (currentYear - 3)) {
            if (redshirt) {
                classStanding = CLASS_REDSHIRT_JUNIOR;
            } else {
                classStanding = CLASS_SENIOR;
            }
        } else if (year == (currentYear - 4)) {
            if (redshirt) {
                classStanding = CLASS_REDSHIRT_SENIOR;
            }
        }
        return classStanding;
    }

}
