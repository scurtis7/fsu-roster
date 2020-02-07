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
 * Date: Feb 06, 2020
 **/

@Entity
@Table(name = "Two47", schema = "fsu_roster")
@Data
@NoArgsConstructor
public class Two47 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(name = "sequence-generator", sequenceName = "two47_two47id_seq", allocationSize = 1, initialValue = 100)
    @Column(name = "Two47Id", unique = true, nullable = false)
    private Long Two47Id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    private Player player;

    @Column(name = "Stars")
    private String stars;

    @Column(name = "Rating")
    private String rating;

    @Column(name = "RankNational")
    private String rankNational;

    @Column(name = "RankPosition")
    private String rankPosition;

    @Column(name = "RankState")
    private String rankState;

    @Column(name = "Link")
    private String link;

}