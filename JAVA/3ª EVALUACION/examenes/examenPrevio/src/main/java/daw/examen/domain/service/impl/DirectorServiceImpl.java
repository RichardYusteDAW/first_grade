package daw.examen.domain.service.impl;

import java.util.List;

import daw.examen.common.exception.ExamException;
import daw.examen.domain.entinty.Director;
import daw.examen.domain.service.DirectorService;
import daw.examen.persistence.repository.DirectorRepository;

public class DirectorServiceImpl implements DirectorService {

    DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public Director findById(Integer id) {

        Director director = directorRepository.findById(id);
        if (director == null) {
            throw new ExamException("This director doesn´t exist");
        }

        return director;
    }

    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director findByName(String name) {

        Director director = directorRepository.findByName(name);
        if (director == null) {
            throw new ExamException("This director doesn´t exist");
        }

        return director;
    }
}