package daw.examen.persistence.zdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import daw.examen.domain.entinty.Actor;
import daw.examen.persistence.zdao.ActorDao;
import daw.examen.persistence.zdao.impl.dbconnection.RawSQL;
import daw.examen.persistence.zdao.mapper.ActorMapper;

public class ActorDaoImpl implements ActorDao {

    @Override
    public Actor findById(Integer id) {

        String sql = "SELECT * FROM `actor` WHERE id = ?";
        List<Object> params = List.of(id);

        try {
            ResultSet resultSet = RawSQL.select(sql, params);
            if (!resultSet.next()) {
                return null;
            }
            return ActorMapper.toActor(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException("Error to find actor by id", e);
        }
    }

    @Override
    public Actor findByName(String name) {

        String sql = "SELECT * FROM `actor` WHERE name = ?";
        List<Object> params = List.of(name);

        try {
            ResultSet resultSet = RawSQL.select(sql, params);
            if (!resultSet.next()) {
                return null;
            }
            return ActorMapper.toActor(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException("Error to find actor by name", e);
        }
    }

    @Override
    public List<Actor> findAll() {

        String sql = "SELECT * FROM `actor`";
        List<Object> params = List.of();

        try {
            return ActorMapper.toActorList(RawSQL.select(sql, params));
        } catch (SQLException e) {
            throw new RuntimeException("Error to find all actors", e);
        }
    }

    @Override
    public List<Actor> findAllByMovieId(Integer id) {

        String sql = "SELECT * FROM `actor` JOIN `movie_actor` ON actor.id = movie_actor.actorId WHERE movie_actor.movieId = ?";
        List<Object> params = List.of(id);

        try {
            return ActorMapper.toActorList(RawSQL.select(sql, params));
        } catch (SQLException e) {
            throw new RuntimeException("Error to find all actors by movie id", e);
        }
    }
}