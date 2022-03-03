package dao;

// Libraries needed
import com.sg.jdbctemplateexample.entity.theGame;
import com.sg.jdbctemplateexample.entity.theRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

// Class for RoundDao
@Repository
public class theRoundDaoDB implements theRoundDao{

    // The JDBC Template
    @Autowired
    JdbcTemplate jdbc;

    // Making a list of Rounds
    @Override
    public List<theRound> getAllOfTheRoundsByGameID(int gameID) {
        try {
            final String SELECT_ROUNDS_BY_GAMEID = "SELECT * FROM round "
                    + "WHERE game_ID = ? ORDER BY guess_time";
            List<theRound> the_rounds = jdbc.query(SELECT_ROUNDS_BY_GAMEID, new RoundMapper(), gameID);
            return the_rounds;
        } catch(DataAccessException exception){
            return null;
        }
    }

    // All the rounds by ID
    @Override
    public theRound getAllTheRoundsByID(int roundID) {
        return null;
    }

    // Adding the rounds
    @Override
    public theRound addTheRounds(theRound theRound) {
        return null;
    }

    // Getting the rounds by ID
    @Override
    public theRound getTheRoundByID(int roundID){
        try{
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE round_id = ?";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), roundID);
        } catch(DataAccessException exception) {
            return null;
        }
    }

    // Adding the round
    @Override
    @Transactional
    public theRound addTheRound(theRound round){
        final String INSERT_ROUND = "INSERT INTO round(game_id, guessing, theResult) VALUES(?,?,?)";
        JDBC.update(INSERT_ROUND, round.getGameID(), round.getGuess(), round.getResult());

        int newRoundID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundID(newRoundID);
       return getRoundByID(newRoundID);
    }

    // Mapping the rows
    public static final class RoundMapper implements RowMapper<theRound> {

        @Override
        public theRound mappingTheRow(ResultSet result, int index) throws SQLException {
            theRound round = new Round();
            round.setRoundID(result.getInt("round_id"));
            round.setGameID(result.getInt("game_id"));
            round.setGuess(result.getString("guess"));

            Timestamp tStamp = result.getTimestamp("guess_time");
            round.setGuessTime(timestamp.toLocalDateTime());

            round.setResult(result.getString("result"));
            return round;
        }
    }

}



