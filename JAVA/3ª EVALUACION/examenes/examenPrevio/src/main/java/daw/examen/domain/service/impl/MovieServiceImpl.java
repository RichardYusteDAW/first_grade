package daw.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import daw.examen.common.IoC.ActorIoC;
import daw.examen.common.IoC.DirectorIoC;
import daw.examen.common.exception.ExamException;
import daw.examen.domain.entinty.Actor;
import daw.examen.domain.entinty.Director;
import daw.examen.domain.entinty.Movie;
import daw.examen.domain.service.ActorService;
import daw.examen.domain.service.DirectorService;
import daw.examen.domain.service.MovieService;
import daw.examen.persistence.repository.MovieRepository;

public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private DirectorService directorService;
    private ActorService actorService;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        directorService = DirectorIoC.getDirectorService();
        actorService = ActorIoC.getActorService();
    }

    @Override
    public Movie findById(Integer id) {

        Movie movie = movieRepository.findById(id);
        if (movie == null) {
            throw new ExamException("This movie does not exist");
        }

        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> findAllByTitle(String title) {
        return movieRepository.findAllByTitle(title);
    }

    @Override
    public List<Movie> findAllByDirectorName(String name) {

        directorService.findByName(name); //Check if the director exists

        return movieRepository.findAllByDirectorName(name);
    }

    @Override
    public List<Movie> findAllByActorName(String name) {

        actorService.findByName(name); //Check if the actor exists

        return movieRepository.findAllByActorName(name);
    }

    @Override
    public List<Movie> findAllByYear(Integer year) {
        return movieRepository.findAllByYear(year);
    }

    @Override
    public void create(String title, String directorName, List<String> actorNameList, Integer year) {

        Director director = directorService.findByName(directorName); //Check if the director exists

        List<Actor> actorList = new ArrayList<>();
        for (String actorName : actorNameList) {
            actorList.add(actorService.findByName(actorName)); //Check if the actor exists
        }

        Movie movie = new Movie(title, director, actorList, year);

        movieRepository.create(movie);
    }

    @Override
    public void update(Integer id, String title, String directorName, List<String> actorNameList, Integer year) {

        Movie movie = findById(id); //Check if the movie exists

        Director director = directorService.findByName(directorName); //Check if the director exists

        List<Actor> actorList = new ArrayList<>();
        for (String actorName : actorNameList) {
            actorList.add(actorService.findByName(actorName)); //Check if the actor exists
        }

        movie.setTitle(title);
        movie.setDirector(director);
        movie.setActorList(actorList);
        movie.setYear(year);

        movieRepository.update(movie);
    }

    @Override
    public void delete(Integer id) {

        findById(id); //Check if the movie exists

        movieRepository.delete(id);
    }
}