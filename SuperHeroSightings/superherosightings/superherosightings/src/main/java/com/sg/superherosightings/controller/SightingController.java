/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.service.HeroService;
import com.sg.superherosightings.service.LocationService;
import com.sg.superherosightings.service.SightingService;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller 
public class SightingController {
    
    private final SightingService sightingService;
    private final HeroService heroService;
    private final LocationService locationService;
    public SightingController(SightingService sightingService, HeroService heroService, LocationService locationService) {
        this.sightingService = sightingService;
        this.heroService = heroService;
        this.locationService = locationService;
    }
    
    Set<ConstraintViolation<Sighting>> violate = new HashSet<>();
    String dateError = null;
    
    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingService.getAllSightings();
        HashMap<Sighting, Hero> heroSightings = sightingService.mapHeroSighting(sightings);
        model.addAttribute("sightings", sightings);
        model.addAttribute("heroSightings", heroSightings);
        return "sightings";
    }
    
    @GetMapping("/sightings/addSighting")
    public String displayAddSightings(Model model) {
        List<Hero> heroes = heroService.getAllHeroes();
        model.addAttribute("heroes", heroes);
        
        List<Location> locations = locationService.getAllTheLocations();
        model.addAttribute("locations", locations);
        
        model.addAttribute("errors", violate);
        
        model.addAttribute("dateError", dateError);
        
        return "/sightings/addSighting";    
    }
    
    @PostMapping("/sightings/addSighting")
    public String addSighting(HttpServletRequest req, Model model) {
        violate.clear();
        dateError = null;
        
        int heroID = Integer.parseInt(req.getParameter("HeroID"));
        int locationID = Integer.parseInt(req.getParameter("locationID"));
        String dateString = req.getParameter("sightingDate");
        
        Date date = null;
        if(sightingService.isValidDate(dateString)) {
            date = Date.valueOf(dateString);   
        } else {
            dateError = "Empty or Invalid date.";
        }
        
        model.addAttribute("dateError", dateError);
        
        Hero hero = heroService.getHeroByID(heroID);
        Location location = locationService.getLocationByID(locationID);
        
        Sighting sighting = sightingService.createSighting(hero, location, date);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violate = validate.validate(sighting);
        if(violate.isEmpty() && date != null) {
            sightingService.addSighting(sighting);
        }
        
        model.addAttribute("errors", violate);
        
        return "redirect:/sightings/addSighting";
    }
    
    @GetMapping("/sightings/deleteSighting")
    public String deleteSighting(Integer ID) {
        sightingService.deleteSightByID(ID);
        return "redirect:/sightings";
    }
    
    @GetMapping("/sightings/editSighting")
    public String displayEditSighting(HttpServletRequest req, Model model) {
        int ID = Integer.parseInt(req.getParameter("id"));
        
        Sighting sighting = sightingService.getSightingByID(ID);
        model.addAttribute("sighting", sighting);
                
        List<Hero> heroes = heroService.getAllHeroes();
        model.addAttribute("hero", heroes);
        
        List<Location> locations = locationService.getAllTheLocations();
        model.addAttribute("locations", locations);
        
        model.addAttribute("errors", violate);
        
        model.addAttribute("dateError", dateError);
        
        return "sightings/editSighting";
    }
    
    @PostMapping("/sightings/editSighting")
    public String editSighting(HttpServletRequest req, Model model) {
        violate.clear();
        dateError = null;
        
        int ID = Integer.parseInt(req.getParameter("sightingID"));
        int heroID = Integer.parseInt(req.getParameter("heroID"));
        int locationID = Integer.parseInt(req.getParameter("locationID"));
        String dateString = req.getParameter("sightingDate");
        
        String dateError = null;
        Date date = null;
        if(sightingService.isValidDate(dateString)){
            date = Date.valueOf(dateString);        
        } else {
            dateError = "Empty or Invalid date.";
        }
        
        model.addAttribute("dateError", dateError);
        
        Hero hero = heroService.getHeroByID(heroID);
        Location location = locationService.getLocationByID(locationID);
        
        Sighting sighting = sightingService.createSighting(hero, location, date);
        sighting.setID(ID);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violate = validate.validate(sighting);
        if(violate.isEmpty() && date != null) {
            sightingService.updateSighting(sighting);
            return "redirect:/sightings";
        } else {
            sighting = sightingService.getSightingByID(sighting.getID());
            model.addAttribute("sighting", sighting);
            
            List<Hero> heroes = heroService.getAllHeroes();
            model.addAttribute("heroes", heroes);
            
            List<Location> locations = locationService.getAllTheLocations();
            model.addAttribute("locations", locations);
            
            model.addAttribute("errors", violate);
            
            return "sightings/editSighting";
        }
    }
}
