package com.scurtis.roster.model.coach;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Author: Steve Curtis
 * Date: Dec 22, 2019
 **/

@Entity
@Table(name = "Coach", schema = "fsu_roster")
@Data
@NoArgsConstructor
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(name = "sequence-generator", sequenceName = "coach_coachid_seq", allocationSize = 1, initialValue = 100)
    @Column(name = "CoachId", unique = true, nullable = false)
    private Long coachId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Position", nullable = false)
    private String position;

    @Column(name = "Sport", nullable = false)
    private String sport;

    public Coach(String name, String position, String sport) {
        this.name = name;
        this.position = position;
        this.sport = sport;
    }

}
