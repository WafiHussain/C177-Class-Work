package dao;

import com.sg.guessthenumber.TestApplicationConfiguration;
import com.sg.guessthenumber.entity.theGame;
import com.sg.guessthenumber.entity.theRound;
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

// Spring Boot functions
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)

// Round Dao Test Class
public class RoundDaoTest {

    @Autowired
    RoundDao roundDao;

    @Autowired
    GameDao gameDao;

    public RoundDaoTest() {
    }

    @Test
    public void testAddGetAll(){
        int gameID = 1;

        theGame game = new Game();
        game.setAnswer("5678");
        game.setFinished(false);
        game = gameDao.addGame(game);

        theRound round = new theRound();
        round.setGuessing("1234");
        round.setResult("e:0:p:0");
        round.setGameID(gameID);
        roundDao.addRound(round);

        theRound round2 = new theRound();
        round2.setGuess("5678");
        round2.setResult("e:4:p:0");
        round2.setGameID(gameID);
        roundRao.addRound(round2);

        List<theRound> rounds = roundDao.getAllRoundsByGameID(gameID);

        assertEquals(2, rounds.size());
        assertEquals(round = roundRao.getRoundByID(round.getRoundID()));
    }
}