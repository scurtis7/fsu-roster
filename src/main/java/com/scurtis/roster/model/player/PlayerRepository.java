package com.scurtis.roster.model.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Dec 22, 2019
 **/

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT DISTINCT p.position FROM Player p ORDER BY p.position")
    List<String> findAllPositions();

}
