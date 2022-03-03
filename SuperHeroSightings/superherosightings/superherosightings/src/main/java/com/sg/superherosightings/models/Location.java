/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.models;

import java.util.Objects;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Location Class
public class Location {
    
    private int ID;
    
    @NotBlank(message = "Name must not be empty")
    @Size(max = 50, message="Name must be less than 50 characters.")
    private String name;
    
    @Digits(integer=10, fraction=8)
    private double latitude;
    
    @Digits(integer=11, fraction=8)
    private double longitude;
    
    @Size(max=255, message="Description must be less than 255 characters.")
    private String description;
    
    @Size(max=255, message="Address Information must be less than 255 characters.")
    private String addInfo;
    
    public String toString() {
        return "Location{" + "id=" + ", name" + name + ", latitude=" + latitude + ", longitude=" 
                + longitude + ", description=" + description + ", addInfo" + addInfo + '}';
    }
    
    @Override
    public int hashCode() {
        int hashC = 7;
        hashC = 83 * hashC + Objects.hashCode(this.name);
        hashC = 83 * hashC + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hashC = 83 * hashC + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        hashC = 83 * hashC + Objects.hashCode(this.description);
        hashC = 83 * hashC + Objects.hashCode(this.addInfo);
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
        final Location other = (Location) ob;
        if(this.ID != other.ID) {
            return false;
        }
        if(Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)){
            return false;
        }
        if(Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.latitude)){
            return false;
        }
        if(!Objects.equals(this.name, other.name)) {
            return false;
        }
        if(!Objects.equals(this.addInfo, other.addInfo)){
            return false;
        }
        return true;
    }
    
    // Getters and Setters
    public int getID() {
        return ID;
    }
    
    public void setID(int getID) {
        this.ID = ID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    public double getLongitude(){
        return longitude;
    }
    
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getAddressInformation() {
        return addInfo;
    }
    
    public void setAddressInformation(String addInfo) {
        this.addInfo = addInfo;
    }         
}
