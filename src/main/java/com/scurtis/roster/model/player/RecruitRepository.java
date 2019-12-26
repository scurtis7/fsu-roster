package com.scurtis.roster.model.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Steve Curtis
 * Date: Dec 24, 2019
 **/

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

}
