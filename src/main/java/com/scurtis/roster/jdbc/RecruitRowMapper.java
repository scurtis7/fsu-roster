package com.scurtis.roster.jdbc;

import com.scurtis.roster.dto.RecruitDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Steve Curtis
 * Date: Feb 18, 2020
 **/

public class RecruitRowMapper implements RowMapper<RecruitDto> {

    @Override
    public RecruitDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        RecruitDto recruit = new RecruitDto();
        recruit.setPlayerId(Integer.toString(rs.getInt("playerId")));
        recruit.setName(rs.getString("name"));
        recruit.setYear(rs.getString("year"));
        recruit.setClassYear(rs.getString("classyear"));
        recruit.setPosition(rs.getString("position"));
        recruit.setJersey(rs.getString("jersey"));
        recruit.setHeight(rs.getString("height"));
        recruit.setWeight(rs.getString("weight"));
        recruit.setHomeTown(rs.getString("hometown"));
        recruit.setOtherCollege(rs.getString("othercollege"));
        recruit.setRivalsSiteId(rs.getString("siteid"));
        recruit.setRivalsName(rs.getString("name"));
        recruit.setRivalsCity(rs.getString("city"));
        recruit.setRivalsState(rs.getString("state"));
        recruit.setRivalsStars(rs.getString("stars"));
        recruit.setRivalsPosition(rs.getString("position"));
        recruit.setRivalsHeight(rs.getString("height"));
        recruit.setRivalsWeight(rs.getString("weight"));
        recruit.setRivalsSign(rs.getString("sign"));
        recruit.setRivalsRating(rs.getString("rating"));
        recruit.setRivalsYear(rs.getString("year"));
        recruit.setRivalsRankNational(rs.getString("ranknational"));
        recruit.setRivalsRankPosition(rs.getString("rankposition"));
        recruit.setRivalsRankState(rs.getString("rankstate"));
        recruit.setTwo47SiteId(rs.getString("siteid"));
        recruit.setTwo47Name(rs.getString("name"));
        recruit.setTwo47Position(rs.getString("position"));
        recruit.setTwo47Height(rs.getString("height"));
        recruit.setTwo47Weight(rs.getString("weight"));
        recruit.setTwo47HomeTown(rs.getString("hometown"));
        recruit.setTwo47HighSchool(rs.getString("highschool"));
        recruit.setTwo47Year(rs.getString("year"));
        recruit.setTwo47CompositeRank(rs.getString("compositerank"));
        recruit.setTwo47RankNational(rs.getString("ranknational"));
        recruit.setTwo47RankPosition(rs.getString("rankposition"));
        recruit.setTwo47RankState(rs.getString("rankstate"));
        recruit.setTwo47Stars(rs.getString("stars"));
        return recruit;
    }

}
