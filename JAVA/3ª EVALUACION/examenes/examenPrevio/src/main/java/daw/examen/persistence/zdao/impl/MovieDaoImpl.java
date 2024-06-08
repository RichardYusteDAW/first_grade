package daw.examen.persistence.zdao.impl;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import daw.examen.common.tools.Color;
import daw.examen.domain.entinty.Actor;
import daw.examen.domain.entinty.Movie;
import daw.examen.persistence.zdao.MovieDao;
import daw.examen.persistence.zdao.impl.dbconnection.RawSQL;
import daw.examen.persistence.zdao.mapper.MovieMapper;

public class MovieDaoImpl implements MovieDao {

    @Override
    public Movie findById(Integer id) {

        String sql = "SELECT * FROM `movie` WHERE id = ?";
        List<Object> params = List.of(id);

        try {
            ResultSet resultSet = RawSQL.select(sql, params);
            if (!resultSet.next()) {
                return null;
            }
            return MovieMapper.toMovie(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "DAO: Error to find movie by id" + Color.RESET, e);
        }
    }

    @Override
    public List<Movie> findAll() {

        String sql = "SELECT * FROM `movie`";
        List<Object> params = List.of();

        try {
            return MovieMapper.toMovieList(RawSQL.select(sql, params));

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "DAO: Error to find all movies" + Color.RESET, e);
        }
    }

    @Override
    public List<Movie> findAllByTitle(String title) {

        String sql = "SELECT * FROM `movie` WHERE title LIKE ?";
        List<Object> params = List.of("%" + title + "%");

        try {
            return MovieMapper.toMovieList(RawSQL.select(sql, params));

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "DAO: Error to find movies by title" + Color.RESET, e);
        }
    }

    @Override
    public List<Movie> findAllByDirectorName(String name) {

        String sql = "SELECT * FROM `movie` JOIN `director` ON movie.directorId = director.id WHERE director.name = ?";
        List<Object> params = List.of(name);

        try {
            return MovieMapper.toMovieList(RawSQL.select(sql, params));

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "DAO: Error to find movies by director name" + Color.RESET, e);
        }
    }

    @Override
    public List<Movie> findAllByActorName(String name) {

        String sql = "SELECT * FROM `movie` JOIN `movie_actor` ON movie.id = movie_actor.movieId JOIN `actor` ON movie_actor.actorId = actor.id WHERE actor.name = ?";
        List<Object> params = List.of(name);

        try {
            return MovieMapper.toMovieList(RawSQL.select(sql, params));

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "DAO: Error to find movies by actor name" + Color.RESET, e);
        }
    }

    @Override
    public List<Movie> findAllByYear(Integer year) {

        String sql = "SELECT * FROM `movie` WHERE year = ?";
        List<Object> params = List.of(year);

        try {
            return MovieMapper.toMovieList(RawSQL.select(sql, params));

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "DAO: Error to find movies by year" + Color.RESET, e);
        }
    }

    @Override
    public void create(Movie movie) {

        String sql = "INSERT INTO `movie` (title, directorId, year) VALUES (?, ?, ?)";
        List<Object> params = List.of(movie.getTitle(), movie.getDirector().getId(), movie.getYear());

        String sql2 = "INSERT INTO `movie_actor` (movieId, actorId) VALUES (?, ?)";

        try {
            Integer movieId = ((BigInteger) RawSQL.insert(sql, params, true)).intValue(); // Insert new movie and get the id (We need to cast to Integer because the return type is Object)
            for (Actor actor : movie.getActorList()) {
                RawSQL.insert(sql2, List.of(movieId, actor.getId()), false);
            }

        } catch (Exception e) {
            throw new RuntimeException(Color.RED + "DAO: Error to create movie" + Color.RESET, e);
        }
    }

    @Override
    public void update(Movie movie) {

        String sql = "UPDATE `movie` SET title = ?, directorId = ?, year = ? WHERE id = ?";
        List<Object> params = List.of(movie.getTitle(), movie.getDirector().getId(), movie.getYear(), movie.getId());

        String sql2 = "DELETE FROM `movie_actor` WHERE movieId = ?";

        String sql3 = "INSERT INTO `movie_actor` (movieId, actorId) VALUES (?, ?)";

        try {
            RawSQL.update(sql, params);
            for (Actor actor : movie.getActorList()) {
                RawSQL.delete(sql2, List.of(movie.getId()));
                RawSQL.insert(sql3, List.of(movie.getId(), actor.getId()), false);
            }

        } catch (Exception e) {
            throw new RuntimeException(Color.RED + "DAO: Error to update movie" + Color.RESET, e);
        }
    }

    @Override
    public void delete(Integer id) {

        String sql = "DELETE FROM `movie` WHERE id = ?";
        List<Object> params = List.of(id);

        try {
            RawSQL.delete(sql, params);

        } catch (Exception e) {
            throw new RuntimeException(Color.RED + "DAO: Error to delete movie" + Color.RESET, e);
        }
    }
}