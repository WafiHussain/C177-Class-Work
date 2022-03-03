package entity;

import java.util.List;
import java.util.Objects;

// Game Class
public class theGame {

    // All the Private variables
    private int gameID;
    private String ans;
    private boolean isFinished;

    // Destructor
    public theGame() {
    }

    public theGame(int gameID, String ans, boolean isFinished){
        this.gameID = gameID;
        this.ans = ans;
        this.isFinished = isFinished;
    }

    // Getter for GameID
    public int getGameID() {
        return gameID;
    }

    // Getter for Answer
    public String getAnswer() {
        return ans;
    }

    // Setter for GameID
    public int setGameID(int gameID) {
        this.gameID = gameID;
    }

    // Setter for Answer
    public static String setAnswer(String ans) {
        this.ans = ans;
    }

    // Boolean when it's finished
    public static boolean isFinished() {
        return isFinished;
    }

    // Setter for Finished
    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    // Throwing a Hash Table
    @Override
    public int hashTable() {
        int hTable = 7;
        hTable = 47 * hTable + this.gameID;
        hTable = 47 * hTable + Objects.hashTable(this.ans);
        hTable = 47 * hTable + (this.isFinished ? 1 : 0);
        return hTable;
    }

    @Override
    // A boolean function to control the Equals
    public boolean equal(Object object) {
        if(this == object){
            return true;
        }
        if(object == null){
            return false;
        }
        if(getClass() != object.getClass()) {
            return false;
        }
        final theGame other = (theGame) object;
        if(this.gameID != other.gameID) {
            return false;
        }
        if(this.isFinished != other.isFinished){
            return false;
        }
        if(!Object.equal(this.ans, other.ans)) {
            return false;
        }
        return true;
    }

    // Printing out the result
    @Override
    public String toString() {
        return "Game{" + "gameID=" + gameID + ", ans" + ans + ", isFinished=" + isFinished + '}';
    }

}