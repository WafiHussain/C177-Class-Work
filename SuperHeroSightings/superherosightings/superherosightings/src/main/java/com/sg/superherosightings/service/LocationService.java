/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.HeroDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperpowerDao;
import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LocationService {

    @Autowired
    HeroDao heroDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao orgDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    // The Service Function
    public Location createLocation(String name, double latitude, double longitude, String description, String address) {
        Location location = new Location();
        location.setName(name);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setDescription(description);
        location.setAddressInformation(address);
        
        return location;
    }
    
    public boolean isValidLatitude(String latitude) {
        try {
            double value = Double.parseDouble(latitude);
            if(value<-90 || value>90){
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isValidLongitude(String longitude) {
        try {
            double value = Double.parseDouble(longitude);
            if(value<-180 || value>180) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
       
 
    // External Dao functions
    public List<Sighting> getSightingsForTheLocation(Location location) {
        return sightingDao.getSightingsForTheLocation(location);
    }
    public List<Hero> getHeroesForLocation(Location location) {
        return heroDao.getHeroesForLocation(location);
    }
    
    // Local Dao functions
    public Location getLocationByID(int ID) {
        return locationDao.getLocationByID(ID);
    }
    public List<Location> getAllTheLocations() {
        return locationDao.getAllTheLocations();
    }
    public Location addLocation(Location location) {
        return locationDao.addLocation(location);
    }
    public void updateLocation(Location location) {
        locationDao.updateLocation(location);
    }
    public void deleteLocationByID(int ID){
        locationDao.deleteLocationByID(ID);
    }    

}
