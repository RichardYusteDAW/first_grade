package daw.examen.persistence.repository;

import java.util.List;

import daw.examen.domain.entinty.Director;
import daw.examen.domain.entinty.Game;

public interface DirectorRepository {

    Director findById(Integer id);

    List<Game> findAllMoviesByDirectorId(Integer id);
}