package com.scurtis.roster.converter;

import com.scurtis.roster.dto.PlayerDto;
import com.scurtis.roster.model.player.Player;
import com.scurtis.roster.model.player.Recruit;
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
                    .playerId(player.getPlayerId())
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

    public List<PlayerDto> recruitEntityToPlayerDto(List<Recruit> recruits) {
        List<PlayerDto> playerDtos = new ArrayList<>();
        recruits.forEach(recruit -> {
            PlayerDto playerDto = PlayerDto.builder()
                    .playerId(recruit.getPlayer().getPlayerId())
                    .name(recruit.getPlayer().getName())
                    .position(recruit.getPlayer().getPosition())
                    .year(recruit.getPlayer().getYear())
                    .redshirt(recruit.getPlayer().getRedshirt())
                    .classStanding(setClassStanding(recruit.getPlayer().getYear(), recruit.getPlayer().getRedshirt()))
                    .jersey(recruit.getPlayer().getJersey())
                    .sport(recruit.getPlayer().getSport())
                    .status(recruit.getPlayer().getStatus())
                    .height(recruit.getPlayer().getHeight())
                    .weight(recruit.getPlayer().getWeight())
                    .homeTown(recruit.getPlayer().getHomeTown())
                    .highSchool(recruit.getPlayer().getHighSchool())
                    .otherCollege(recruit.getPlayer().getOtherCollege())
                    .draftPick(recruit.getPlayer().getDraftPick())
                    .nflTeam(recruit.getPlayer().getNflTeam())
                    .notes(recruit.getPlayer().getNotes())
                    .stars(recruit.getStars())
                    .rating(recruit.getRating())
                    .rankNational(recruit.getRankNational())
                    .rankPosition(recruit.getRankPosition())
                    .rankState(recruit.getRankState())
                    .link(recruit.getLink())
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
