/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.entity;

import java.time.LocalDateTime;
import java.util.Objects;


// Round Entity Class
public class Round {
    
    private int roundID;
    private int gameID;
    private LocalDateTime guessTheTime;
    private String theGuess;
    private String res;
    
    public Round() {
    }
    
    public Round(int gameID, String theGuess) {
        this.gameID = gameID;
        this.theGuess = theGuess;
    }
    
   public Round(int roundID, int gameID, LocalDateTime guessTheTime, String theGuess, String res) {
        this.roundID = roundID;
        this.gameID = gameID;
        this.guessTheTime = guessTheTime;
        this.theGuess = theGuess;
        this.res = res;
   }
   
   public int getRoundID() {
       return roundID;
   }
   
   public void setRoundID(int roundID) {
       this.roundID = roundID;
   }
   
    public int getGameID() {
        return gameID;
    }
    
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
    
    public LocalDateTime getGuessTime() {
        return guessTheTime;
    }
    
    public void setGuessTime(LocalDateTime guessTheTime) {
        this.guessTheTime = guessTheTime;
    }
    
    public String getGuess() {
        return theGuess;
    }
    
    public void setGuess(String theGuess) {
        this.theGuess = theGuess;
    }
    
    public String getResult() {
        return res;
    }
    
    public void setResult(String res) {
        this.res = res;
    }
    
    @Override
    public int hashCode() {
        int hashC = 7;
        hashC = 43 * hashC + this.roundID;
        hashC = 43 * hashC + this.gameID;
        hashC = 43 * hashC + Objects.hashCode(this.guessTheTime);
        hashC = 43 * hashC + Objects.hashCode(this.theGuess);
        hashC = 43 * hashC + Objects.hashCode(this.res);
        return hashC;
    }
    
    @Override
    public boolean equals(Object ob) {
        if(this == ob) {
            return true;
        }
        if(ob == null) {
            return false;
        }
        if(getClass() != ob.getClass()) {
            return false;
        }
        final Round other = (Round) ob;
        if(this.roundID != other.roundID) {
            return false;
        }
        if(this.gameID != other.gameID) {
            return false;
        }
        if(!Objects.equals(this.theGuess, other.theGuess)) {
            return false;
        }
        if(!Objects.equals(this.res, other.res)) {
            return false;
        }
        if(!Objects.equals(this.guessTheTime, other.guessTheTime)) {
            return false;
        }
        return true;
    }
}
