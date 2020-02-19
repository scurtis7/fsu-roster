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
            RecruitDto recruitDto = new RecruitDto();
            recruitDto.setPlayerId(Long.toString(player.getPlayerId()));
            recruitDto.setName(player.getName());
            recruitDto.setPosition(player.getPosition());
            recruitDto.setYear(player.getYear());
            recruitDto.setJersey(player.getJersey());
            recruitDto.setHeight(player.getHeight());
            recruitDto.setWeight(player.getWeight());
            recruitDto.setHomeTown(player.getHomeTown());
            recruitDto.setTwo47HighSchool(player.getHighSchool());
            recruitDto.setOtherCollege(player.getOtherCollege());
            recruitDtos.add(recruitDto);
        });
        return recruitDtos;
    }

    public List<RecruitDto> recruitEntityToPlayerDto(List<Recruit> recruits) {
        List<RecruitDto> recruitDtos = new ArrayList<>();
        recruits.forEach(recruit -> {
            RecruitDto recruitDto = new RecruitDto();
            recruitDto.setPlayerId(Long.toString(recruit.getPlayer().getPlayerId()));
            recruitDto.setName(recruit.getPlayer().getName());
            recruitDto.setPosition(recruit.getPlayer().getPosition());
            recruitDto.setYear(recruit.getPlayer().getYear());
            recruitDto.setJersey(recruit.getPlayer().getJersey());
            recruitDto.setHeight(recruit.getPlayer().getHeight());
            recruitDto.setWeight(recruit.getPlayer().getWeight());
            recruitDto.setHomeTown(recruit.getPlayer().getHomeTown());
            recruitDto.setTwo47HighSchool(recruit.getPlayer().getHighSchool());
            recruitDto.setOtherCollege(recruit.getPlayer().getOtherCollege());
            recruitDto.setRivalsStars(recruit.getRivalsStars());
            recruitDto.setRivalsRating(recruit.getRivalsRating());
            recruitDto.setRivalsRankNational(recruit.getRivalsRankNational());
            recruitDto.setRivalsRankPosition(recruit.getRivalsRankPosition());
            recruitDto.setRivalsRankState(recruit.getRivalsRankState());
            recruitDto.setRivalsLink(recruit.getRivalsLink());
            recruitDto.setTwo47Stars(recruit.getRivalsStars());
            recruitDto.setTwo47RankNational(recruit.getTwo47RankNational());
            recruitDto.setTwo47RankPosition(recruit.getTwo47RankPosition());
            recruitDto.setTwo47RankState(recruit.getTwo47RankState());
            recruitDto.setTwo47Link(recruit.getTwo47Link());
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
