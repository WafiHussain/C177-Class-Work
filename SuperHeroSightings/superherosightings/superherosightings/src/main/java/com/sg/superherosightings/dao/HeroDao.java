/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.models.Superpower;
import java.util.List;

// Hero Dao Interface
public interface HeroDao {
    
    Hero getHeroByID(int ID);
    List<Hero> getAllHeroes();
    Hero addHero(Hero hero);
    void updateHero(Hero hero);
    void deleteHeroByID(int ID);
    
    List<Hero> getHeroesForSuperpower(Superpower superpower);
    Hero getHeroForSighting(Sighting sighting);
    List<Hero> getHeroesForLocation(Location location);

}
