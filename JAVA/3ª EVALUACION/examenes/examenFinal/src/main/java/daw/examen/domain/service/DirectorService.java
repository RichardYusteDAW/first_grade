package daw.examen.domain.service;

import java.util.List;

import daw.examen.domain.entinty.Director;
import daw.examen.domain.entinty.Game;

public interface DirectorService {

    Director findById(Integer id);

    List<Game> findAllMoviesByDirectorId(Integer id);

}