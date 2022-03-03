package dao;

import com.sg.jdbctemplateexample.entity.theGame;
import com.sg.jdbctemplateexample.entity.theRound;
import GuessTheNumberRESTService;
import java.util.List;

// The interface for theRoundDao
public interface theRoundDao {

    List<theRound> getAllOfTheRoundsByGameID(int gameID);
    theRound getAllTheRoundsByID(int roundID);
    theRound addTheRounds(theRound theRound);
}