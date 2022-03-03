/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Organization;
import com.sg.superherosightings.service.HeroService;
import com.sg.superherosightings.service.OrganizationService;
import java.util.ArrayList;
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


// Organization Controller Class
@Controller
public class OrganizationController {
    
    private final OrganizationService orgService;
    private final HeroService hService;
    public OrganizationController(OrganizationService oService, HeroService hService) {
        this.orgService = oService;
        this.hService = hService;
    }
    
    Set<ConstraintViolation<Organization>> violate = new HashSet<>();
    
    @GetMapping("organizations")
    public String displayOrganization(Model model) {
        List<Organization> org = orgService.getAllOfTheOrganization();
        model.addAttribute("organizations", org);
        return "organizations";
    }
    
    @GetMapping("/organizations/addOrganization")
    public String displayingAddOrganizations(Model model) {
        List<Hero> heroes = hService.getAllHeroes();
        model.addAttribute("heroes", heroes);
        
        model.addAttribute("errors", violate);
        
        return "/organizations/addOrganization";
    }
    
    @PostMapping("/organizations/addOrganization")
    public String addOrganization(HttpServletRequest req, Model model) {
        violate.clear();
        
        String name = req.getParameter("organizationName");
        boolean isHero = Boolean.parseBoolean(req.getParameter("isHero"));
        String description = req.getParameter("organizationDescription");
        String address = req.getParameter("organizationAddress");
        String contact = req.getParameter("organizationContact");
        String[] heroIDs = req.getParameterValues("heroID");
        
        List<Hero> heroes = new ArrayList<>();
        if(heroIDs != null) {
            for(String heroID : heroIDs) {
                heroes.add(hService.getHeroByID(Integer.parseInt(heroID)));
            }
        }
        
        Organization org = orgService.createOrganization(name, isHero, description, address, contact, heroes);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violate = validate.validate(org);
        if(violate.isEmpty()) {
            orgService.addingOrganization(org);
        }
        
        model.addAttribute("errors", violate);
        
        
        return "redirect:/organizations/addOrganization";   
    }
    
    @GetMapping("/organizations/deleteOrganization")
    public String deleteOrganization(Integer ID) {
        orgService.deleteOrganizationByID(ID);
        return "redirect:/organizations";
    }
    
    @GetMapping("/organizations/editOrganization")
    public String displayEditOrganization(HttpServletRequest req, Model model) {
        int ID = Integer.parseInt(req.getParameter("ID"));
        
        Organization org = orgService.getOrganizationByID(ID);
        model.addAttribute("organization", org);
        
        List<Hero> heroes = hService.getAllHeroes();
        model.addAttribute("heroes", heroes);
        
        model.addAttribute("errors", violate);
        
        return "organizations/editOrganization";
    }
    
    @PostMapping("/organizations/editOrganization")
    public String editOrganization(HttpServletRequest req, Model model) {
        violate.clear();
        
        int ID = Integer.parseInt(req.getParameter("organizationID"));
        String name = req.getParameter("organizationName");
        boolean isHero = Boolean.parseBoolean(req.getParameter("isHero"));
        String description = req.getParameter("organizationDescription");
        String address = req.getParameter("organizationAddress");
        String contact = req.getParameter("organizationContact");
        String[] heroIDs = req.getParameterValues("heroID");
        
        List<Hero> heroes = new ArrayList<>();
        if(heroIDs != null) {
            for(String heroID : heroIDs) {
                heroes.add(hService.getHeroByID(Integer.parseInt(heroID)));
            }
        }
        
        Organization org = orgService.createOrganization(name, isHero, description, address, contact, heroes);
        org.setID(ID);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violate = validate.validate(org);
        if(violate.isEmpty()) {
            orgService.updateOrganization(org);
            return "redirect:/organizations";
        } else {
            org = orgService.getOrganizationByID(org.getID());
            model.addAttribute("organization", org);
            
            heroes = hService.getAllHeroes();
            model.addAttribute("heroes", heroes);
            
            model.addAttribute("errors", violate);
            
            return "organizations/editOrganization";
        }
    }
        
    @GetMapping("organizations/detailOrganization")
    public String displayDetailsOrganization(HttpServletRequest req, Model model) {
        int ID = Integer.parseInt(req.getParameter("ID"));
        
        Organization org = orgService.getOrganizationByID(ID);
        model.addAttribute("organization", org);
        
        return "organizations/detailsOrganization";
    }
  
}
