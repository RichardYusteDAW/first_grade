package daw.examen.persistence.zdao;

import java.util.List;

import daw.examen.domain.entinty.Director;
import daw.examen.domain.entinty.Game;

public interface DirectorDao {

    Director findById(Integer id);

    Director findByGameCode(String code);

    List<Game> findAllMoviesByDirectorId(Integer id);
}