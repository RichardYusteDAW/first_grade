package daw.examen.domain.service.impl;

import java.util.List;

import daw.examen.common.IoC.CharacterIoC;
import daw.examen.common.IoC.DirectorIoC;
import daw.examen.common.exception.ExamException;
import daw.examen.domain.entinty.Character;
import daw.examen.domain.entinty.Game;
import daw.examen.domain.service.CharacterService;
import daw.examen.domain.service.DirectorService;
import daw.examen.domain.service.GameService;
import daw.examen.persistence.repository.GameRepository;

public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private DirectorService directorService;
    private CharacterService characterService;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        directorService = DirectorIoC.getDirectorService();
        characterService = CharacterIoC.getCharacterService();
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public List<Game> findAllByDirectorId(Integer id) {

        directorService.findById(id);// Check if director exists

        return gameRepository.findAllByDirectorId(id);
    }

    @Override
    public Game findByCode(String code) {
        Game game = gameRepository.findByCode(code);
        if (game == null) {
            throw new ExamException("This movie does not exist");
        }

        return game;
    }

    @Override
    public void add(Game game) {

        // Check if director exists
        directorService.findById(game.getDirector().getId());

        // Check if character exists
        for (Character character : game.getCharacterList()) {
            characterService.findById(character.getId());
        }

        gameRepository.add(game);
    }
}