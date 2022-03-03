/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.service.LocationService;
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


// Location Controller Class
@Controller
public class LocationController {
    
    private final LocationService locationService;
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    
    // Violations
    Set<ConstraintViolation<Location>> violate = new HashSet<>();
    String latitudeError = null;
    String longitudeError= null;
    
    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationService.getAllTheLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }
    
    @GetMapping("/locations/addLocation")
    public String displayAddLocations(Model model) {
        model.addAttribute("errors", violate);
        model.addAttribute("latitudeError", latitudeError);
        model.addAttribute("longitudeError", longitudeError);
        
        return "/locations/addLocation";
    }
    
    // Adding a Location
    @PostMapping("/locations/addLocation")
    public String addLocation(HttpServletRequest request, Model model) {
        violate.clear();
        latitudeError = null;
        longitudeError = null;
        
        String name = request.getParameter("locationName");
        String stringLatitude = request.getParameter("latitude");
        String stringLongitude = request.getParameter("longitude");
        String description = request.getParameter("description");
        String address = request.getParameter("addressInformation");
        
        double latitude = 0;
        if(locationService.isValidLatitude(stringLatitude)) {
            latitude = Double.parseDouble(stringLatitude);
        } else {
            latitudeError = "Invalid or Empty Latitude";
        }
        
        double longitude = 0;
        if(locationService.isValidLongitude(stringLongitude)) {
            longitude = Double.parseDouble(stringLongitude);
        } else {
            longitudeError = "Invalid or Empty Longitude";
        }
        
        Location location = locationService.createLocation(name, latitude, longitude, description, address);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violate = validate.validate(location);
        if(violate.isEmpty() && locationService.isValidLatitude(stringLatitude) && locationService.isValidLongitude(stringLongitude)) {
            locationService.addLocation(location);
        }
        
        model.addAttribute("errors", violate);
        model.addAttribute("latitudeError", latitudeError);
        model.addAttribute("longitudeError", longitudeError);
        
        return "redirect:/locations/addLocation";
    }
    
    @GetMapping("/locations/deleteLocation")
    public String deleteLocation(Integer ID) {
        locationService.deleteLocationByID(ID);
        return "redirect:/locations";
    }
    
    @GetMapping("/locations/editLocation")
    public String displayEditLocation(HttpServletRequest request, Model model) {
        int ID = Integer.parseInt(request.getParameter("ID"));
        Location location = locationService.getLocationByID(ID);
        
        model.addAttribute("location", location);
        
        model.addAttribute("errors", violate);
        model.addAttribute("latitudeError", latitudeError);
        model.addAttribute("longitudeError", longitudeError);
        
        return "locations/editLocation";
    }
    
    @PostMapping("/locations/editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        violate.clear();
        latitudeError = null;
        longitudeError = null;
        
        int ID = Integer.parseInt(request.getParameter("locationID"));
        String name = request.getParameter("locationName");
        String stringLatitude = request.getParameter("latitude");
        String stringLongitude = request.getParameter("longitude");
        String description = request.getParameter("description");
        String address = request.getParameter("addressInformation");
       
        double latitude = 0;
        if(locationService.isValidLatitude(stringLatitude)) {
            latitude = Double.parseDouble(stringLatitude);
        } else {
            latitudeError = "Invalid or Empty Latitude";
        }
        
        double longitude = 0;
        if(locationService.isValidLongitude(stringLongitude)) {
            longitude = Double.parseDouble(stringLongitude);
        } else{
            longitudeError = "Invalid or Empty Longitude";
        }
        
        Location location = locationService.createLocation(name, latitude, longitude, description, address);
        location.setID(ID);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violate = validate.validate(location);
        if(violate.isEmpty() && locationService.isValidLatitude(stringLatitude) && locationService.isValidLongitude(stringLongitude)) {
            locationService.updateLocation(location);
            return "redirect:/locations";
        } else {
            model.addAttribute("location", locationService.getLocationByID(location.getID()));
            model.addAttribute("errors", violate);
            model.addAttribute("latitudeError", latitudeError);
            model.addAttribute("longitudeError", longitudeError);
            return "locations/editLocation";
        }
    }
    
    @GetMapping("locations/detailsLocation")
    public String displayDetailsLocation(HttpServletRequest request, Model model) {
        int ID = Integer.parseInt(request.getParameter("ID"));
        
        Location location = locationService.getLocationByID(ID);
        model.addAttribute("location", location);
        
        List<Hero> heroes = locationService.getHeroesForLocation(location);
        model.addAttribute("heroes", heroes);
       
        return "locations/detailsLocation";
        
    }
    
    
}
