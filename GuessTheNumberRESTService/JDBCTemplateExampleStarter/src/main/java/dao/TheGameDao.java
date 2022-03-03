package dao;

import com.sg.guessthenumber.entity.theGame;
import java.util.List;

// Game Dao Interface
public interface TheGameDao {
    List<theGame> getAllTheGames();
    theGame getGameByID(int gameID);
    theGame addTheGame(theGame theGame);
    void updateGame(theGame theRound);
}