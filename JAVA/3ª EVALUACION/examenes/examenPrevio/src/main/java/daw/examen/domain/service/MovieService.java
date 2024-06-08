package daw.examen.domain.service;

import java.util.List;

import daw.examen.domain.entinty.Movie;

public interface MovieService {

    Movie findById(Integer id);

    List<Movie> findAll();

    List<Movie> findAllByTitle(String title);

    List<Movie> findAllByDirectorName(String name);

    List<Movie> findAllByActorName(String name);

    List<Movie> findAllByYear(Integer year);

    void create(String title, String directorName, List<String> actorNameList, Integer year);

    void update(Integer id, String title, String directorName, List<String> actorNameList, Integer year);

    void delete(Integer id);
}