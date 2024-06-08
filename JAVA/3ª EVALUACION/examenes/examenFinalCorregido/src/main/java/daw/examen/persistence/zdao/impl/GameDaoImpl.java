package daw.examen.persistence.zdao.impl;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import daw.examen.domain.entinty.Character;

import daw.examen.common.tools.Color;
import daw.examen.domain.entinty.Game;
import daw.examen.persistence.zdao.GameDao;
import daw.examen.persistence.zdao.impl.dbconnection.RawSQL;
import daw.examen.persistence.zdao.mapper.GameMapper;

public class GameDaoImpl implements GameDao {

    @Override
    public List<Game> findAll() {

        String sql = "SELECT * FROM `games`";
        List<Object> params = List.of();

        try {
            return GameMapper.toGameList(RawSQL.select(sql, params));

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "DAO: Error to find all games" + Color.RESET, e);
        }
    }

    @Override
    public List<Game> findAllByDirectorId(Integer id) {

        String sql = "SELECT * FROM `games` WHERE director_id = ?";
        List<Object> params = List.of(id);

        try {
            return GameMapper.toGameList(RawSQL.select(sql, params));

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "DAO: Error to find all games by director id" + Color.RESET, e);
        }
    }

    @Override
    public Game findByCode(String code) {

        String sql = "SELECT * FROM `games` WHERE game_code = ?";
        List<Object> params = List.of(code);

        try {
            ResultSet resultSet = RawSQL.select(sql, params);
            if (!resultSet.next()) {
                return null;
            }
            return GameMapper.toGame(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "DAO: Error to find game by id" + Color.RESET, e);
        }
    }

    @Override
    public void add(Game game) {

        String sql = "INSERT INTO `games` (game_code, title, release_year, description, director_id) VALUES (?, ?, ?, ?, ?)";
        List<Object> params = List.of(
                game.getCode(),
                game.getTitle(),
                game.getYear(),
                game.getDescription(),
                game.getDirector().getId());

        String sql2 = "INSERT INTO `games_characters` (game_id, character_id) VALUES (?, ?)";

        try {
            Integer gameId = ((BigInteger) RawSQL.insert(sql, params, true)).intValue(); // Insert new movie and get the id (We need to cast to Integer because the return type is Object)
            for (Character character : game.getCharacterList()) {
                RawSQL.insert(sql2, List.of(gameId, character.getId()), false);
            }

        } catch (Exception e) {
            throw new RuntimeException(Color.RED + "DAO: Error to create game" + Color.RESET, e);
        }
    }

}
