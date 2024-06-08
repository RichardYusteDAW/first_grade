package daw.examen.persistence.repository.impl;

import java.util.List;

import daw.examen.common.IoC.ActorIoC;
import daw.examen.common.IoC.DirectorIoC;
import daw.examen.domain.entinty.Actor;
import daw.examen.domain.entinty.Director;
import daw.examen.domain.entinty.Movie;
import daw.examen.persistence.repository.MovieRepository;
import daw.examen.persistence.zdao.ActorDao;
import daw.examen.persistence.zdao.DirectorDao;
import daw.examen.persistence.zdao.MovieDao;

public class MovieRepositoryImpl implements MovieRepository {

    MovieDao movieDao;
    DirectorDao directorDao;
    ActorDao actorDao;

    public MovieRepositoryImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
        directorDao = DirectorIoC.getDirectorDao();
        actorDao = ActorIoC.getActorDao();
    }

    @Override
    public Movie findById(Integer id) {

        // Find void Movie
        Movie movie = movieDao.findById(id);

        // Add Director
        Director director = directorDao.findByMovieId(id);
        movie.setDirector(director);

        // Add Actors
        List<Actor> actorList = actorDao.findAllByMovieId(id);
        movie.setActorList(actorList);

        return movie;
    }

    @Override
    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    @Override
    public List<Movie> findAllByTitle(String title) {

        // Find void Movie
        List<Movie> movieList = movieDao.findAllByTitle(title);

        //addActorAndDirector(movieList);

        return movieList;
    }

    @Override
    public List<Movie> findAllByDirectorName(String name) {

        // Find void Movie
        List<Movie> movieList = movieDao.findAllByDirectorName(name);

        //addActorAndDirector(movieList);

        return movieList;
    }

    @Override
    public List<Movie> findAllByActorName(String name) {

        // Find void Movie
        List<Movie> movieList = movieDao.findAllByActorName(name);

        //addActorAndDirector(movieList);

        return movieList;
    }

    @Override
    public List<Movie> findAllByYear(Integer year) {

        // Find void Movie
        List<Movie> movieList = movieDao.findAllByYear(year);

        //addActorAndDirector(movieList);

        return movieList;
    }
    /* 
    private void addActorAndDirector(List<Movie> movieList) {
    
        for (Movie movie : movieList) {
    
            // Add Director
            Director director = directorDao.findById(movie.getDirector().getId());
            movie.setDirector(director);
    
            // Add Actors
            List<Actor> actorList = actorDao.findAllByMovieId(movie.getId());
            movie.setActorList(actorList);
        }
    }
    */

    @Override
    public void create(Movie movie) {
        movieDao.create(movie);
    }

    @Override
    public void update(Movie movie) {
        movieDao.update(movie);
    }

    @Override
    public void delete(Integer id) {
        movieDao.delete(id);
    }
}