-- Delete the FSU Roster database

ALTER TABLE IF EXISTS fsu_roster.recruit
DROP CONSTRAINT IF EXISTS recruit_pkey;

ALTER TABLE IF EXISTS fsu_roster.recruit
DROP CONSTRAINT IF EXISTS recruit_playerid_fkey;

ALTER TABLE IF EXISTS fsu_roster.player
DROP CONSTRAINT IF EXISTS player_pkey;

DROP SEQUENCE IF EXISTS fsu_roster.recruit_recruitid_seq CASCADE;
DROP SEQUENCE IF EXISTS fsu_roster.player_playerid_seq CASCADE;

DROP TABLE IF EXISTS fsu_roster.coach;
DROP TABLE IF EXISTS fsu_roster.player;
DROP TABLE IF EXISTS fsu_roster.recruit;

DROP SCHEMA IF EXISTS fsu_roster;
