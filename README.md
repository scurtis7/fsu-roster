# fsu-roster

Florida State University is my alma-mater and I enjoy watching the football team.  This application is my personal 
project to keep track of the FSU Football roster.  This is a spring boot reactjs application using a postgresql 
database.  The official roster can be found on the [fsu seminoles](https://seminoles.com/sports/football/roster/) site.
Here is a link to the [fsu communications](http://unicomm.fsu.edu/brand/applying/colors/) site that contains all of 
the official branding information.

## Version 0.0.3
Add players page to list all of the players in the database.  Added the players to the database

## Version 0.0.2
Add coaches page to list all of the coaches in the database.

## Version 0.0.1
This is the initial version with all of the three main pieces (Spring Boot, ReactJS & Postgresql) working together.
Not much functionality yet just the initial shell of the application.

## Technologies
- Spring Boot - `v2.2.2-RELEASE`
- Java - `v1.8`
- ReactJS - `v16.12.0`
- Postgresql - `v12.1`
- Gradle - `v6.0.1`
- Yarn - `v1.19.2`

## How to run locally
I use a Mac and IntelliJ IDEA so these instructions are for that setup.
After you clone the repository then follow the steps below.

### Start DB in docker container
First step is to start the Postgresql database in a docker container.  To do that run the following command of course
replace the `<password>` with your own:

`docker run --name fsuroster -e POSTGRES_PASSWORD=<password> -d -p 5432:5432 postgres`

### Initialize the database
Next you will need to setup the database and initialize the tables.  I do this from IntelliJ and the database init
scripts can be found in `fsu-roster/miscellaneous/database/init.sql`

### Build the ReactJS application
To build the React application follow the following steps:
- open a terminal
- navigate to `fsu-roster/src/webapp`
- install react dependencies with this command `yarn install` (you only have to do this once)
- run the following command `yarn build`

### Build the spring boot application
Next build the Spring Boot application with these steps:
- open a terminal
- navigate to `fsu-roster/`
- run the following command `./gradlew clean build`

### Start the application
Once the application is built you can run it with the following command:
- In the terminal from the same location you ran the build from
- run the following command `./gradlew boot-run`
- navigate to the [fsu roster application](http://localhost:8080)

**NOTE:** You can also build and run the application from IntelliJ
