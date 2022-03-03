/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.controller;

import com.sg.guessthenumber.entity.Game;
import com.sg.guessthenumber.entity.Round;
import com.sg.guessthenumber.service.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// Controller Class
public class Controller {
    @Autowired
    ServiceLayer service;
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createTheGame() {
        return service.newGame();
    }


@PostMapping("/guess")
public Round makeAGuess(@RequestBody Round round) {
    return service.makeGuess(round);
}

@GetMapping("/game")
public List<Game> getAllTheGames() {
    return service.getAllTheGames();
}

@GetMapping("/game/{game_id}")
public List<Round> getRoundsForGame(@PathVariable("game_id") int gameID) {
    return service.getAllRoundsByGameID(gameID);
}
}

