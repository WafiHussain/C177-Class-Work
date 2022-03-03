/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.models;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Hero Class Model
public class Hero {
    
    private int ID;
    private boolean isHero;
    
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message ="Name must be less than 50 characters.")
    private String name;
    
    @Size(max = 255, message="Description must be less than 255 characters.")
    private String description;
    
    private List<Superpower> sPowers;
    private List<Sighting> sightings;
    
    @Override
    public String toString() {
        return "Hero(" + "id=" + ID + ", isHero=" + isHero + ", name=" + name + ", description" + description + ", superpowers=" + sPowers +
                ", sightings" + sightings + '}';
    }
    
    // Hash Code
    @Override
    public int hashCode() {
        int hashC = 3;
        hashC = 79 * hashC + this.ID;
        hashC = 79 * hashC + (this.isHero ? 1 : 0);
        hashC = 79 * hashC + Objects.hashCode(this.name);
        hashC = 79 * hashC + Objects.hashCode(this.description);
        hashC = 79 + hashC + Objects.hashCode(this.sPowers);
        hashC = 79 + hashC + Objects.hashCode(this.sightings);
        return hashC;
    }
    
    @Override
    public boolean equals(Object ob) {
        if(this == ob){
            return true;
        }
        if(ob == null) {
            return false;
        }
        if(getClass() != ob.getClass()) {
            return false;
        }
        final Hero other = (Hero) ob;
        if(this.ID != other.ID){
            return false;
        }
        if(this.isHero != other.isHero) {
            return false;
        }
        if(!Objects.equals(this.name, other.name)){
            return false;
        }
        if(!Objects.equals(this.description, other.description)){
            return false;
        }
        if(!Objects.equals(this.sPowers, other.sPowers)) {
            return false;
        }
        if(!Objects.equals(this.sightings, other.sightings)) {
            return false;
        }
        return true;
    } 
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getID() {
        return ID;
    }
    
    public void  setID(int ID) {
        this.ID = ID;
    }
    
    public boolean isIsHero() {
        return isHero;
    }
    
    public void setIsHero(boolean isHero) {
        this.isHero = isHero;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Superpower> getSuperpowers() {
        return sPowers;
    }
    
    public List<Sighting> getSightings() {
        return sightings;
    }
    
    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }

    public void setSuperpowers(List<Superpower> sPowers) {
       this.sPowers = sPowers;
    }
}
