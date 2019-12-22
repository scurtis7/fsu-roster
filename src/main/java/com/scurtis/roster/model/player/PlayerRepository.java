package com.scurtis.roster.model.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Steve Curtis
 * Date: Dec 22, 2019
 **/

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
