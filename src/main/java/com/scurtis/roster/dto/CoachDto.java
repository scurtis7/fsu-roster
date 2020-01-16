package com.scurtis.roster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Steve Curtis
 * Date: Jan 12, 2020
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoachDto {

    private String coachId;
    private String name;
    private String position;
    private String sport;

}
