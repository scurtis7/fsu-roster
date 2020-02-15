package com.scurtis.roster.model.player;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Author: Steve Curtis
 * Date: Feb 06, 2020
 **/

@Entity
@Table(name = "Rivals", schema = "fsu_roster")
@Data
@NoArgsConstructor
public class Rivals {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(name = "sequence-generator", sequenceName = "rivals_rivalsid_seq", allocationSize = 1, initialValue = 100)
    @Column(name = "RivalsId", unique = true, nullable = false)
    private Long RecruitId;

    @Column(name = "playerId")
    private Long playerId;

    @Column(name = "siteId")
    private String siteId;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "position")
    private String position;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "sign")
    private String sign;

    @Column(name = "stars")
    private String stars;

    @Column(name = "rating")
    private String rating;

    @Column(name = "commitDate")
    private String commitDate;

    @Column(name = "link")
    private String link;

    @Column(name = "status")
    private String status;

    @Column(name = "sport")
    private String sport;

    @Column(name = "year")
    private String year;

    @Column(name = "RankNational")
    private String rankNational;

    @Column(name = "RankPosition")
    private String rankPosition;

    @Column(name = "RankState")
    private String rankState;

}
