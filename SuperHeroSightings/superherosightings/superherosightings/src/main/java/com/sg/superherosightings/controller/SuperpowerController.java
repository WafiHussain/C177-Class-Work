/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Superpower;
import com.sg.superherosightings.service.SuperpowerService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


// Superpower Controller Class
@Controller
public class SuperpowerController {

    @Autowired
    SuperpowerService superpowerService;

    Set<ConstraintViolation<Superpower>> violate = new HashSet<>();
    
    @GetMapping("superpowers")
    public String displaySuperpowers(Model model) {
        List<Superpower> sPowers = superpowerService.getAllSuperpowers();
        model.addAttribute("superpowers", sPowers);
        return "superpowers";
    }
    
    @GetMapping("/superpowers/addSuperpower")
    public String displayAddSuperpowers(Model model) {
        model.addAttribute("errors", violate);
        
        return "/superpowers/addSuperpower";
    }
    
    @PostMapping("/superpowers/addSuperpower")
    public String addSuperpower(HttpServletRequest req, Model model) {
        violate.clear();
        
        String name = req.getParameter("superpowerName");
        String description = req.getParameter("superpowerDescription");
        
        Superpower sPower = superpowerService.createSuperpower(name, description);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violate = validate.validate(sPower);
        if(violate.isEmpty()){
            superpowerService.addSuperpower(sPower);
        }
        
        model.addAttribute("errors", violate);
        
        return "redirect:/superpowers/addSuperpower";
    }
    
    @GetMapping("/superpowers/editSuperpower")
    public String displayEditSuperpower(HttpServletRequest req, Model model) {
        int ID = Integer.parseInt(req.getParameter("ID"));
        Superpower sPower = superpowerService.getTheSuperpowerByID(ID);
        
        model.addAttribute("superpower", sPower);
        
        model.addAttribute("errors", violate);
        
        return "superpowers/editSuperpower";
    }
    
    @PostMapping("/superpowers/editSuperpower")
    public String editSuperpower(HttpServletRequest req, Model model) {
        
        int ID = Integer.parseInt(req.getParameter("superpowerID"));
        String name = req.getParameter("superpowerName");
        String description = req.getParameter("superpowerDescription");
        
        Superpower sPower = superpowerService.createSuperpower(name, description);
        sPower.setID(ID);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violate = validate.validate(sPower);
        if(violate.isEmpty()){
            superpowerService.updateSuperpower(sPower);
            return "redirect:/superpowers";
        } else {
            sPower = superpowerService.getTheSuperpowerByID(sPower.getID());
            
            model.addAttribute("superpower", sPower);
            
            model.addAttribute("errors", violate);
            
            return "superpowers/editSuperpower";
        }
    }
    
    @GetMapping("superpowers/details")
    public String displayDetailsSuperpower(HttpServletRequest req, Model model) {
        int ID = Integer.parseInt(req.getParameter("ID"));
        
        Superpower sPower = superpowerService.getTheSuperpowerByID(ID);
        model.addAttribute("superpower", sPower);
        
        List<Hero> heroes = superpowerService.getHeroesForSuperpower(sPower);
        model.addAttribute("heroes", heroes);
        
        return "superpowers/detailsSuperpower";
    }
}
