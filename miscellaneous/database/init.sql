-- Initialize fsu_roster SCHEMA

-- CREATE SCHEMA fsu_roster;

DROP TABLE IF EXISTS
    fsu_roster.Player, fsu_roster.Coach;

CREATE OR REPLACE FUNCTION trigger_set_timestamp()
              RETURNS TRIGGER AS $$
BEGIN
    NEW.LastUpdateDate = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create the Player table
CREATE TABLE fsu_roster.Player
(
    Id              SERIAL PRIMARY KEY,
    Name            VARCHAR(100) NOT NULL,
    Position        VARCHAR(50) NOT NULL,
    Class           VARCHAR(50) NOT NULL,
    Jersey          INT NOT NULL,
    Height          VARCHAR(10),
    Weight          INT,
    HomeTown        VARCHAR(100),
    HighSchool      VARCHAR(100),
    OtherCollege    VARCHAR(100),
    Notes           TEXT,
    LastUpdateDate  TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TRIGGER set_player_timestamp
    BEFORE UPDATE ON fsu_roster.Player
    FOR EACH ROW
    EXECUTE PROCEDURE trigger_set_timestamp();

-- Create the Coach table
CREATE TABLE fsu_roster.Coach
(
    Id              SERIAL PRIMARY KEY,
    Name            VARCHAR(100) NOT NULL DEFAULT '',
    Position        VARCHAR(100) NOT NULL DEFAULT '',
    Sport           VARCHAR(100) NOT NULL DEFAULT '',
    LastUpdateDate  TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TRIGGER set_coach_timestamp
    BEFORE UPDATE ON fsu_roster.Coach
    FOR EACH ROW
    EXECUTE PROCEDURE trigger_set_timestamp();

COMMIT;

-- INSERT INTO Team (Id, Code, FullName, ShortName, Conference, Division)
-- VALUES (1, 'ARI', 'Arizona Cardinals', 'Arizona', 'NFC', 'West'),
--        (2, 'ATL', 'Atlanta Falcons', 'Atlanta', 'NFC', 'South'),
--        (3, 'BAL', 'Baltimore Ravens', 'Baltimore', 'AFC', 'North'),
--        (4, 'BUF', 'Buffalo Bills', 'Buffalo', 'AFC', 'East'),
--        (5, 'CAR', 'Carolina Panthers', 'Carolina', 'NFC', 'South'),
--        (6, 'CHI', 'Chicago Bears', 'Chicago', 'NFC', 'North'),
--        (7, 'CIN', 'Cincinnati Bengals', 'Cincinnati', 'AFC', 'North'),
--        (8, 'CLE', 'Cleveland Browns', 'Cleveland', 'AFC', 'North'),
--        (9, 'DAL', 'Dallas Cowboys', 'Dallas', 'NFC', 'East'),
--        (10, 'DEN', 'Denver Broncos', 'Denver', 'AFC', 'West'),
--        (11, 'DET', 'Detroit Lions', 'Detroit', 'NFC', 'North'),
--        (12, 'GB', 'Green Bay Packers', 'Green Bay', 'NFC', 'North'),
--        (13, 'HOU', 'Houston Texans', 'Houston', 'AFC', 'South'),
--        (14, 'IND', 'Indianapolis Colts', 'Indianapolis', 'AFC', 'South'),
--        (15, 'JAX', 'Jacksonville Jaguars', 'Jacksonville', 'AFC', 'South'),
--        (16, 'KC', 'Kansas City Chiefs', 'Kansas City', 'AFC', 'West'),
--        (17, 'MIA', 'Miami Dolphins', 'Miami', 'AFC', 'East'),
--        (18, 'MIN', 'Minnesota Vikings', 'Minnesota', 'NFC', 'North'),
--        (19, 'NYG', 'N.Y. Giants', 'N.Y. Giants', 'NFC', 'East'),
--        (20, 'NYJ', 'N.Y. Jets', 'N.Y. Jets', 'AFC', 'East'),
--        (21, 'NE', 'New England Patriots', 'New England', 'AFC', 'East'),
--        (22, 'NO', 'New Orleans Saints', 'New Orleans', 'NFC', 'South'),
--        (23, 'OAK', 'Oakland Raiders', 'Oakland', 'AFC', 'West'),
--        (24, 'PHI', 'Philadelphia Eagles', 'Philadelphia', 'NFC', 'East'),
--        (25, 'PIT', 'Pittsburgh Steelers', 'Pittsburgh', 'AFC', 'North'),
--        (26, 'LAC', 'Los Angeles Chargers', 'L.A. Chargers', 'AFC', 'West'),
--        (27, 'SF', 'San Francisco 49ers', 'San Francisco', 'NFC', 'West'),
--        (28, 'SEA', 'Seattle Seahawks', 'Seattle', 'NFC', 'West'),
--        (29, 'LA', 'Los Angeles Rams', 'L.A. Rams', 'NFC', 'West'),
--        (30, 'TB', 'Tampa Bay Buccaneers', 'Tampa Bay', 'NFC', 'South'),
--        (31, 'TEN', 'Tennessee Titans', 'Tennessee', 'AFC', 'South'),
--        (32, 'WAS', 'Washington Redskins', 'Washington', 'NFC', 'East');
--
-- COMMIT;
