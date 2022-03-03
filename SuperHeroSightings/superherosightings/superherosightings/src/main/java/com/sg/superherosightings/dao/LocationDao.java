/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import java.util.List;


// Location Dao Interface
public interface LocationDao {
    
    Location getLocationByID(int ID);
    List<Location> getAllTheLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocationByID(int ID);
    
    List<Location> getLocationForHero(Hero hero);
}
