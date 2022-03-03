/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.entity;

import java.util.Objects;

// Game Entity Class
public class Game {
    
    private int gameID;
    private String ans;
    private boolean isFinished;
    
    public Game(){
    }
    
    public Game(String ans, boolean isFinished) {
        this.ans = ans;
        this.isFinished = isFinished;
    }
    
    // Constructor
    public Game(int gameID, String ans, boolean isFinished) {
        this.gameID = gameID;
        this.ans = ans;
        this.isFinished = isFinished;
    }
    
    // Getters and Setters
    public int getGameID() {
        return gameID;
    }
    
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
    
    public String getAnswer() {
        return ans;
    }
    
    public void setAnswer(String ans) {
        this.ans = ans;
    }
    
    public boolean getIsFinished() {
        return isFinished;
    }
    
    public void setIsFinished() {
        this.isFinished = isFinished;
    }
    
    @Override
    public int hashCode() {
        int hashC = 7;
        hashC = 47 * hashC + this.gameID;
        hashC = 47 * hashC + Objects.hashCode(this.ans);
        hashC = 47 * hashC + (this.isFinished ? 1 : 0);
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
        final Game other = (Game) ob;
        if(this.gameID != other.gameID) {
            return false;
        }
        if(this.isFinished != other.isFinished) {
            return false;
        }
        if(!Objects.equals(this.ans, other.ans)){
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Game{" + "gameID=" + gameID + ", ans" + ans + ", isFinished" + isFinished + '}';
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean b) {
        this.isFinished = isFinished;
    }
}
