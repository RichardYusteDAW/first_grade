package daw.examen.common.IoC;

import daw.examen.domain.service.MovieService;
import daw.examen.domain.service.impl.MovieServiceImpl;
import daw.examen.persistence.repository.MovieRepository;
import daw.examen.persistence.repository.impl.MovieRepositoryImpl;
import daw.examen.persistence.zdao.MovieDao;
import daw.examen.persistence.zdao.impl.MovieDaoImpl;

public class MovieIoC {

    private static MovieService movieService;
    private static MovieRepository movieRepository;
    private static MovieDao movieDao;

    public static MovieService getMovieService() {
        if (movieService == null) {
            movieService = new MovieServiceImpl(getMovieRepository());
        }
        return movieService;
    }

    public static MovieRepository getMovieRepository() {
        if (movieRepository == null) {
            movieRepository = new MovieRepositoryImpl(getMovieDao());
        }
        return movieRepository;
    }

    public static MovieDao getMovieDao() {
        if (movieDao == null) {
            movieDao = new MovieDaoImpl();
        }
        return movieDao;
    }
}