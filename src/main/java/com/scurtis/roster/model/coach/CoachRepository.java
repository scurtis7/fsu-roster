package com.scurtis.roster.model.coach;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Steve Curtis
 * Date: Dec 22, 2019
 **/

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    @Query(value = "SELECT c FROM Coach c ORDER BY CoachId")
    List<Coach> findAllCoaches();

}
