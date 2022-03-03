/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.entity.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;


// Game Dao DB Class
public class GameDaoDB {
    
    @Autowired
    JdbcTemplate jdbc;
    
    public List<Game> getAllTheGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }
    
    public Game getGameByID(int gameID) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE game_id = ?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameID);
        } catch(DataAccessException ex){
            return null;
        }
    }
    
    @Transactional
    public Game addGame(Game game) {
        final String INSERT_GAME = "INSERT INTO game(answer) VALUES (?)";
        jdbc.update(INSERT_GAME, game.getAnswer());
        
        int newGameID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameID(newGameID);
        return game;
    }
    
    // GameMapping 
    public static final class GameMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet res, int index) throws SQLException {
            Game game = new Game();
            game.setGameID(res.getInt("game_id"));
            game.setAnswer(res.getString("answer"));
            game.setFinished(res.getBoolean("finished"));
            return game;
        }
    }
}
