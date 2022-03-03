-- WafiHussain-HotelData.sql

-- GUESTS
    INSERT INTO Guest(GuestID, FirstName, LastName, Address, City, State, Zip, Phone)
    VALUES
    ('Carlos Beltran','Carlos','Beltran','12348 Golden Knight Cir', 'Orlando','FL', '32826', '(954) ...-....'),
    ('Mack Simmer','Mack','Simmer','379 Old Shore Street','Council Bluffs','IA','51501', '(291) 553-0508'),
    ('Bettyann Seery','Bettyann','Seery',	'750 Wintergreen Dr.',	'Wasilla',	'AK',	'99654',	'(478) 277-9632'),
    ('Duane Cullison','Duane','Cullison',	'9662 Foxrun Lane',	'Harlingen',	'TX',	'78552',	'(308) 494-0198'),
    ('Karie Yang','Karie','Yang',	'9378 W. Augusta Ave.',	'West Deptford',	'NJ',	'08096',	'(214) 730-0298'),
    ('Aurore Lipton','Aurore','Lipton',	'762 Wild Rose Street',	'Saginaw',	'MI',	'48601',	'(377) 507-0974'),
    ('Zachery Luechtefeld','Zachery','Luechtefeld',	'7 Poplar Dr.',	'Arvada',	'CO',	'80003',	'(814) 485-2615'),
    ('Jeremiah Pendergrass'	,'Jeremiah','Pendergrass','70 Oakwood St.',	'Zion',	'IL',	'60099',	'(279) 491-0960'),
    ('Walter Holaway','Walter','Holaway',	'7556 Arrowhead St.',	'Cumberland',	'RI',	'02864',	'(446) 396-6785'),
    ('Wilfred Vise','Wilfred','Vise',	'77 West Surrey Street',	'Oswego',	'NY',	'13126',	'(834) 727-1001'),
    ('Maritza Tilton','Maritza','Tilton',	'939 Linda Rd.',	'Burke',	'VA',	'22015',	'(446) 351-6860'),
    ('Joleen Tison','Joleen','Tison',	'87 Queen St.',	'Drexel Hill',	'PA',	'19026',	'(231) 893-2755');
    
    -- RESERVATION
    INSERT INTO Reservation
    VALUES
    (1,1,0,'2023-02-02','2023-02-04',299.98);
    INSERT INTO Reservation(Adults, Children, StartDate, EndDate, TotalRoomCost)
    VALUES
    (2, 1,	'2023-02-05', '2023-02-10', 999.95),
    (2, 0,'2023-02-22','2023-02-24',349.98),
    (2, 2,'2023-03-06','2023-03-07',199.99),
    (1, 1,'2023-03-17','2023-03-20',524.97),
    (3, 0,'2023-03-18','2023-03-23',924.95),
    (2, 2,'2023-03-29','2023-03-31',349.98),
    (2,0,'2023-03-31','2023-04-05',874.95),
	(1,0,'2023-04-09','2023-04-13',799.96),
    (1,1,'2023-04-23','2023-04-24',174.99),
    (2,4,'2023-05-30','2023-06-02',1199.97),
    (2,0,'2023-06-10','2023-06-14',599.96),
    (1,0,'2023-06-10','2023-06-14',599.96),
    (3,0,'2023-06-17','2023-06-18',184.99),
    (2,0,'2023-06-28','2023-07-02',699.96),
    (3,1,'2023-07-13','2023-07-14',184.99),
    (4,2,'2023-07-18','2023-07-21',1259.97),
    (2,1,'2023-07-28','2023-07-29',199.99),
    (1,0,'2023-08-30','2023-09-01',349.98),
    (2,0,'2023-09-16','2023-09-17',149.99),
    (2,2,'2023-09-13','2023-09-15',399.98),
    (2,2,'2023-11-22','2023-11-25',1199.97),
    (2,0,'2023-11-22','2023-11-25',449.97),
    (2,2,'2023-11-22','2023-11-25',599.97),
    (2,0,'2023-12-24','2023-12-28',699.96);
    
    -- Amenities
	INSERT INTO Amenity
    VALUES
    ('Microwave'),
    ('Refrigerator'),
    ('Jacuzzi'),
    ('Oven');
    -- Room-Amenity Bridge Table
    INSERT INTO RoomAmenity(RoomID,AmenID)
    VALUES
    (201,'Microwave'),
    (201,'Jacuzzi'),
    (202,'Refrigerator'),
    (203,'Microwave'),
    (203,'Jacuzzi'),
    (204,'Microwave'),
    (205,'Microwave'),
	(205,'Refrigerator'),
    (205,'Jacuzzi'),
    (206,'Microwave'),
    (206,'Refrigerator'),
	(207,'Microwave'),
    (207,'Refrigerator'),
    (207,'Jacuzzi'),
    (208,'Microwave'),
    (208,'Refrigerator'),
    (301,'Microwave'),
    (301,'Jacuzzi'),
    (302,'Refrigerator'),
    (303,'Microwave'),
    (303,'Jacuzzi'),
    (304,'Refrigerator'),
    (305,'Microwave'),
    (305,'Refrigerator'),
    (305,'Jacuzzi'),
    (306,'Microwave'),
    (306,'Refrigerator'),
    (307,'Microwave'),
    (307,'Refrigerator'),
    (307,'Jacuzzi'),
    (308,'Microwave'),
    (308,'Refrigerator'),
    (401,'Microwave'),
    (401,'Refrigerator'),
    (401,'Oven'),
    (402,'Microwave'),
    (402,'Refrigerator'),
    (402,'Oven');
    
    -- Room-Reservation Bridge Table
    INSERT INTO RoomReservation(RoomID,ReservationID)
    VALUES
    (308,1),
    (203,2),
    (305,3),
    (201,4),
    (307,5),
    (302,6),
    (202,7),
    (304,8),
    (301,9),
    (207,10),
    (401,11),
    (206,12),
    (208,13),
    (304,14),
    (205,15),
    (204,16),
    (401,17),
    (303,18),
    (305,19),
    (208,20),
    (203,21),
    (401,22),
    (206,23),
    (301,24),
    (302,25);
    
	-- Guest_reservation Bridge Table
    INSERT INTO GuestReservation(GuestID,ReservationID)
    VALUES
	('Mack Simmer', 1),
	('Bettyann Seery', 2),
	('Duane Cullison', 3),
	('Karie Yang', 4),
	('Carlos Beltran', 5),
    ('Aurore Lipton', 6),
    ('Zachery Luechtefeld', 7),
    ('Jeremiah Pendergrass',8),
    ('Walter Holaway',9),
    ('Wilfred Vise',10),
    ('Maritza Tilton',11),
    ('Joleen Tison',12),
	('Joleen Tison',13),
    ('Aurore Lipton', 14),
	('Carlos Beltran', 15),
    ('Walter Holaway',16),
    ('Wilfred Vise',17),
    ('Bettyann Seery',18),
    ('Bettyann Seery',19),
    ('Mack Simmer',20),
    ('Karie Yang', 21),
    ('Duane Cullison',22),
    ('Mack Simmer',23),
    ('Mack Simmer',24),
    ('Maritza Tilton',25);

-- Altering the table for Room
-- alter table Room
-- add constraint fk_AmenitiesID foreign key (AmenitiesID) references Amenities
-- (AmenitiesID) on delete no action;

-- Altering the table for Amenities
-- alter table Amenities
-- add constraint fk_RoomID foreign key (RoomID) references Room
-- (RoomID) on delete no action;

-- Altering the table to the Reservation List
-- alter table ReservationList
-- add constraint fk_ReservationID foreign key (ReservationID) references Reservations
-- (ReservationID) on delete no action;

