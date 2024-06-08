package daw.examen.persistence.zdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import daw.examen.domain.entinty.Director;
import daw.examen.persistence.zdao.DirectorDao;
import daw.examen.persistence.zdao.impl.dbconnection.RawSQL;
import daw.examen.persistence.zdao.mapper.DirectorMapper;

public class DirectorDaoImpl implements DirectorDao {

    @Override
    public Director findById(Integer id) {

        String sql = "SELECT * FROM `director` WHERE id = ?";
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
    public Director findByName(String name) {

        String sql = "SELECT * FROM `director` WHERE name = ?";
        List<Object> params = List.of(name);

        try {
            ResultSet resultSet = RawSQL.select(sql, params);
            if (!resultSet.next()) {
                return null;
            }
            return DirectorMapper.toDirector(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException("Error to find director by name", e);
        }
    }

    @Override
    public Director findByMovieId(Integer id) {

        String sql = "SELECT * FROM `director` JOIN `movie` ON director.id = movie.directorId WHERE movie.id = ?";
        List<Object> params = List.of(id);

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
    public List<Director> findAll() {

        String sql = "SELECT * FROM `director`";
        List<Object> params = List.of();

        try {
            return DirectorMapper.toDirectorList(RawSQL.select(sql, params));
        } catch (SQLException e) {
            throw new RuntimeException("Error to find all directors", e);
        }
    }
}