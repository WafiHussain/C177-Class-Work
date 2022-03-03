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


// Hero Dao DB Test Class
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HeroDaoDBTest {
    
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
    
    public HeroDaoDBTest() {
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
        for(Hero hero :  heroes) {
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
    
    @Test
    public void testGetHeroByID() {
    }
    
    @Test 
    public void testGetAndAddHero() {
        
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
        location.setAddressInformation("Test address information");
        location = lDao.addLocation(location);
        
        Date date = Date.valueOf("2022-02-14");
        
        Sighting sighting = new Sighting();
        sighting.setHeroID(hero.getID());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = siDao.addTheSighting(sighting);
        
        sightings.add(sighting);
        
        hero.setSightings(sightings);
        
        hDao.updateHero(hero);
        
        Hero fromDao = hDao.getHeroByID(hero.getID());
        
        assertEquals(hero, fromDao);
    }
    
    // Testing to getALlOfTheHeroes
    @Test
    public void testGetAllOfTheHeroes() {
        
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
        location.setLongitude((-5.36));
        location.setDescription("Test description");
        location.setAddressInformation("Test address info");
        location = lDao.addLocation(location);
        
        Date date = Date.valueOf("2022-02-14");
        
        Sighting sighting = new Sighting();
        sighting.setHeroID(hero.getID());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = siDao.addTheSighting(sighting);
        
        sightings.add(sighting);
        
        hero.setSightings(sightings);
        
        hDao.updateHero(hero);
        
        // Hero 2
        
        Superpower sPower2 = new Superpower();
        sPower2.setName("Test name2");
        sPower2.setDescription("Test description2");
        sPower2 = sPowerDao.addSuperpower(sPower2);
        
        List<Superpower> sPowers2 = new ArrayList<>();
        sPowers2.add(sPower2);
        
        List<Sighting> sightings2 = new ArrayList<>();
        
        Hero hero2 = new Hero();
        hero2.setIsHero(false);
        hero2.setName("Test name2");
        hero2.setDescription("Test description2");
        hero2.setSuperpowers(sPowers2);
        hero2.setSightings(sightings2);
        hero2 = hDao.addHero(hero2);
        
        Location location2 = new Location();
        location2.setName("Test name2");
        location2.setLatitude(1.3);
        location2.setLongitude(5.36);
        location2.setDescription("Test description2");
        location2.setAddressInformation("Test address info2");
        location2 = lDao.addLocation(location2);
        
        Date date2 = Date.valueOf("2022-02-14");
        
        Sighting sighting2 = new Sighting();
        sighting2.setHeroID(hero2.getID());
        sighting2.setLocation(location2);
        sighting2.setDate(date2);
        sighting2 = siDao.addTheSighting(sighting2);
        
        sightings2.add(sighting2);
        
        hero2.setSightings(sightings2);
        
        hDao.updateHero(hero2);
        
        List<Hero> heroes = hDao.getAllHeroes();
        assertEquals(2, heroes.size());
        assertTrue(heroes.contains(hero));
        assertTrue(heroes.contains(hero2));
    }
    
    // Testing addHero method
    @Test
    public void testAddHero() {
    }
    
    // Testing updateHero method
    @Test
    public void testUpdateHero() {
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
        
        Date date = Date.valueOf("2022-02-14");
        
        Sighting sighting = new Sighting();
        sighting.setHeroID(hero.getID());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = siDao.addTheSighting(sighting);
        
        sightings.add(sighting);
        
        hero.setSightings(sightings);
        
        hDao.updateHero(hero);
        
        Hero fromDao = hDao.getHeroByID(hero.getID());
        
        assertEquals(hero, fromDao);
        
        Date date2 = Date.valueOf("2022-02-14");
        
        Sighting sighting2 = new Sighting();
        sighting2.setHeroID(hero.getID());
        sighting2.setLocation(location);
        sighting2.setDate(date2);
        sighting2 = siDao.addTheSighting(sighting2);
        
        sightings.add(sighting2);
        
        hero.setSightings(sightings);
        
        hDao.updateHero(hero);
        
        assertNotEquals(hero, fromDao);
        
        fromDao = hDao.getHeroByID(hero.getID());
        
        assertEquals(hero, fromDao);
    }
    
    // Testing deleteByHeroID method
    @Test
    public void testDeleteHeroByID() {
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
        
        Date date = Date.valueOf("2022-02-14");
        
        Sighting sighting = new Sighting();
        sighting.setHeroID(hero.getID());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = siDao.addTheSighting(sighting);
        
        sightings.add(sighting);
        
        hero.setSightings(sightings);
        
        hDao.updateHero(hero);
        
        Hero fromDao = hDao.getHeroByID(hero.getID());
        assertEquals(hero, fromDao);
        
        hDao.deleteHeroByID(hero.getID());
        
        fromDao = hDao.getHeroByID(hero.getID());
        assertNull(fromDao);
    }
    
    // Test getHeroesForSuperpower method
   @Test
   public void testGetHeroesForSuperpower() {
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
       
       Superpower sPower2 = new Superpower();
       sPower2.setName("Test name");
       sPower2.setDescription("Test description");
       sPower2 = sPowerDao.addSuperpower(sPower2);
       
       List<Superpower> sPowers2 = new ArrayList<>();
       sPowers.add(sPower2);
       
       List<Sighting> sightings2 = new ArrayList<>();
       
       Hero hero2 = new Hero();
       hero2.setIsHero(true);
       hero2.setName("Test name");
       hero2.setDescription("Test description");
       hero2.setSuperpowers(sPowers);
       hero2.setSightings(sightings);
       hero2 = hDao.addHero(hero2);
       
       List<Sighting> sightings3 = new ArrayList<>();
       
       Hero hero3 = new Hero();
       hero3.setIsHero(true);
       hero3.setName("Test name");
       hero3.setDescription("Test description");
       hero3.setSuperpowers(sPowers);
       hero3.setSightings(sightings);
       hero3 = hDao.addHero(hero3);
       
       List<Hero> heroes = hDao.getHeroesForSuperpower(sPower);
       assertTrue(heroes.contains(hero));
       assertFalse(heroes.contains(hero2));
       assertTrue(heroes.contains(hero3));
   }
   
   // Testing getHeroSighting method
   @Test
   public void testGetHeroForSighting() {
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
       
       Date date = Date.valueOf("2022-02-14");
       
       Sighting sighting = new Sighting();
       sighting.setHeroID(hero.getID());
       sighting.setLocation(location);
       sighting.setDate(date);
       sighting = siDao.addTheSighting(sighting);
       
       sightings.add(sighting);
        
       hero.setSightings(sightings);
       
       hDao.updateHero(hero);
       
       // Hero 2
       Superpower sPower2 = new Superpower();
       sPower2.setName("Test name");
       sPower2.setDescription("Test description");
       sPower2 = sPowerDao.addSuperpower(sPower2);
        
       List<Superpower> sPowers2 = new ArrayList<>();
       sPowers2.add(sPower2);
        
       List<Sighting> sightings2 = new ArrayList<>();
       
       Hero hero2 = new Hero();
       hero2.setIsHero(true);
       hero2.setName("Test name");
       hero2.setDescription("Test description");
       hero2.setSuperpowers(sPowers);
       hero2.setSightings(sightings);
       hero2 = hDao.addHero(hero2);
       
       Location location2 = new Location();
       location2.setName("Test name");
       location2.setLatitude(12.3);
       location2.setLongitude(-5.36);
       location2.setDescription("Test description");
       location2.setAddressInformation("Test address info");
       location2 = lDao.addLocation(location2);
       
       Date date2 = Date.valueOf("2022-02-14");
       
       Sighting sighting2 = new Sighting();
       sighting2.setHeroID(hero.getID());
       sighting2.setLocation(location);
       sighting2.setDate(date);
       sighting2 = siDao.addTheSighting(sighting2);
       
       sightings2.add(sighting2);
       
       hDao.updateHero(hero2);
       
       Hero fromDao = hDao.getHeroForSighting(sighting);
       assertEquals(hero, fromDao);
       assertNotEquals(hero2, fromDao);
   }
   
   // Test getHeroForLocation method
   public void testGetHeroForLocation() {
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
       
       Date date = Date.valueOf("2022-02-14");
       
       Sighting sighting = new Sighting();
       sighting.setHeroID(hero.getID());
       sighting.setLocation(location);
       sighting.setDate(date);
       sighting = siDao.addTheSighting(sighting);
       
       sightings.add(sighting);
        
       hero.setSightings(sightings);
       
       hDao.updateHero(hero);
       
       // Hero 2
       Superpower sPower2 = new Superpower();
       sPower2.setName("Test name");
       sPower2.setDescription("Test description");
       sPower2 = sPowerDao.addSuperpower(sPower2);
       
       List<Superpower> sPowers2 = new ArrayList<>();
       sPowers.add(sPower2);
       
       List<Sighting> sightings2 = new ArrayList<>();
       
       Hero hero2 = new Hero();
       hero2.setIsHero(true);
       hero2.setName("Test name");
       hero2.setDescription("Test description");
       hero2.setSuperpowers(sPowers);
       hero2.setSightings(sightings);
       hero2 = hDao.addHero(hero2);
       
       Location location2 = new Location();
       location2.setName("Test name");
       location2.setLatitude(12.3);
       location2.setLongitude(-5.36);
       location2.setDescription("Test description");
       location2.setAddressInformation("Test address info");
       location2 = lDao.addLocation(location2);
       
       Date date2 = Date.valueOf("2022-02-14");
       
       Sighting sighting2 = new Sighting();
       sighting2.setHeroID(hero.getID());
       sighting2.setLocation(location);
       sighting2.setDate(date);
       sighting2 = siDao.addTheSighting(sighting2);
       
       sightings2.add(sighting);
        
       hero2.setSightings(sightings);
       
       hDao.updateHero(hero2);
       
       List<Hero> heroes = hDao.getHeroesForLocation(location);
       
       assertTrue(heroes.contains(hero));
       assertFalse(heroes.contains(hero2));   
   }  
}
