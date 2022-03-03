/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.entity.Round;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


// Round Dao DB Class
@Repository
public class RoundDaoDB {
    
    @Autowired
    JdbcTemplate jdbc;
    
    public List<Round> getAllTheRoundsByGameID(int gameID) {
        try {
            final String SELECT_ROUNDS_BY_GAMEID = "SELECT * FROM round "
                    + "WHERE game_id = ? ORDER BY guess_time";
            List<Round> rounds = jdbc.query(SELECT_ROUNDS_BY_GAMEID, new RoundMapper(), gameID);
            return rounds;
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    public Round getRoundByID(int roundID) {
        try {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE round_ID = ?";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), roundID);
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    public Round addRound(Round round) {
        final String INSERT_ROUND = "INSERT INTO round(game_id, guess, result) VALUES(?,?,?)";
        jdbc.update(INSERT_ROUND, round.getGameID(), round.getGuess(), round.getResult());
        
        int newRoundID = jdbc.queryForObject("SELECT LAST INSERT ID()", Integer.class);
        round.setRoundID(newRoundID);
        return getRoundByID(newRoundID);
    }

    
    public static final class RoundMapper implements RowMapper<Round> {
        
        @Override
        public Round mapRow(ResultSet res, int index) throws SQLException {
            Round round = new Round();
            round.setRoundID(res.getInt("round_id"));
            round.setGameID(res.getInt("game_id"));
            round.setGuess(res.getString("guess"));
            
            Timestamp timestamp = res.getTimestamp("guess_time");
            round.setGuessTime(timestamp.toLocalDateTime());
            
            round.setResult(res.getString("result"));
            return round;
        }
    }
}
