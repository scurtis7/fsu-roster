package com.scurtis.roster.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Author: Steve Curtis
 * Date: Feb 06, 2020
 **/

@Data
@Builder
public class RivalsDto {
    private String rivalsId;
    private String stars;
    private String rating;
    private String rankNational;
    private String rankPosition;
    private String rankState;
    private String link;
}
