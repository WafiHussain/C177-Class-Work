/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Organization;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.models.Superpower;
import com.sg.superherosightings.service.HeroService;
import com.sg.superherosightings.service.SuperpowerService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


// Controlling the Hero - HeroController
@Controller
public class HeroController {
    
    public final HeroService heroService;
    public final SuperpowerService superpowerService;
    public HeroController(HeroService heroService, SuperpowerService superpowerService) {
        this.heroService = heroService;
        this.superpowerService = superpowerService;
    }
    
    Set<ConstraintViolation<Hero>> violate = new HashSet<>();
    private boolean displayImage = false;
    
    // Display the Heroes - displayHeroes
    @GetMapping("heroes")
    public String displayHeroes(Model model){
        List<Hero> heroes = heroService.getAllHeroes();
        model.addAttribute("heroes", heroes);
        return "heroes";
    }
    
    // Display the added hero - DisplayTheAdded
    @GetMapping("/heroes/addHero")
    public String displayTheAddedHeroes(Model model) {
        List<Superpower> sPowers = superpowerService.getAllOfTheSuperpowers();
        model.addAttribute("superpowers", sPowers);
        model.addAttribute("errors", violate);
        return "/heroes/addHero";
    }
    
    // Adding the Hero - addHero as a String
    @PostMapping("/heroes/addHero")
    public String addHero(HttpServletRequest request, Model model, @RequestParam("image") MultipartFile multipartFile){
        violate.clear();
        
        String name = request.getParameter("heroName");
        boolean isHero = Boolean.parseBoolean(request.getParameter("isHero"));
        String description = request.getParameter("heroDescription");
        String[] superpowerIDs = request.getParameterValues("superpowerID");
        
        List<Superpower> sPowers = new ArrayList<>();
        if(superpowerIDs != null) {
            for(String superpowerID : superpowerIDs) {
                sPowers.add(superpowerService.getTheSuperpowerByID(Integer.parseInt(superpowerID)));
            }
        }
        
        Hero hero = heroService.createHero(name, isHero, description, sPowers);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violate = validate.validate(hero);
        if(violate.isEmpty()){
            heroService.addHero(hero);
        }
        
        if(!multipartFile.isEmpty()) {
            String fileName = hero.getID() + "";
            
            try {
                heroService.uploadFile(fileName, multipartFile);
            } catch (IOException ex) {
                System.out.println("File could not be saved");
            }
        }
        
        model.addAttribute("errors", violate);
        
        return "redirect:heroes";
    }
    
    // Destroy the Hero - deleteHero
    public String deleteHero(Integer ID){
        heroService.deleteHeroByID(ID);
        return "redirect:/heroes";
    }
    
    // Display the edited hero - displayEditHero
    @GetMapping("/heroes/editHero")
    public String displayEditHero(HttpServletRequest req, Model model) {
        int ID = Integer.parseInt(req.getParameter("ID"));
        
        Hero hero = heroService.getHeroByID(ID);
        model.addAttribute("hero", hero);
        
        List<Superpower> sPowers = superpowerService.getAllSuperpowers();
        model.addAttribute("superpowers", sPowers);
        
        model.addAttribute("errors", violate);
        return "heroes/editHero";
    }
    
    // Editing the Hero - editHero
    @PostMapping("/heroes/editHero")
    public String editHero(HttpServletRequest req, Model model, @RequestParam("image") MultipartFile multipartFile) {
        violate.clear();
        
        int ID = Integer.parseInt(req.getParameter("HeroID"));
        String name = req.getParameter("heroName");
        boolean isHero = Boolean.parseBoolean(req.getParameter("isHero"));
        String description = req.getParameter("heroDescription");
        String[] superpowerIDs = req.getParameterValues("superpowerID");
        
        List<Superpower> sPowers = new ArrayList<>();
        if(superpowerIDs != null) {
            for(String superpowerID : superpowerIDs){
                sPowers.add(superpowerService.getTheSuperpowerByID(Integer.parseInt(superpowerID)));
            }
        }
        
        List<Sighting> sightTemp = heroService.getHeroByID(ID).getSightings();
        Hero hero = heroService.createHero(name, isHero, description, sPowers);
        hero.setSightings(sightTemp);
        hero.setID(ID);
        
        Validator valid = Validation.buildDefaultValidatorFactory().getValidator();
        violate = valid.validate(hero);
        if(violate.isEmpty()) {
            String fileName = hero.getID()+"";
            
            try {
                heroService.uploadFile(fileName, multipartFile);
            } catch(IOException ex) {
                System.out.println("File could not be saved");
            }
            return "redirect:/heroes";
        } else {
            hero = heroService.getHeroByID(hero.getID());
            model.addAttribute("hero", hero);
            
            sPowers = superpowerService.getAllSuperpowers();
            model.addAttribute("superpowers", sPowers);
            model.addAttribute("errors", violate);
            return "heroes/editHero";
        }
    }
    
    // Displaying the detailed hero - displayDetailsHero
    @GetMapping("heroes/detailsHero")
    public String displayDetailsHero(HttpServletRequest req, Model model) {
        int ID = Integer.parseInt(req.getParameter("ID"));
        
        Hero hero = heroService.getHeroByID(ID);
        model.addAttribute("hero", hero);
        
        List<Organization> org = heroService.getOrganizationForHero(hero);
        model.addAttribute("organizations", org);
        
        displayImage = heroService.isImageSet(hero.getID()+"");
        model.addAttribute("displayImage", displayImage);
        
        return "heroes/detailsHero";
    }
}
