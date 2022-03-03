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

// Organization Dao DB Test Class
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrganizationDaoDBTest {
    
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
    
    public OrganizationDaoDBTest(){
    }
    
    @BeforeAll
    public static void setUpClass(){
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
    
    // Testing getOrganizationByID method
    @Test
    public void testGetOrganizationByID() {
    }
    
    @Test
    public void testGetAndAddOrganization() {
        
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
       
       hero.setSightings(sightings);
       
       hDao.updateHero(hero);
       
       List<Hero> heroes = new ArrayList<>();
       heroes.add(hero);
       
       Organization org = new Organization();
       org.setName("Test name");
       org.setIsHero(true);
       org.setDescription("Test description");
       org.setAddress("Test address");
       org.setContact("Test contact");
       org.setMembers(heroes);
       org = orgDao.addingOrganization(org);
       
       Organization fromDao = orgDao.getOrganizationByTheID(org.getID());
       assertEquals(org, fromDao);
    }
    
    // Testing getAllOrganizations Method
   @Test
   public void testGetAllOrganizations() {
       
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
       
       hero.setSightings(sightings);
       
       hDao.updateHero(hero);
       
       List<Hero> heroes = new ArrayList<>();
       heroes.add(hero);
       
       Organization org = new Organization();
       org.setName("Test name");
       org.setIsHero(true);
       org.setDescription("Test description");
       org.setAddress("Test address");
       org.setContact("Test contact");
       org.setMembers(heroes);
       org = orgDao.addingOrganization(org);
       
       // Organization 2
       
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
       
       Date date2 = Date.valueOf("2022-15-02");
       
       Sighting sighting2 = new Sighting();
       sighting2.setHeroID(hero.getID());
       sighting2.setLocation(location2);
       sighting2.setDate(date2);
       sighting2 = siDao.addTheSighting(sighting2);
       
       hero.setSightings(sightings);
       
       hDao.updateHero(hero);
       
       List<Hero> heroes2 = new ArrayList<>();
       heroes2.add(hero2);
       
       Organization org2 = new Organization();
       org2.setName("Test name");
       org2.setIsHero(true);
       org2.setDescription("Test description");
       org2.setAddress("Test address");
       org2.setContact("Test contact");
       org2.setMembers(heroes);
       org2 = orgDao.addingOrganization(org2);
       
       List<Organization> orgs = orgDao.getAllOfTheOrganization();
       assertEquals(2, orgs.size());
       assertTrue(orgs.contains(org));
       assertTrue(orgs.contains(org2));
       
   }
   
   // Test addOrganization Method
   @Test
   public void testAddOrganization() {
   }
   
   // Test updateOrganization method
   @Test
   public void testUpdateOrganization() {
       
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
       
       hero.setSightings(sightings);
       
       hDao.updateHero(hero);
       
       List<Hero> heroes = new ArrayList<>();
       heroes.add(hero);
       
       Organization org = new Organization();
       org.setName("Test name");
       org.setIsHero(true);
       org.setDescription("Test description");
       org.setAddress("Test address");
       org.setContact("Test contact");
       org.setMembers(heroes);
       org = orgDao.addingOrganization(org);
       
       Organization fromDao = orgDao.getOrganizationByTheID(org.getID());
       assertEquals(org, fromDao);
       
       org.setName("Test name2");
       org.setIsHero(true);
       org.setDescription("Test description2");
       org.setAddress("Test address2");
       org.setContact("Test contact2");
       
       orgDao.updateOrganization(org);
       assertNotEquals(org, fromDao);
       
       fromDao = orgDao.getOrganizationByTheID((org.getID()));
       assertEquals(org, fromDao);
   }
   
   // Testing deleteOrganizationByID method
   @Test
   public void testDeleteOrganizationByID() {
       
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
       
       hero.setSightings(sightings);
       
       hDao.updateHero(hero);
       
       List<Hero> heroes = new ArrayList<>();
       heroes.add(hero);
       
       Organization org = new Organization();
       org.setName("Test name");
       org.setIsHero(true);
       org.setDescription("Test description");
       org.setAddress("Test address");
       org.setContact("Test contact");
       org.setMembers(heroes);
       org = orgDao.addingOrganization(org);
       
       Organization fromDao = orgDao.getOrganizationByTheID(org.getID());
       assertEquals(org, fromDao);
       
       orgDao.deleteOrganizationByID(org.getID());
       
       fromDao = orgDao.getOrganizationByTheID(org.getID());
       assertNull(fromDao);
   }

    // Test getOrganizationsForHero method
   @Test
   public void testGetOrgananizationsForHero() {
       
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
       
       hero.setSightings(sightings);
       
       hDao.updateHero(hero);
       
       List<Hero> heroes = new ArrayList<>();
       heroes.add(hero);
       
       Organization org = new Organization();
       org.setName("Test name");
       org.setIsHero(true);
       org.setDescription("Test description");
       org.setAddress("Test address");
       org.setContact("Test contact");
       org.setMembers(heroes);
       org = orgDao.addingOrganization(org);
       
       // Organization 2
       
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
       
       hero2.setSightings(sightings2);
       
       hDao.updateHero(hero2);
       
       List<Hero> heroes2 = new ArrayList<>();
       heroes2.add(hero2);
       
       Organization org2 = new Organization();
       org2.setName("Test name2");
       org2.setIsHero(true);
       org2.setDescription("Test description2");
       org2.setAddress("Test address2");
       org2.setContact("Test contact2");
       org2.setMembers(heroes2);
       org2 = orgDao.addingOrganization(org2);
       
       List<Organization> fromDao = orgDao.getOrganizationForHero(hero);
       assertTrue(fromDao.contains(org));
       assertFalse(fromDao.contains(org2));
   }

    

   
}
