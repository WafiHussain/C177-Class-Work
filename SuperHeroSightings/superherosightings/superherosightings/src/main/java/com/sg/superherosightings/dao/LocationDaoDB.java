/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// Location Dao Class
@Repository 
public class LocationDaoDB implements LocationDao {
    
        @Autowired
        JdbcTemplate jdbc;
        
        @Override
        public Location getLocationByID(int ID) {
            try {
                final String SELECT_LOCATION_BY_ID = "SELECT * FROM Location WHERE LocationID = ?";
                return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), ID);
            } catch (DataAccessException ex){
                return null;
            }
        }

    @Override
    public List<Location> getAllTheLocations() {
        return null;
    }

    public List<Location> getAllLocations() {
            final String SELECT_ALL_LOCATIONS = "SELECT * FROM Location";
            return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
        }
        
        @Transactional
        @Override
        public Location addLocation(Location location) {
            final String INSERT_LOCATION = "INSERT INTO Location(Name, Latitude, Longitude, Description, AddressInformation) "
                    + "VALUES(?, ?, ?, ?, ?)";
            jdbc.update(INSERT_LOCATION,
                    location.getName(),
                    location.getLatitude(),
                    location.getLongitude(),
                    location.getDescription(),
                    location.getAddressInformation());
            return location;
        }
        
        @Override
        public void updateLocation(Location location) {
            final String UPDATE_LOCATION = "UPDATE Location SET Name = ?, Latitude = ?, Longitude = ?, Description = ?, AddressInformation = ?"
                    + "WHERE LocationID = ?";
            jdbc.update(UPDATE_LOCATION,
                    location.getName(),
                    location.getLatitude(),
                    location.getLongitude(),
                    location.getDescription(),
                    location.getAddressInformation(),
                    location.getID());         
        }
        
        @Transactional
        @Override
        public void deleteLocationByID(int ID) {
            final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE LocationID = ?";
            jdbc.update(DELETE_SIGHTING, ID);
            
            final String DELETE_LOCATION = "DELETE FROM Location WHERE LocationID = ?";
            jdbc.update(DELETE_LOCATION, ID);
        }

    @Override
    public List<Location> getLocationForHero(Hero hero) {
        return null;
    }

    public List<Location> getLocationsForHero(Hero hero) {
            final String SELECT_LOCATIONS_FOR_HERO = "SELECT 1.* FROM Location 1 "
                    + "JOIN Sighting s ON s.LocationID = 1.LocationID "
                    + "WHERE s.HeroID = ?";
            List<Location> locations = jdbc.query(SELECT_LOCATIONS_FOR_HERO,
                    new LocationMapper(), hero.getID());
            return locations;
        }
        
        public static final class LocationMapper implements RowMapper<Location> {
            
            @Override
            public Location mapRow(ResultSet res, int index) throws SQLException {

            Location location = new Location();
            location.setID(res.getInt("LocationID"));
            location.setName(res.getString("name"));
            location.setLatitude(res.getDouble("latitude"));
            location.setLongitude(res.getDouble("longitude"));
            location.setDescription(res.getString("description"));
            location.setAddressInformation(res.getString("addressInformation"));
            return location;
        }
    }  
}
