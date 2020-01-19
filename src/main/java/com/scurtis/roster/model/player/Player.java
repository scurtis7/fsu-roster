package com.scurtis.roster.model.player;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "Player", schema = "fsu_roster")
@Data
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(name = "sequence-generator", sequenceName = "player_playerid_seq")
    @Column(name = "PlayerId", unique = true, nullable = false)
    private Long playerId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Position", nullable = false)
    private String position;

    @Column(name = "Year", nullable = false)
    private int year;

    @Column(name = "Redshirt", nullable = false)
    private Boolean redshirt;

    @Column(name = "Jersey", nullable = false)
    private int jersey;

    @Column(name = "Sport", nullable = false)
    private String sport;

    @Column(name = "Active", nullable = false)
    private Boolean active;

    @Column(name = "Status")
    private String status;

    @Column(name = "Height")
    private String height;

    @Column(name = "Weight")
    private int weight;

    @Column(name = "HomeTown")
    private String homeTown;

    @Column(name = "HighSchool")
    private String highSchool;

    @Column(name = "OtherCollege")
    private String otherCollege;

    @Column(name = "DraftPick")
    private int draftPick;

    @Column(name = "NFLTeam")
    private String nflTeam;

    @Column(name = "Notes")
    private String notes;

}
