DROP DATABASE IF EXISTS HeroSightingsDBTest;

CREATE DATABASE HeroSightingDBTest;

USE HeroSightingDBTest;

-- Hero
CREATE TABLE Hero (
	HeroID INT PRIMARY KEY AUTO_INCREMENT,
	IsHero BOOL NOT NULL,
	Name VARCHAR(50) NOT NULL,
	Description VARCHAR(255)
);

-- Superpowers
CREATE TABLE Superpower (
	SuperpowerID INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(50) NOT NULL,
	Description VARCHAR(255)
);

-- Hero Superpowers 
CREATE TABLE HeroSuperpower (
	HeroID INT NOT NULL,
	SuperpowerID INT NOT NULL,
	PRIMARY KEY pk_HeroSuperpower(HeroID, SuperpowerID),
	FOREIGN KEY fk_HeroSuperpower_HeroID(HeroID)
				REFERENCES Hero(HeroID),
		FOREIGN KEY fk_HeroSuperpower_SuperpowerID(SuperpowerID)
				REFERENCES Superpower(SuperpowerID)
);

-- Sighting
CREATE TABLE Sighting (
	SightingID INT PRIMARY KEY AUTO_INCREMENT,
	HeroID INT NOT NULL,
	LocationID INT NOT NULL,
	Date dateTime NOT NULL,
		FOREIGN KEY fk_Sighting_HeroID(HeroID)
			REFERENCES Hero(HeroID),
		FOREIGN KEY fk_Sighting_LocationID(LocationID)
			REFERENCES Location(LocationID)
);

-- Organization
CREATE TABLE Organization (
	OrganizationID INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(50) NOT NULL,
	IsHero BOOL NOT NULL,
	Description VARCHAR(255),
	Address VARCHAR(255),
	Contact VARCHAR(255)
);

-- HeroOrganization
CREATE TABLE HeroOrganization (
	HeroID INT PRIMARY KEY AUTO_INCREMENT,
	OrganizationID INT PRIMARY KEY AUTO_INCREMENT,
		FOREIGN KEY fk_HeroOrganization_HeroID(HeroID)
			REFERENCES Hero(HeroID),
		FOREIGN KEY fk_HeroOrganization_OrganizationID(OrganizationID)
			REFERENCES Organization(OrganizationID)
);

-- Location
CREATE TABLE Location (
	LocationID INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(50) NOT NULL,
	Latitude DECIMAL(10, 8) NOT NULL,
	Longitude DECIMAL(11, 8) NOT NULL,
	Description VARCHAR(255),
	AddressInformation VARCHAR(255)
);