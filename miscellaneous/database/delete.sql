-- Delete the FSU Roster database

ALTER TABLE IF EXISTS fsu_roster.coach
DROP CONSTRAINT IF EXISTS coach_pkey CASCADE;

ALTER TABLE IF EXISTS fsu_roster.recruit
DROP CONSTRAINT IF EXISTS recruit_pkey CASCADE;

ALTER TABLE IF EXISTS fsu_roster.recruit
DROP CONSTRAINT IF EXISTS recruit_playerid_fkey CASCADE;

ALTER TABLE IF EXISTS fsu_roster.player
DROP CONSTRAINT IF EXISTS player_pkey CASCADE;

DROP SEQUENCE IF EXISTS fsu_roster.coach_coachid_seq CASCADE;
DROP SEQUENCE IF EXISTS fsu_roster.recruit_recruitid_seq CASCADE;
DROP SEQUENCE IF EXISTS fsu_roster.player_playerid_seq CASCADE;

DROP FUNCTION IF EXISTS fsu_roster.trigger_set_timestamp() CASCADE;

DROP TABLE IF EXISTS fsu_roster.coach CASCADE;
DROP TABLE IF EXISTS fsu_roster.recruit CASCADE;
DROP TABLE IF EXISTS fsu_roster.player CASCADE;

DROP SCHEMA IF EXISTS fsu_roster;
