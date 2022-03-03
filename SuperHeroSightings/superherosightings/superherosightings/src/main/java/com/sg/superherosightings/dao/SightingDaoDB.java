/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// Sighting Dao DB Class
@Repository
public class SightingDaoDB implements SightingDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Sighting getSightingByID(int ID) {
        try{
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM Sighting WHERE SightingID = ?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), ID);
            sighting.setLocation(getLocationForTheSighting(ID));
            return sighting;
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    private Location getLocationForTheSighting(int ID) {
        final String SELECT_LOCATION_FOR_THE_SIGHTING = "SELECT 1.* FROM Location 1 "
                + "JOIN Sighting s ON s.LocationID = 1.LocationID WHERE s.SightingID = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_THE_SIGHTING, new LocationDaoDB.LocationMapper(), ID);
    }
    
    @Override
    public List<Sighting> getAllOfTheSightings() {
        final String SELECT_ALL_OF_THE_SIGHTINGS = "SELECT * FROM Sighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_OF_THE_SIGHTINGS, new SightingMapper());
        associateTheLocationsForSightings(sightings);
        return sightings;
    }
    
    void associateTheLocationsForSightings(List<Sighting> sightings){
        for(Sighting sighting : sightings) {
            sighting.setLocation(getLocationForTheSighting(sighting.getID()));
        }
    }
    
    @Transactional
    @Override
    public Sighting addTheSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO Sighting(HeroID, LocationID, Date "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getHeroID(),
                sighting.getLocation().getID(),
                sighting.getDate());
        
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setID(newID);
        return sighting;
    }
    
    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE Sighting SET HeroID = ?, LocationID = ?, Date = ?"
                + "WHERE SightingID = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getHeroID(),
                sighting.getLocation().getID(),
                sighting.getDate(),
                sighting.getID());
    }

    @Override
    public void deleteSightByID(int ID) {

    }

    @Override
    public List<Sighting> getSightingsForTheLocation(Location location) {
        return null;
    }

    @Transactional
    public void deleteSightingByID(int ID) {
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE SightingID = ?";
        jdbc.update(DELETE_SIGHTING, ID);
    }
    
    public List<Sighting> getSightingsForLocation(Location location) {
        final String SELECT_SIGHTINGS_FOR_LOCATION = "SELECT * FROM Sighting WHERE LocationID = ?";
        List<Sighting> sighting = jdbc.query(SELECT_SIGHTINGS_FOR_LOCATION,
                new SightingMapper(), location.getID());
        associateTheLocationsForSightings(sighting);
        return sighting;
    }
    
    public static final class SightingMapper implements RowMapper<Sighting> {
        
        @Override
        public Sighting mapRow(ResultSet res, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setID(res.getInt("SightingID"));
            sighting.setHeroID(res.getInt("HeroID"));
            sighting.setDate(Date.valueOf(res.getDate("Date").toString()));
            return sighting;
        }
    }
}
