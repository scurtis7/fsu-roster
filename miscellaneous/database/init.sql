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
       (37, 'Asante Samuel, JR.', 'Defensive Back', 2018, false, 26, 'Football', 'active', '5 10', 180, 'Sunrise, Fla.', 'St. Thomas Aquinas', '', 0, 0, '', ''),
       (38, 'Akeem Dent', 'Defensive Back', 2019, false, 27, 'Football', 'active', '6 1', 182, 'Pahokee, Fla.', 'Palm Beach Central', '', 0, 0, '', ''),
       (39, 'DeCalon Brooks', 'Linebacker', 2017, true, 28, 'Football', 'active', '5 10', 209, 'Tampa, Fla.', 'Gaither', '', 0, 0, '', ''),
       (40, 'Isaiah Bolden', 'Defensive Back', 2018, true, 29, 'Football', 'active', '6 2', 194, 'Tampa, Fla.', 'Wesley Chapel', '', 0, 0, '', ''),
       (41, 'Tre Young', 'Wide Receiver', 2019, false, 29, 'Football', 'active', '6 0', 195, 'Atlanta, Ga.', 'Riverwood International Charter', '', 0, 0, '', ''),
       (42, 'Quashon Fuller', 'Defensive End', 2019, false, 30, 'Football', 'active', '6 3', 267, 'Fort Myers, Fla.', 'Lehigh Senior', '', 0, 0, '', ''),
       (43, 'Tommy Martin', 'Punter', 2017, true, 30, 'Football', 'active', '5 11', 191, 'Chesapeake, Va.', 'Hickory', '', 0, 0, '', ''),
       (44, 'Raymond Woodie III', 'Defensive Back', 2019, false, 31, 'Football', 'active', '6 0', 198, 'Palmetto, Fla.', 'Florida State University School', '', 0, 0, '', ''),
       (45, 'Gabe Nabers', 'Tight End', 2016, false, 32, 'Football', 'active', '6 3', 243, 'Hahira, Ga.', 'Lowndes', '', 0, 0, '', ''),
       (46, 'Amari Gainer', 'Linebacker', 2018, true, 33, 'Football', 'active', '6 3', 215, 'Tallahassee, Fla.', 'Chiles', '', 0, 0, '', ''),
       (47, 'Leonard Warner III', 'Linebacker', 2017, false, 35, 'Football', 'active', '6 4', 241, 'Snellville, Ga.', 'Brookwood', '', 0, 0, '', ''),
       (48, 'Renardo Green', 'Defensive Back', 2019, false, 36, 'Football', 'active', '6 0', 174, 'Orlando, Fla.', 'Wekiva', '', 0, 0, '', ''),
       (49, 'Parker Grothaus', 'Kicker', 2017, true, 37, 'Football', 'active', '6 2', 220, 'Indian Lake, Ohio', 'Indian Lake', '', 0, 0, '', ''),
       (50, 'Raekwon Webb', 'Running Back', 2016, true, 37, 'Football', 'active', '5 8', 195, 'Panama City, Fla.', 'Bay', '', 0, 0, '', ''),
       (51, 'Treshaun Ward', 'Running Back', 2019, false, 38, 'Football', 'active', '5 10', 194, 'Plant City, Fla.', 'Tampa Bay Tech', '', 0, 0, '', ''),
       (52, 'DeAundre Emeric', 'Defensive Back', 2017, true, 39, 'Football', 'active', '5 9', 196, 'Orlando, Fla.', 'University', '', 0, 0, '', ''),
       (53, 'Ethan Unstead', 'Linebacker', 2019, false, 40, 'Football', 'active', '6 1', 220, 'Lake City, Fla.', 'Columbia', '', 0, 0, '', ''),
       (54, 'Nolan McDonald', 'Defensive Back', 2018, true, 41, 'Football', 'active', '6 2', 180, 'Long Beach, Calif.', 'Long Beach Poly', '', 0, 0, '', ''),
       (55, 'Garrett Murray', 'Long Snapper', 2018, true, 42, 'Football', 'active', '6 0', 240, 'Tampa, Fla.', 'Plant', '', 0, 0, '', ''),
       (56, 'Jaleel McRae', 'Linebacker', 2019, false, 42, 'Football', 'active', '6 2', 233, 'New Smyrna Beach, Fla.', 'IMG Academy', '', 0, 0, '', ''),
       (57, 'Joseph Schergen', 'Defensive Back', 2017, false, 43, 'Football', 'active', '5 9', 176, 'Loxahatchee, Fla.', 'Seminole Ridge', '', 0, 0, '', ''),
       (58, 'Brendan Gant', 'Defensive Back', 2019, false, 44, 'Football', 'active', '6 2', 198, 'Lakeland, Fla.', 'Kathleen', '', 0, 0, '', ''),
       (59, 'Grant Glennon', 'Long Snapper', 2017, true, 44, 'Football', 'active', '6 3', 218, 'Tallahassee, Fla.', 'Lincoln', '', 0, 0, '', ''),
       (60, 'Tyler Gilroy', 'Linebacker', 2017, true, 45, 'Football', 'active', '5 11', 235, 'St. Augustine, Fla.', 'Bartram Trail', '', 0, 0, '', ''),
       (61, 'Joseph Garcia', 'Linebacker', 2016, true, 47, 'Football', 'active', '6 2', 218, 'Miami, Fla.', 'Belen Jesuit', '', 0, 0, '', ''),
       (62, 'Armani Kerr', 'Linebacker', 2016, true, 48, 'Football', 'active', '5 11', 220, 'Miami, Fla.', 'Miami Jackson', '', 0, 0, '', ''),
       (63, 'Cedric Wood', 'Defensive Tackle', 2016, true, 49, 'Football', 'active', '6 3', 318, 'Tallahassee, Fla.', 'Godby', '', 0, 0, '', ''),
       (64, 'Baveon Johnson', 'Offensive Lineman', 2016, true, 51, 'Football', 'active', '6 3', 305, 'Lakeland, Fla.', 'Lake Gibson', '', 0, 0, '', ''),
       (65, 'Josh Brown', 'Linebacker', 2016, false, 51, 'Football', 'active', '6 3', 229, 'Charlotte, N.C.', 'Mallard Creek', '', 0, 0, '', ''),
       (66, 'Christian Meadows', 'Offensive Lineman', 2018, true, 52, 'Football', 'active', '6 4', 336, 'Montezuma, Ga.', 'Macon County', '', 0, 0, '', ''),
       (67, 'Maurice Smith', 'Offensive Lineman', 2019, false, 53, 'Football', 'active', '6 3', 301, 'Miami, Fla.', 'Miami Central', '', 0, 0, '', ''),
       (68, 'Ricardo Watson', 'Linebacker', 2019, false, 54, 'Football', 'active', '6 0', 247, 'Tampa, Fla.', 'Armwood', '', 0, 0, '', ''),
       (69, 'Derrick McLendon II', 'Defensive End', 2019, false, 55, 'Football', 'active', '6 4', 252, 'Decatur, Ga.', 'Tucker', '', 0, 0, '', ''),
       (70, 'Dontae Lucas', 'Offensive Lineman', 2019, false, 55, 'Football', 'active', '6 3', 315, 'Overtown, Fla.', 'IMG Academy', '', 0, 0, '', ''),
       (71, 'Ryan Roberts', 'Offensive Lineman', 2015, true, 56, 'Football', 'active', '6 6', 300, 'Northville, Mich.', 'Northville', 'Northern Illinois', 0, 0, '', ''),
       (72, 'Emmett Rice', 'Linebacker', 2016, true, 56, 'Football', 'active', '6 2', 220, 'Miami Gardens, Fla.', 'Norland', '', 0, 0, '', ''),
       (73, 'Axel Rizo', 'Linebacker', 2018, true, 57, 'Football', 'active', '6 2', 238, 'Miami, Fla.', 'Gulliver Prep', '', 0, 0, '', ''),
       (74, 'Dennis Briggs, JR.', 'Defensive End', 2018, true, 58, 'Football', 'active', '6 4', 268, 'Kissimmee, Fla.', 'Gateway', '', 0, 0, '', ''),
       (75, 'Brady Scott', 'Offensive Lineman', 2017, true, 59, 'Football', 'active', '6 6', 312, 'Powder Springs, Ga.', 'Mount Paran Christian', '', 0, 0, '', ''),
       (76, 'Andrew Boselli', 'Offensive Lineman', 2016, true, 60, 'Football', 'active', '6 5', 308, 'Jacksonville, Fla.', 'Episcopal', '', 0, 0, '', ''),
       (77, 'Jalen Goss', 'Offensive Lineman', 2018, true, 61, 'Football', 'active', '6 7', 270, 'Valdosta, Ga.', 'Lowndes', '', 0, 0, '', ''),
       (78, 'Alexander Strozier', 'Offensive Lineman', 2015, true, 62, 'Football', 'active', '6 2', 302, 'Bartow, Fla.', 'Bartow', '', 0, 0, '', ''),
       (79, 'Tanner Adkison', 'Long Snapper', 2016, true, 63, 'Football', 'active', '5 10', 192, 'Tallahassee, Fla.', 'Godby', '', 0, 0, '', ''),
       (80, 'Jeremy Czerenda', 'Offensive Lineman', 2018, true, 68, 'Football', 'active', '6 3', 285, 'Fort Lauderdale, Fla.', 'University', '', 0, 0, '', ''),
       (81, 'Robert Elder IV', 'Offensive Lineman', 2018, true, 69, 'Football', 'active', '6 2', 255, 'Stuart, Fla.', 'Jensen Beach', '', 0, 0, '', ''),
       (82, 'Cole Minshew', 'Offensive Lineman', 2015, true, 70, 'Football', 'active', '6 5', 330, 'Pridgen, Ga.', 'Coffee County', '', 0, 0, '', ''),
       (83, 'Chaz Neal', 'Offensive Lineman', 2018, true, 71, 'Football', 'active', '6 7', 305, 'Zephyrhills, Fla.', 'Wesley Chapel', '', 0, 0, '', ''),
       (84, 'Mike Arnold', 'Offensive Lineman', 2016, true, 72, 'Football', 'active', '6 5', 340, 'Winter Haven, Fla.', 'Winter Haven', '', 0, 0, '', ''),
       (85, 'Jauan Williams', 'Offensive Lineman', 2016, true, 73, 'Football', 'active', '6 7', 310, 'Washington, D.C.', 'Archbishop Carroll', '', 0, 0, '', ''),
       (86, 'Jay Williams', 'Offensive Lineman', 2016, true, 74, 'Football', 'active', '6 6', 300, 'Moreno Valley, Calif.', 'Nuview Bridge Early College', 'Grossmont College', 0, 0, '', ''),
       (87, 'Abdul Bello', 'Offensive Lineman', 2015, true, 75, 'Football', 'active', '6 6', 315, 'Warri, Nigeria', 'Montverde Academy (Fla.)', '', 0, 0, '', ''),
       (88, 'Darius Washington', 'Offensive Lineman', 2019, false, 76, 'Football', 'active', '6 4', 308, 'Pensacola, Fla.', 'West Florida', '', 0, 0, '', ''),
       (89, 'Christian Armstrong', 'Offensive Lineman', 2018, true, 77, 'Football', 'active', '6 4', 320, 'Warner Robins, Ga.', 'Warner Robins', '', 0, 0, '', ''),
       (90, 'Ira Henry III', 'Offensive Lineman', 2019, false, 78, 'Football', 'active', '6 4', 317, 'St. Louis, Mo.', 'Trinity Catholic', '', 0, 0, '', ''),
       (91, 'Ontaria Wilson', 'Wide Receiver', 2017, true, 80, 'Football', 'active', '6 0', 170, 'Ashburn, Ga.', 'Turner County', '', 0, 0, '', ''),
       (92, 'Caleb Faris', 'Wide Receiver', 2019, false, 81, 'Football', 'active', '5 8', 175, 'Tampa, Fla.', 'Plant', '', 0, 0, '', ''),
       (93, 'Austin White', 'Tight End', 2019, false, 82, 'Football', 'active', '6 5', 242, 'Tampa, Fla.', 'Tampa Catholic', '', 0, 0, '', ''),
       (94, 'Jordan Young', 'Wide Receiver', 2018, true, 83, 'Football', 'active', '6 2', 192, 'Conyers, Ga.', 'Heritage', '', 0, 0, '', ''),
       (95, 'Adarius Dent', 'Wide Receiver', 2018, false, 84, 'Football', 'active', '6 1', 174, 'Pahokee, Fla.', 'Pahokee', '', 0, 0, '', ''),
       (96, 'Tyrell Moorer', 'Wide Receiver', 2015, true, 85, 'Football', 'active', '5 11', 199, 'Jacksonville, Fla.', 'The Bolles School', '', 0, 0, '', ''),
       (97, 'Michael Barulich', 'Wide Receiver', 2016, true, 86, 'Football', 'active', '5 11', 195, 'Orlando, Fla.', 'Lake Nona', '', 0, 0, '', ''),
       (98, 'Camren McDonald', 'Tight End', 2018, false, 87, 'Football', 'active', '6 4', 231, 'Long Beach, Calif.', 'Long Beach Poly', '', 0, 0, '', ''),
       (99, 'Ryan Fitzgerald', 'Kicker', 2019, false, 88, 'Football', 'active', '6 1', 183, 'Coolidge, Ga.', 'Colquitt County', '', 0, 0, '', ''),
       (100, 'TreShaun Harrison', 'Wide Receiver', 2018, false, 88, 'Football', 'active', '6 2', 191, 'Seattle, Wash.', 'Garfield', '', 0, 0, '', ''),
       (101, 'Keith Gavin', 'Wide Receiver', 2016, false, 89, 'Football', 'active', '6 3', 212, 'Crawfordville, Fla.', 'Wakulla', '', 0, 0, '', ''),
       (102, 'Tru Thompson', 'Defensive Tackle', 2019, false, 90, 'Football', 'active', '6 0', 315, 'Loganville, Ga.', 'Grayson', '', 0, 0, '', ''),
       (103, 'Robert Cooper', 'Defensive Tackle', 2018, false, 91, 'Football', 'active', '6 2', 346, 'Lilburn, Ga.', 'South Gwinnett', '', 0, 0, '', ''),
       (104, 'Peter Osimen', 'Defensive Tackle', 2015, true, 93, 'Football', 'active', '6 2', 297, 'Tampa, Fla.', 'Jesuit', '', 0, 0, '', ''),
       (105, 'Jamarcus Chatman', 'Defensive End', 2018, true, 95, 'Football', 'active', '6 2', 271, 'Rome, Ga.', 'Rome', '', 0, 0, '', ''),
       (106, 'Malcolm Lamar', 'Defensive End', 2018, true, 97, 'Football', 'active', '6 5', 277, 'Seffner, Fla.', 'Armwood', '', 0, 0, '', ''),
       (107, 'Curtis Fann, JR.', 'Defensive End', 2019, false, 98, 'Football', 'active', '6 3', 250, 'Stillmore, Ga.', 'Emanuel County Institute', '', 0, 0, '', ''),
       (108, 'Malcolm Ray', 'Defensive Tackle', 2019, false, 99, 'Football', 'active', '6 2', 278, 'Miami Gardens, Fla.', 'Miami Carol City', '', 0, 0, '', '');
COMMIT;
