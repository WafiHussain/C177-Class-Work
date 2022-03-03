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

// Location Dao DB Test Class
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LocationDaoDBTest {

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

    public LocationDaoDBTest() {
    }

    @BeforeAll
    public static void setUpTheClass() {
    }

    @AfterAll
    public static void tearDownTheClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Hero> heroes = hDao.getAllHeroes();
        for (Hero hero : heroes) {
            hDao.deleteHeroByID(hero.getID());
        }

        List<Location> locations = lDao.getAllTheLocations();
        for (Location location : locations) {
            lDao.deleteLocationByID(location.getID());
        }

        List<Organization> orgs = orgDao.getAllOfTheOrganization();
        for (Organization org : orgs) {
            orgDao.deleteOrganizationByID(org.getID());
        }

        List<Sighting> sightings = siDao.getAllOfTheSightings();
        for (Sighting sighting : sightings) {
            siDao.deleteSightByID(sighting.getID());
        }

        List<Superpower> sPowers = sPowerDao.getAllOfTheSuperpowers();
        for (Superpower sPower : sPowers) {
            sPowerDao.deleteSuperpowerByTheID(sPower.getID());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    // Test of Location by ID method
    @Test
    public void testGetLocationByID() {
    }

    @Test
    public void testGetAndAddLocation() {

        Location location = new Location();
        location.setName("Test name");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("Test description");
        location.setAddressInformation("Test address info");
        location = lDao.addLocation(location);

        Location fromDao = lDao.getLocationByID(location.getID());
        assertEquals(location, fromDao);
    }

    // Test of Getting All Locations method
    public void testGetAllOfLocations() {

        Location location = new Location();
        location.setName("Test name");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("Test description");
        location.setAddressInformation("Test address info");
        location = lDao.addLocation(location);

        Location location2 = new Location();
        location2.setName("Test name");
        location2.setLatitude(12.3);
        location2.setLongitude(-5.36);
        location2.setDescription("Test description");
        location2.setAddressInformation("Test address info");
        location2 = lDao.addLocation(location2);

        List<Location> locations = lDao.getAllTheLocations();
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));

    }

    // Test Adding Location Method
    @Test
    public void testAddingLocation() {
    }

    // Test Update Location Method
    public void testUpdateLocation() {

        Location location = new Location();
        location.setName("Test name");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("Test description");
        location.setAddressInformation("Test address info");
        location = lDao.addLocation(location);

        Location fromDao = lDao.getLocationByID(location.getID());
        assertEquals(location, fromDao);

        location.setName("Test name2");
        location.setLatitude(12.33);
        location.setLongitude(-5.365);
        location.setDescription("Test description2");

        lDao.updateLocation(location);
        assertNotEquals(location, fromDao);

        fromDao = lDao.getLocationByID(location.getID());
        assertEquals(location, fromDao);
    }

    // Test Delete Location By ID Method
    public void testDeleteLocationByID() {

        Location location = new Location();
        location.setName("Test name");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("Test description");
        location.setAddressInformation("Test address info");
        location = lDao.addLocation(location);

        Location fromDao = lDao.getLocationByID(location.getID());
        assertEquals(location, fromDao);

        lDao.deleteLocationByID(location.getID());

        fromDao = lDao.getLocationByID(location.getID());
        assertNull(fromDao);
    }
}

