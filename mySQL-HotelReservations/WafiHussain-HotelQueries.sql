-- WafiHussain-HotelQueries.sql

-- Query 1.) Returning a list of reservations that end in July 2023
-- including the name of the guest, the room number(s), and the reservation dates.
select Room, Name, startDate, EndDate
from Reservations
where (EndDate <= date'2023-07-31' and EndDate >= date'2023-07-01');

-- Query 2.) Returning  a list of all reservations 
-- for rooms with a jacuzzi, displaying the guest's name,
-- the room number, and the dates of the reservation.
select Name, Room, startDate, endDate
from Reservations
where (Room % 2 = 1) and (Room < '400');

-- Query 3.) Returning all the rooms reserved for a 
-- specific guest, including the guest's name,
-- the room(s) reserved, the starting date of the reservation, 
-- and how many people were included in the reservation.
select Room, Name, startDate, (Adults + Children) as totalGuests
from Reservations
where (Name = 'Mack Simmer');  -- Mark Simmer is a name example

-- Query 4.) Returning a list of rooms, reservation ID, and 
-- per-room cost for each reservation. 
-- The results should include all rooms, whether or not there 
-- is a reservation associated with the room.
select Room, ReservationID, TotalRoomCost as costPerRoom
from reservations
order by room;

-- Query 5.) Returning all the rooms accommodating at 
-- least three guests and that are reserved on any date 
-- in April 2023.
select Room
from Reservations
where ((Adults+Children) <= 3 and (Adults+Children) >= 1)
and (startDate <= date'2023-04-30' and startDate >= date'2023-04-01');

-- Query 6.) Returning a list of all guest names and 
-- the number of reservations per guest, sorted starting 
-- with the guest with the most reservations and then by the guest's last name.
select Name, count(Name) as numOfGuests
from Reservations
group by Name
order by startDate asc;

-- Query 7.) Displaying the name, address, and phone number of a guest based on 
-- their phone number. (Choose a phone number from the existing data.)
select Name, Address, Phone
from guests
where (Phone = '(214) 730-0298');   


