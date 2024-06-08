package daw.examen.common.IoC;

import daw.examen.domain.service.GameService;
import daw.examen.domain.service.impl.GameServiceImpl;
import daw.examen.persistence.repository.GameRepository;
import daw.examen.persistence.repository.impl.GameRepositoryImpl;
import daw.examen.persistence.zdao.GameDao;
import daw.examen.persistence.zdao.impl.GameDaoImpl;

public class GameIoC {

    private static GameService gameService;
    private static GameRepository gameRepository;
    private static GameDao gameDao;

    public static GameService getGameService() {
        if (gameService == null) {
            gameService = new GameServiceImpl(getGameRepository());
        }
        return gameService;
    }

    public static GameRepository getGameRepository() {
        if (gameRepository == null) {
            gameRepository = new GameRepositoryImpl(getGameDao());
        }
        return gameRepository;
    }

    public static GameDao getGameDao() {
        if (gameDao == null) {
            gameDao = new GameDaoImpl();
        }
        return gameDao;
    }
}
