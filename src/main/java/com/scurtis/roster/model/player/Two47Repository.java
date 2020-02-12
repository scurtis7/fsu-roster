package com.scurtis.roster.model.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Author: Steve Curtis
 * Date: Feb 06, 2020
 **/

public interface Two47Repository extends JpaRepository<Two47, Long> {

    @Query("SELECT t FROM Two47 t WHERE t.siteId = :siteId")
    Rivals find247Player(@Param("siteId") String siteId);

}
