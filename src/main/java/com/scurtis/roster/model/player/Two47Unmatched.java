package com.scurtis.roster.model.player;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: Steve Curtis
 * Date: Feb 13, 2020
 **/

@Entity
@Table(name = "Two47Unmatched", schema = "fsu_roster")
@Data
@NoArgsConstructor
public class Two47Unmatched {

    @Id
    @Column(name = "siteId", unique = true, nullable = false)
    private String siteId;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "homeTown")
    private String homeTown;

    @Column(name = "highSchool")
    private String highSchool;

    @Column(name = "year")
    private String year;

    @Column(name = "compositeRank")
    private String compositeRank;

    @Column(name = "RankNational")
    private String rankNational;

    @Column(name = "RankPosition")
    private String rankPosition;

    @Column(name = "RankState")
    private String rankState;

    @Column(name = "Stars")
    private String stars;

    @Column(name = "Link")
    private String link;

}
