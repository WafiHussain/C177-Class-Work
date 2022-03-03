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
import com.sg.superherosightings.models.Superpower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SuperpowerService {
    
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
    public Superpower createSuperpower(String name, String description) {
        Superpower sPower = new Superpower();
        sPower.setName(name);
        sPower.setDescription(description);
        
        return sPower;
    }
    
    // External Dao Functions
    public List<Hero> getHeroesForSuperpower(Superpower sPower) {
        return hDao.getHeroesForSuperpower(sPower);
    }
   // Local Dao Functtions
    public List<Superpower> getAllSuperpowers() {
        return sPowerDao.getAllOfTheSuperpowers();
    }

    public Superpower getTheSuperpowerByID(int ID) {
       return sPowerDao.getTheSuperpowerByID(ID);
    }

    public List<Superpower> getAllOfTheSuperpowers() {
        return sPowerDao.getAllOfTheSuperpowers();
    }

    public void updateSuperpower(Superpower sPower) {
        sPowerDao.updateSuperpower(sPower);
    }
    
    public Superpower addSuperpower(Superpower sPower) {
        return sPowerDao.addSuperpower(sPower);
    }
    
}
