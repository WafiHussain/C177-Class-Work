/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Superpower;
import java.util.List;

// Superpower Dao Interface
public interface SuperpowerDao {
    
    Superpower getTheSuperpowerByID(int ID);
    List<Superpower> getAllOfTheSuperpowers();
    Superpower addSuperpower(Superpower superpower);
    void updateSuperpower(Superpower superpower);
    void deleteSuperpowerByTheID(int ID);
    
}
