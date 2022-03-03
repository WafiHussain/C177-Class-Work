package service;

import com.sg.guessthenumber.dao.theGameDao;
import com.sg.guessthenumber.dao.theRoundDao;
import com.sg.guessthenumber.entity.theGame;
import com.sg.guessthenumber.entity.theRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

// Service Layer
@Service
class LayeredService {

    // Game Dao
    @Autowired
    theGameDao gameDao;

    // Round Dao
    @Autowired
    theRoundDao roundDao;

    // Adding all of the games
    public List<theGame> getAllOfTheGames() {
        List<theGame> games = gameDao.getAllTheGames();
        for(theGame game : games) {
            if(!theGame.isFinished()) {
                theGame.setAnswer("****");
            }
        }
        return theGames;
    }
    // Getting all the rounds by Game ID
    public List<theRound> getAllRoundsByGameID(int gameID) {
        return roundDao.getAllRoundsByGameID(gameID);
    }

    // Make a Guess
    public theRound makeAGuess(theRound round){
        String ans = gameDao.getTheGameByID(round.getGameID()).getAnswer();
        String guess = round.getGuessing();
        String result = determineTheResult(guess, ans);
        round.setResult(result);

        if(guess.equals(ans)) {
            theGame game = getGameByID(round.getGameID());
            game.setFinished(true);
            gameDao.updateGame(game);
        }
        return roundDao.addTheRound(theRound);
    }

    // Get the game by ID
    public theGame getGameByID(int gameID){
        theGame game = gameDao.getGameByID(gameID);
        if(!game.isFinished()) {
            game.setAnswer("****");
        }
        return game;
    }

    // Adding the game from theGame class
    public theGame addtheGame(theGame game) {
        return gameDao.addTheGame(game);
    }

    // New Game
    public int newGame() {
        theGame game = new theGame();
        game.setAnswer(generateAnswer());
        game.gameDao.addGame(game);

        return game.getGameID();
    }

    // Generate the Answer
    private String generateAnswer() {
        Random rand = new Random();
        int dig1 = rand.nextInt(10);

        int dig2 = rand.nextInt(10);
        while(dig2 == dig1){
            dig2 = rand.nextInt(10);
        }
        
        int dig3 = rand.nextInt(10);
        while(dig3 == dig2 || dig3 == dig1) {
            dig3 = rand.nextInt(10);
        }

        int dig4 = rand.nextInt(10);
        while(dig4 == dig3 || dig4 == dig2 || dig4 == dig1){
            dig4 = rand.nextInt(10);
        }

        String ans = String.valueOf(dig1) + String.valueOf(dig2)
                + String.valueOf(dig3) + String.valueOf(dig4);

        return ans;
    }

    // Determine Results
    public String determineTheResult(String guess, String ans) {
        char[] guessTheChars = guess.toCharArray();
        char[] answerTheChars = ans.toCharArray();
        int exactNum = 0;
        int partialNum = 0;

        for (int i = 0; i < guessTheChars.length; i++) {
            if(ans.indexOf(guessTheChars[i]) > -1) {
                if(guessTheChars[i] == answerTheChars[i]){
                    exactNum++;
                } else {
                    partialNum++;
                }
            }
        }

        // Return the value
        return "e:" + exactNum + ":p:" + partialNum;
    }

}