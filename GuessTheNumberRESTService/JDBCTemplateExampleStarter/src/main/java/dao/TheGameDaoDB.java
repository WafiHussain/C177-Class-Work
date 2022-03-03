package dao;

import com.sg.guessthenumber.entity.theGame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


// Main class for the GameDaoDB
@Repository
public class TheGameDaoDB implements theGameDao {

    // Declaring the JDBC Template
    @Autowired
    JdbcTemplate JDBC;

    // Add up all the games
    // This is where you select the games
    @Override
    public List<com.sg.guessthenumber.entity.theGame> getAllTheGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return JDBC.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public com.sg.guessthenumber.entity.theGame getIDGame(int gameID) {
        try{
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE gameID = ?";
            return JDBC.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameID);
        } catch(DataAccessException exception){
            return null;
        }
    }

    @Override
    @Transactional
    public com.sg.guessthenumber.entity.theGame addTheGame(com.sg.guessthenumber.entity.theGame theGame) {
        final String INSERT_GAME = "INSERT INTO theGame(answer) VALUES (?)";
        JDBC.update(INSERT_GAME, theGame.getAnswer());

        int newGameID = JDBC.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        theGame.setGameID(newGameID);
        return theGame;
    }

    // This is where we update the game
    @Override
    public void updateTheGame(com.sg.guessthenumber.entity.theGame theGame) {
        final String UPDATE_GAME = "UPDATE game SET finished = ? WHERE gameID = ?";
        JDBC.update(UPDATE_GAME, theGame.isFinished(), theGame.getGameID());
    }

    @RowMapper
    public static final class GameMapper implements RowMapper<com.sg.guessthenumber.entity.theGame> {

        @Override
        // This is where we map the row
        public com.sg.guessthenumber.entity.theGame mappingTheRow(ResultSet result, int index) throws SQLException {
            com.sg.guessthenumber.entity.theGame theGame = new theGame();
            theGame.setGameID(result.getInt("gameID"));
            theGame.setAnswer(result.getString("answer"));
            theGame.setFinished(result.getBoolean("finished"));
            return theGame;
        }
    }

}