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
import com.sg.superherosightings.models.Organization;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.models.Superpower;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class HeroService {
    
    // All the Dao classes featured
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
    
    private final String IMAGE_DIRECTORY = "src/main/resources/static/images";
    private final String IMAGE_EXTENTION = ".jpg";
    private String IMAGE_EXTENSION;
    
    // Service Function
    
    // Creating A Hero
    public Hero createHero(String name, boolean isHero, String description, List<Superpower> sPowers) {
        Hero hero = new Hero();
        hero.setName(name);
        hero.setIsHero(isHero);
        hero.setDescription(description);
        hero.setSuperpowers(sPowers);
        hero.setSightings(new ArrayList<>());
        
        return hero;
    }
    
    // Uploading the File
    public void uploadFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadThePath = Paths.get(IMAGE_DIRECTORY);
        
        if(!Files.exists(uploadThePath)) {
            Files.createDirectories(uploadThePath);
        }
        
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadThePath.resolve(fileName+IMAGE_EXTENSION);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
    
    // Image is set
    public boolean isImageSet(String fileName) {
        try {
            File fi = new File(IMAGE_DIRECTORY+"/"+fileName+IMAGE_EXTENTION);
            if(fi.exists() && !fi.isDirectory()) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            return false;
        }
    }
    
    // External Dao Functions
    
    //Organization
    public List<Organization> getOrganizationsForHero(Hero hero) {
        return organizationDao.getOrganizationForHero(hero);
    }
    // Location
    public List<Location> getLocationsForHero(Hero hero){
        return locationDao.getLocationForHero(hero);
    }
    
    // Local Dao Functions
    public Hero getHeroByID(int ID){
        return heroDao.getHeroByID(ID);
    }
    public List<Hero> getAllHeroes(){
       return heroDao.getAllHeroes();
    }
    public Hero addHero(Hero hero){
        return heroDao.addHero(hero);
    }
    public void updateHero(Hero hero) {
        heroDao.updateHero(hero);
    }
    public void deleteHeroByID(int ID) {
        heroDao.deleteHeroByID(ID);
    }

    public List<Organization> getOrganizationForHero(Hero hero) {
        return organizationDao.getOrganizationForHero(hero);
    }

 }
