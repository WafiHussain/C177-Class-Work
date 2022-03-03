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
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Superpower Dao DB Test Class
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SuperpowerDaoDBTest {
    
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
    
    public SuperpowerDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass(){
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
            
        List<Sighting> sightings = siDao.getAllOfTheSightings();
        for(Sighting sighting : sightings) {
            siDao.deleteSightByID(sighting.getID());
        }
        
        List<Superpower> sPowers = sPowerDao.getAllOfTheSuperpowers();
        for(Superpower sPower : sPowers) {
            sPowerDao.deleteSuperpowerByTheID(sPower.getID());
        } 
    }
        
     @AfterEach
     public void tearDown() {
     }
     
     // Test getSuperpowerByID method
     @Test
     public void testGetSuperpowerByID() {
     }
     
     @Test
     public void testGetAndAddSuperpower() {
     
       Superpower sPower = new Superpower();
       sPower.setName("Test name");
       sPower.setDescription("Test description");
       sPower = sPowerDao.addSuperpower(sPower);
       
       Superpower sPower2 = new Superpower();
       sPower2.setName("Test name");
       sPower2.setDescription("Test description");
       sPower2 = sPowerDao.addSuperpower(sPower2);
       
       List<Superpower> sPowers = sPowerDao.getAllOfTheSuperpowers();
       assertEquals(2, sPowers.size());
       assertTrue(sPowers.contains(sPower));
       assertTrue(sPowers.contains(sPower2));
        
     }
     
     // Test addSuperpower Method
     @Test
     public void testAddSuperpower(){
     }
     
     // Test updateSuperpower method
     public void testUpdateSuperpower() {
         
       Superpower sPower = new Superpower();
       sPower.setName("Test name");
       sPower.setDescription("Test description");
       sPower = sPowerDao.addSuperpower(sPower);
       
       Superpower fromDao = sPowerDao.getTheSuperpowerByID(sPower.getID());
       assertEquals(sPower, fromDao);
       
       sPower.setName("Test name2");
       sPower.setDescription("Test description2");
       
       sPowerDao.updateSuperpower(sPower);
       assertNotEquals(sPower, fromDao);
       
       fromDao = sPowerDao.getTheSuperpowerByID(sPower.getID());
       assertEquals(sPower, fromDao);
       
     }
     
     // Test deleteSuperpowerByID method
     @Test
     public void testDeleteSuperpowerByID() {
         
       Superpower sPower = new Superpower();
       sPower.setName("Test name");
       sPower.setDescription("Test description");
       sPower = sPowerDao.addSuperpower(sPower);
       
       Superpower fromDao = sPowerDao.getTheSuperpowerByID(sPower.getID());
       assertEquals(sPower, fromDao);
       
       sPowerDao.deleteSuperpowerByTheID(sPower.getID());
       
       fromDao = sPowerDao.getTheSuperpowerByID(sPower.getID());
       assertNull(fromDao);
         
     }
        
   }
    

