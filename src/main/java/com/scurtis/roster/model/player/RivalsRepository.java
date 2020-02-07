package com.scurtis.roster.model.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Steve Curtis
 * Date: Feb 06, 2020
 **/

@Repository
public interface RivalsRepository extends JpaRepository<Rivals, Long> {
}
