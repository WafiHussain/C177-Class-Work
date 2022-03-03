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
import com.sg.superherosightings.models.Sighting;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// Home Service Class
@Service
public class HomeService {

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
    
    public String buildUrl(HashMap<Sighting, Hero> heroSighting){
        final String BASE_URL = "https://maps.googleapis.com/maps/api/staticmap?";
        int height = 600;
        int length = 300;
        final String SIZE = "size=" + height + "x" + length;
        
        String mapType = "roadmap";
        final String MAP_TYPE = "maptype="+mapType;
        
        String markers = "";
        if(heroSighting != null) {
            for(Sighting sighting : heroSighting.keySet()) {
                markers += "&markers="
                        
                        + "label:"
                        + heroSighting.get(sighting).getName().charAt(0)
                
                        + "%7C"
                                
                        + sighting.getLocation().getLatitude()
                        + ","
                        + sighting.getLocation().getLongitude();
            }
        }
        
        final String KEY = "key=" + getAPIKey();
        
        final String url = BASE_URL
                + SIZE 
                + "&" + MAP_TYPE
                + markers
                + "&" + KEY;
        
        return url;        
    }
    
    private String getAPIKey() {
        final String API_KEY_FILE_PATH = "../API_KEY.txt";
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(API_KEY_FILE_PATH)));
            return sc.nextLine();
        } catch(Exception e){
            System.out.println("Error:"+e);
            return "";
        }
    }
    
    public int getNumberOfSuperheroes() {
        int noOfSuperheroes = 0;
        List<Hero> heroes = heroDao.getAllHeroes();
        for(Hero hero : heroes){
            if(hero.isIsHero()){
                noOfSuperheroes++;
            }
        }
        return noOfSuperheroes;
    }
    
    public int getNoOfSupervillains() {
        int noOfSupervillains = 0;
        List<Hero> heroes = heroDao.getAllHeroes();
        for(Hero hero : heroes) {
            if(!hero.isIsHero()) {
                noOfSupervillains++;
            }
        }
        return noOfSupervillains;
    }
    
    public int getNoOfHeroOrganization() {
        int noOfHeroOrganization = 0;
        List<Organization> orgs = orgDao.getAllOfTheOrganization();
        for(Organization org : orgs) {
            if(org.isIsHero()) {
                noOfHeroOrganization++;
            }
        }
        return noOfHeroOrganization;
    }
    
    public int getNoOfVillainOrganization() {
        int noOfVillainOrganization = 0;
        List<Organization> orgs = orgDao.getAllOfTheOrganization();
        for(Organization org : orgs) {
            if(!org.isIsHero()) {
                noOfVillainOrganization++;
            }
        }
        return noOfVillainOrganization;
    }
    
    public int getNoOfLocations() {
        return locationDao.getAllTheLocations().size();
    }
    
    public int getNoOfSightings() {
        return sightingDao.getAllOfTheSightings().size();
    }
    
    public int getNoOfSuperpowers() {
        return superpowerDao.getAllOfTheSuperpowers().size();
    }

    public int getNumberOfSupervillains() {
        int noOfSupervillains = 0;
        List<Hero> heroes = heroDao.getAllHeroes();
        for(Hero hero : heroes) {
            if(!hero.isIsHero()) {
                noOfSupervillains++;
            }
        }
        return noOfSupervillains;
    }

    public int getNumberOfHeroOrganization() {
        int noOfHeroOrganization = 0;
        List<Organization> orgs = orgDao.getAllOfTheOrganization();
        if(orgs.size() > 0) {
            for(Organization org : orgs) {
                if(org.isIsHero()) {
                    noOfHeroOrganization++;
                }
            }
        }
        return noOfHeroOrganization;
    }


    public int getNumberOfSuperpowers() {
       return superpowerDao.getAllOfTheSuperpowers().size();
    }

    public int getNumberOfVillainOrganizations() {
        int noOfVillainOrganization = 0;
        List<Organization> orgs = orgDao.getAllOfTheOrganization();
        for(Organization org : orgs) {
            noOfVillainOrganization++;
        }
        return noOfVillainOrganization;
    }

    public int getNumberOfLocations() {
       return locationDao.getAllTheLocations().size();
    }

    public int getNumberOfSightings() {
        return sightingDao.getAllOfTheSightings().size();
    }

    
}
