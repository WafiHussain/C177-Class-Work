-- WafiHussain-HotelDB.sql
DROP DATABASE IF EXISTS HotelReservations;
    CREATE DATABASE HotelReservations;
    USE HotelReservations;
    
    -- PRIMARY TABLES
    CREATE TABLE IF NOT EXISTS Room(
    RoomID INT PRIMARY KEY,
    roomType VARCHAR(6),
    ADAAccess BOOL,
    StandardOccupancy INT,
    MaxOccupancy INT,
    BasePrice DOUBLE,
    ExtraPerson INT
    );
    
    -- Guest
	CREATE TABLE IF NOT EXISTS Guest(
	GuestID VARCHAR (50) PRIMARY KEY,
    FirstName VARCHAR (30),
    LastName VARCHAR (30),
    Address VARCHAR (50),
    City VARCHAR(25),
    State VARCHAR(2),
    Zip VARCHAR(5),
    Phone VARCHAR (15)
    );
    
    -- Reservation
    CREATE TABLE IF NOT EXISTS Reservation(
    ReservationID INT PRIMARY KEY AUTO_INCREMENT,
    Adults INT,
    Children INT,
    StartDate DATE,
    EndDate DATE,
    TotalRoomCost DOUBLE
    );
    
    -- Amenity
    CREATE TABLE IF NOT EXISTS Amenity(
    AmenID VARCHAR (15) PRIMARY KEY
    );
    
    -- BRIDGE TABLES
    CREATE TABLE IF NOT EXISTS RoomAmenity(
   -- roomAmenID INT PRIMARY KEY AUTO_INCREMENT,
    RoomID INT NOT NULL,
    AmenID VARCHAR(15) NOT NULL,
    FOREIGN KEY fk_Room_RoomAmenity(RoomID)
		REFERENCES Room(RoomID),
	FOREIGN KEY fk_Amenity_RoomAmenity(AmenID)
		REFERENCES Amenity(AmenID)
    );
    
    CREATE TABLE IF NOT EXISTS GuestReservation(
   -- guestReservationID INT PRIMARY KEY AUTO_INCREMENT,
    GuestID VARCHAR(50) NOT NULL,
    ReservationID INT NOT NULL,
    FOREIGN KEY fk_Guest_GuestReservation(GuestID)
		REFERENCES Guest(GuestID),
	FOREIGN KEY fk_Reservation_GuestReservation(ReservationID)
		REFERENCES Reservation(ReservationID)
    );
    
    CREATE TABLE IF NOT EXISTS RoomReservation(
   -- RoomReservationID INT PRIMARY KEY AUTO_INCREMENT
   RoomID INT NOT NULL,
   ReservationID INT NOT NULL,
   FOREIGN KEY fk_Room_RoomReservation(RoomID)
	REFERENCES Room(RoomID),
   FOREIGN KEY fk_Reservation_RoomReservation(ReservationID)
	REFERENCES Reservation(ReservationID)
    );

-- Inserting the values into the Room
INSERT INTO Room(RoomID, roomType, ADAAccess, StandardOccupancy, MaxOccupancy, BasePrice, ExtraPerson)
    VALUES
    (201, 'Double', FALSE, 2, 4, 199.99, 10),
    (202, 'Double', TRUE, 2, 4, 174.99, 10),
    (203, 'Double', FALSE, 2, 4, 199.99, 10),
    (204, 'Double', TRUE, 2, 4, 174.99, 10),
	(205, 'Single', FALSE, 2, 2, 174.99, NULL),
    (206, 'Single', TRUE, 2, 2, 149.99, NULL),
    (207, 'Single', FALSE, 2, 2, 174.99, NULL),
    (208, 'Single', TRUE, 2, 2, 149.99, NULL),
    (301, 'Double', FALSE, 2, 4, 199.99, 10),
    (302, 'Double', TRUE, 2, 4, 174.99, 10),
    (303, 'Double', FALSE, 2, 4, 199.99, 10),
    (304, 'Double', TRUE, 2, 4, 174.99, 10),
    (305, 'Single', FALSE, 2, 2, 174.99, NULL),
    (306, 'Single', TRUE, 2, 2, 149.99, NULL),
    (307, 'Single', FALSE, 2, 2, 174.99, NULL),
    (308, 'Single', TRUE, 2, 2, 149.99, NULL),
    (401, 'Suite', TRUE, 3, 8, 399.99, 20),
    (402, 'Suite', TRUE, 3, 8, 399.99, 20);


