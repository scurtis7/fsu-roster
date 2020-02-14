package com.scurtis.roster.converter;

import com.scurtis.roster.dto.PlayerDto;
import com.scurtis.roster.dto.RecruitDto;
import com.scurtis.roster.model.player.Player;
import com.scurtis.roster.model.player.Recruit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Dec 23, 2019
 **/

public class PlayerConverter {

    private static final String CLASS_FRESHMAN = "Freshman";
    private static final String CLASS_REDSHIRT_FRESHMAN = "Redshirt Freshman";
    private static final String CLASS_SOPHOMORE = "Sophomore";
    private static final String CLASS_REDSHIRT_SOPHOMORE = "Redshirt Sophomore";
    private static final String CLASS_JUNIOR = "Junior";
    private static final String CLASS_REDSHIRT_JUNIOR = "Redshirt Junior";
    private static final String CLASS_SENIOR = "Senior";
    private static final String CLASS_REDSHIRT_SENIOR = "Redshirt Senior";

    public List<Player> playerDtoToPlayer(List<PlayerDto> playerDtos) {
        List<Player> players = new ArrayList<>();
        playerDtos.forEach(dto -> {
            Player player = new Player();
            player.setName(dto.getName());
            player.setPosition(dto.getPosition());
            player.setYear(dto.getYear());
            player.setClassYear(dto.getClassYear());
            player.setJersey(dto.getJersey());
            player.setHeight(dto.getHeight());
            player.setWeight(dto.getWeight());
            player.setHomeTown(dto.getHomeTown());
            player.setOtherCollege(dto.getOtherCollege());
            players.add(player);
        });
        return players;
    }
    
    public List<RecruitDto> playerEntityToPlayerDto(List<Player> players) {
        List<RecruitDto> recruitDtos = new ArrayList<>();
        players.forEach(player -> {
            RecruitDto recruitDto = RecruitDto.builder()
                    .playerId(Long.toString(player.getPlayerId()))
                    .name(player.getName())
                    .position(player.getPosition())
                    .year(player.getYear())
                    .jersey(player.getJersey())
                    .height(player.getHeight())
                    .weight(player.getWeight())
                    .homeTown(player.getHomeTown())
                    .highSchool(player.getHighSchool())
                    .otherCollege(player.getOtherCollege())
                    .draftPick(player.getDraftPick())
                    .nflTeam(player.getNflTeam())
                    .notes(player.getNotes())
                    .build();
            recruitDtos.add(recruitDto);
        });
        return recruitDtos;
    }

    public List<RecruitDto> recruitEntityToPlayerDto(List<Recruit> recruits) {
        List<RecruitDto> recruitDtos = new ArrayList<>();
        recruits.forEach(recruit -> {
            RecruitDto recruitDto = RecruitDto.builder()
                    .playerId(Long.toString(recruit.getPlayer().getPlayerId()))
                    .name(recruit.getPlayer().getName())
                    .position(recruit.getPlayer().getPosition())
                    .year(recruit.getPlayer().getYear())
                    .jersey(recruit.getPlayer().getJersey())
                    .height(recruit.getPlayer().getHeight())
                    .weight(recruit.getPlayer().getWeight())
                    .homeTown(recruit.getPlayer().getHomeTown())
                    .highSchool(recruit.getPlayer().getHighSchool())
                    .otherCollege(recruit.getPlayer().getOtherCollege())
                    .draftPick(recruit.getPlayer().getDraftPick())
                    .nflTeam(recruit.getPlayer().getNflTeam())
                    .notes(recruit.getPlayer().getNotes())
                    .rivalsStars(recruit.getRivalsStars())
                    .rivalsRating(recruit.getRivalsRating())
                    .rivalsRankNational(recruit.getRivalsRankNational())
                    .rivalsRankPosition(recruit.getRivalsRankPosition())
                    .rivalsRankState(recruit.getRivalsRankState())
                    .rivalsLink(recruit.getRivalsLink())
                    .two47Stars(recruit.getRivalsStars())
                    .two47Rating(recruit.getTwo47Rating())
                    .two47RankNational(recruit.getTwo47RankNational())
                    .two47RankPosition(recruit.getTwo47RankPosition())
                    .two47RankState(recruit.getTwo47RankState())
                    .two47Link(recruit.getTwo47Link())
                    .build();
            recruitDtos.add(recruitDto);
        });
        return recruitDtos;
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
