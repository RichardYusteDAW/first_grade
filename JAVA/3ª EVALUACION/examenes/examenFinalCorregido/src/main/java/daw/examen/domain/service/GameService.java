package daw.examen.domain.service;

import java.util.List;

import daw.examen.domain.entinty.Game;

public interface GameService {

    List<Game> findAll();

    List<Game> findAllByDirectorId(Integer id);

    Game findByCode(String code);

    void add(Game game);
}