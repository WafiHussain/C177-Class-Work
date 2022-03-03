-- Wafi Hussain
-- GuessTheNumberDBandData.sql

DROP DATABASE IF EXISTS GuessTheNumberDB;
CREATE DATABASE GuessTheNumberDB;
USE GuessTheNumberDB;

-- Adding the data to the table for theGame
CREATE TABLE theGame (
    gameID INT PRIMARY KEY AUTO_INCREMENT,
    answer CHAR(4),
    finish BOOLEAN DEFAULT false
);

INSERT INTO theGame(gameID, theAnswer, finish)
VALUES
    (1, "2971", true),
    (2, "9218", true),
    (3, "2345", true);

-- Adding the data to the table for Rounds
CREATE TABLE rounds (
    roundID INT PRIMARY KEY AUTO_INCREMENT,
    gameID INT NOT NULL,
    guessTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    guess CHAR(4),
    result CHAR(7),
    FOREIGN KEY fk_gameID(gameID)
        REFERENCES theGame(gameID)
);

INSERT INTO rounds(roundID, gameID, guessTime, result)
VALUES
(1, 1, "", "", ""),
(2, 1, "", "", ""),
(3, 1, "", "", ""),
(4, 2, "", "", ""),
(5, 3, "", "", ""),
(6, 3, "", "", "");

-- Make a selection for the games and rounds
select * from theGame;
select * from rounds;