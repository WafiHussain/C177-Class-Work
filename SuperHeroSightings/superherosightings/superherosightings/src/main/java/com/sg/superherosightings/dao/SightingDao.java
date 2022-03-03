/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;
import java.util.List;

// Sighting Dao Interface
public interface SightingDao {
   
    Sighting getSightingByID(int ID);
    List<Sighting> getAllOfTheSightings();
    Sighting addTheSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSightByID(int ID);
    
    List<Sighting> getSightingsForTheLocation(Location location);
}
