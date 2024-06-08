package daw.examen.persistence.repository.impl;

import java.util.List;

import daw.examen.domain.entinty.Director;
import daw.examen.persistence.repository.DirectorRepository;
import daw.examen.persistence.zdao.DirectorDao;

public class DirectorRepositoryImpl implements DirectorRepository {

    private DirectorDao directorDao;

    public DirectorRepositoryImpl(DirectorDao directorDao) {
        this.directorDao = directorDao;
    }

    @Override
    public Director findById(Integer id) {
        return directorDao.findById(id);
    }

    @Override
    public List<Director> findAll() {
        return directorDao.findAll();
    }

    @Override
    public Director findByName(String name) {
        return directorDao.findByName(name);
    }

}
