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
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class SightingService {

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
   
   // Service Function
   public Sighting createSighting(Hero hero, Location location, Date date) {
       Sighting sighting = new Sighting();
       sighting.setHeroID(hero.getID());
       sighting.setLocation(location);
       sighting.setDate(date);
       
       return sighting;
   }
   
   public boolean isValidDate(String date) {
       try {
           Date.valueOf(date);
           return true;
       } catch (IllegalArgumentException e) {
           return false;
       }
   }
   
   
  public List<Sighting> getLastSightings(int no) {
      List<Sighting> sightings = siDao.getAllOfTheSightings();
      Collections.sort(sightings, new SortByDateDesc());
      if(no >= sightings.size()) {
          return sightings;
      } else {
          List<Sighting> res = new ArrayList<>();
          for(int i=0; i<sightings.size(); i++) {
              res.add(sightings.get(i));
          }
          return res;
      }
  }
  
  public HashMap<Sighting, Hero> mapHeroSighting(List<Sighting> sightings) {
      HashMap<Sighting, Hero> heroSightings = new HashMap<>();
      for(int i=0; i<sightings.size(); i++) {
          heroSightings.put(sightings.get(i), getHeroForSighting(sightings.get(i)));
      }
      return heroSightings;
  }
  
  public static final class SortByDateDesc implements Comparator<Sighting> {
      
      @Override
      public int compare(Sighting sight1, Sighting sight2) {
          return sight2.getDate().compareTo(sight1.getDate());
      }
  }
  
  // External Dao Functions
  public Hero getHeroForSighting(Sighting sighting) {
        return hDao.getHeroForSighting(sighting);
    }
  // Local Dao Functions
  public Sighting getSightingByID(int ID){
      return siDao.getSightingByID(ID);
  }
  public List<Sighting> getAllSightings() {
      return siDao.getAllOfTheSightings();
  }
  public Sighting addSighting(Sighting sighting) {
      return siDao.addTheSighting(sighting);
  }
  public void updateSighting(Sighting sighting) {
      siDao.updateSighting(sighting);
  }
  public void deleteSightByID(int ID) {
      siDao.deleteSightByID(ID);
  }
}
