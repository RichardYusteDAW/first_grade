package daw.examen.domain.service;

import java.util.List;

import daw.examen.domain.entinty.Game;

public interface GameService {

    List<Game> findAll();

    Game findByCode(String code);

    void add(Game game);
}