package daw.examen.persistence.zdao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daw.examen.domain.entinty.Game;

public class GameMapper {

    public static Game toGame(ResultSet resultSet) throws SQLException {

        String code = resultSet.getString("game_code");
        String title = resultSet.getString("title");
        Integer year = resultSet.getInt("release_year");
        String description = resultSet.getString("description");

        return new Game(code, title, year, description);
    }

    public static List<Game> toGameList(ResultSet resultSet) throws SQLException {

        List<Game> gameList = new ArrayList<>();

        while (resultSet.next()) {
            gameList.add(toGame(resultSet));
        }

        return gameList;
    }
}