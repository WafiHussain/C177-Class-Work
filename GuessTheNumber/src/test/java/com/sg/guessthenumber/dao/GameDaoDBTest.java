/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.entity.Game;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

// Game Dao DB Test Class
public class GameDaoDBTest {
    
    @Autowired
    GameDao gameDao;
    
    public GameDaoDBTest() {   
    }
    
    @Test
    public void testGetAllGames() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setFinished(false);
        gameDao.addGame(game);
        
        Game game2 = new Game();
        game2.setAnswer("5678");
        game2.setFinished(false);
        gameDao.addGame(game2);
        
        List<Game> games = gameDao.getAllGames();
        
        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }
    
    @Test 
    public void testAddGetGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setFinished(false);
        game = gameDao.addGame(game);
        
        Game fromDao = gameDao.getGameByID(game.getGameID());
        
        assertEquals(game, fromDao);
    }
    
    @Test
    public void testUpdateGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setFinished(false);
        game = gameDao.addGame(game);
        
        Game fromDao = gameDao.getGameByID(game.getGameID());
        
        assertEquals(game, fromDao);
        
        game.setFinished(true);
        
        gameDao.updateGame(game);
        
        assertNotEquals(game, fromDao);
        
        fromDao = gameDao.getGameByID(game.getGameID());
        
        assertEquals(game, fromDao);
    }
    
}
