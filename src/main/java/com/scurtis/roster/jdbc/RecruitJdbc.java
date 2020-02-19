package com.scurtis.roster.jdbc;

import com.scurtis.roster.dto.RecruitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Feb 18, 2020
 **/

@RequiredArgsConstructor
public class RecruitJdbc {

    private final JdbcTemplate jdbcTemplate;
    private final RecruitRowMapper recruitRowMapper;

    private static final String FIND_ALL_RECRUITS = "SELECT p.*, r.*, t.* FROM fsu_roster.player p LEFT OUTER JOIN fsu_roster.rivals r ON p.playerid = r.playerid LEFT OUTER JOIN fsu_roster.two47 t ON p.playerid = t.playerid;";

    public List<RecruitDto> findAllRecruits() {
        return jdbcTemplate.query(FIND_ALL_RECRUITS, recruitRowMapper);
    }

}
