package com.scurtis.roster.model.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Author: Steve Curtis
 * Date: Feb 06, 2020
 **/

@Repository
public interface RivalsRepository extends JpaRepository<Rivals, String> {

    @Query("SELECT r FROM Rivals r WHERE r.siteId = :siteId")
    Rivals findRivalsPlayer(@Param("siteId") String siteId);

}
