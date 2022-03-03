/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Organization;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.models.Superpower;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Sighthing Dao DB Test Class
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SightingDaoDBTest {
    
    @Autowired
    HeroDao hDao;
    
    @Autowired
    LocationDao lDao;
    
    @Autowired
    OrganizationDao orgDao;
    
    @Autowired
    SightingDao siDao;
    
    @Autowired
    SuperpowerDao sPowerDao;
    
    public SightingDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Hero> heroes = hDao.getAllHeroes();
        for(Hero hero : heroes) {
            hDao.deleteHeroByID(hero.getID());
        }
        
        List<Location> locations = lDao.getAllTheLocations();
        for(Location location : locations) {
            lDao.deleteLocationByID(location.getID());
        }
        
        List<Organization> orgs = orgDao.getAllOfTheOrganization();
        for(Organization org : orgs) {
            orgDao.deleteOrganizationByID(org.getID());
        }
        
        List<Superpower> sPowers = sPowerDao.getAllOfTheSuperpowers();
        for(Superpower sPower : sPowers) {
            sPowerDao.deleteSuperpowerByTheID(sPower.getID());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    // Test getSightingByID
    @Test
    public void testgetSightingByID() {
    }
    
    @Test
    public void testGetAndAddSighting() {
        
       Superpower sPower = new Superpower();
       sPower.setName("Test name");
       sPower.setDescription("Test description");
       sPower = sPowerDao.addSuperpower(sPower);
        
       List<Superpower> sPowers = new ArrayList<>();
       sPowers.add(sPower);
        
       List<Sighting> sightings = new ArrayList<>();
       
       Hero hero = new Hero();
       hero.setIsHero(true);
       hero.setName("Test name");
       hero.setDescription("Test description");
       hero.setSuperpowers(sPowers);
       hero.setSightings(sightings);
       hero = hDao.addHero(hero);
       
       Location location = new Location();
       location.setName("Test name");
       location.setLatitude(12.3);
       location.setLongitude(-5.36);
       location.setDescription("Test description");
       location.setAddressInformation("Test address info");
       location = lDao.addLocation(location);
       
       Date date = Date.valueOf("2022-14-02");
       
       Sighting sighting = new Sighting();
       sighting.setHeroID(hero.getID());
       sighting.setLocation(location);
       sighting.setDate(date);
       sighting = siDao.addTheSighting(sighting);
       
       Sighting fromDao = siDao.getSightingByID(sighting.getID());
       
       assertEquals(sighting, fromDao);
    }
    
    // Test of deleteSightingByID method
    @Test
    public void testDeleteSightingByID() {
        
       Superpower sPower = new Superpower();
       sPower.setName("Test name");
       sPower.setDescription("Test description");
       sPower = sPowerDao.addSuperpower(sPower);
        
       List<Superpower> sPowers = new ArrayList<>();
       sPowers.add(sPower);
        
       List<Sighting> sightings = new ArrayList<>();
       
       Hero hero = new Hero();
       hero.setIsHero(true);
       hero.setName("Test name");
       hero.setDescription("Test description");
       hero.setSuperpowers(sPowers);
       hero.setSightings(sightings);
       hero = hDao.addHero(hero);
       
       Location location = new Location();
       location.setName("Test name");
       location.setLatitude(12.3);
       location.setLongitude(-5.36);
       location.setDescription("Test description");
       location.setAddressInformation("Test address info");
       location = lDao.addLocation(location);
       
       Date date = Date.valueOf("2022-14-02");
       
       Sighting sighting = new Sighting();
       sighting.setHeroID(hero.getID());
       sighting.setLocation(location);
       sighting.setDate(date);
       sighting = siDao.addTheSighting(sighting);
       
       Sighting fromDao = siDao.getSightingByID(sighting.getID());
       assertEquals(sighting, fromDao);
       
       siDao.deleteSightByID(sighting.getID());
       
       fromDao = siDao.getSightingByID(sighting.getID());
       assertNull(fromDao);
    }
    
    // Test getSightingsForLocation Method
    @Test
    public void testGetSightingsForLocation() {
        
       Superpower sPower = new Superpower();
       sPower.setName("Test name");
       sPower.setDescription("Test description");
       sPower = sPowerDao.addSuperpower(sPower);
        
       List<Superpower> sPowers = new ArrayList<>();
       sPowers.add(sPower);
        
       List<Sighting> sightings = new ArrayList<>();
       
       Hero hero = new Hero();
       hero.setIsHero(true);
       hero.setName("Test name");
       hero.setDescription("Test description");
       hero.setSuperpowers(sPowers);
       hero.setSightings(sightings);
       hero = hDao.addHero(hero);
       
       Location location = new Location();
       location.setName("Test name");
       location.setLatitude(12.3);
       location.setLongitude(-5.36);
       location.setDescription("Test description");
       location.setAddressInformation("Test address info");
       location = lDao.addLocation(location);
       
       Date date = Date.valueOf("2022-14-02");
       
       Sighting sighting = new Sighting();
       sighting.setHeroID(hero.getID());
       sighting.setLocation(location);
       sighting.setDate(date);
       sighting = siDao.addTheSighting(sighting);
       
       // Sighting 2
       
       Superpower sPower2 = new Superpower();
       sPower2.setName("Test name");
       sPower2.setDescription("Test description");
       sPower2 = sPowerDao.addSuperpower(sPower2);
        
       List<Superpower> sPowers2 = new ArrayList<>();
       sPowers2.add(sPower2);
        
       List<Sighting> sightings2 = new ArrayList<>();
       
       Hero hero2 = new Hero();
       hero2.setIsHero(true);
       hero2.setName("Test name2");
       hero2.setDescription("Test description2");
       hero2.setSuperpowers(sPowers2);
       hero2.setSightings(sightings2);
       hero2 = hDao.addHero(hero2);
       
       Location location2 = new Location();
       location2.setName("Test name2");
       location2.setLatitude(12.3);
       location2.setLongitude(-5.36);
       location2.setDescription("Test description2");
       location2.setAddressInformation("Test address info2");
       location2 = lDao.addLocation(location2);
       
       Date date2 = Date.valueOf("2022-14-02");
       
       Sighting sighting2 = new Sighting();
       sighting2.setHeroID(hero2.getID());
       sighting2.setLocation(location2);
       sighting2.setDate(date2);
       sighting2 = siDao.addTheSighting(sighting2);
       
       List<Sighting> sisDao = siDao.getSightingsForTheLocation(location);
       assertTrue(sisDao.contains(sighting));
       assertFalse(sisDao.contains(sighting2));
    }
    
}
