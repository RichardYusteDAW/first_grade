package daw.examen.persistence.zdao;

import java.util.List;

import daw.examen.domain.entinty.Movie;

public interface MovieDao {

    Movie findById(Integer id);

    List<Movie> findAll();

    List<Movie> findAllByTitle(String title);

    List<Movie> findAllByDirectorName(String name);

    List<Movie> findAllByActorName(String name);

    List<Movie> findAllByYear(Integer year);

    void create(Movie movie);

    void update(Movie movie);

    void delete(Integer id);

}