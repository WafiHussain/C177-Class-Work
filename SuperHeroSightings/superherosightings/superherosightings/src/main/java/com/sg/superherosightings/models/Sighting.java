/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.models;

import java.util.Date;
import java.util.Objects;


public class Sighting {
    
    private int ID;
    private int heroID;
    private Location location;
    private Date date;
    
    // String to return
    @Override
    public String toString() {
        return "Sighting{" + "id=" + ID + ", heroID=" + heroID + ", location" + location
                + ", date" + date + '}';
    }
    
    // The Hash Code
    @Override
    public int hashCode() {
        int hashC = 5;
        hashC = 97 * hashC + this.ID;
        hashC = 97 * hashC + this.heroID;
        hashC = 97 * hashC + Objects.hashCode(this.location);
        hashC = 97 * hashC + Objects.hashCode(this.date);
        return hashC;
    }
    
    // Equals
    @Override
    public boolean equals(Object ob) {
        if(this == ob) {
            return true;
        }
        if(ob == null){
            return false;
        }
        if(getClass() != ob.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) ob;
        if(this.ID != other.ID){
            return false;
        }
        if(this.heroID != other.heroID) {
            return false;
        }
        if(!Objects.equals(this.location, other.location)) {
            return false;
        }
        if(!Objects.equals(this.date, other.date)){
            return false;
        }
        return true;
    }
    
    // Getters and Setters
    public int getHeroID() {
        return heroID;
    }
    
    public void setHeroID(int heroID) {
        this.heroID = heroID;
    }
    
    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }  
    
}
