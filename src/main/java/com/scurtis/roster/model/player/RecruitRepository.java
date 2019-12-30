package com.scurtis.roster.model.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Dec 24, 2019
 **/

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    @Query("SELECT r FROM Recruit r WHERE r.player.position = :position")
    List<Recruit> findRecruitsByPosition(@Param("position") String position);

}
