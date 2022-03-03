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
import com.sg.superherosightings.models.Organization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    // Service functions
    public Organization createOrganization(String name, boolean isHero, String description, String address, String contact, List<Hero> heroes){
        Organization org = new Organization();
        org.setName(name);
        org.setIsHero(isHero);
        org.setDescription(description);
        org.setAddress(address);
        org.setContact(contact);
        org.setMembers(heroes);
        
        return org;
    }
    
    
    // External Dao Functions
    
    // Local Dao Functions
    public Organization getOrganizationByID(int ID) {
        return organizationDao.getOrganizationByTheID(ID);
    }
    public List<Organization> getAllOfTheOrganization() {
        return organizationDao.getAllOfTheOrganization();
    }
    public Organization addingOrganization(Organization org) {
        return organizationDao.addingOrganization(org);
    }
    public void updateOrganization(Organization org) {
        organizationDao.updateOrganization(org);
    }
    public void deleteOrganizationByID(int ID) {
        organizationDao.deleteOrganizationByID(ID);
    }


}
