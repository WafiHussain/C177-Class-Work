/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.models;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Superpower Class Model
public class Superpower {
    
    private int ID;
    
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message ="Name must be less than 50 characters.")
    private String name;
    
    @Size(max = 255, message="Description must be less than 255 characters.")
    private String description;
    
    // To String
    @Override
    public String toString() {
        return "Superpower{" + "id=" + ID + ", name=" + name + ", description" + description + '}';
    }
    
    // Hash Code
    @Override
    public int hashCode() {
        int hashC = 7;
        hashC = 53 * hashC + this.ID;
        hashC = 53 * hashC + Objects.hashCode(this.name);
        hashC = 53 * hashC + Objects.hashCode(this.description);
        return hashC;
    }
    
    // Equals
    @Override
    public boolean equals(Object ob) {
        if(this == ob){
            return true;
        }
        if(ob == null){
            return false;
        }
        if(getClass() != ob.getClass()){
            return false;
        }
        final Superpower other = (Superpower) ob;
        if(this.ID != other.ID) {
            return false;
        }
        if(!Objects.equals(this.name, other.name)) {
            return false;
        }
        if(!Objects.equals(this.description, other.description)){
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
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
