/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.SightingDaoDB.SightingMapper;
import com.sg.superherosightings.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;
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

// Hero Dao DB Class
@Repository
public class HeroDaoDB implements HeroDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    SightingDaoDB sightingDaoDB;
    
    @Override
    public Hero getHeroByID(int ID){
        try {
            final String SELECT_HERO_BY_ID = "SELECT * FROM Hero WHERE HeroID = ?";
            Hero hero = jdbc.queryForObject(SELECT_HERO_BY_ID, new HeroMapper(), ID);
            hero.setSuperpowers(getSuperpowersForHero(ID));
            hero.setSightings(getSightingsForHero(ID));
            return hero;
        } catch (DataAccessException ex){
            return null;
        }
    }
    
    private List<Superpower> getSuperpowersForHero(int ID) {
        final String SELECT_SUPERPOWERS_FOR_HERO = "SELECT s.* FROM Superpower s "
                + "JOIN HeroSuperpower hs ON hs.SuperpowerID = s.SuperpowerID WHERE hs.HeroID = ?";
        return jdbc.query(SELECT_SUPERPOWERS_FOR_HERO, new SuperpowerMapper(), ID);
    }
    
    private List<Sighting> getSightingsForHero(int ID) {
        final String SELECT_SIGHTINGS_FOR_HERO = "SELECT * FROM Sighting WHERE HeroID = ?";
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_FOR_HERO, new SightingMapper(), ID);
        sightingDaoDB.associateTheLocationsForSightings(sightings);
        return sightings;
    }
        
    @Override
    public List<Hero> getAllHeroes() {
        final String SELECT_ALL_HEROES = "SELECT * FROM super";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HEROES, new HeroMapper());
        return heroes;
    }
    
    public void associateSuperpowersAndSightings(List<Hero> heroes) {
        for(Hero hero : heroes){
            hero.setSuperpowers(getSuperpowersForHero(hero.getID()));
            hero.setSightings(getSightingsForHero(hero.getID()));
        }
    }
    
    @Transactional
    @Override
    public Hero addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO Hero(IsHero, Name, Description) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_HERO, hero.isIsHero(), hero.getName(), hero.getDescription());
        
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setID(newID);
        insertHeroSuperpower(hero);
        insertSighting(hero);
        return hero;
    }
    
    private void insertHeroSuperpower(Hero hero) {
        final String INSERT_HERO_SUPERPOWER = "INSERT INTO "
                + "HeroSuperpower(HeroID, SuperpowerID) VALUES(?,?)";
        for(Superpower superpower : hero.getSuperpowers()) {
            jdbc.update(INSERT_HERO_SUPERPOWER,
                    hero.getID(),
                    superpower.getID());
        }
    }
    
    private void insertSighting(Hero hero) {
        final String INSERT_SIGHTING = "INSERT INTO "
                + "Sighting(HeroID, LocationID, Date) VALUES(?,?,?)";
        for(Sighting sighting : hero.getSightings()) {
            jdbc.update(INSERT_SIGHTING,
                    hero.getID(),
                    sighting.getLocation().getID(),
                    sighting.getDate());
            int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            sighting.setID(newID);
        }
    }
    
    @Override
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE Hero SET IsHero = ?, Name = ?, Description = ?"
                + "WHERE HeroID = ?";
        jdbc.update(UPDATE_HERO,
                hero.isIsHero(),
                hero.getName(),
                hero.getDescription(),
                hero.getID());
        
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM HeroSuperpower WHERE HeroID = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, hero.getID());
        insertHeroSuperpower(hero);
        
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE HeroID = ?";
        jdbc.update(DELETE_SIGHTING, hero.getID());
        insertSighting(hero);
    }
    
    @Transactional
    @Override
    public void deleteHeroByID(int ID){
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM HeroSuperpower WHERE HeroID = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, ID);
        
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE HeroID = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, ID);
        
        final String DELETE_HEROSIGHTING = "DELETE FROM Sighting WHERE HeroID = ?";
        jdbc.update(DELETE_HEROSIGHTING, ID);
        
        final String DELETE_HERO = "DELETE FROM Hero WHERE HeroID = ?";
        jdbc.update(DELETE_HERO, ID);
    }

    @Override
    public List<Hero> getHeroesForSuperpower(Superpower superpower) {
        return null;
    }

    @Override
    public Hero getHeroForSighting(Sighting sighting) {
        return null;
    }

    public List<Hero> getHeroForSuperpower(Superpower superpower) {
        final String SELECT_HEROES_FOR_SUPERPOWER = "SELECT h.* FROM hero h JOIN "
                + "HeroSuperpower hs ON hs.HeroID = h.HeroID WHERE hs.SuperpowerID = ?";
        List<Hero> heroes = jdbc.query(SELECT_HEROES_FOR_SUPERPOWER,
                new HeroMapper(), superpower.getID());
        associateSuperpowersAndSightings(heroes);
        return heroes;
    }
    
    @Override
    public List<Hero> getHeroesForLocation(Location location) {
        final String SELECT_HEROES_FOR_LOCATION = "SELECT h.* FROM hero h "
                + "JOIN Sighting s ON s.HeroID = h.heroID "
                + "WHERE s.LocationID = ?";
        List<Hero> heroes = jdbc.query(SELECT_HEROES_FOR_LOCATION, 
                new HeroMapper(), location.getID());
        associateSuperpowersAndSightings(heroes);
        return heroes;
    }
    
    public static final class HeroMapper implements RowMapper<Hero> {
        
        @Override
        public Hero mapRow(ResultSet res, int index) throws SQLException {
            Hero hero = new Hero();
            hero.setID(res.getInt("HeroID"));
            hero.setIsHero(res.getBoolean("IsHero"));      
            hero.setName(res.getString("Name"));
            hero.setDescription(res.getString("Description"));
            return hero;
        }
    }
    
}
