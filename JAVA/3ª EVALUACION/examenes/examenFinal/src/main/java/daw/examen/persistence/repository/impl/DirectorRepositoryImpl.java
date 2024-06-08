package daw.examen.persistence.repository.impl;

import java.util.List;

import daw.examen.domain.entinty.Director;
import daw.examen.domain.entinty.Game;
import daw.examen.persistence.repository.DirectorRepository;
import daw.examen.persistence.zdao.DirectorDao;

public class DirectorRepositoryImpl implements DirectorRepository {

    DirectorDao directorDao;

    public DirectorRepositoryImpl(DirectorDao directorDao) {
        this.directorDao = directorDao;
    }

    @Override
    public Director findById(Integer id) {
        return directorDao.findById(id);
    }

    @Override
    public List<Game> findAllMoviesByDirectorId(Integer id) {
        return directorDao.findAllMoviesByDirectorId(id);
    }
}