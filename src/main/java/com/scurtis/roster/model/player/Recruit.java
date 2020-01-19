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
    @SequenceGenerator(name = "sequence-generator", sequenceName = "recruit_recruitid_seq")
    @Column(name = "RecruitId", unique = true, nullable = false)
    private Long RecruitId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    private Player player;

    @Column(name = "RivalsStars")
    private int rivalsStars;

    @Column(name = "RivalsRating")
    private double rivalsRating;

    @Column(name = "RivalsRankNational")
    private int rivalsRankNational;

    @Column(name = "RivalsRankPosition")
    private int rivalsRankPosition;

    @Column(name = "RivalsRankState")
    private int rivalsRankState;

    @Column(name = "RivalsLink")
    private String rivalsLink;

    @Column(name = "Two47Stars")
    private int two47Stars;

    @Column(name = "Two47Rating")
    private double two47Rating;

    @Column(name = "Two47RankNational")
    private int two47RankNational;

    @Column(name = "Two47RankPosition")
    private int two47RankPosition;

    @Column(name = "Two47RankState")
    private int two47RankState;

    @Column(name = "Two47Link")
    private String two47Link;

}
