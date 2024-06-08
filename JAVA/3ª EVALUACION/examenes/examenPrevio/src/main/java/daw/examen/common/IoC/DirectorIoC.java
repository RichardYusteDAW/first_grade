package daw.examen.common.IoC;

import daw.examen.domain.service.DirectorService;
import daw.examen.domain.service.impl.DirectorServiceImpl;
import daw.examen.persistence.repository.DirectorRepository;
import daw.examen.persistence.repository.impl.DirectorRepositoryImpl;
import daw.examen.persistence.zdao.DirectorDao;
import daw.examen.persistence.zdao.impl.DirectorDaoImpl;

public class DirectorIoC {

    private static DirectorService directorService;
    private static DirectorRepository directorRepository;
    private static DirectorDao directorDao;

    public static DirectorService getDirectorService() {
        if (directorService == null) {
            directorService = new DirectorServiceImpl(getDirectorRepository());
        }
        return directorService;
    }

    public static DirectorRepository getDirectorRepository() {
        if (directorRepository == null) {
            directorRepository = new DirectorRepositoryImpl(getDirectorDao());
        }
        return directorRepository;
    }

    public static DirectorDao getDirectorDao() {
        if (directorDao == null) {
            directorDao = new DirectorDaoImpl();
        }
        return directorDao;
    }

}
