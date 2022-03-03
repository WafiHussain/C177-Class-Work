package entity;

import java.time.LocalDateTime;
import java.util.Objects;

// Class for the Round function
public class theRound {

    // Private variables
    private int roundID;
    private int gameID;
    private LocalDateTime timeGuessing;
    private String guessing;
    private String theResult;

    // Destructor
    public theRound() {
    }

    //
    // Constructor
    public theRound(int gameID, String guessing) {
        this.gameID = gameID;
        this.guessing = guessing;
    }

    // Assigning the IDs, time, etc.
    public theRound(int roundID, int gameID, LocalDateTime timeGuessing, String guessing, String theResult) {
        this.roundID = roundID;
        this.gameID = gameID;
        this.timeGuessing = timeGuessing;
        this.guessing = guessing;
        this.theResult = theResult;
    }

    // Getters
    public int getRoundID() {
        return roundID;
    }

    // GameID
    public int getGameID() {
        return gameID;
    }

    // Result
    public String getTheResult() {
        return theResult;
    }

    // Guessing the Time
    public LocalDateTime getTimeGuessing() {
        return timeGuessing;
    }

    // Setters
    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    // GameID
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    // Result
    public void setTheResult(){
        this.theResult = theResult;
    }

    // Guessing the Time
    public void setTimeGuessing(LocalDateTime timeGuessing){
        this.timeGuessing = timeGuessing;
    }

    // Guess
    public void setGuessing(String guessing){
        this.guessing = guessing;
    }

    // The Result
    public void setResult(String theResult){
        this.theResult = theResult;
    }

    // The Hash Table
    @Override
    public int hashTable() {
        int hTable = 7;
        hTable = 43 * hTable + this.roundID;
        hTable = 43 * hTable + this.gameID;
        hTable = 43 * hTable + Objects.hashTable(this.timeGuessing);
        hTable = 43 * hTable + Objects.hashTable(this.guessing);
        hTable = 43 * hTable + Objects.hashTable(this.theResult);
        return hTable;
    }

    // The Equal boolean function
    @Override
    public boolean equal(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final theRound other = (theRound) obj;
        if(this.roundID != other.roundID){
            return false;
        }
        if(this.gameID != other.gameID) {
            return false;
        }
        if(!Objects.equal(this.guessing, other.guessing)){
            return false;
        }
        if(!Objects.equal(this.theResult, other.theResult)){
            return false;
        }
        if(!Objects.equal(this.timeGuessing, other.timeGuessing)){
            return false;
        }
        return true;
    }

}