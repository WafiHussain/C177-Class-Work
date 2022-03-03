/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.TestApplicationConfiguration;
import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

// Round Dao Test Class
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDaoTest {
    
    @Autowired
    RoundDao roundDao;
    
    @Autowired
    GameDao gameDao;
    
    public RoundDaoTest() {
    }
    
    @Test
    public void testAddGetAll() {
        int gameID = 1;
        
        Game game = new Game();
        game.setAnswer("5678");
        game.setFinished(false);
        game = gameDao.addGame(game);
        
        Round rnd = new Round();
        rnd.setGuess("1234");
        rnd.setResult("e:0:p:0");
        rnd.setGameID(gameID);
        roundDao.addRound(rnd);
        
        Round rnd2 = new Round();
        rnd2.setGuess("5678");
        rnd2.setResult("e:4:p:0");
        rnd2.setGameID(gameID);
        roundDao.addRound(rnd2);
        
        List<Round> rnds = roundDao.getAllTheRoundsByGameID(gameID);
        
        assertEquals(2, rnds.size());
        assertNotNull(rnd = roundDao.getRoundByID(rnd.getRoundID()));
    }
    
    
    
}
