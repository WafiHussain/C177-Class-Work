package dao;

import com.sg.guessthenumber.TestApplicationConfiguration;
import com.sg.guessthenumber.entity.theGame;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDaoDBTest() {

    // The GameDao variable that's needed
    @Autowired
    GameDao gameDao;

    // Destructor
    public GameDaoDBTest() {
    }

    // Test to get all the games
    @Test
    public void testGetAllGames() {
        theGame game = new theGame();
        game.setAnswer("1234");
        game.setFinished(false);
        gameDao.addTheGame(game);

        theGame game2 = new theGame();
        game2.setAnswer("5678");
        game2.setFinished(false);
        gameDao.addTheGame(game2);

        List<theGame> games = gameDao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    // Adding the test to get the game
    @Test
    public void testAddGetGame() {
        theGame game = new theGame();
        game.setAnswer("1234");
        game.setFinished(false);
        game = gameDao.addTheGame(game);

        theGame fromDao = gameDao.getGameByID(game.getGameID());

        assertEquals(game, fromDao);
    }

    // Testing the Game Update
    @Test
    public void testUpdateGame() {
        theGame game = new theGame();
        game.setAnswer("1234");
        game.setFinished(false);
        game = gameDao.addTheGame(game);

        theGame fromDao = gameDao.getGameByID(game.getGameID());

        assertEquals(game, fromDao);

        game.setFinished(true);

        gameDao.updateGame(game);

        assertNotEquals(game, fromDao);

        fromDao = gameDao.getGameByID(game.getGameID());

        assertEquals(game, fromDao);
    }
}