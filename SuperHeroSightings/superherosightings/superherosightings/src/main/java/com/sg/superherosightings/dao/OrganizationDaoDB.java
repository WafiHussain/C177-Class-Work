/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.HeroDaoDB.HeroMapper;
import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


// Organization Dao DB
@Repository
public class OrganizationDaoDB implements OrganizationDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    HeroDaoDB heroDaoDB;
    
    @Override
    public Organization getOrganizationByTheID(int ID) {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM Organization WHERE OrganizationID = ?";
            Organization org = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), ID);
            org.setMembers(getHeroesForOrganization(ID));
            return org;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOfTheOrganization() {
        return null;
    }

    private List<Hero> getHeroesForOrganization(int ID) {
        final String SELECT_HEROES_FOR_ORGANIZATION = "SELECT h.* FROM Hero h "
                + "JOIN HeroOrganization ho ON h.HeroID = ho.HeroID WHERE ho.OrganizationID = ?";
        List<Hero> heroes = jdbc.query(SELECT_HEROES_FOR_ORGANIZATION, new HeroMapper(), ID);
        return heroes;
    }
    
    public List<Organization> getAllOfTheOrganizations() {
        final String SELECT_ALL_OF_THE_ORGANIZATIONS = "SELECT * FROM Organization";
        List<Organization> org = jdbc.query(SELECT_ALL_OF_THE_ORGANIZATIONS, new OrganizationMapper());
        associateHeroes(org);
        return org;
    }
    
    public void associateHeroes(List<Organization> orgs) {
        for(Organization org : orgs) {
            org.setMembers(getHeroesForOrganization(org.getID()));
        }
    }
    
    @Transactional
    @Override
    public Organization addingOrganization(Organization org) {
        final String INSERT_ORGANIZATION = "INSERT INTO Organization(Name,IsHero,Description,Address,Contact) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                org.getName(),
                org.isIsHero(),
                org.getDescription(),
                org.getAddress(),
                org.getContact());
        
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        org.setID(newID);
        insertTheHeroOrganization(org);
        return org;
    }
    
    private void insertTheHeroOrganization(Organization org) {
        final String INSERT_HERO_ORGANIZATION = "INSERT INTO "
                + "HeroOrganization(HeroID, OrganizationID) VALUES(?,?)";
        for(Hero hero : org.getMembers()) {
            jdbc.update(INSERT_HERO_ORGANIZATION,
                    hero.getID(),
                    org.getID());
        }
    }
    
    @Override
    public void updateOrganization(Organization org) {
        final String UPDATE_ORGANIZATION = "UPDATE Organization SET Name = ?, IsHero = ?, Description = ?, Address = ?, Contact = ?"
                + "WHERE OrganizationID = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                org.getName(),
                org.isIsHero(),
                org.getDescription(),
                org.getAddress(),
                org.getContact(),
                org.getID());
        
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE OrganizationID = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, org.getID());
        insertTheHeroOrganization(org);
    }

    @Override
    public void deleteOrganizationByID(int ID) {

    }

    @Override
    public List<Organization> getOrganizationForHero(Hero hero) {
        return null;
    }

    @Transactional
    public void deleteTheOrganizationByID(int ID) {
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE OrganizationID = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, ID);
        
        final String DELETE_ORGANIZATION = "DELETE FROM Organization WHERE OrganizationID = ?";
        jdbc.update(DELETE_ORGANIZATION, ID);
    }
    
    public List<Organization> getTheOrganizationsForTheHero(Hero hero) {
        final String SELECT_ORGANIZATIONS_FOR_HERO = "SELECT o.* FROM Organization o JOIN "
                + "HeroOrganization ho ON ho.OrganizationID = o.OrganizationID WHERE ho.HeroID = ?";
        List<Organization> org = jdbc.query(SELECT_ORGANIZATIONS_FOR_HERO,
                new OrganizationMapper(), hero.getID());
        associateHeroes(org);
        return org;
    }
    
    public static final class OrganizationMapper implements RowMapper<Organization> {
        
        @Override 
        public Organization mapRow(ResultSet res, int index) throws SQLException {
            Organization org = new Organization();
            org.setID(res.getInt("OrganizationID"));
            org.setName(res.getString("name"));
            org.setIsHero(res.getBoolean("isHero"));
            org.setDescription(res.getString("description"));
            org.setAddress(res.getString("address"));
            org.setContact(res.getString("contact"));
            return org;
        }
    }
}
