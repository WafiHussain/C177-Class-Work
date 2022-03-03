/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.entity.Round;
import java.util.List;


// Round Dao Interface
public interface RoundDao {
    List<Round> getAllTheRoundsByGameID(int gameID);
    Round getRoundByID(int roundID);
    Round addRound(Round round);

    public List<Round> getAllRoundsByGameID(int gameID);
}
