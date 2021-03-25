CREATE DATABASE races;

use races;

CREATE TABLE `racers` (`id` int(11) PRIMARY KEY AUTO_INCREMENT, `name` TEXT, `surname` TEXT,
 `score` int(11), `teamid` int(11)) AUTO_INCREMENT=1;

CREATE TABLE `teams` (`id` int(11) PRIMARY KEY AUTO_INCREMENT, `name` TEXT) AUTO_INCREMENT=1;
 
CREATE TABLE `tracks` (`id` int(11) PRIMARY KEY AUTO_INCREMENT, `name` TEXT,
 `country` TEXT, `winnerid` int(11), FOREIGN KEY (winnerid) REFERENCES `racers` (id)) AUTO_INCREMENT=1;
 
CREATE TABLE `competitions` (`id` int(11) PRIMARY KEY AUTO_INCREMENT, `name` TEXT, `date` DATE,
 `trackid` int(11), `racerid` int(11), `winnerid` int(11),
 FOREIGN KEY (trackid) REFERENCES `tracks` (id),
 FOREIGN KEY (racerid) REFERENCES `racers` (id),
 FOREIGN KEY (winnerid) REFERENCES `racers` (id)) AUTO_INCREMENT=1;
 
 
CREATE TABLE `test` (`id` int(11) PRIMARY KEY AUTO_INCREMENT, `name` TEXT, `surname` TEXT,
 `score` int(11)) AUTO_INCREMENT=1;