package daw.examen.common.IoC;

import daw.examen.domain.service.ActorService;
import daw.examen.domain.service.impl.ActorServiceImpl;
import daw.examen.persistence.repository.ActorRepository;
import daw.examen.persistence.repository.impl.ActorRepositoryImpl;
import daw.examen.persistence.zdao.ActorDao;
import daw.examen.persistence.zdao.impl.ActorDaoImpl;

public class ActorIoC {

    private static ActorService actorService;
    private static ActorRepository actorRepository;
    private static ActorDao actorDao;

    public static ActorService getActorService() {
        if (actorService == null) {
            actorService = new ActorServiceImpl(getActorRepository());
        }
        return actorService;
    }

    public static ActorRepository getActorRepository() {
        if (actorRepository == null) {
            actorRepository = new ActorRepositoryImpl(getActorDao());
        }
        return actorRepository;
    }

    public static ActorDao getActorDao() {
        if (actorDao == null) {
            actorDao = new ActorDaoImpl();
        }
        return actorDao;
    }
}
