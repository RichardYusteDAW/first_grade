package daw.examen.persistence.repository;

import java.util.List;

import daw.examen.domain.entinty.Game;

public interface GameRepository {

    List<Game> findAll();

    Game findByCode(String code);

    void add(Game game);
}