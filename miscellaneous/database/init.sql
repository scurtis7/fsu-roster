-- Initialize fsu_roster SCHEMA

-- CREATE SCHEMA fsu_roster;

DROP TABLE IF EXISTS
    fsu_roster.Player, fsu_roster.Coach, fsu_roster.Rivals, fsu_roster.Two47, fsu_roster.Two47Unmatched CASCADE;

-- Create the Player table
CREATE TABLE fsu_roster.Player
(
    PlayerId        SERIAL PRIMARY KEY,
    Name            VARCHAR(100) NOT NULL,
    Year            VARCHAR(10) NOT NULL,
    ClassYear       VARCHAR(30) NOT NULL,
    Position        VARCHAR(50) NOT NULL,
    Jersey          VARCHAR(10) NOT NULL,
    Height          VARCHAR(10),
    Weight          VARCHAR(10),
    HomeTown        VARCHAR(100),
    HighSchool      VARCHAR(100),
    OtherCollege    VARCHAR(100),
    DraftPick       VARCHAR(10),
    NFLTeam         VARCHAR(100),
    Notes           TEXT,
    LastUpdateDate  TIMESTAMP NOT NULL DEFAULT now()
);

-- Create the Rivals table
CREATE TABLE fsu_roster.Rivals
(
    RivalsId       SERIAL PRIMARY KEY,
    PlayerId       BIGINT NOT NULL REFERENCES fsu_roster.Player (PlayerId),
    SiteId         VARCHAR(50),
    Name           VARCHAR(50),
    City           VARCHAR(50),
    State          VARCHAR(10),
    Stars          VARCHAR(10),
    Position       VARCHAR(50),
    Height         VARCHAR(10),
    Weight         VARCHAR(10),
    Sign           VARCHAR(10),
    Rating         VARCHAR(10),
    CommitDate     VARCHAR(10),
    Link           VARCHAR(100),
    Status         VARCHAR(10),
    Sport          VARCHAR(50),
    Year           VARCHAR(10),
    RankNational   VARCHAR(10),
    RankPosition   VARCHAR(10),
    RankState      VARCHAR(10),
    LastUpdateDate TIMESTAMP NOT NULL DEFAULT now()
);

-- Create the 247 table
CREATE TABLE fsu_roster.Two47
(
    Two47Id        SERIAL PRIMARY KEY,
    PlayerId       BIGINT NOT NULL REFERENCES fsu_roster.Player (PlayerId),
    SiteId         VARCHAR(50),
    Name           VARCHAR(50),
    Position       VARCHAR(50),
    Height         VARCHAR(10),
    Weight         VARCHAR(10),
    HomeTown       VARCHAR(100),
    HighSchool     VARCHAR(100),
    Year           VARCHAR(10),
    CompositeRank  VARCHAR(10),
    RankNational   VARCHAR(10),
    RankPosition   VARCHAR(10),
    RankState      VARCHAR(10),
    Stars          VARCHAR(10),
    Link           VARCHAR(100),
    LastUpdateDate TIMESTAMP NOT NULL DEFAULT now()
);

-- Create the 247 table
CREATE TABLE fsu_roster.Two47Unmatched
(
    SiteId         VARCHAR(50) PRIMARY KEY,
    Name           VARCHAR(50),
    Position       VARCHAR(50),
    Height         VARCHAR(10),
    Weight         VARCHAR(10),
    HomeTown       VARCHAR(100),
    HighSchool     VARCHAR(100),
    Year           VARCHAR(10),
    CompositeRank  VARCHAR(10),
    RankNational   VARCHAR(10),
    RankPosition   VARCHAR(10),
    RankState      VARCHAR(10),
    Stars          VARCHAR(10),
    Link           VARCHAR(100)
);

-- Create the Coaches table
CREATE TABLE fsu_roster.Coach
(
    CoachId         SERIAL PRIMARY KEY,
    Name            VARCHAR(100) NOT NULL DEFAULT '',
    Position        VARCHAR(100) NOT NULL DEFAULT '',
    Sport           VARCHAR(100) NOT NULL DEFAULT '',
    LastUpdateDate  TIMESTAMP NOT NULL DEFAULT now()
);

CREATE OR REPLACE FUNCTION fsu_roster.trigger_set_timestamp()
              RETURNS TRIGGER AS $$
BEGIN
    NEW.LastUpdateDate = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_player_timestamp
    BEFORE UPDATE ON fsu_roster.Player
    FOR EACH ROW
    EXECUTE PROCEDURE fsu_roster.trigger_set_timestamp();

CREATE TRIGGER set_rivals_timestamp
    BEFORE UPDATE ON fsu_roster.Rivals
    FOR EACH ROW
    EXECUTE PROCEDURE fsu_roster.trigger_set_timestamp();

CREATE TRIGGER set_two47_timestamp
    BEFORE UPDATE ON fsu_roster.Two47
    FOR EACH ROW
    EXECUTE PROCEDURE fsu_roster.trigger_set_timestamp();

CREATE TRIGGER set_coach_timestamp
    BEFORE UPDATE ON fsu_roster.Coach
    FOR EACH ROW
    EXECUTE PROCEDURE fsu_roster.trigger_set_timestamp();

COMMIT;
