/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Organization;
import java.util.List;

// Organization Dao Inteface
public interface OrganizationDao {
    
    Organization getOrganizationByTheID(int ID);
    List<Organization> getAllOfTheOrganization();
    Organization addingOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganizationByID(int ID);
    
    List<Organization> getOrganizationForHero(Hero hero);
}
