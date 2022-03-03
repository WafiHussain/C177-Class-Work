-- Wafi Hussain
-- GuessTheNumberDB.sql

DROP DATABASE IF EXISTS GuessTheNumberDB;
CREATE DATABASE GuessTheNumberDB;
USE GuessTheNumberDB;

-- This is where the game is made
CREATE TABLE theGame (
    gameID INT PRIMARY KEY AUTO_INCREMENT,
    theAnswer char(4),
    finish BOOLEAN DEFAULT false
);

-- This is where the Rounds are made
CREATE TABLE rounds (
    roundID INT PRIMARY KEY AUTO_INCREMENT,
    gameID INT NOT NULL,
    timeGuess TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    guess CHAR(4),
    theResult CHAR(7),
    FOREIGN KEY fk_gameID(gameID)
        REFERENCES theGame(gameID)
);

-- Now selecting from both game and rounds
SELECT * FROM theGame;
SELECT * FROM rounds
