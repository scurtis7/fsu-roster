package com.scurtis.roster.dto;

import lombok.Data;

/**
 * Author: Steve Curtis
 * Date: Feb 09, 2020
 **/

@Data
public class PlayerDto {

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

}
