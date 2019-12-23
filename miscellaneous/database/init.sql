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
    Year            INT NOT NULL,
    Redshirt        BOOLEAN DEFAULT FALSE,
    Jersey          INT NOT NULL,
    Sport           VARCHAR(50) NOT NULL,
    Status          VARCHAR(100) NOT NULL,
    Height          VARCHAR(10),
    Weight          INT,
    HomeTown        VARCHAR(100),
    HighSchool      VARCHAR(100),
    OtherCollege    VARCHAR(100),
    Stars           INT,
    DraftPick       INT,
    NFLTeam         VARCHAR(100),
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

INSERT INTO fsu_roster.Coach (Id, Name, Position, Sport)
VALUES (1, 'Mike Norvell', 'Head Coach', 'Football'),
       (2, 'Kenny Dillingham', 'Offensive Coordinator/Quarterbacks Coach', 'Football'),
       (3, 'Adam Fuller', 'Defensive Coordinator', 'Football'),
       (4, 'Odell Haggins', 'Defensive Lin Coach', 'Football'),
       (5, 'Chris Thomsen', 'Deputy Head Coach', 'Football'),
       (6, 'Ron Dugans', 'Wide Receivers Coach', 'Football'),
       (7, 'Harlon Barnett', 'Defensive Coordinator/Defensive Backs Coach', 'Football'),
       (8, 'Kendal Briles', 'Offensive Coordinator/Quarterbacks Coach', 'Football'),
       (9, 'Mark Snyder', 'Special Teams Coordinator/Outside Linebackers Coach', 'Football'),
       (10, 'Randy Clements', 'Offensive Line Coach', 'Football'),
       (11, 'Telly Lockette', 'Tight Ends Coach', 'Football'),
       (12, 'Donte Pimpleton', 'Running Backs Coach', 'Football'),
       (13, 'Raymond Woodie', 'Inside Linebackers Coach', 'Football'),
       (14, 'Raymond Woodie', 'Inside Linebackers Coach', 'Football');

COMMIT;

INSERT INTO fsu_roster.Player (Id, Name, Position, Year, Redshirt, Jersey, Sport, Status, Height, Weight, HomeTown, HighSchool, OtherCollege, Stars, DraftPick, NFLTeam, Notes)
VALUES (1, 'James Blackman', 'Quarterback', 2017, true, 1, 'Football', 'active', '6 5', 195, 'South Bay, Fla.', 'Glades Central', '', 0, 0, '', ''),
       (2, 'Levonta Taylor', 'Defensive Back', 2016, false, 1, 'Football', 'active', '5 10', 190, 'Virginia Beach, Va.', 'Ocean Lakes', '', 0, 0, '', ''),
       (3, 'Cam Akers', 'Running Back', 2017, false, 3, 'Football', 'active', '5 11', 212, 'Clinton, Miss.', 'Clinton', '', 0, 0, '', ''),
       (4, 'Khalan Laborn', 'Running Back', 2017, true, 4, 'Football', 'active', '5 11', 205, 'Virginia Beach, Va.', 'Bishop Sullivan', '', 0, 0, '', ''),
       (5, 'Dontavious Jackson', 'Linebacker', 2016, false, 5, 'Football', 'active', '6 3', 229, 'Alief, Texas', 'Elsik', '', 0, 0, '', ''),
       (6, 'Jaiden Lars-Woodbey', 'Defensive Back', 2018, false, 6, 'Football', 'active', '6 3', 223, 'Fontana, Calif.', 'St. John Bosco', '', 0, 0, '', ''),
       (7, 'Tre McKitty', 'Tight End', 2017, false, 6, 'Football', 'active', '6 5', 245, 'Wesley Chapel, Fla.', 'IMG Academy', '', 0, 0, '', ''),
       (8, 'D.J. Matthews', 'Wide Receiver', 2017, false, 7, 'Football', 'active', '5 10', 156, 'Jacksonville, Fla.', 'Trinity Christian', '', 0, 0, '', ''),
       (9, 'Stanford Samuels III', 'Defensive Back', 2017, false, 8, 'Football', 'active', '6 2', 185, 'Pembroke Pines, Fla.', 'Flanagan', '', 0, 0, '', ''),
       (10, 'Kevon Glenn', 'Linebacker', 2019, false, 10, 'Football', 'active', '6 2', 236, 'Riverdale, Ga.', 'Dutchtown', '', 0, 0, '', ''),
       (11, 'Anthony Grant', 'Running Back', 2018, false, 10, 'Football', 'active', '5 11', 197, 'Buford, Ga.', 'Buford', '', 0, 0, '', ''),
       (12, 'Gino English', 'Quarterback', 2019, false, 10, 'Football', 'active', '6 1', 185, 'Winter Park, Fla.', 'Winter Park', '', 0, 0, '', ''),
       (13, 'Warren Thompson', 'Wide Receiver', 2018, true, 11, 'Football', 'active', '6 3', 201, 'Seffner, Fla.', 'Armwood', '', 0, 0, '', ''),
       (14, 'Janarius Robinson', 'Defensive End', 2016, true, 11, 'Football', 'active', '6 5', 261, 'Panama City, Fla.', 'Bay', '', 0, 0, '', ''),
       (15, 'Alex Hornibrook', 'Quarterback', 2016, true, 12, 'Football', 'active', '6 4', 220, 'West Chester, Pa.', 'Malvern Prep', 'Wisconsin', 0, 0, '', ''),
       (16, 'A.J. Lytton', 'Defensive Back', 2018, false, 12, 'Football', 'active', '5 11', 179, 'Upper Marlboro, Md.', 'Wise', '', 0, 0, '', ''),
       (17, 'Caleb Ward', 'Wide Receiver', 2018, true, 13, 'Football', 'active', '6 1', 173, 'Pensacola, Fla.', 'Booker T. Washington', '', 0, 0, '', ''),
       (18, 'Jordan Travis', 'Quarterback', 2018, true, 13, 'Football', 'active', '6 1', 208, 'West Palm Beach, Fla.', 'The Benjamin School', 'Louisville', 0, 0, '', ''),
       (19, 'Joshua Kaindoh', 'Defensive End', 2017, false, 13, 'Football', 'active', '6 7', 261, 'Baltimore, Md.', 'IMG Academy (Fla.)', '', 0, 0, '', ''),
       (20, 'Deonte Sheffield', 'Running Back', 2017, true, 14, 'Football', 'active', '5 10', 203, 'Destin, Fla.', 'Niceville', '', 0, 0, '', ''),
       (21, 'Kyle Meyers', 'Defensive Back', 2016, false, 14, 'Football', 'active', '6 0', 174, 'New Orleans, La.', 'Holy Cross', '', 0, 0, '', ''),
       (22, 'Carlos Becker III', 'Defensive Back', 2016, true, 15, 'Football', 'active', '6 2', 200, 'Kissimmee, Fla.', 'Osceola', '', 0, 0, '', ''),
       (23, 'Tamorrion Terry', 'Wide Receiver', 2017, true, 15, 'Football', 'active', '6 4', 203, 'Ashburn, Ga.', 'Turner County', '', 0, 0, '', ''),
       (24, 'Cory Durden', 'Defensive Tackle', 2017, true, 16, 'Football', 'active', '6 5', 305, 'Newberry, Fla.', 'Newberry', '', 0, 0, '', ''),
       (25, 'Alex Eleyssami', 'Quarterback', 2018, true, 16, 'Football', 'active', '6 1', 190, 'Orlando, Fla', 'First Academy', '', 0, 0, '', ''),
       (26, 'Travis Jay', 'Defensive Back', 2019, false, 18, 'Football', 'active', '6 2', 185, 'Greenville, Fla.', 'Madison County', '', 0, 0, '', ''),
       (27, 'D Anfernee McGriff', 'Wide Receiver', 2016, true, 18, 'Football', 'active', '6 1', 203, 'Gretna, Fla.', 'Leon', 'Florida Atlantic', 0, 0, '', ''),
       (28, 'Jarvis Brownlee', 'Defensive Back', 2019, false, 19, 'Football', 'active', '5 11', 165, 'Miami Gardens, Fla.', 'Miami Carol City', '', 0, 0, '', ''),
       (29, 'Wyatt Rector', 'Quarterback', 2018, true, 19, 'Football', 'active', '6 2', 227, 'Leesburg, Fla.', 'Leesburg', 'Western Michigan', 0, 0, '', ''),
       (30, 'Kalen DeLoach', 'Linebacker', 2019, false, 20, 'Football', 'active', '6 1', 211, 'Savannah, Ga.', 'Islands', '', 0, 0, '', ''),
       (31, 'Keyshawn Helton', 'Wide Receiver', 2018, false, 20, 'Football', 'active', '5 9', 170, 'Pensacola, Fla.', 'West Florida', '', 0, 0, '', ''),
       (32, 'Marvin Wilson', 'Defensive Tackle', 2017, false, 21, 'Football', 'active', '6 5', 311, 'Houston, Texas', 'Episcopal', '', 0, 0, '', ''),
       (33, 'Adonis Thomas', 'Linebacker', 2015, true, 22, 'Football', 'active', '6 4', 239, 'Atlanta, Ga.', 'Central Gwinnett', '', 0, 0, '', ''),
       (34, 'Hamsah Nasirildeen', 'Defensive Back', 2017, false, 23, 'Football', 'active', '6 4', 215, 'Concord, N.C.', 'Concord', '', 0, 0, '', ''),
       (35, 'Ricky Aguayo', 'Kicker', 2016, false, 23, 'Football', 'active', '6 2', 215, 'Mascotte, Fla.', 'IMG Academy', '', 0, 0, '', ''),
       (36, 'Cyrus Fagan', 'Defensive Back', 2017, false, 24, 'Football', 'active', '6 1', 191, 'Daytona Beach, Fla.', 'Mainland', '', 0, 0, '', ''),
       (37, 'Asante Samuel, JR.', 'Defensive Back', 2018, false, 26, 'Football', 'active', '5 10', 180, 'Sunrise, Fla.', 'St. Thomas Aquinas', '', 0, 0, '', '')
;
COMMIT;
