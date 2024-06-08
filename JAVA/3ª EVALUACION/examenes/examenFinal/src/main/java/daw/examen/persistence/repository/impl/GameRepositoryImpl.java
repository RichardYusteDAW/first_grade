package daw.examen.persistence.repository.impl;

import java.util.List;

import daw.examen.common.IoC.CharacterIoC;
import daw.examen.common.IoC.DirectorIoC;
import daw.examen.domain.entinty.Game;
import daw.examen.persistence.repository.GameRepository;
import daw.examen.persistence.zdao.CharacterDao;
import daw.examen.persistence.zdao.DirectorDao;
import daw.examen.persistence.zdao.GameDao;

public class GameRepositoryImpl implements GameRepository {

    GameDao gameDao;
    DirectorDao directorDao;
    CharacterDao characterDao;

    public GameRepositoryImpl(GameDao gameDao) {
        this.gameDao = gameDao;
        directorDao = DirectorIoC.getDirectorDao();
        characterDao = CharacterIoC.getCharacterDao();
    }

    @Override
    public List<Game> findAll() {
        return gameDao.findAll();
    }

    @Override
    public Game findByCode(String code) {

        // Find void Game
        Game game = gameDao.findByCode(code);

        // Add Director
        game.setDirector(directorDao.findByGameCode(code));

        // Add Characthers
        game.setCharacterList(characterDao.findAllByGameCode(code));

        return game;
    }

    @Override
    public void add(Game game) {
        gameDao.add(game);
    }
}