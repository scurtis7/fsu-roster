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
    @SequenceGenerator(name = "sequence-generator", sequenceName = "player_playerid_seq", allocationSize = 1, initialValue = 100)
    @Column(name = "PlayerId", unique = true, nullable = false)
    private Long playerId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Year", nullable = false)
    private String year;

    @Column(name = "Position", nullable = false)
    private String position;

    @Column(name = "Jersey", nullable = false)
    private String jersey;

    @Column(name = "Height")
    private String height;

    @Column(name = "Weight")
    private String weight;

    @Column(name = "HomeTown")
    private String homeTown;

    @Column(name = "HighSchool")
    private String highSchool;

    @Column(name = "OtherCollege")
    private String otherCollege;

    @Column(name = "DraftPick")
    private String draftPick;

    @Column(name = "NFLTeam")
    private String nflTeam;

    @Column(name = "Notes")
    private String notes;

}
