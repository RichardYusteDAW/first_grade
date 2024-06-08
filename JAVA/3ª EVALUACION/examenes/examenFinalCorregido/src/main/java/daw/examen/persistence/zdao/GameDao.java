package daw.examen.persistence.zdao;

import java.util.List;

import daw.examen.domain.entinty.Game;

public interface GameDao {

    List<Game> findAll();

    List<Game> findAllByDirectorId(Integer id);

    Game findByCode(String code);

    void add(Game game);
}
