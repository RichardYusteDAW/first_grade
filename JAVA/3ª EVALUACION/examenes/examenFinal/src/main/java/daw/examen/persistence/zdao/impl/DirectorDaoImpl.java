package daw.examen.persistence.zdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import daw.examen.domain.entinty.Director;
import daw.examen.domain.entinty.Game;
import daw.examen.persistence.zdao.DirectorDao;
import daw.examen.persistence.zdao.impl.dbconnection.RawSQL;
import daw.examen.persistence.zdao.mapper.DirectorMapper;
import daw.examen.persistence.zdao.mapper.GameMapper;

public class DirectorDaoImpl implements DirectorDao {

    @Override
    public Director findById(Integer id) {

        String sql = "SELECT * FROM `directors` WHERE id = ?";
        List<Object> params = List.of(id);

        try {
            ResultSet resultSet = RawSQL.select(sql, params);
            if (!resultSet.next()) {
                return null;
            }
            return DirectorMapper.toDirector(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException("Error to find director by id", e);
        }
    }

    @Override
    public Director findByGameCode(String code) {

        String sql = "SELECT * FROM `directors` JOIN `games` ON directors.id = games.director_id WHERE game_code = ?";
        List<Object> params = List.of(code);

        try {
            ResultSet resultSet = RawSQL.select(sql, params);
            if (!resultSet.next()) {
                return null;
            }
            return DirectorMapper.toDirector(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException("Error to find director by movie id", e);
        }
    }

    @Override
    public List<Game> findAllMoviesByDirectorId(Integer id) {

        String sql = "";
        List<Object> params = List.of(id);

        try {
            return GameMapper.toGameList(RawSQL.select(sql, params));

        } catch (SQLException e) {
            throw new RuntimeException("Error to find director by movie id", e);
        }

    }
}