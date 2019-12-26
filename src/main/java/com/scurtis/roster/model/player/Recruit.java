package com.scurtis.roster.model.player;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Author: Steve Curtis
 * Date: Dec 24, 2019
 **/

@Entity
@Table(name = "Recruit", schema = "fsu_roster")
@Data
@NoArgsConstructor
public class Recruit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(name = "sequence-generator", sequenceName = "team_sequence")
    @Column(name = "RecruitId", unique = true, nullable = false)
    private Long RecruitId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    private Player player;

    @Column(name = "Service", nullable = false)
    private String service;

    @Column(name = "Stars")
    private int stars;

    @Column(name = "Rating")
    private double rating;

    @Column(name = "RankNational")
    private int rankNational;

    @Column(name = "RankPosition")
    private int rankPosition;

    @Column(name = "RankState")
    private int rankState;

    @Column(name = "Link")
    private String link;

}
