/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dao.RoundDao;
import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;


// Sertvice Layer Class 
public class ServiceLayer {

    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    public List<Game> getAllTheGames() {
        List<Game> games = gameDao.getAllTheGames();
        for (Game game : games) {
            if(!game.isFinished()) {
                game.setAnswer("****");
            }
        }
        return games;
    }
    
    public Round makeTheGuess(Round round) {
        String ans = gameDao.getGameByID(round.getGameID()).getAnswer();
        String theGuess = round.getGuess();
        String res = determineResult(theGuess, ans);
        round.setResult(res);
        
        if(theGuess.equals(ans)) {
            Game game = getGameByID(round.getGameID());
            game.setFinished(true);
            gameDao.updateGame(game);
        }
        
        return roundDao.addRound(round);
    }
    
    public Game getGameByID(int gameID) {
        Game game = gameDao.getGameByID(gameID);
        if(!game.isFinished()) {
            game.setAnswer("****");
        }
        return game;
    }
    
    public Game addGame(Game game) {
        return gameDao.addGame(game);
    }

    public int newGame() {
        Game game = new Game();
        game.setAnswer(generateAnswer());
        game = gameDao.addGame(game);
        
        return game.getGameID();
    }
    
    private String generateAnswer() {
        Random rand = new Random();
        int digit1 = rand.nextInt(10);
        
        int digit2 = rand.nextInt(10);
        while(digit2 == digit1) {
            digit2 = rand.nextInt(10);
        }
        
        int digit3 = rand.nextInt(10);
        while (digit3 == digit2 || digit3 == digit1) {
            digit3 = rand.nextInt(10);
        }
        
        int digit4 = rand.nextInt(10);
        while(digit4 == digit3 || digit4 == digit2 || digit4 == digit1) {
            digit4 = rand.nextInt(10);
        }
        
        String ans = String.valueOf(digit1) + String.valueOf(digit2)
                + String.valueOf(digit3) + String.valueOf(digit4);
        
        return ans;
    }
    
    public String determineResult(String theGuess, String ans) {
        char[] guessChars = theGuess.toCharArray();
        char[] answerChars = ans.toCharArray();
        int exact = 0;
        int partial = 0;
        
        for(int a = 0; a < guessChars.length; a++) {
            if(ans.indexOf(guessChars[a]) > -1) {
                if(guessChars[a] == answerChars[a]) {
                    exact++;
                } else {
                    partial++;
                }
            }
        }
        String res = "e:" + exact + ":p:" + partial;
        return res;
    }

    public Round makeGuess(Round round) {
       return round;
    }

    public List<Round> getAllRoundsByGameID(int gameID) {
       return roundDao.getAllRoundsByGameID(gameID);
    }
}
