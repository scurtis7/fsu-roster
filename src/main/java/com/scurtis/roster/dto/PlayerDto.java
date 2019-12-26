package com.scurtis.roster.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Author: Steve Curtis
 * Date: Dec 23, 2019
 **/
@Data
@Builder
public class PlayerDto {

    private String playerId;
    private String name;
    private String position;
    private String year;
    private String redshirt;
    private String classStanding;
    private String jersey;
    private String sport;
    private String status;
    private String height;
    private String weight;
    private String homeTown;
    private String highSchool;
    private String otherCollege;
    private String draftPick;
    private String nflTeam;
    private String notes;
    private String stars;
    private String rating;
    private String rankNational;
    private String rankPosition;
    private String rankState;
    private String link;

}
