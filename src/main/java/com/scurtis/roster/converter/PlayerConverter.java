package com.scurtis.roster.converter;

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
    
    public List<RecruitDto> playerEntityToPlayerDto(List<Player> players) {
        List<RecruitDto> recruitDtos = new ArrayList<>();
        players.forEach(player -> {
            RecruitDto recruitDto = RecruitDto.builder()
                    .playerId(Long.toString(player.getPlayerId()))
                    .name(player.getName())
                    .position(player.getPosition())
                    .year(player.getYear())
//                    .redshirt(Boolean.toString(player.getRedshirt()))
//                    .classStanding(setClassStanding(player.getYear(), player.getRedshirt()))
                    .jersey(player.getJersey())
//                    .sport(player.getSport())
//                    .status(player.getStatus())
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
//                    .redshirt(Boolean.toString(recruit.getPlayer().getRedshirt()))
//                    .classStanding(setClassStanding(recruit.getPlayer().getYear(), recruit.getPlayer().getRedshirt()))
                    .jersey(recruit.getPlayer().getJersey())
//                    .sport(recruit.getPlayer().getSport())
//                    .active(Boolean.toString(recruit.getPlayer().getActive()))
//                    .status(recruit.getPlayer().getStatus())
                    .height(recruit.getPlayer().getHeight())
                    .weight(recruit.getPlayer().getWeight())
                    .homeTown(recruit.getPlayer().getHomeTown())
                    .highSchool(recruit.getPlayer().getHighSchool())
                    .otherCollege(recruit.getPlayer().getOtherCollege())
                    .draftPick(recruit.getPlayer().getDraftPick())
                    .nflTeam(recruit.getPlayer().getNflTeam())
                    .notes(recruit.getPlayer().getNotes())
                    .rivalsStars(recruit.getRivalsStars())
//                    .rivalsStars(recruit.getRivalsStars() > 0 ? Integer.toString(recruit.getRivalsStars()) : "-")
                    .rivalsRating(recruit.getRivalsRating())
//                    .rivalsRating(recruit.getRivalsRating() > 0 ? Double.toString(recruit.getRivalsRating()) : "-")
                    .rivalsRankNational(recruit.getRivalsRankNational())
//                    .rivalsRankNational(recruit.getRivalsRankNational() > 0 ? Integer.toString(recruit.getRivalsRankNational()) : "-")
                    .rivalsRankPosition(recruit.getRivalsRankPosition())
//                    .rivalsRankPosition(recruit.getRivalsRankPosition() > 0 ? Integer.toString(recruit.getRivalsRankPosition()) : "-")
                    .rivalsRankState(recruit.getRivalsRankState())
//                    .rivalsRankState(recruit.getRivalsRankState() > 0 ? Integer.toString(recruit.getRivalsRankState()) : "-")
                    .rivalsLink(recruit.getRivalsLink())
                    .two47Stars(recruit.getRivalsStars())
//                    .two47Stars(recruit.getRivalsStars() > 0 ? Integer.toString(recruit.getRivalsStars()) : "-")
                    .two47Rating(recruit.getTwo47Rating())
//                    .two47Rating(recruit.getTwo47Rating() > 0 ? Double.toString(recruit.getTwo47Rating()) : "-")
                    .two47RankNational(recruit.getTwo47RankNational())
//                    .two47RankNational(recruit.getTwo47RankNational() > 0 ? Integer.toString(recruit.getTwo47RankNational()) : "-")
                    .two47RankPosition(recruit.getTwo47RankPosition())
//                    .two47RankPosition(recruit.getTwo47RankPosition() > 0 ? Integer.toString(recruit.getTwo47RankPosition()) : "-")
                    .two47RankState(recruit.getTwo47RankState())
//                    .two47RankState(recruit.getTwo47RankState() > 0 ? Integer.toString(recruit.getTwo47RankState()) : "-")
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
