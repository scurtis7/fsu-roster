package com.scurtis.roster.dto;

import lombok.Data;

/**
 * Author: Steve Curtis
 * Date: Dec 23, 2019
 **/

@Data
public class RecruitDto {

    // Player
    private String playerId;
    private String season;
    private String name;
    private String position;
    private String classYear;
    private String year;
    private String jersey;
    private String height;
    private String weight;
    private String homeTown;
    private String otherCollege;

    // Rivals
    private String rivalsSiteId;
    private String rivalsName;
    private String rivalsCity;
    private String rivalsState;
    private String rivalsPosition;
    private String rivalsHeight;
    private String rivalsWeight;
    private String rivalsSign;
    private String rivalsStars;
    private String rivalsRating;
    private String rivalsCommitDate;
    private String rivalsLink;
    private String rivalsStatus;
    private String rivalsSport;
    private String rivalsYear;
    private String rivalsRankNational;
    private String rivalsRankPosition;
    private String rivalsRankState;

    // Noles 247
    private String two47SiteId;
    private String two47Name;
    private String two47Position;
    private String two47Height;
    private String two47Weight;
    private String two47HomeTown;
    private String two47HighSchool;
    private String two47Year;
    private String two47CompositeRank;
    private String two47RankNational;
    private String two47RankPosition;
    private String two47RankState;
    private String two47Stars;
    private String two47Link;

}
