/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.entity.Game;
import java.util.List;

// GameDao Interface
public interface GameDao {
    List<Game> getAllTheGames();
    Game getGameByID(int gameID);
    Game addGame(Game game);
    void updateGame(Game round);

    public List<Game> getAllGames();

}
