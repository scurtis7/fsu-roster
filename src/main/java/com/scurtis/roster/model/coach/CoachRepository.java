package com.scurtis.roster.model.coach;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Steve Curtis
 * Date: Dec 22, 2019
 **/

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

}
