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

    private Long id;
    private String name;
    private String position;
    private int year;
    private Boolean redshirt;
    private String classStanding;
    private int jersey;
    private String sport;
    private String status;
    private String height;
    private int weight;
    private String homeTown;
    private String highSchool;
    private String otherCollege;
    private int stars;
    private int draftPick;
    private String nflTeam;
    private String notes;

}
