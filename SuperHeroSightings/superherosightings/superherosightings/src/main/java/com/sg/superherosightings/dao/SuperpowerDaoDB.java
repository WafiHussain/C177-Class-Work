/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// Superpower Dao DB Class
@Repository 
public class SuperpowerDaoDB implements SuperpowerDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    public Superpower getSuperpowerByID(int ID){
        try {
            final String SELECT_SUPERPOWER_BY_THE_ID = "SELECT * FROM Superpower WHERE SuperpowerID = ?";
            return jdbc.queryForObject(SELECT_SUPERPOWER_BY_THE_ID, new SuperpowerMapper(), ID);
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    public List<Superpower> getAllSuperpowers() {
        final String SELECT_ALL_SUPERPOWERS = "SELECT * FROM Superpower";
        return jdbc.query(SELECT_ALL_SUPERPOWERS, new SuperpowerMapper());
    }
    
    @Transactional
    public Superpower addSuperpowers(Superpower sPower) {
        final String INSERT_SUPERPOWER = "INSERT INTO Superpower(Name, Description) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_SUPERPOWER,
                sPower.getName(),
                sPower.getDescription());
        
        int newID = jdbc.queryForObject("SELECT LAST_INSET_ID()", Integer.class);
        sPower.setID(newID);
        return sPower;
    }

    @Override
    public Superpower getTheSuperpowerByID(int ID) {
        return null;
    }

    @Override
    public List<Superpower> getAllOfTheSuperpowers() {
        return null;
    }

    @Override
    public Superpower addSuperpower(Superpower superpower) {
        return null;
    }

    @Override
    public void updateSuperpower(Superpower sPower) {
        final String UPDATE_SUPERPOWER = "UPDATE Superpower SET Name = ?, Description = ?"
                + "WHERE SuperpowerID = ?";
        jdbc.update(UPDATE_SUPERPOWER,
                sPower.getName(),
                sPower.getDescription(),
                sPower.getID());
    }

    @Override
    public void deleteSuperpowerByTheID(int ID) {

    }


    @Transactional
    public void deleteSuperpowerByID(int ID) {
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM HeroSuperpower WHERE SuperpowerID = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, ID);
        
        final String DELETE_SUPERPOWER = "DELETE FROM Superpower WHERE SuperpowerID = ?";
        jdbc.update(DELETE_SUPERPOWER, ID);
    }
    
    public static final class SuperpowerMapper implements RowMapper<Superpower> {
        
        @Override
        public Superpower mapRow(ResultSet res, int index) throws SQLException {
            Superpower sPower = new Superpower();
            sPower.setID(res.getInt("SuperpowerID"));
            sPower.setName(res.getString("name"));
            sPower.setDescription(res.getString("description"));
            return sPower;
        }
    }
}
