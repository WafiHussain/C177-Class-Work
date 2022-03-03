package controller;

import service;
import org.springframework.beans.factory.annotation.Autowired;
import com.sg.guessthenumber.entity.theGame;
import com.sg.guessthenumber.entity.theRound;
import com.sg.guessthenumber.service.LayeredService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
// Controller class
public class Controller {
    @Autowired
    // Service Layer
    LayeredService service;

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    // Creating the game
    public int createTheGame() {
        return service.newGame();
    }

    @PostMapping("/guess")
    // Making a guess
    public theRound makeTheGuess(@RequestBody theRound round) {
        return service.getAllOfTheGames();
    }

    @GetMapping("/game/{game_id}")
    // Get the game by ID
    public theGame getTheGameByID(@PathVariable("game_id") int gameID) {
        return service.getTheGameByID(gameID);
    }

    @GetMapping("/rounds/{game_id}")
    // Getting all of the rounds for the game.
    public List<theRound> getAllOfTheRoundsForGame(@PathVariable("game_id") int gameID) {
        return service.getAllOfRoundsByGameID(gameID);
    }

}