/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.models;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Organization Class Model
public class Organization {
    
    private int ID;
    private boolean isHero;
    
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message ="Name must be less than 50 characters.")
    private String name;
    
    @Size(max = 255, message="Description must be less than 255 characters.")
    private String description;
    
    @Size(max = 255, message="Address must be less than 255 characters.")
    private String address;
    
    @Size(max = 255, message="Contact must be less than 255 characters.")
    private String contact;
    
    private List<Hero> members;
    
    @Override
    public String toString() {
        return "Location{" + "id=" + ID + ", name" + name + ", isHero" + isHero + ", description=" + description + 
                ", address=" + address +", contact=" + contact + ", members=" + members + '}';
    }
    
    @Override
    public int hashCode() {
        int hashC = 7;
        hashC = 53 * hashC + Objects.hashCode(this.name);
        hashC = 53 * hashC + (this.isHero ? 1 : 0);
        hashC = 53 * hashC + Objects.hashCode(this.description);
        hashC = 53 * hashC + Objects.hashCode(this.address);
        hashC = 53 * hashC + Objects.hashCode(this.contact);
        hashC = 53 * hashC + Objects.hashCode(this.members);
        return hashC;
    }
    
    @Override
    public boolean equals(Object ob) {
        if(this == ob) {
            return true;
        }
        if(ob == null) {
            return false;
        }
        if(getClass() != ob.getClass()) {
            return false;
        }
        final Organization other = (Organization) ob;
        if(this.isHero != other.isHero) {
            return false;
        }
        if(!Objects.equals(this.name, other.name)) {
            return false;
        }
        if(!Objects.equals(this.description, other.description)){
            return false;
        }
        if(!Objects.equals(this.address, other.address)){
            return false;
        }
        if(!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if(!Objects.equals(this.members, other.members)) {
           return false;
        }
        return true;
    }
    
    // Getters and Setters
    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public List<Hero> getMembers() {
       return members;
    }
    
    public void setMembers(List<Hero> members) {
        this.members = members;
    }
}
